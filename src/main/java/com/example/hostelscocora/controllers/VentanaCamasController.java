package com.example.hostelscocora.controllers;

import com.example.hostelscocora.model.Cama;
import com.example.hostelscocora.model.ESTADO_CAMA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

    @FXML
    void actualizarAction(ActionEvent event) {

    }

    @FXML
    void regresarAction(ActionEvent event) {

    }

}
