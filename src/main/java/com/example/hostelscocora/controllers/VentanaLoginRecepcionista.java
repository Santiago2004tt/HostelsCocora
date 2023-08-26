package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.RecepcionistaException;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VentanaLoginRecepcionista {

    @FXML
    private Button btnAcceder;

    @FXML
    private Button btnCliente;

    @FXML
    private PasswordField tfContrasenia;

    @FXML
    private TextField tfUsuario;

    /**
     * ATRIBUTOS
     */
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private Application application;

    @FXML
    void accederAction(ActionEvent event) {
        accederRecepcionista();
    }

    @FXML
    void clienteAction(ActionEvent event) {
        application.mostrarVentanaClientelogin();
    }

    @FXML
    void contraseniaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnAcceder.fire();
    }

    @FXML
    void usuarioReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfContrasenia.requestFocus();
    }

    private void accederRecepcionista() {
        String usuario = tfUsuario.getText();
        String contrsenia = tfContrasenia.getText();

        try {
            if (modelFactoryController.verificarUsuarioRecepcionista(usuario, contrsenia)) {
                application.mostrarVentanaAdministrar();
            }
        } catch (RecepcionistaException e) {
            MensajeUtil.mensajeAlerta("Error de inicio", e.getMessage());
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
