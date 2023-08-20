package com.example.hostelscocora.model;

import java.time.LocalDate;

public class Fecha {

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
}
