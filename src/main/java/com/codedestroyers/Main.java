package com.codedestroyers;

import java.util.List;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        CSVParser convertidor = new CSVParser("src/main/resources/datos.csv");
        List<Hito> datos = convertidor.obtenerDatos();

        SwingUtilities.invokeLater(() -> {
            TimelineGUI timeline = new TimelineGUI(datos);
            timeline.setVisible(true);
        });
    }
}
