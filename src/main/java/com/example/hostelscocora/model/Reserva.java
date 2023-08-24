package com.example.hostelscocora.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * atributos
     */
    private String codigo;
    private double total;
    private String medioPago;
    private ArrayList<DetalleReserva> listaDetallesReserva;
    private int cantidadPersonas;

    /**
     * constructor
     *
     * @param codigo
     * @param total
     * @param medioPago
     */
    public Reserva(String codigo, double total, String medioPago, int cantidadPersonas) {
        this.codigo = codigo;
        this.total = total;
        this.medioPago = medioPago;
        this.cantidadPersonas = cantidadPersonas;
        listaDetallesReserva = new ArrayList<>();
    }

    /**
     * constructor vació
     */
    public Reserva() {
        listaDetallesReserva = new ArrayList<>();
    }

    /**
     * métodos get y set
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public ArrayList<DetalleReserva> getListaDetallesReserva() {
        return listaDetallesReserva;
    }

    public void setListaDetallesReserva(ArrayList<DetalleReserva> listaDetallesReserva) {
        this.listaDetallesReserva = listaDetallesReserva;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    /**
     * método equals para verificar código de reserva
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(codigo, reserva.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public DetalleReserva crearDetalleReserva(double subTotal, boolean isCamaExtra, Fecha fechaNueva) {
        DetalleReserva detalleReserva = new DetalleReserva();
        detalleReserva.setCamaExtra(isCamaExtra);
        detalleReserva.setFecha(fechaNueva);
        detalleReserva.setSubTotal(subTotal);
        detalleReserva.setId(listaDetallesReserva.size()+1+"");
        listaDetallesReserva.add(detalleReserva);
        return detalleReserva;
    }

    public double obtenerTotal() {
        double contador = 0;
        for (DetalleReserva detalleReserva : listaDetallesReserva) {
            contador += detalleReserva.getSubTotal();
        }
        return contador;
    }
}
