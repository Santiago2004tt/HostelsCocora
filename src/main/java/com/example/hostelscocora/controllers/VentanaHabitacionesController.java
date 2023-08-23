package com.example.hostelscocora.controllers;

import com.example.hostelscocora.model.ESTADO_HABITACION;
import com.example.hostelscocora.model.Habitacion;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VentanaHabitacionesController {

    @FXML
    private ComboBox<ESTADO_HABITACION> cbEstadoHabitacion;

    @FXML
    private ListView<Habitacion> listViewHabitaciones;

    @FXML
    private TextField tfCantidadReservas;

    @FXML
    private TextField tfIdHabitacion;

    @FXML
    private TextField tfTipoHabitacion;

}
