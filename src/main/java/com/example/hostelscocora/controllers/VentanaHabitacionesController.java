package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.CeldaHabitacion;
import com.example.hostelscocora.model.ESTADO_HABITACION;
import com.example.hostelscocora.model.Habitacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class VentanaHabitacionesController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnRegresar;

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

    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private final ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
    private Application application;
    private ObservableList<Habitacion> getListaHabitacionesData() {
        listaHabitacionesData.addAll(modelFactoryController.obtenerHabitaciones());
        return listaHabitacionesData;
    }

    /**
     * ACTIONS QUE LE DAN FUNCION A LOS BOTONES
     * @param event
     */
    @FXML
    void actualizarAction(ActionEvent event) {

    }

    @FXML
    void regresarAction(ActionEvent event) {
        application.mostrarVentanaAdministrar();
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

    public void setApplication(Application application) {
        this.application = application;
    }
}
