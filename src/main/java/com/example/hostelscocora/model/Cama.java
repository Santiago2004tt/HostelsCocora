package com.example.hostelscocora.model;

import java.util.Objects;

public class Cama {

    /**
     * atributos
     */
    private byte peso;
    private String id;
    private Habitacion habitacion;
    private TIPO_CAMA tipoCama;
    private ESTADO_CAMA estadoCama;

    /**
     * constructor
     * @param peso
     * @param id
     * @param habitacion
     * @param tipoCama
     * @param estadoCama
     */
    public Cama(byte peso, String id, Habitacion habitacion, TIPO_CAMA tipoCama, ESTADO_CAMA estadoCama) {
        this.peso = peso;
        this.id = id;
        this.habitacion = habitacion;
        this.tipoCama = tipoCama;
        this.estadoCama = estadoCama;
    }

    /**
     * constructor vació
     */
    public Cama(){

    }

    /**
     * métodos get y set
     * @return
     */
    public byte getPeso() {
        return peso;
    }

    public void setPeso(byte peso) {
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public TIPO_CAMA getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(TIPO_CAMA tipoCama) {
        this.tipoCama = tipoCama;
    }

    public ESTADO_CAMA getEstadoCama() {
        return estadoCama;
    }

    public void setEstadoCama(ESTADO_CAMA estadoCama) {
        this.estadoCama = estadoCama;
    }

    /**
     * método equals para id
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cama cama = (Cama) o;
        return Objects.equals(id, cama.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
