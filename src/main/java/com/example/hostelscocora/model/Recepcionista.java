package com.example.hostelscocora.model;

import java.io.Serializable;
import java.util.Objects;

public class Recepcionista implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * atributos
     */
    private String usuario;
    private String contrasenia;

    /**
     * constructor
     * @param usuario
     * @param contrasenia
     */
    public Recepcionista(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * constructor vacio
     */
    public Recepcionista(){

    }

    /**
     * get y set
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * método equals con usuario y contrasenia
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recepcionista that = (Recepcionista) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(contrasenia, that.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }
}
