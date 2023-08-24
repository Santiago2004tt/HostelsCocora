package com.example.hostelscocora.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cama implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * atributos
     */
    private byte peso;
    private String id;
    private String imagen;
    private ArrayList<Habitacion> listaHabitaciones;
    private TIPO_CAMA tipoCama;
    private ESTADO_CAMA estadoCama;

    /**
     * constructor
     * @param peso
     * @param id
     * @param tipoCama
     * @param estadoCama
     */
    public Cama(byte peso, String id, String imagen, TIPO_CAMA tipoCama, ESTADO_CAMA estadoCama) {
        this.peso = peso;
        this.id = id;
        this.imagen = imagen;
        this.tipoCama = tipoCama;
        this.estadoCama = estadoCama;
        listaHabitaciones = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Cama(){
        listaHabitaciones = new ArrayList<>();
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
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

    public boolean verificarDisponibilidad(Fecha fechaNueva) {
        for (Habitacion habitacion: listaHabitaciones) {
            if(habitacion.verificarFiltroFecha(fechaNueva)){
                return true;
            }
        }
        return false;
    }
}
