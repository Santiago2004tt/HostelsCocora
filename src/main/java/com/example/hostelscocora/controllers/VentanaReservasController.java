package com.example.hostelscocora.controllers;

import com.example.hostelscocora.CustomListCell;
import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class VentanaReservasController {

    @FXML
    private Button btnRealizarReserva;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblCamasDobles;

    @FXML
    private Label lblCamasSimples;

    @FXML
    private ListView<Habitacion> listViewHabitaciones;

    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private final ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
    private Application application;

    private ObservableList<Habitacion> getListaHabitacionesData() {
        listaHabitacionesData.addAll(modelFactoryController.obtenerHabitaciones());
        return listaHabitacionesData;
    }


    @FXML
    void realizarReservaAction(ActionEvent event) {
        application.mostrarGenerarReservas("ventana-reservas");
    }

    @FXML
    void regresarAction(ActionEvent event) {
        application.mostrarVentanaAdministrar();
    }

    @FXML
    void initialize() {

        byte camasDoble = 0;
        byte camasSimples = 0;

        for (Cama cama : modelFactoryController.obtenerCamas()) {
            if (cama.getEstadoCama() == ESTADO_CAMA.OPERACION ) {
                if (cama.getTipoCama() == TIPO_CAMA.CAMA_SIMPLE) {
                    camasSimples++;
                } else {
                    camasDoble++;
                }
            }
        }

        lblCamasSimples.setText("Camas Simples Disponibles: " + camasSimples);
        lblCamasDobles.setText("Camas Dobles Disponibles: " + camasDoble);

        listViewHabitaciones.setItems(getListaHabitacionesData());

        // Se encarga de asignarle el formato con el que va a mostrar los elementos de la lista
        listViewHabitaciones.setCellFactory(new Callback<ListView<Habitacion>, ListCell<Habitacion>>() {
            @Override
            public ListCell<Habitacion> call(ListView<Habitacion> habitacionListView) {
                return new CustomListCell();
            }
        });
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
