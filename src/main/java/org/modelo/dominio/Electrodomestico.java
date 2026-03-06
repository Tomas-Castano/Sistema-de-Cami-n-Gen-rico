package org.modelo.dominio;

public abstract class Electrodomestico implements Comparable<Electrodomestico> {
    protected String nombre;
    protected double precio;
    protected double peso;
    protected String marca;

    public Electrodomestico(String nombre, double precio, double peso, String marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.marca = marca;
    }

    @Override
    public int compareTo(Electrodomestico otro) {
        return Double.compare(this.precio, otro.precio);
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public double getPeso()   { return peso; }
    public String getMarca()  { return marca; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setPeso(double peso)     { this.peso = peso; }
    public void setMarca(String marca)   { this.marca = marca; }

    @Override
    public String toString() {
        return String.format("%s [Marca: %s, Precio: $%.2f, Peso: %.2fkg]",
                nombre, marca, precio, peso);
    }
}