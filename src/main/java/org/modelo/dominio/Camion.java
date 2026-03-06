package org.modelo.dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Camion<T> implements Iterable<T> {
    private String placa;
    private double capacidadMaxima;
    private List<T> carga;
    
    public Camion(String placa, double capacidadMaxima) {
        this.placa = placa;
        this.capacidadMaxima = capacidadMaxima;
        this.carga = new ArrayList<>();
    }
    
    // MÉTODOS CARGAR Y DESCARGAR (STACK - LIFO)
    public boolean cargar(T elemento) {
        if (elemento == null) {
            System.out.println("❌ No se puede cargar un elemento nulo");
            return false;
        }
        
        carga.add(elemento); // Agrega al final (top del stack)
        System.out.println("✅ Cargado: " + elemento);
        return true;
    }
    
    public T descargar() {
        if (carga.isEmpty()) {
            System.out.println("❌ El camión está vacío, no hay nada que descargar");
            return null;
        }
        
        // Remover el último elemento (top del stack)
        T elementoDescargado = carga.remove(carga.size() - 1);
        System.out.println("📦 Descargado: " + elementoDescargado);
        return elementoDescargado;
    }

    public boolean estaVacio() {
        return carga.isEmpty();
    }
    
    public int cantidadCargada() {
        return carga.size();
    }
    
    public void mostrarCarga() {
        System.out.println("\n🚛 CARGA DEL CAMIÓN [" + placa + "]");
        System.out.println("═".repeat(60));
        
        if (carga.isEmpty()) {
            System.out.println("   (Vacío)");
        } else {
            for (int i = 0; i < carga.size(); i++) {
                System.out.printf("   [%d] %s\n", i, carga.get(i));
            }
        }
        System.out.println("   Total elementos: " + carga.size());
        System.out.println("═".repeat(60));
    }
    

    // MÉTODO OBTENER MAYOR (COMPARABLE)
    public <E extends Comparable<E>> E obtenerMayor() {
        if (carga.isEmpty()) {
            System.out.println("❌ No hay elementos en la carga");
            return null;
        }
        
        // Verificar que los elementos sean Comparable
        try {
            @SuppressWarnings("unchecked")
            E mayor = (E) carga.get(0);
            
            for (int i = 1; i < carga.size(); i++) {
                @SuppressWarnings("unchecked")
                E actual = (E) carga.get(i);
                
                if (actual.compareTo(mayor) > 0) {
                    mayor = actual;
                }
            }
            
            return mayor;
            
        } catch (ClassCastException e) {
            System.out.println("❌ Los elementos no implementan Comparable");
            return null;
        }
    }
    
    // MÉTODO CON WILDCARD (? extends)
    public void transferirCargaDesde(Camion<? extends T> otroCamion, int cantidad) {
        System.out.println("\n🔄 TRANSFIRIENDO CARGA...");
        System.out.println("   Desde: " + otroCamion.placa + " → Hacia: " + this.placa);
        
        int transferidos = 0;
        
        for (int i = 0; i < cantidad && !otroCamion.estaVacio(); i++) {
            T elemento = otroCamion.descargar(); // Descarga del otro camión
            if (elemento != null) {
                this.cargar(elemento); // Carga en este camión
                transferidos++;
            }
        }
        
        System.out.println("✅ Transferidos: " + transferidos + " elementos");
    }
    
    public void transferirCargaHacia(Camion<? super T> otroCamion, int cantidad) {
        System.out.println("\n🔄 TRANSFIRIENDO CARGA...");
        System.out.println("   Desde: " + this.placa + " → Hacia: " + otroCamion.placa);
        
        int transferidos = 0;
        
        for (int i = 0; i < cantidad && !this.estaVacio(); i++) {
            T elemento = this.descargar(); // Descarga de este camión
            if (elemento != null) {
                otroCamion.cargar(elemento); // Carga en el otro camión
                transferidos++;
            }
        }
        
        System.out.println("✅ Transferidos: " + transferidos + " elementos");
    }
    
    // ITERATOR PERSONALIZADO (SOLO POSICIONES PARES)
    @Override
    public Iterator<T> iterator() {
        return new IteradorPosicionesPares();
    }
    
    private class IteradorPosicionesPares implements Iterator<T> {
        
        private int posicionActual = 0; // Comienza en 0
        
        @Override
        public boolean hasNext() {
            // Hay siguiente si la posición actual está dentro del rango
            return posicionActual < carga.size();
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos en posiciones pares");
            }
            
            T elemento = carga.get(posicionActual);
            posicionActual += 2; // Saltar a la siguiente posición PAR
            return elemento;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove no está soportado en este iterator");
        }
    }
    

    public void mostrarPosicionesPares() {
        System.out.println("\nELEMENTOS EN POSICIONES PARES:");
        System.out.println("─".repeat(60));
        
        if (carga.isEmpty()) {
            System.out.println("   (Sin elementos)");
            return;
        }
        
        int contador = 0;
        for (T elemento : this) { // Usa el iterator personalizado
            System.out.printf("   Posición %d: %s\n", contador * 2, elemento);
            contador++;
        }
        
        System.out.println("─".repeat(60));
    }
    
    // GETTERS Y SETTERS
    public String getPlaca() {
        return placa;
    }
    
    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public List<T> getCarga() {
        return new ArrayList<>(carga); // Retorna copia para proteger la lista interna
    }
    
    @Override
    public String toString() {
        return String.format("Camión[Placa: %s, Capacidad: %.2fT, Elementos cargados: %d]",
                             placa, capacidadMaxima, carga.size());
    }
}