package com.example.hostelscocora.controllers;

import com.example.hostelscocora.model.CeldaHabitacion;
import com.example.hostelscocora.model.CustomListCell;
import com.example.hostelscocora.model.ESTADO_HABITACION;
import com.example.hostelscocora.model.Habitacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

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

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
    private ObservableList<Habitacion> getListaHabitacionesData() {
        listaHabitacionesData.addAll(modelFactoryController.obtenerHabitaciones());
        return listaHabitacionesData;
    }

    @FXML
    void initialize() {

        cbEstadoHabitacion.setItems(FXCollections.observableArrayList(ESTADO_HABITACION.values()));
        cbEstadoHabitacion.setPromptText("Seleccionar");

        listViewHabitaciones.setItems(getListaHabitacionesData());
        listViewHabitaciones.setCellFactory(new Callback<ListView<Habitacion>, ListCell<Habitacion>>() {
            @Override
            public ListCell<Habitacion> call(ListView<Habitacion> habitacionListView) {
                return new CeldaHabitacion();
            }
        });

    }

}
