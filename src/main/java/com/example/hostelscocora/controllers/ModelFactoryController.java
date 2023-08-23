package com.example.hostelscocora.controllers;

import com.example.hostelscocora.model.*;
import com.example.hostelscocora.persistence.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ModelFactoryController implements Runnable{

    /**
     * atributos
     */
    Hotel hotel;
    Thread guardarXML;
    Thread guardarSerializable;

    /**
     *
     */
    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        if (hiloActual == guardarXML)
            guardarResourceXML();
        else if (hiloActual == guardarSerializable)
            guardarResourceSerializable();
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eInstance = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eInstance;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setMarketPlace(Hotel hotel) {
        this.hotel = hotel;
    }

    public ModelFactoryController() {
        this.hotel = Persistencia.cargarRecursoMarketPlaceXML();

        if(hotel == null) {
            inicializarDatos();
            guardarResourceXmlService();
            guardarResourceSerializableService();

        }
    }

    private void guardarResourceSerializableService() {
        guardarSerializable = new Thread(this);
        guardarSerializable.start();
    }

    private void guardarResourceXmlService() {
        guardarXML = new Thread(this);
        guardarXML.start();
    }

    private void inicializarDatos() {

    }

    public ArrayList<Habitacion> obtenerHabitaciones() {
        return hotel.getListaHabitaciones();
    }

    public ArrayList<Cama> obtenerCamas() {
        return hotel.getListaCamas();
    }

    public ArrayList<Cliente> obtenerClientes() {
        return hotel.getListaClientes();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoMarketPlaceXML(hotel);
    }

    private void guardarResourceSerializable() {
        Persistencia.guardarRecursoMarketPlaceBinario(hotel);
    }
}
