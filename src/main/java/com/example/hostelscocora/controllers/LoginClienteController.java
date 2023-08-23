package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LoginClienteController {

    Application application;
    ModelFactoryController modelFactoryController;

    @FXML
    private Button btnAccederCrearCuenta;

    @FXML
    private Button btnAccederCuenta;

    @FXML
    private PasswordField pfContrasenia;

    @FXML
    private TextField tfCedula;

    @FXML
    private Button btnRecepcionista;

    @FXML
    void accederRecepcionista(ActionEvent event) {
        accederRecepcionistaAction();
    }



    @FXML
    void tfCedulaKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            pfContrasenia.requestFocus();
        }
    }

    @FXML
    void pfContraseniaKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            btnAccederCuenta.fire();
        }
    }

    @FXML
    void accederCuenta(ActionEvent event) {
        accederCuentaAction();
    }

    @FXML
    void accederCrearCuenta(ActionEvent event) {
        accederCrearCuentaAction();
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
    }

    //métodos

    public void setApplication(Application application){
        this.application = application;
    }

    /**
     * método para verificar el inicio de sesión
     */
    private void accederCuentaAction() {
        String cedula = tfCedula.getText();
        String contrasenia = pfContrasenia.getText();

        try {
            if (modelFactoryController.verificarUsuario(cedula, contrasenia)) {
                Cliente clienteLogueado = modelFactoryController.obtenerClienteLogueado(cedula);
                mostrarCuentaCliente(clienteLogueado);
            }
        } catch (ClienteException e) {
            mensajeAlerta("Error de inicio", e.getMessage());
        }
    }

    /**
     *
     * @param titulo
     * @param mensaje
     */
    public static void mensajeAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * método para cambiar de ventana
     * @param clienteLogueado
     */
    private void mostrarCuentaCliente(Cliente clienteLogueado) {
        //aplicacion.mostrarCuentaCliente(clienteLogueado);
    }

    private void accederCrearCuentaAction() {
        //aplicacion.
    }

    private void accederRecepcionistaAction() {
        //aplicacion.
    }
}
