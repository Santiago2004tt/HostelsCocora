package com.example.hostelscocora.controllers;

import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.model.Cliente;
import com.example.hostelscocora.model.Hotel;
import com.example.hostelscocora.model.Reserva;
import com.example.hostelscocora.persistence.Persistencia;

import java.util.ArrayList;
import java.util.Collection;

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

    public ArrayList<Reserva> obtenerReserva(Cliente clienteLogueado) {
        return hotel.obtenerReservaCliente(clienteLogueado);
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

        if(hotel ==null) {
            inicializarDatos();
            guardarResourceXmlService();
            guardarResourceSerializableService();

        }
    }

    private void guardarResourceSerializableService() {
    }

    private void guardarResourceXmlService() {
        guardarXML = new Thread(this);
        guardarXML.start();
    }

    private void inicializarDatos() {
        guardarSerializable = new Thread(this);
        guardarSerializable.start();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoMarketPlaceXML(hotel);
    }

    private void guardarResourceSerializable() {
        Persistencia.guardarRecursoMarketPlaceBinario(hotel);
    }

    //métodos Clientes

    public boolean verificarUsuario(String cedula, String contrasenia) throws ClienteException {
        return hotel.verificarUsuario(cedula, contrasenia);
    }

    public Cliente obtenerClienteLogueado(String cedula) throws ClienteException {
        return hotel.obtenerClienteLogueado(cedula);
    }

    public boolean crearCuentaCliente(String nombre, String apellido, String cedula, String telefono, String email, String contrasenia) throws ClienteException {
        return hotel.crearCuentaCliente(nombre, apellido, cedula, telefono, email, contrasenia);
    }

}
