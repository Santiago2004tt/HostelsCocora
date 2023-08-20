package com.example.hostelscocora.model;

import java.util.ArrayList;
import java.util.Objects;

public class Habitacion {

    /**
     * atributos
     */
    private byte capacidad;
    private boolean disponible;
    private String id;
    private ESTADO_HABITACION estadoHabitacion;
    private TIPO_HABITACION tipoHabitacion;
    private ArrayList<Cama> listaCamas;

    /**
     * constructor
     * @param capacidad
     * @param disponible
     * @param id
     * @param estadoHabitacion
     * @param tipoHabitacion
     */
    public Habitacion(byte capacidad, boolean disponible, String id, ESTADO_HABITACION estadoHabitacion, TIPO_HABITACION tipoHabitacion) {
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.id = id;
        this.estadoHabitacion = estadoHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        listaCamas = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Habitacion(){
        listaCamas = new ArrayList<>();
    }

    /**
     * métodos get y set
     * @return
     */
    public byte getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(byte capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ESTADO_HABITACION getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(ESTADO_HABITACION estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public TIPO_HABITACION getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TIPO_HABITACION tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public ArrayList<Cama> getListaCamas() {
        return listaCamas;
    }

    public void setListaCamas(ArrayList<Cama> listaCamas) {
        this.listaCamas = listaCamas;
    }

    /**
     * método equal para id
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
