package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CrearClienteController {

    @FXML
    private Button btnCrearCliente;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfContrasenia;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private Application application;
    private String ventanaAnterior;

    /**
     * ACTIONS QUE LE DAN FUNCION A LOS BOTONES
     * @param event
     */
    @FXML
    void apellidoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfCedula.requestFocus();
    }

    @FXML
    void cedulaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfTelefono.requestFocus();
    }

    @FXML
    void contraseniaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnCrearCliente.fire();
    }

    @FXML
    void correoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfContrasenia.requestFocus();
    }

    @FXML
    void crearClienteAction(ActionEvent event) {
        try {
            crearCliente();
            MensajeUtil.mensajeInformacion("Éxito", "El cliente fue creado correctamente");
        } catch (ValorRequeridoException | ClienteException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
    }

    @FXML
    void nombreReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfApellido.requestFocus();
    }

    @FXML
    void regresarAction(ActionEvent event) {
        if (ventanaAnterior.equals("ventana-generar-reservas")) {
            application.mostrarGenerarReservas("ventana-reservas");
        } else if(ventanaAnterior.equals("ventana-administrar")){
            application.mostrarVentanaAdministrar();
        }
    }

    @FXML
    void telefonoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfCorreo.requestFocus();
    }

    /**
     * FUNCIONES PARA LOS ACTIONS
     */
    private void crearCliente() throws ValorRequeridoException, ClienteException {
        if (tfNombre.getText().isEmpty())
            throw new ValorRequeridoException("El campo nombre es requerido");
        if (tfApellido.getText().isEmpty())
            throw new ValorRequeridoException("El campo apellido es requerido");
        if (tfCedula.getText().isEmpty())
            throw new ValorRequeridoException("El campo cédula es requerido");
        if (tfTelefono.getText().isEmpty())
            throw new ValorRequeridoException("El campo teléfono es requerido");
        if (tfCorreo.getText().isEmpty())
            throw new ValorRequeridoException("El campo correo es requerido");
        if (tfContrasenia.getText().isEmpty())
            throw new ValorRequeridoException("El campo contraseña es requerido");

        modelFactoryController.crearCuentaCliente(tfNombre.getText(), tfApellido.getText(), tfCedula.getText(),
                tfTelefono.getText(), tfCorreo.getText(), tfContrasenia.getText());
        modelFactoryController.guardarResourceSerializableService();
        modelFactoryController.guardarResourceXmlService();
    }


    public void setApplication(Application application, String ventanaAnterior) {
        this.application = application;
        this.ventanaAnterior = ventanaAnterior;
    }

}
