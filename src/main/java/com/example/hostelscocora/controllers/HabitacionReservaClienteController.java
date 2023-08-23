package com.example.hostelscocora.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HabitacionReservaClienteController {

    @FXML
    private DatePicker dpFechaFinal;

    @FXML
    private TextField tfCantidadPersonas;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<?, ?> colTipoHabitacion;

    @FXML
    private ComboBox<?> cbCamaExtra;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private TableView<?> tblHabitacion;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Button btnBuscar;

    @FXML
    void accederPerfilCliente(ActionEvent event) {

    }

    @FXML
    void buscarHabitacionesDisponibles(ActionEvent event) {

    }

    @FXML
    void crearReserva(ActionEvent event) {

    }

}
