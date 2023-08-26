package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.Cama;
import com.example.hostelscocora.model.CustomListCamas;
import com.example.hostelscocora.model.ESTADO_CAMA;
import com.example.hostelscocora.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class VentanaCamasController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<ESTADO_CAMA> cbEstadoCama;

    @FXML
    private ListView<Cama> listViewCamas;

    @FXML
    private TextField tfHabitacion;

    @FXML
    private TextField tfIdCamas;

    @FXML
    private TextField tfTipoCama;

    /**
     * INSTANCIAS
     */
    private Application application;
    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private final Hotel hotel = modelFactoryController.getHotel();
    private final ObservableList<Cama> listaCamasData = FXCollections.observableArrayList();

    public ObservableList<Cama> getListaCamasData() {
        listaCamasData.addAll(hotel.getListaCamas());
        return listaCamasData;
    }

    /**
     * ACTIONS DE LOS BOTONES
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

        listViewCamas.setItems(getListaCamasData());
        listViewCamas.setCellFactory(new Callback<ListView<Cama>, ListCell<Cama>>() {
            @Override
            public ListCell<Cama> call(ListView<Cama> camaListView) {
                return new CustomListCamas();
            }
        });

        listViewCamas.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                llenarCampos(newValue);
            }
        });

        cbEstadoCama.setItems(FXCollections.observableArrayList(ESTADO_CAMA.values()));
        cbEstadoCama.setPromptText("Seleccionar");
    }

    //ESTA FUNCION SE ENCUENTRA INCOMPLETA
    private void llenarCampos(Cama cama) {

        tfIdCamas.clear();
        tfHabitacion.clear();
        tfTipoCama.clear();

        tfIdCamas.setText(cama.getId());
//        tfHabitacion.setText(cama.ge);
        cbEstadoCama.setValue(cama.getEstadoCama());
        tfTipoCama.setText(cama.getTipoCama().toString());
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
