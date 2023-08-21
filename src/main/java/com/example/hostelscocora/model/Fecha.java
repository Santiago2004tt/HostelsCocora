package com.example.hostelscocora.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Fecha implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * atributos
     */
    private String fechaInicio;
    private String fechaFinal;

    /**
     * constructor
     * @param fechaInicio
     * @param fechaFinal
     */
    public Fecha(String fechaInicio, String fechaFinal) {
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
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
