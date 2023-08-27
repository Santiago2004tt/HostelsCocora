package com.example.hostelscocora.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * atributos
     */
    private byte capacidad;
    private String id;
    private String imagen;
    private ESTADO_HABITACION estadoHabitacion;
    private TIPO_HABITACION tipoHabitacion;
    private ArrayList<Cama> listaCamas;
    private ArrayList<DetalleReserva> listaDetalleReserva;

    /**
     * constructor
     * @param capacidad
     * @param id
     * @param estadoHabitacion
     * @param tipoHabitacion
     */
    public Habitacion(byte capacidad, String id, String imagen, ESTADO_HABITACION estadoHabitacion, TIPO_HABITACION tipoHabitacion) {
        this.capacidad = capacidad;
        this.id = id;
        this.imagen = imagen;
        this.estadoHabitacion = estadoHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        listaCamas = new ArrayList<>();
        listaDetalleReserva = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Habitacion(){
        listaCamas = new ArrayList<>();
        listaDetalleReserva = new ArrayList<>();
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

    public ArrayList<DetalleReserva> getListaDetalleReserva() {
        return listaDetalleReserva;
    }

    public void setListaDetalleReserva(ArrayList<DetalleReserva> listaDetalleReserva) {
        this.listaDetalleReserva = listaDetalleReserva;
    }

    /**
     * SE ENCARGA DE VERIFICAR SI LA HABITACION ESTA DISPONIBLE HOY
     * @return
     */
    public boolean habitacionDisponible() {
        for (DetalleReserva detalleReserva : listaDetalleReserva) {
            LocalDate fechaInicial = detalleReserva.getFecha().obtenerFechaInicio();
            LocalDate fechaFinal = detalleReserva.getFecha().obtenerFechaFinal();
            LocalDate fechaHoy = LocalDate.now();

            if (fechaHoy.isAfter(fechaInicial.minusDays(1)) &&
                    fechaHoy.isBefore(fechaFinal.plusDays(1))) {
                return false;
            }
        }
        return true;
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

    public boolean verificarFiltroFecha(Fecha fechaNueva) {
        for (DetalleReserva detalleReserva: this.listaDetalleReserva) {
            if(detalleReserva.verificarFecha(fechaNueva)){
                return false;
            }
        }
        return true;
    }
}
