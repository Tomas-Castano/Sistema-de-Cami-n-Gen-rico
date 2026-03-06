package org.example;

import java.util.List;


public class EncontrarMaximo {

    /**
     * Encuentra el elemento máximo usando divide y vencerás
     * @param arr arreglo de enteros
     * @param inicio índice inicial
     * @param fin índice final
     * @return el elemento máximo en el rango [inicio, fin]
     */
    public static int encontrarMaximo(int[] arr, int inicio, int fin) {
        // CASO BASE 1: si solo hay un elemento
        if (inicio == fin) {
            return arr[inicio];
        }

        // CASO BASE 2: si hay dos elementos
        if (fin == inicio + 1) {
            return Math.max(arr[inicio], arr[fin]);
        }

        // DIVIDIR: encontrar el punto medio
        int medio = inicio + (fin - inicio) / 2;

        // CONQUISTAR: encontrar el máximo en cada mitad
        int maximoIzquierda = encontrarMaximo(arr, inicio, medio);
        int maximoDerecha = encontrarMaximo(arr, medio + 1, fin);

        // COMBINAR: retornar el mayor de los dos
        return Math.max(maximoIzquierda, maximoDerecha);
    }

    public static int encontrarMaximo(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede estar vacío");
        }
        return encontrarMaximo(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 41, 52, 26, 38, 57};

        System.out.println("Arreglo:");
        imprimirArreglo(arr);

        int maximo = encontrarMaximo(arr);

        System.out.println("\nEl número máximo es: " + maximo);

        System.out.println("\n--- Ejemplo con arreglo pequeño ---");
        int[] arrPequeño = {5, 2, 8, 1, 9, 3};
        System.out.println("Arreglo:");
        imprimirArreglo(arrPequeño);
        System.out.println("Máximo: " + encontrarMaximo(arrPequeño));
    }

    private static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }



}
