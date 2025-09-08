package com.codedestroyers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Amarillo");
        CSVParser convertidor = new CSVParser("src/main/resources/datos.csv");
        List<Hito> datos = convertidor.obtenerDatos();
        System.out.println(datos);
    }
}