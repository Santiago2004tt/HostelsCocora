package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PerfilClienteController {

    ModelFactoryController modelFactoryController;
    Cliente clienteLogueado;
    Application application;
    @FXML
    private Label lblTelefono;

    @FXML
    private Button btnReserva;

    @FXML
    private Button btnSalir;

    @FXML
    private Label lblNombre;

    @FXML
    private Button btnHabitacion;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblApellido;

    @FXML
    void mostrarReservas(ActionEvent event) {
        //application
    }

    @FXML
    void mostrarHabitacionesDisponibles(ActionEvent event) {
        //application
    }

    @FXML
    void mostrarLoginCliente(ActionEvent event) {
        mostrarLoginClienteAcction();
    }

    private void mostrarLoginClienteAcction() {
        //application.mostrarLoginCliente();
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
    }

    public void setApplication(Application application, Cliente clienteLogueado){
        this.application = application;
        this.clienteLogueado = clienteLogueado;
        aniadirDatos();
    }

    private void aniadirDatos() {
        lblNombre.setText("Nombre: "+ clienteLogueado.getNombre());
        lblApellido.setText("Apellido: "+ clienteLogueado.getApellido());
        lblCedula.setText("Cédula: "+ clienteLogueado.getCedula());
        lblEmail.setText("Email: "+ clienteLogueado.getEmail());
        lblTelefono.setText("Teléfono: "+ clienteLogueado.getTelefono());
    }


}
