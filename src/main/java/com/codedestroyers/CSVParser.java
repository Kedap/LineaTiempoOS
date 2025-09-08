package com.codedestroyers;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVParser {

    String ruta_csv;

    CSVParser(String ruta_csv) {
        this.ruta_csv = ruta_csv;
    }

    public List<Hito> obtenerDatos() {
        try {
            return new CsvToBeanBuilder<Hito>(new FileReader(ruta_csv))
                    .withType(Hito.class)
                    .withSkipLines(1) // Omitir la primera línea (cabecera)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "El archivo " + ruta_csv + " no se encontro",
                    e
            );
        }
    }
}
