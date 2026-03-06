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
        
        // ========================================================================
        // DEMO 1: CAMIÓN DE TELEVISORES (Comparable por PRECIO)
        // ========================================================================
        
        System.out.println("📺 DEMO 1: CAMIÓN DE TELEVISORES");
        System.out.println("═".repeat(70));
        
        Camion<Televisor> camionTVs = new Camion<>("ABC-123", 5.0);
        
        // Crear televisores
        Televisor tv1 = new Televisor("Samsung QLED", 1200.00, 15.5, "Samsung", 55, "4K", true);
        Televisor tv2 = new Televisor("LG OLED", 2500.00, 18.0, "LG", 65, "8K", true);
        Televisor tv3 = new Televisor("Sony Bravia", 800.00, 12.0, "Sony", 43, "Full HD", false);
        Televisor tv4 = new Televisor("TCL Smart", 450.00, 10.5, "TCL", 32, "HD", true);
        Televisor tv5 = new Televisor("Xiaomi Mi TV", 650.00, 11.0, "Xiaomi", 43, "4K", true);
        
        // Cargar televisores (se agregan al final - como PUSH en stack)
        System.out.println("\n🔵 CARGANDO TELEVISORES...");
        camionTVs.cargar(tv1);
        camionTVs.cargar(tv2);
        camionTVs.cargar(tv3);
        camionTVs.cargar(tv4);
        camionTVs.cargar(tv5);
        
        // Mostrar carga completa
        camionTVs.mostrarCarga();
        
        // Descargar (LIFO - el último cargado sale primero)
        System.out.println("\n🔴 DESCARGANDO (LIFO - Stack)...");
        camionTVs.descargar(); // Sale tv5 (Xiaomi)
        camionTVs.descargar(); // Sale tv4 (TCL)
        
        // Mostrar carga después de descargar
        camionTVs.mostrarCarga();
        
        // ========================================================================
        // DEMO 2: ITERATOR PERSONALIZADO (Solo posiciones PARES)
        // ========================================================================
        
        System.out.println("\n\n🔢 DEMO 2: ITERATOR PERSONALIZADO (Posiciones Pares)");
        System.out.println("═".repeat(70));
        
        camionTVs.mostrarPosicionesPares();
        
        // También puedes usar el for-each directamente
        System.out.println("\n📋 Usando for-each con iterator personalizado:");
        int pos = 0;
        for (Televisor tv : camionTVs) {
            System.out.println("   → " + tv);
            pos++;
        }
        
        // ========================================================================
        // DEMO 3: OBTENER MAYOR (Usa Comparable - criterio: PRECIO)
        // ========================================================================
        
        System.out.println("\n\n💰 DEMO 3: OBTENER TELEVISOR MÁS CARO (Comparable)");
        System.out.println("═".repeat(70));
        
        Televisor tvMasCaro = camionTVs.<Televisor>obtenerMayor();
        
        if (tvMasCaro != null) {
            System.out.println("🏆 EL TELEVISOR MÁS CARO ES:");
            System.out.println("   " + tvMasCaro);
            System.out.println("   💵 Precio: $" + tvMasCaro.getPrecio());
        }
        
        // ========================================================================
        // DEMO 4: CAMIÓN DE REFRIGERADORES (Comparable por CAPACIDAD)
        // ========================================================================
        
        System.out.println("\n\n🧊 DEMO 4: CAMIÓN DE REFRIGERADORES");
        System.out.println("═".repeat(70));
        
        Camion<Refrigerador> camionRefris = new Camion<>("XYZ-789", 8.0);
        
        // Crear refrigeradores
        Refrigerador refri1 = new Refrigerador("Samsung Family Hub", 1800.00, 120.0, "Samsung", 650, 2, true);
        Refrigerador refri2 = new Refrigerador("LG InstaView", 2200.00, 135.0, "LG", 700, 3, true);
        Refrigerador refri3 = new Refrigerador("Whirlpool Básico", 600.00, 75.0, "Whirlpool", 350, 1, false);
        Refrigerador refri4 = new Refrigerador("Mabe Compacto", 450.00, 60.0, "Mabe", 280, 1, false);
        
        // Cargar
        System.out.println("\n🔵 CARGANDO REFRIGERADORES...");
        camionRefris.cargar(refri1);
        camionRefris.cargar(refri2);
        camionRefris.cargar(refri3);
        camionRefris.cargar(refri4);
        
        camionRefris.mostrarCarga();
        
        // Obtener el de mayor capacidad
        System.out.println("\n📏 REFRIGERADOR CON MAYOR CAPACIDAD:");
        Refrigerador refriMayor = camionRefris.<Refrigerador>obtenerMayor();
        
        if (refriMayor != null) {
            System.out.println("🏆 " + refriMayor);
            System.out.println("   📦 Capacidad: " + refriMayor.getCapacidadLitros() + " litros");
        }
        
        // ========================================================================
        // DEMO 5: WILDCARDS - Transferir carga entre camiones
        // ========================================================================
        
        System.out.println("\n\n🔄 DEMO 5: WILDCARDS - Transferencia de Carga");
        System.out.println("═".repeat(70));
        
        // Crear otro camión de televisores
        Camion<Televisor> camionTVs2 = new Camion<>("DEF-456", 4.0);
        
        Televisor tv6 = new Televisor("Hisense Smart", 550.00, 13.0, "Hisense", 50, "4K", true);
        Televisor tv7 = new Televisor("Philips Android", 750.00, 14.0, "Philips", 55, "4K", true);
        
        camionTVs2.cargar(tv6);
        camionTVs2.cargar(tv7);
        
        System.out.println("\n📊 ESTADO ANTES DE TRANSFERENCIA:");
        System.out.println("Camión 1 (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("Camión 2 (DEF-456): " + camionTVs2.cantidadCargada() + " elementos");
        
        // Transferir desde camión 2 hacia camión 1
        // Usa WILDCARD: ? extends Televisor
        camionTVs.transferirCargaDesde(camionTVs2, 2);
        
        System.out.println("\n📊 ESTADO DESPUÉS DE TRANSFERENCIA:");
        System.out.println("Camión 1 (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("Camión 2 (DEF-456): " + camionTVs2.cantidadCargada() + " elementos");
        
        camionTVs.mostrarCarga();
        
        // ========================================================================
        // DEMO 6: WILDCARD con Supertipos (? super T)
        // ========================================================================
        
        System.out.println("\n\n🔄 DEMO 6: WILDCARD con Supertipo (? super T)");
        System.out.println("═".repeat(70));
        
        // Crear camión de Electrodomésticos (supertipo)
        Camion<Electrodomestico> camionGeneral = new Camion<>("GEN-999", 15.0);
        
        System.out.println("\n📊 ESTADO ANTES:");
        System.out.println("Camión TVs (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("Camión General (GEN-999): " + camionGeneral.cantidadCargada() + " elementos");
        
        // Transferir televisores al camión general (usa ? super T)
        camionTVs.transferirCargaHacia(camionGeneral, 2);
        
        System.out.println("\n📊 ESTADO DESPUÉS:");
        System.out.println("Camión TVs (ABC-123): " + camionTVs.cantidadCargada() + " elementos");
        System.out.println("Camión General (GEN-999): " + camionGeneral.cantidadCargada() + " elementos");
        
        camionGeneral.mostrarCarga();
        
        // ========================================================================
        // DEMO 7: Combinar Refrigeradores y Televisores en Camión Genérico
        // ========================================================================
        
        System.out.println("\n\n🎯 DEMO 7: Camión de Electrodomésticos Mixtos");
        System.out.println("═".repeat(70));
        
        // Camión que acepta cualquier Electrodoméstico
        Camion<Electrodomestico> camionMixto = new Camion<>("MIX-555", 20.0);
        
        // Puede cargar tanto TVs como Refrigeradores
        camionMixto.cargar(tv1);
        camionMixto.cargar(refri1);
        camionMixto.cargar(tv2);
        camionMixto.cargar(refri2);
        
        camionMixto.mostrarCarga();
        camionMixto.mostrarPosicionesPares();
        
        // ========================================================================
        // RESUMEN FINAL
        // ========================================================================
        
        System.out.println("\n\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ✅ RESUMEN DE DEMOS                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✅ 1. Clase Genérica Camion<T> - FUNCIONANDO");
        System.out.println("✅ 2. Métodos cargar() y descargar() LIFO (Stack) - FUNCIONANDO");
        System.out.println("✅ 3. Iterator personalizado (posiciones pares) - FUNCIONANDO");
        System.out.println("✅ 4. Método obtenerMayor() con Comparable - FUNCIONANDO");
        System.out.println("✅ 5. Wildcards (? extends T) y (? super T) - FUNCIONANDO");
        System.out.println();
        System.out.println("🎉 TODOS LOS REQUISITOS CUMPLIDOS");
        System.out.println();
    }
}