package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class VentanaAdministrarController {

    private Application application;
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private List<String> listaHistorial = new ArrayList<>();

    @FXML
    void administrarCamasAction(ActionEvent event) {
        application.mostrarVentanaCamas();
    }

    @FXML
    void administrarHabitacionesAction(ActionEvent event) {
        application.mostrarVentanaHabitaciones();
    }

    @FXML
    void crearClienteAction(ActionEvent event) {
        listaHistorial.add("ventana-administrar");
        application.mostrarCrearCliente(listaHistorial);
    }

    @FXML
    void generarReservasAction(ActionEvent event) {
        listaHistorial.add("ventana-administrar");
        application.mostrarGenerarReservas(listaHistorial);
    }


    @FXML
    void salirAction(ActionEvent event) {
        application.mostrarVentanaLoginRecepcionista();
    }
    @FXML
    void ventanaReservasAction(ActionEvent event) {
        application.mostrarVentanaReservas(listaHistorial);
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
