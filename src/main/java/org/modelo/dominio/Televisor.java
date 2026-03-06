package org.modelo.dominio;

public class Televisor extends Electrodomestico implements Comparable<Televisor> {
    private int pulgadas;
    private String resolucion;
    private boolean esSmart;
    
    public Televisor(String nombre, double precio, double peso, String marca, 
                     int pulgadas, String resolucion, boolean esSmart) {
        super(nombre, precio, peso, marca);
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.esSmart = esSmart;
    }
    
    // Getters específicos
    public int getPulgadas() {
        return pulgadas;
    }
    
    public String getResolucion() {
        return resolucion;
    }
    
    public boolean isEsSmart() {
        return esSmart;
    }
    
    //Comparable por precio
    @Override
    public int compareTo(Televisor otro) {
        // Comparar por precio descendente (el más caro es "mayor")
        return Double.compare(this.precio, otro.precio);
    }
    
    @Override
    public String toString() {
        return String.format("TV %s [%d\", %s, %s, Marca: %s, Precio: $%.2f, Peso: %.2fkg]",
                             nombre, pulgadas, resolucion, 
                             esSmart ? "Smart" : "No Smart", 
                             marca, precio, peso);
    }
}