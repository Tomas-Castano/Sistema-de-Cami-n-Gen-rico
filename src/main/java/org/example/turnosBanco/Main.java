package org.example.turnosBanco;

public class Main {

    public static void main(String[] args) {

        ColaConPilas sistema = new ColaConPilas();

        sistema.registrarCliente("Cliente 1");
        sistema.registrarCliente("Cliente 2");
        sistema.registrarCliente("Cliente 3");

        System.out.println(sistema.verSiguienteCliente());
        System.out.println(sistema.atenderCliente());

        System.out.println("Clientes en espera: " + sistema.cantidadClientes());

        System.out.println(sistema.atenderCliente());
        System.out.println(sistema.atenderCliente());

        System.out.println("¿Fila vacía? " + sistema.estaVacia());
        System.out.println(sistema.atenderCliente());
    }
}