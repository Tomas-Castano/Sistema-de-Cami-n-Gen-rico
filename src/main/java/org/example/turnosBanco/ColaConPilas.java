package org.example.turnosBanco;

import java.util.Stack;

public class ColaConPilas {

    private Stack<String> pilaEntrada;
    private Stack<String> pilaSalida;

    public ColaConPilas() {
        pilaEntrada = new Stack<>();
        pilaSalida = new Stack<>();
    }

    // 1. Registrar un nuevo cliente
    public void registrarCliente(String cliente) {
        pilaEntrada.push(cliente);
        System.out.println("Cliente registrado: " + cliente);
    }

    // Mueve elementos si es necesario
    private void moverEntradaASalida() {
        if (pilaSalida.isEmpty()) {
            while (!pilaEntrada.isEmpty()) {
                pilaSalida.push(pilaEntrada.pop());
            }
        }
    }

    // 2. Atender al siguiente cliente
    public String atenderCliente() {
        if (estaVacia()) {
            return "No hay clientes en la fila.";
        }
        moverEntradaASalida();
        return "Atendiendo a: " + pilaSalida.pop();
    }

    // 3. Mostrar el próximo cliente sin retirarlo
    public String verSiguienteCliente() {
        if (estaVacia()) {
            return "No hay clientes en la fila.";
        }
        moverEntradaASalida();
        return "Próximo cliente: " + pilaSalida.peek();
    }

    // 4. Mostrar cantidad de clientes en espera
    public int cantidadClientes() {
        return pilaEntrada.size() + pilaSalida.size();
    }

    // 5. Verificar si la fila está vacía
    public boolean estaVacia() {
        return pilaEntrada.isEmpty() && pilaSalida.isEmpty();
    }
}