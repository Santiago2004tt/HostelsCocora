package com.example.hostelscocora.model;

import java.io.Serializable;
import java.util.Objects;

public class DetalleReserva implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * atributos
     */
    private double subTotal;
    private boolean camaExtra;
    private String id;
    private Fecha fecha;
    private Habitacion habitacion;

    /**
     * constructor
     * @param subTotal
     * @param camaExtra
     * @param id
     * @param fecha
     * @param habitacion
     */
    public DetalleReserva(double subTotal, boolean camaExtra, String id, Fecha fecha, Habitacion habitacion) {
        this.subTotal = subTotal;
        this.camaExtra = camaExtra;
        this.id = id;
        this.fecha = fecha;
        this.habitacion = habitacion;
    }

    /**
     * constructor vació
     */
    public DetalleReserva(){

    }

    /**
     * métodos get y set
     * @return
     */
    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public boolean isCamaExtra() {
        return camaExtra;
    }

    public void setCamaExtra(boolean camaExtra) {
        this.camaExtra = camaExtra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * método equals para comparar por id
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleReserva that = (DetalleReserva) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean verificarFecha(Fecha fechaNueva) {
        if(this.fecha.isSobrepuesto(fechaNueva)){
            return true;
        }
        return false;
    }
}
