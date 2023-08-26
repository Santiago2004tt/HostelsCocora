package com.example.hostelscocora.model;

public enum CAMA_EXTRA {
    SI (true),
    NO (false);

    private final boolean valor;

    CAMA_EXTRA(boolean valor) {
        this.valor = valor;
    }

    public boolean getBoolean() {
        return valor;
    }

    @Override
    public String toString() {
        return valor ? "Sí" : "No";
    }
}
