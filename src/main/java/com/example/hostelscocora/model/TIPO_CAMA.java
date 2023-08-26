package com.example.hostelscocora.model;

public enum TIPO_CAMA {
    CAMA_DOBLE("Cama Doble"),
    CAMA_SIMPLE("Cama Simple");

    private final String descripcion;
    TIPO_CAMA(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return descripcion;
    }

}
