package com.example.hostelscocora.model;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {

    /**
     * atributos
     */
    private String cedula;
    private String telefono;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private ArrayList<Reserva> listaReserva;

    /**
     * constructor
     * @param cedula
     * @param telefono
     * @param email
     * @param nombre
     * @param apellido
     * @param contrasenia
     */
    public Cliente(String cedula, String telefono, String email, String nombre, String apellido, String contrasenia) {
        this.cedula = cedula;
        this.telefono = telefono;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        listaReserva=new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Cliente(){
        listaReserva=new ArrayList<>();
    }

    /**
     * métodos get y set
     * @return
     */
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cedula, cliente.cedula) && Objects.equals(contrasenia, cliente.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, contrasenia);
    }
}
