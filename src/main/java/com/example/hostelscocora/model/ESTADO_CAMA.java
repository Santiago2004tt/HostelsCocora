package com.example.hostelscocora.model;

public enum ESTADO_CAMA {
    MANTENIMIENTO("Mantenimiento"),
    OPERACION("Operacion");

    private final String descripcion;

    ESTADO_CAMA(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return descripcion;
    }
}
