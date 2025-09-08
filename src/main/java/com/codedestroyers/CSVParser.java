package com.codedestroyers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    String ruta_csv;

    CSVParser(String ruta_csv) {
        this.ruta_csv = ruta_csv;
    }

    private Hito procesarLinea(String linea) {
        List<String> valores = new ArrayList<String>();

        boolean enComillas = false;
        StringBuilder valorActual = new StringBuilder();

        for (char c : linea.toCharArray()) {
            if (c == '"') {
                enComillas = !enComillas;
            } else if (c == ',' && !enComillas) {
                valores.add(valorActual.toString());
                valorActual = new StringBuilder();
            } else {
                valorActual.append(c);
            }
        }
        valores.add(valorActual.toString());

        Hito hito = new Hito(valores.get(0), valores.get(1), valores.get(2), valores.get(3));
        return hito;
    }

    public List<Hito> obtenerDatos() {
        List<Hito> hitos = new ArrayList<Hito>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta_csv))) {
            String linea = "";
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                hitos.add(procesarLinea(linea));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("El archivo " + ruta_csv + " no se encontro");
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al leer el archivo " + ruta_csv);
        }
        return hitos;
    }
}