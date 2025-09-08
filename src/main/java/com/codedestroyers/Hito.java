package com.codedestroyers;

import com.opencsv.bean.CsvBindByPosition;

public class Hito {

    @CsvBindByPosition(position = 0)
    private String anio;

    @CsvBindByPosition(position = 1)
    private String titulo;

    @CsvBindByPosition(position = 2)
    private String descripcion;

    @CsvBindByPosition(position = 3)
    private String imagen_alt;

    // Constructor requerido por CsvToBean
    public Hito() {
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen_alt() {
        return imagen_alt;
    }

    public void setImagen_alt(String imagen_alt) {
        this.imagen_alt = imagen_alt;
    }

    @Override
    public String toString() {
        return "Hito{" +
                "anio='" + anio + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen_alt='" + imagen_alt + '\'' +
                '}';
    }
}
