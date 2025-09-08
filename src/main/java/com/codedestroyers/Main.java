package com.codedestroyers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CSVParser convertidor = new CSVParser("src/main/resources/datos.csv");
        List<Hito> datos = convertidor.obtenerDatos();
        System.out.println(datos);
    }
}