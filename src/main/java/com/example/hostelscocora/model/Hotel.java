package com.example.hostelscocora.model;

import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.exceptions.RecepcionistaException;

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * atributos
     */
    private String nombre;
    private ArrayList<Habitacion> listaHabitaciones;
    private ArrayList<Cama> listaCamas;
    private ArrayList<Recepcionista> listaRecepcionistas;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Reserva> listaReserva;

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
        listaReserva = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Hotel(){
        listaHabitaciones = new ArrayList<>();
        listaCamas = new ArrayList<>();
        listaRecepcionistas = new ArrayList<>();
        listaClientes = new ArrayList<>();
        listaReserva = new ArrayList<>();
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

    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
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

    public boolean verificarUsuario(String cedula, String contrasenia) throws ClienteException {

        for (Cliente cliente: listaClientes) {
            if(cliente.verificarCuenta(cedula, contrasenia)){
                return true;
            }
        }
        throw new ClienteException("El usuario o la contraseña no existen o están incorrectos");
    }

    public boolean verificarUsuarioRecepcionista(String usuario, String contrasenia) throws RecepcionistaException {
        for (Recepcionista recepcionista: listaRecepcionistas) {
            if(recepcionista.verificarCuenta(usuario, contrasenia)){
                return true;
            }
        }
        throw new RecepcionistaException("El usuario o la contraseña no existen o están incorrectos");
    }

    public Cliente obtenerClienteLogueado(String cedula) throws ClienteException {
        for (Cliente cliente:listaClientes) {
            if (cliente.getCedula().equals(cedula)){
                return cliente;
            }
        }
        throw new ClienteException("El cliente no existe");
    }

    public Recepcionista obtenerRecepcionistaLogueado(String usuario) throws RecepcionistaException {
        for (Recepcionista recepcionista : listaRecepcionistas) {
            if (recepcionista.getUsuario().equals(usuario)) {
                return recepcionista;
            }
        }
        throw new RecepcionistaException("El cliente no existe");
    }

    public boolean crearCuentaCliente(String nombre, String apellido, String cedula, String telefono, String email, String contrasenia) throws ClienteException {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setContrasenia(contrasenia);

        if(verificarExistenciaCliente(cliente)){
            listaClientes.add(cliente);
            return true;
        }else {
            throw new ClienteException("La cuenta ya se encuentra registrada");
        }
    }

    private boolean verificarExistenciaCliente(Cliente cliente) {
        for (Cliente cliente1: listaClientes) {
            if(cliente.getCedula().equals(cliente1.getCedula())){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Reserva> obtenerReservaCliente(Cliente clienteLogueado) {
        return clienteLogueado.getListaReserva();
    }

    public ArrayList<Habitacion> obtenerHabitacionesFiltroFecha(Fecha fechaNueva) {
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
        for (Habitacion habitacion: this.listaHabitaciones) {
            if(habitacion.verificarFiltroFecha(fechaNueva)&&habitacion.getEstadoHabitacion().equals(ESTADO_HABITACION.OPERACION)){
                listaHabitaciones.add(habitacion);
            }
        }
        return listaHabitaciones;
    }

    public void aniadirCamas(Habitacion habitacionSeleccionada, boolean isCamaExtra, Fecha fechaNueva) {
        int contador=0;
        if(isCamaExtra){
            contador=0;
        }else {
            contador = 1;
        }
        for (Cama cama: listaCamas ) {
            if(cama.verificarDisponibilidad(fechaNueva)){
                if(contador+ cama.getPeso()<=habitacionSeleccionada.getCapacidad()){
                    contador+= cama.getPeso();
                    habitacionSeleccionada.getListaCamas().add(cama);
                    cama.getListaHabitaciones().add(habitacionSeleccionada);
                    System.out.println("se agrego "+ cama.getId() );
                }
            }
        }
    }

    public Reserva crearReserva() {
        Reserva reserva = new Reserva();
        reserva.setCodigo(listaReserva.size()+1+"");
        return reserva;
    }

    public boolean verificarCamasDisponibles(byte contador, byte capacidad, Fecha fechaNueva) {
        for (Cama cama:listaCamas) {
            if(cama.verificarDisponibilidad(fechaNueva)&&contador<=capacidad){
                if(contador+cama.getPeso()<=capacidad && cama.getEstadoCama().equals(ESTADO_CAMA.OPERACION)){
                    contador+=cama.getPeso();
                }
            }
        }
        if(contador== capacidad){
            System.out.println("entro");
            return true;
        }
        return false;
    }
}
