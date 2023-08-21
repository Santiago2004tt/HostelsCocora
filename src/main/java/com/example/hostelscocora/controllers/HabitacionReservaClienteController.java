package com.example.hostelscocora.controllers;


import com.example.hostelscocora.model.Habitacion;
import com.example.hostelscocora.model.TIPO_HABITACION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HabitacionReservaClienteController {

    @FXML
    private DatePicker dpFechaFinal;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Habitacion, TIPO_HABITACION> colTipoHabitacion;

    @FXML
    private ComboBox<String> cbCamaExtra;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private TableView<Habitacion> tblHabitacion;

    @FXML
    private TableColumn<Habitacion, String> colId;

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