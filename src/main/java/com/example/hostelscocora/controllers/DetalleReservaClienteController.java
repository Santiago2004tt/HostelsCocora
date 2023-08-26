package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.Cliente;
import com.example.hostelscocora.model.DetalleReserva;
import com.example.hostelscocora.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


public class DetalleReservaClienteController {

    Cliente clienteLogueado;
    Reserva reservaSeleccionada;
    DetalleReserva detalleReservaSeleccionada;
    Application application;
    ModelFactoryController modelFactoryController;
    ObservableList<DetalleReserva> listaDetallesReservaData = FXCollections.observableArrayList();

    @FXML
    private Label lblFechaInicio;

    @FXML
    private Button btnSalir;

    @FXML
    private Label lblId;

    @FXML
    private ImageView ivImagen;

    @FXML
    private TableView<DetalleReserva> tblDetallesReserva;

    @FXML
    private TableColumn<DetalleReserva, Double> columSubTotal;

    @FXML
    private TableColumn<DetalleReserva, String> columId;

    @FXML
    private Label lblFechaFinal;

    @FXML
    private Label lblTipoHabitacion;

    @FXML
    private Label lblCamaExtra;

    @FXML
    void accederReservas(ActionEvent event) {
        application.mostrarReservacionesCliente(clienteLogueado);
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
        inicializarTabla();
    }

    private void inicializarTabla() {
        this.columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tblDetallesReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            detalleReservaSeleccionada= newSelection;
            mostrarDatos();
        });
    }

    private void mostrarDatos() {
        //Image image = new Image(detalleReservaSeleccionada.getHabitacion().getImagen());
        //ivImagen.setImage(image);
        lblId.setText(detalleReservaSeleccionada.getHabitacion().getId());
        lblTipoHabitacion.setText(String.valueOf(detalleReservaSeleccionada.getHabitacion().getTipoHabitacion()));
        lblFechaInicio.setText(String.valueOf(detalleReservaSeleccionada.getFecha().getFechaInicio()));
        lblFechaFinal.setText(String.valueOf(detalleReservaSeleccionada.getFecha().getFechaFinal()));
        verificarCamaExtra();
    }

    private void verificarCamaExtra() {

        if (detalleReservaSeleccionada.isCamaExtra()){
            lblCamaExtra.setText("Cama extra: Si");
        }else {
            lblCamaExtra.setText("Cama extra: No");
        }

    }

    public void setApplication(Application application, Cliente clienteLogueado, Reserva reservaSeleccionada){
        this.application = application;
        this.clienteLogueado = clienteLogueado;
        this.reservaSeleccionada = reservaSeleccionada;
        tblDetallesReserva.getItems().clear();
        tblDetallesReserva.setItems(getListaDetallesReservaData());
    }

    private ObservableList<DetalleReserva> getListaDetallesReservaData() {
        listaDetallesReservaData.addAll(reservaSeleccionada.getListaDetallesReserva());
        return listaDetallesReservaData;
    }

}