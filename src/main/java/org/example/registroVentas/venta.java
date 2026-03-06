package org.example.registroVentas;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class venta {

    private Producto producto;
    private String codproducto;
    private int cantidadProducto;
    private LocalDateTime fecha;
    private float precioTotal;
    private cliente cliente;

    public venta(Producto producto, String codproducto, int cantidadProducto, LocalDateTime fecha, float precioTotal, cliente cliente) {
        this.producto = producto;
        this.codproducto = codproducto;
        this.cantidadProducto = cantidadProducto;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public static venta registrarVenta(Producto producto, int cantidadProducto, cliente cliente) {
       int preciofinal = producto.getPrecio() * cantidadProducto;
        venta nuevaVenta = new venta(producto, producto.getCodproducto(), cantidadProducto, LocalDateTime.now(), preciofinal , cliente);
        return nuevaVenta;
    }
}
