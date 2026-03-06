package org.modelo.dominio;

public class Refrigerador extends Electrodomestico implements Comparable<Refrigerador> {
    private int capacidadLitros;
    private int numeroPuertas;
    private boolean tieneDispensador;
    
    public Refrigerador(String nombre, double precio, double peso, String marca,
                       int capacidadLitros, int numeroPuertas, boolean tieneDispensador) {
        super(nombre, precio, peso, marca);
        this.capacidadLitros = capacidadLitros;
        this.numeroPuertas = numeroPuertas;
        this.tieneDispensador = tieneDispensador;
    }
    
    // Getters específicos
    public int getCapacidadLitros() {
        return capacidadLitros;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }
    
    public boolean isTieneDispensador() {
        return tieneDispensador;
    }
    
    //Comparable por capacidad en litros
    @Override
    public int compareTo(Refrigerador otro) {
        return Integer.compare(this.capacidadLitros, otro.capacidadLitros);
    }
    
    @Override
    public String toString() {
        return String.format("Refrigerador %s [%dL, %d puertas, %s, Marca: %s, Precio: $%.2f, Peso: %.2fkg]",
                             nombre, capacidadLitros, numeroPuertas,
                             tieneDispensador ? "Con dispensador" : "Sin dispensador",
                             marca, precio, peso);
    }
}