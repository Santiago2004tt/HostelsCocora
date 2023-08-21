package com.example.hostelscocora.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Fecha implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * atributos
     */
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    /**
     * constructor
     * @param fechaInicio
     * @param fechaFinal
     */
    public Fecha(LocalDate fechaInicio, LocalDate fechaFinal) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    /**
     * constructor vació
     */
    public Fecha(){

    }

    /**
     * métodos get y set
     * @return
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isSuperpone(Fecha fecha) {
        return (fechaInicio.isBefore(fecha.fechaFinal) && fechaFinal.isAfter(fecha.fechaInicio))
                || (fecha.fechaInicio.isBefore(fechaFinal) && fecha.fechaFinal.isAfter(fechaInicio));
    }
}
