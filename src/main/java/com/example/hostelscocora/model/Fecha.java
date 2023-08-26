package com.example.hostelscocora.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public LocalDate obtenerFechaInicio(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaInicio, formatter);
    }

    public LocalDate obtenerFechaFinal(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaFinal, formatter);
    }

    public boolean isSobrepuesto(Fecha fecha) {
        LocalDate startDate = obtenerFechaInicio();
        LocalDate endDate = obtenerFechaFinal();

        return (startDate.isBefore(fecha.obtenerFechaFinal()) && endDate.isAfter(fecha.obtenerFechaInicio()))
                || (fecha.obtenerFechaInicio().isBefore(endDate) && fecha.obtenerFechaFinal().isAfter(startDate));
    }


    public void crearFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaInicial = fechaInicio.format(formatter);
        String fechaFin = fechaFinal.format(formatter);
        this.fechaInicio = fechaInicial;
        this.fechaFinal = fechaFin;
    }
}
