package org.modelo.dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Camion<T extends Comparable<? super T>> implements Iterable<T> {

    private String placa;
    private double capacidadMaxima; // cantidad máxima de elementos
    private List<T> carga;

    public Camion(String placa, double capacidadMaxima) {
        this.placa = placa;
        this.capacidadMaxima = capacidadMaxima;
        this.carga = new ArrayList<>();
    }

    // CARGAR Y DESCARGAR  —  comportamiento LIFO (Stack)
    public boolean cargar(T elemento) {
        if (elemento == null) {
            System.out.println("❌ No se puede cargar un elemento nulo");
            return false;
        }

        if (carga.size() >= capacidadMaxima) {
            System.out.println("❌ Camión lleno (capacidad máxima: " + (int) capacidadMaxima + ")");
            return false;
        }
        carga.add(elemento);           // agrega al final → tope del stack
        System.out.println("✅ Cargado: " + elemento);
        return true;
    }

    // Saca el elemento más recientemente añadido (LIFO)
    public T descargar() {
        if (carga.isEmpty()) {
            System.out.println("❌ El camión está vacío, no hay nada que descargar");
            return null;
        }
        T elementoDescargado = carga.remove(carga.size() - 1); // extrae el tope
        System.out.println("📦 Descargado: " + elementoDescargado);
        return elementoDescargado;
    }

    // OBTENER MAYOR  —  usa Comparable<T>
    public T obtenerMayor() {
        if (carga.isEmpty()) {
            System.out.println("❌ No hay elementos en la carga");
            return null;
        }
        T mayor = carga.get(0);
        for (int i = 1; i < carga.size(); i++) {
            T actual = carga.get(i);
            if (actual.compareTo(mayor) > 0) {
                mayor = actual;
            }
        }
        return mayor;
    }

    // WILDCARDS
    // ? extends T → puede recibir un Camion de T o de cualquier subtipo de T
    public void transferirCargaDesde(Camion<? extends T> otroCamion, int cantidad) {
        System.out.println("\n🔄 TRANSFIRIENDO CARGA...");
        System.out.println("   Desde: " + otroCamion.placa + " → Hacia: " + this.placa);

        int transferidos = 0;
        for (int i = 0; i < cantidad && !otroCamion.estaVacio(); i++) {
            T elemento = otroCamion.descargar();
            if (elemento != null) {
                this.cargar(elemento);
                transferidos++;
            }
        }
        System.out.println("✅ Transferidos: " + transferidos + " elementos");
    }

    // ? super T → puede recibir un Camion de T o de cualquier supertipo de T
    public void transferirCargaHacia(Camion<? super T> otroCamion, int cantidad) {
        System.out.println("\n🔄 TRANSFIRIENDO CARGA...");
        System.out.println("   Desde: " + this.placa + " → Hacia: " + otroCamion.placa);

        int transferidos = 0;
        for (int i = 0; i < cantidad && !this.estaVacio(); i++) {
            T elemento = this.descargar();
            if (elemento != null) {
                otroCamion.cargar(elemento);
                transferidos++;
            }
        }
        System.out.println("✅ Transferidos: " + transferidos + " elementos");
    }

    // ITERATOR PERSONALIZADO  —  solo posiciones pares
    @Override
    public Iterator<T> iterator() {
        return new IteradorPosicionesPares();
    }

    private class IteradorPosicionesPares implements Iterator<T> {

        private int posicionActual = 0; // empieza en índice 0 (par)

        @Override
        public boolean hasNext() {
            return posicionActual < carga.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos en posiciones pares");
            }
            T elemento = carga.get(posicionActual);
            posicionActual += 2; // salta al siguiente índice par
            return elemento;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() no está soportado en este iterador");
        }
    }

    // MÉTODOS AUXILIARES
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
                System.out.printf("   [%d] %s%n", i, carga.get(i));
            }
        }
        System.out.println("   Total elementos: " + carga.size());
        System.out.println("═".repeat(60));
    }

    public void mostrarPosicionesPares() {
        System.out.println("\nELEMENTOS EN POSICIONES PARES:");
        System.out.println("─".repeat(60));
        if (carga.isEmpty()) {
            System.out.println("   (Sin elementos)");
            return;
        }
        int contador = 0;
        for (T elemento : this) { // usa el iterator personalizado
            System.out.printf("   Posición %d: %s%n", contador * 2, elemento);
            contador++;
        }
        System.out.println("─".repeat(60));
    }

    // GETTERS
    public String getPlaca()          { return placa; }
    public double getCapacidadMaxima(){ return capacidadMaxima; }

    /** Retorna copia defensiva para proteger la lista interna */
    public List<T> getCarga() {
        return new ArrayList<>(carga);
    }

    @Override
    public String toString() {
        return String.format("Camión[Placa: %s, Capacidad: %.0f, Elementos: %d]",
                placa, capacidadMaxima, carga.size());
    }
}