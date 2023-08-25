package com.example.hostelscocora.controllers;

import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.model.*;
import com.example.hostelscocora.persistence.Persistencia;

import java.util.ArrayList;

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

    public ArrayList<Habitacion> obtenerHabitacionesFiltroFecha(Fecha fechaNueva) {
        return hotel.obtenerHabitacionesFiltroFecha(fechaNueva);
    }

    public void aniadirCamas(Habitacion habitacionSeleccionada, boolean isCamaExtra, Fecha fechaNueva) {
        hotel.aniadirCamas(habitacionSeleccionada, isCamaExtra, fechaNueva);
    }

    public Reserva crearReserva() {
        return hotel.crearReserva();
    }

    public boolean verificarCamasDisponibles(boolean isCamaExtra, byte capacidad, Fecha fechaNueva) {
        byte contador = 1;
        if(isCamaExtra){
            contador--;
        }
        return hotel.verificarCamasDisponibles(contador, capacidad, fechaNueva);
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
        //this.hotel = Persistencia.cargarRecursoMarketPlaceXML();

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
        hotel = new Hotel("Hola");

        Habitacion habitacion = new Habitacion();
        habitacion.setCapacidad((byte) 3);
        habitacion.setId("0");
        habitacion.setTipoHabitacion(TIPO_HABITACION.HABITACION_DOBLE);
        habitacion.setEstadoHabitacion(ESTADO_HABITACION.OPERACION);
        Habitacion habitacion2 = new Habitacion();
        habitacion2.setCapacidad((byte) 2);
        habitacion2.setId("1");
        habitacion2.setTipoHabitacion(TIPO_HABITACION.HABITACION_SIMPLE);
        habitacion2.setEstadoHabitacion(ESTADO_HABITACION.OPERACION);
        Habitacion habitacion3 = new Habitacion();
        habitacion3.setId("2");
        habitacion3.setCapacidad((byte) 3);
        habitacion3.setTipoHabitacion(TIPO_HABITACION.HABITACION_DOBLE);
        habitacion3.setEstadoHabitacion(ESTADO_HABITACION.OPERACION);
        hotel.getListaHabitaciones().add(habitacion);
        hotel.getListaHabitaciones().add(habitacion2);
        hotel.getListaHabitaciones().add(habitacion3);

        for (int i = 0; i < 3; i++) {
            Cama cama = new Cama();
            cama.setId(i+"");
            cama.setTipoCama(TIPO_CAMA.CAMA_SIMPLE);
            cama.setPeso((byte) 1);
            cama.setEstadoCama(ESTADO_CAMA.OPERACION);
            hotel.getListaCamas().add(cama);
        }


        Cliente cliente = new Cliente();
        cliente.setNombre("Jere");
        cliente.setCedula("1");
        cliente.setContrasenia("1");
        hotel.getListaClientes().add(cliente);
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
