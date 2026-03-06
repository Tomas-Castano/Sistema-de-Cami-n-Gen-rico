package org.modelo;

import org.modelo.dominio.Camion;
import org.modelo.dominio.Electrodomestico;
import org.modelo.dominio.Refrigerador;
import org.modelo.dominio.Televisor;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       🚛 SISTEMA DE GESTIÓN DE CAMIONES GENÉRICOS 🚛        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // =====================================================================
        // DEMO 1: CAMIÓN DE TELEVISORES — LIFO (Stack)
        // =====================================================================
        System.out.println("📺 DEMO 1: CAMIÓN DE TELEVISORES");
        System.out.println("═".repeat(70));

        Camion<Televisor> camionTVs = new Camion<>("ABC-123", 10);

        Televisor tv1 = new Televisor("Samsung QLED", 1200.00, 15.5, "Samsung", 55, "4K", true);
        Televisor tv2 = new Televisor("LG OLED",       2500.00, 18.0, "LG",      65, "8K", true);
        Televisor tv3 = new Televisor("Sony Bravia",    800.00, 12.0, "Sony",    43, "Full HD", false);
        Televisor tv4 = new Televisor("TCL Smart",      450.00, 10.5, "TCL",     32, "HD",      true);
        Televisor tv5 = new Televisor("Xiaomi Mi TV",   650.00, 11.0, "Xiaomi",  43, "4K",      true);

        System.out.println("\n🔵 CARGANDO TELEVISORES...");
        camionTVs.cargar(tv1);
        camionTVs.cargar(tv2);
        camionTVs.cargar(tv3);
        camionTVs.cargar(tv4);
        camionTVs.cargar(tv5);

        camionTVs.mostrarCarga();

        System.out.println("\n🔴 DESCARGANDO (LIFO — el último en entrar es el primero en salir)...");
        camionTVs.descargar(); // sale tv5
        camionTVs.descargar(); // sale tv4

        camionTVs.mostrarCarga();

        // =====================================================================
        // DEMO 2: ITERATOR PERSONALIZADO (posiciones pares)
        // =====================================================================
        System.out.println("\n\n🔢 DEMO 2: ITERATOR PERSONALIZADO (Posiciones Pares)");
        System.out.println("═".repeat(70));
        camionTVs.mostrarPosicionesPares();

        // =====================================================================
        // DEMO 3: OBTENER MAYOR (Comparable — criterio: precio)
        // =====================================================================
        System.out.println("\n\n💰 DEMO 3: TELEVISOR MÁS CARO (Comparable por precio)");
        System.out.println("═".repeat(70));

        // ✅ CORRECCIÓN: ya no hace falta el type witness .<Televisor>
        // porque T está acotado en la clase y el compilador lo infiere.
        Televisor tvMasCaro = camionTVs.obtenerMayor();

        if (tvMasCaro != null) {
            System.out.println("🏆 EL TELEVISOR MÁS CARO ES:");
            System.out.println("   " + tvMasCaro);
            System.out.println("   💵 Precio: $" + tvMasCaro.getPrecio());
        }

        // =====================================================================
        // DEMO 4: CAMIÓN DE REFRIGERADORES — Comparable por capacidad (litros)
        // =====================================================================
        System.out.println("\n\n🧊 DEMO 4: CAMIÓN DE REFRIGERADORES");
        System.out.println("═".repeat(70));

        Camion<Refrigerador> camionRefris = new Camion<>("XYZ-789", 10);

        Refrigerador refri1 = new Refrigerador("Samsung Family Hub", 1800.00, 120.0, "Samsung", 650, 2, true);
        Refrigerador refri2 = new Refrigerador("LG InstaView",       2200.00, 135.0, "LG",      700, 3, true);
        Refrigerador refri3 = new Refrigerador("Whirlpool Básico",    600.00,  75.0, "Whirlpool",350, 1, false);
        Refrigerador refri4 = new Refrigerador("Mabe Compacto",       450.00,  60.0, "Mabe",    280, 1, false);

        System.out.println("\n🔵 CARGANDO REFRIGERADORES...");
        camionRefris.cargar(refri1);
        camionRefris.cargar(refri2);
        camionRefris.cargar(refri3);
        camionRefris.cargar(refri4);

        camionRefris.mostrarCarga();

        System.out.println("\n📏 REFRIGERADOR CON MAYOR CAPACIDAD:");
        Refrigerador refriMayor = camionRefris.obtenerMayor(); // ✅ sin type witness
        if (refriMayor != null) {
            System.out.println("🏆 " + refriMayor);
            System.out.println("   📦 Capacidad: " + refriMayor.getCapacidadLitros() + " litros");
        }

        // =====================================================================
        // DEMO 5: WILDCARD ? extends T — transferencia entre camiones del mismo tipo
        // =====================================================================
        System.out.println("\n\n🔄 DEMO 5: WILDCARD (? extends T) — Transferir entre camiones de TV");
        System.out.println("═".repeat(70));

        Camion<Televisor> camionTVs2 = new Camion<>("DEF-456", 10);
        Televisor tv6 = new Televisor("Hisense Smart",   550.00, 13.0, "Hisense", 50, "4K", true);
        Televisor tv7 = new Televisor("Philips Android", 750.00, 14.0, "Philips", 55, "4K", true);
        camionTVs2.cargar(tv6);
        camionTVs2.cargar(tv7);

        System.out.println("\n📊 ANTES:");
        System.out.println("  Camión ABC-123: " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("  Camión DEF-456: " + camionTVs2.cantidadCargada() + " elementos");

        camionTVs.transferirCargaDesde(camionTVs2, 2);

        System.out.println("\n📊 DESPUÉS:");
        System.out.println("  Camión ABC-123: " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("  Camión DEF-456: " + camionTVs2.cantidadCargada() + " elementos");
        camionTVs.mostrarCarga();

        // =====================================================================
        // DEMO 6: WILDCARD ? super T — transferir a camión de supertipo
        // =====================================================================
        System.out.println("\n\n🔄 DEMO 6: WILDCARD (? super T) — Transferir a camión de Electrodomestico");
        System.out.println("═".repeat(70));

        Camion<Electrodomestico> camionGeneral = new Camion<>("GEN-999", 20);

        System.out.println("\n📊 ANTES:");
        System.out.println("  Camión TVs   (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("  Camión Gral  (GEN-999): " + camionGeneral.cantidadCargada() + " elementos");

        camionTVs.transferirCargaHacia(camionGeneral, 2);

        System.out.println("\n📊 DESPUÉS:");
        System.out.println("  Camión TVs   (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("  Camión Gral  (GEN-999): " + camionGeneral.cantidadCargada() + " elementos");
        camionGeneral.mostrarCarga();

        // =====================================================================
        // DEMO 7: Camión mixto de Electrodomesticos
        // =====================================================================
        System.out.println("\n\n🎯 DEMO 7: Camión Mixto de Electrodomésticos");
        System.out.println("═".repeat(70));

        Camion<Electrodomestico> camionMixto = new Camion<>("MIX-555", 20);
        camionMixto.cargar(tv1);
        camionMixto.cargar(refri1);
        camionMixto.cargar(tv2);
        camionMixto.cargar(refri2);

        camionMixto.mostrarCarga();
        camionMixto.mostrarPosicionesPares();

        // El mayor en un camión mixto se compara por precio (definido en Electrodomestico)
        System.out.println("\n💰 ELECTRODOMÉSTICO MÁS CARO DEL CAMIÓN MIXTO:");
        Electrodomestico masCaroMixto = camionMixto.obtenerMayor();
        if (masCaroMixto != null) {
            System.out.println("🏆 " + masCaroMixto);
        }
    }
}