package com.example.hostelscocora.model;

public enum ESTADO_HABITACION {
    MANTENIMIENTO("Mantenimiento"),
    OPERACION("Operacion");

    private final String descripcion;

    ESTADO_HABITACION(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return descripcion;
    }
}
