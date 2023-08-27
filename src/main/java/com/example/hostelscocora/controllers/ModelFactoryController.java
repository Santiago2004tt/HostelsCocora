package com.example.hostelscocora.controllers;

import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.exceptions.RecepcionistaException;
import com.example.hostelscocora.model.*;
import com.example.hostelscocora.persistence.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelFactoryController implements Runnable{

    /**
     * atributos
     */
    private Hotel hotel;
    private Thread guardarXML;
    private Thread guardarSerializable;

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
        this.hotel = Persistencia.cargarRecursoMarketPlaceXML();

        if(hotel == null) {
            inicializarDatos();
            guardarResourceXmlService();
            guardarResourceSerializableService();

        }
    }

    public void guardarResourceSerializableService() {
        guardarSerializable = new Thread(this);
        guardarSerializable.start();
    }

    public void guardarResourceXmlService() {
        guardarXML = new Thread(this);
        guardarXML.start();
    }

    private void inicializarDatos() {
        hotel = new Hotel("Hostels Cocora");
        inicializarHabitaciones();
        inicializarCamas();
        inicializarRecepcionistaClientes();
    }

    private void inicializarRecepcionistaClientes() {
        Recepcionista recepcionista = new Recepcionista();
        recepcionista.setUsuario("admin");
        recepcionista.setContrasenia("1");
        hotel.getListaRecepcionistas().add(recepcionista);
        Cliente cliente = new Cliente();
        cliente.setNombre("Jeremias");
        cliente.setApellido("Garmendia");
        cliente.setCedula("1");
        cliente.setContrasenia("1");
        cliente.setTelefono("3213412321");
        cliente.setEmail("jere@");
        hotel.getListaClientes().add(cliente);
    }

    private void inicializarCamas() {
        for (int i = 1; i <= 40; i++) {
            Cama cama = new Cama();
            cama.setId("c"+i);
            cama.setTipoCama(TIPO_CAMA.CAMA_SIMPLE);
            cama.setPeso((byte) 1);
            cama.setEstadoCama(ESTADO_CAMA.OPERACION);
            cama.setImagen("cama-simple.png");
            hotel.getListaCamas().add(cama);
        }
        for (int i = 41; i <= 45; i++) {
            Cama cama = new Cama();
            cama.setId("c"+i);
            cama.setTipoCama(TIPO_CAMA.CAMA_DOBLE);
            cama.setPeso((byte) 2);
            cama.setEstadoCama(ESTADO_CAMA.OPERACION);
            cama.setImagen("cama-doble.png");
            hotel.getListaCamas().add(cama);
        }
    }

    private void inicializarHabitaciones() {
        for(int i= 1; i<=10; i++ ){
            Habitacion habitacion = new Habitacion();
            habitacion.setCapacidad((byte) 2);
            habitacion.setId("h"+i);
            habitacion.setTipoHabitacion(TIPO_HABITACION.HABITACION_SIMPLE);
            habitacion.setEstadoHabitacion(ESTADO_HABITACION.OPERACION);
            habitacion.setImagen("habitacion-simple.png");
            hotel.getListaHabitaciones().add(habitacion);
        }
        for (int i = 11; i<=20; i++){
            Habitacion habitacion = new Habitacion();
            habitacion.setCapacidad((byte) 3);
            habitacion.setId("h"+i);
            habitacion.setTipoHabitacion(TIPO_HABITACION.HABITACION_DOBLE);
            habitacion.setEstadoHabitacion(ESTADO_HABITACION.OPERACION);
            habitacion.setImagen("habitacion-doble.png");
            hotel.getListaHabitaciones().add(habitacion);
        }
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

    public boolean verificarUsuarioRecepcionista(String usuario, String contrasenia) throws RecepcionistaException {
        return hotel.verificarUsuarioRecepcionista(usuario, contrasenia);
    }

    public Cliente obtenerClienteLogueado(String cedula) throws ClienteException {
        return hotel.obtenerClienteLogueado(cedula);
    }

    public boolean crearCuentaCliente(String nombre, String apellido, String cedula, String telefono, String email, String contrasenia) throws ClienteException {
        return hotel.crearCuentaCliente(nombre, apellido, cedula, telefono, email, contrasenia);
    }
    public void crearCuentaRecepcionista(String usuario, String contrasenia) throws RecepcionistaException {
        hotel.crearCuentaRecepcionista(usuario, contrasenia);
    }

}
