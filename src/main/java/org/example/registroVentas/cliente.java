package org.example.registroVentas;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class cliente {

    private String nombre;
    private String ID;
    private int edad;


    public cliente(String nombre, String ID, int edad) {
        this.nombre = nombre;
        this.ID = ID;
        this.edad = edad;
    }


}
