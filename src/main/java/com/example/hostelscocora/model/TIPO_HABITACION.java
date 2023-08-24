package com.example.hostelscocora.model;

public enum TIPO_HABITACION {
    HABITACION_DOBLE("Habitacion doble"),
    HABITACION_SIMPLE("Habitacion simple");

    private final String descripcion;

    TIPO_HABITACION(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return descripcion;
    }
}
