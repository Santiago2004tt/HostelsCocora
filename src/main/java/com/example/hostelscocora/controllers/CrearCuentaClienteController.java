package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ClienteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class CrearCuentaClienteController {

    Application application;
    ModelFactoryController modelFactoryController;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfContrasenia;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfApellido;

    @FXML
    void tfNombreKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tfApellido.requestFocus();
        }
    }

    @FXML
    void tfApellidoKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tfCedula.requestFocus();
        }
    }

    @FXML
    void tfCedulaKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tfContrasenia.requestFocus();
        }
    }

    @FXML
    void tfContraseniaKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tfEmail.requestFocus();
        }
    }

    @FXML
    void tfEmailKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tfTelefono.requestFocus();
        }
    }

    @FXML
    void tfTelefonoKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            btnCrearCuenta.fire();
        }
    }

    @FXML
    void crearCuenta(ActionEvent event) {
        crearCuentaAction();
    }

    @FXML
    void accederLoginCliente(ActionEvent event) {
       accederLoginClienteAction();
    }

    private void accederLoginClienteAction() {
        application.mostrarVentanaClientelogin();
    }

    @FXML
    void accederLoginClienteKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE){
            btnRegresar.fire();
        }
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
    }

    private void crearCuentaAction() {
        String nombre = tfNombre.getText();
        String apellido = tfApellido.getText();
        String cedula = tfCedula.getText();
        String telefono = tfTelefono.getText();
        String email = tfEmail.getText();
        String contrasenia = tfContrasenia.getText();

        if(verificarEspacios(nombre, apellido, cedula, telefono, email, contrasenia)){
            try {
                if(modelFactoryController.crearCuentaCliente(nombre, apellido, cedula, telefono, email, contrasenia)){
                    mensajeInfo("Creación de cuenta", "Se creo correctamente la cuenta");
                    accederLoginClienteAction();
                }
            }catch (ClienteException e){
                mensajeAlerta("Error al crear cuenta", e.getMessage());
            }
        }else {
            mensajeAlerta("Error al crear cuenta", "no se han llenado correctamente los campos");
        }
    }

    /**
     * metodo para verificar espacio
     * @param nombre
     * @param apellido
     * @param cedula
     * @param telefono
     * @param email
     * @param contrasenia
     * @return
     */
    private boolean verificarEspacios(String nombre, String apellido, String cedula, String telefono, String email, String contrasenia) {
        if(nombre.isEmpty()){
            return false;
        }
        if(apellido.isEmpty()){
            return false;
        }
        if(cedula.isEmpty()){
            return false;
        }
        if(telefono.isEmpty()){
            return false;
        }
        if(email.isEmpty()){
            return false;
        }
        if(contrasenia.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * método para enviar un mensaje o comunicado al usuario
     * @param titulo
     * @param mensaje
     */
    public static void mensajeAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mensajeInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     *
     * @param application
     */
    public void setApplication(Application application){
        this.application = application;
    }

}