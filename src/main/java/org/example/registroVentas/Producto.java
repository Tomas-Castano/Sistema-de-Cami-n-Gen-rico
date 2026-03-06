package org.example.registroVentas;

import lombok.Data;

@Data
public class Producto {

    private String codproducto;
    private int precio;
    private int cantidad;

    public Producto(String codproducto, int precio, int cantidad) {
        this.codproducto = codproducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static Producto crearProducto(String codproducto, int precio, int cantidad){
        Producto producto = new Producto(codproducto,precio,cantidad);
        return producto;
    }
}
