package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.Cliente;
import com.example.hostelscocora.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservacionesClienteController {

    ModelFactoryController modelFactoryController;
    Cliente clienteLogueado;
    Reserva reservaSeleccionada;
    Application application;
    ObservableList<Reserva> listaReservasData = FXCollections.observableArrayList();

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Reserva, Double> columTotal;

    @FXML
    private TableView<Reserva> tblReservaciones;

    @FXML
    private TableColumn<Reserva, String> columCodigo;

    @FXML
    private Button btnAcceder;

    @FXML
    void mostrarPerfilCliente(ActionEvent event) {
        mostrarPerfilClienteAction();
    }

    private void mostrarPerfilClienteAction() {
        application.mostrarPerfilCliente(clienteLogueado);
    }

    @FXML
    void accederDetallesReserva(ActionEvent event) {
        accederDetallesReservaAction();
    }

    private void accederDetallesReservaAction() {
        if(reservaSeleccionada != null){
            application.mostrarDetallesReservacionesCliente(clienteLogueado, reservaSeleccionada);
        }
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
        inicializarTabla();
    }

    private void inicializarTabla() {
        this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblReservaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            reservaSeleccionada= newSelection;
        });
    }

    public void setApplication(Application application, Cliente clienteLogueado){
        this.application = application;
        this.clienteLogueado = clienteLogueado;
        tblReservaciones.getItems().clear();
        tblReservaciones.setItems(getListaReservasData());
    }

    private ObservableList<Reserva> getListaReservasData() {
        //Obtener los clientes del modelo para guardarlos en una lista Observable
        listaReservasData.addAll(modelFactoryController.obtenerReserva(clienteLogueado));
        return listaReservasData;
    }

}