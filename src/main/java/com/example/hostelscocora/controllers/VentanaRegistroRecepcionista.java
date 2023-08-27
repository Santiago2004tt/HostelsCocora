package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.RecepcionistaException;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.model.Hotel;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VentanaRegistroRecepcionista {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfContrasenia;

    @FXML
    private TextField tfUsuario;

    private Application application;
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private Hotel hotel = modelFactoryController.getHotel();

    @FXML
    void contraseniaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnCrear.fire();
    }

    @FXML
    void loginAction(ActionEvent event) {
        application.mostrarVentanaLoginRecepcionista();
    }

    @FXML
    void crearUsuarioAction(ActionEvent event) {
        try {
            crearUsuario();
            MensajeUtil.mensajeInformacion("Éxito", "El usuario fue creado correctamente");
        } catch (ValorRequeridoException | RecepcionistaException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
    }

    @FXML
    void usuarioReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfContrasenia.requestFocus();
    }

    private void crearUsuario() throws ValorRequeridoException, RecepcionistaException {
        String usuario = tfUsuario.getText();
        String contrasenia = pfContrasenia.getText();
        if (usuario.isEmpty())
            throw new ValorRequeridoException("El campo usuario es requerido");
        if (contrasenia.isEmpty())
            throw new ValorRequeridoException("El campo contraseña es requerido");
        modelFactoryController.crearCuentaRecepcionista(usuario, contrasenia);
        modelFactoryController.guardarResourceXmlService();
        modelFactoryController.guardarResourceSerializableService();
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
