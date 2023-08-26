package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class VentanaAdministrarController {

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private Application application;

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
        application.mostrarCrearCliente("ventana-administrar");
    }

    @FXML
    void generarReservasAction(ActionEvent event) {
        application.mostrarGenerarReservas("ventana-administrar");
    }


    @FXML
    void salirAction(ActionEvent event) {
        application.mostrarVentanaLoginRecepcionista();
    }
    @FXML
    void ventanaReservasAction(ActionEvent event) {
        application.mostrarVentanaReservas();
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
