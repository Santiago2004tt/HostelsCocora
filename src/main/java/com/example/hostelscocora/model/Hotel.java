package com.example.hostelscocora.model;

import java.util.ArrayList;

public class Hotel {

    /**
     * atributos
     */
    private String nombre;
    private ArrayList<Habitacion> listaHabitaciones;
    private ArrayList<Cama> listaCamas;
    private ArrayList<Recepcionista> listaRecepcionistas;
    private ArrayList<Cliente> listaClientes;

    /**
     * constructor
     * @param nombre
     */
    public Hotel(String nombre) {
        this.nombre = nombre;
        listaHabitaciones = new ArrayList<>();
        listaCamas = new ArrayList<>();
        listaRecepcionistas = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Hotel(){
        listaHabitaciones = new ArrayList<>();
        listaCamas = new ArrayList<>();
        listaRecepcionistas = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }

    /**
     * get y set
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public ArrayList<Cama> getListaCamas() {
        return listaCamas;
    }

    public void setListaCamas(ArrayList<Cama> listaCamas) {
        this.listaCamas = listaCamas;
    }

    public ArrayList<Recepcionista> getListaRecepcionistas() {
        return listaRecepcionistas;
    }

    public void setListaRecepcionistas(ArrayList<Recepcionista> listaRecepcionistas) {
        this.listaRecepcionistas = listaRecepcionistas;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * método to string
     * @return
     */
    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", listaHabitaciones=" + listaHabitaciones +
                ", listaCamas=" + listaCamas +
                ", listaRecepcionistas=" + listaRecepcionistas +
                ", listaClientes=" + listaClientes +
                '}';
    }
}
