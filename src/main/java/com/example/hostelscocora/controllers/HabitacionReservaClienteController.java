package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class HabitacionReservaClienteController {

    ModelFactoryController modelFactoryController;
    Cliente clienteLogueado;
    Application application;
    Habitacion habitacionSeleccionada;
    Fecha fechaNueva;
    Reserva reserva;
    ObservableList<Habitacion> listaHabitaciones = FXCollections.observableArrayList();

    @FXML
    private DatePicker dpFechaFinal;

    @FXML
    private TextField tfCantidadPersonas;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Habitacion, TIPO_HABITACION> colTipoHabitacion;

    @FXML
    private ComboBox<String> cbCamaExtra;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private TableView<Habitacion> tblHabitacion;

    @FXML
    private TableColumn<Habitacion, String > colId;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Button btnBuscar;

    @FXML
    void accederPerfilCliente(ActionEvent event) {
        accederPerfilClienteAction();
    }

    private void accederPerfilClienteAction() {
       if(reserva!=null){
           double total = obtenerTotal();
           reserva.setTotal(total);
           clienteLogueado.getListaReserva().add(reserva);
           modelFactoryController.guardarResourceSerializableService();
           modelFactoryController.guardarResourceXmlService();
       }
        application.mostrarPerfilCliente(clienteLogueado);
    }

    private double obtenerTotal() {
        return reserva.obtenerTotal();
    }

    @FXML
    void buscarHabitacionesDisponibles(ActionEvent event) {
        buscarHabitacionesDisponiblesAction();
    }

    private void buscarHabitacionesDisponiblesAction() {
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFinal = dpFechaFinal.getValue();

        if(verificarEspacios(fechaInicio, fechaFinal)){
            if(fechaInicio.isBefore(fechaFinal)){
                LocalDate fechaHoy = LocalDate.from(LocalDateTime.now());
                fechaHoy=fechaHoy.minusDays(1);
                if(fechaHoy.isBefore(fechaInicio)){
                    fechaNueva = new Fecha();
                    fechaNueva.crearFecha(fechaInicio, fechaFinal);
                    ArrayList<Habitacion> listaHabitaciones = modelFactoryController.obtenerHabitacionesFiltroFecha(fechaNueva);
                    this.listaHabitaciones.clear();
                    this.listaHabitaciones.addAll(listaHabitaciones);
                    tblHabitacion.setItems(this.listaHabitaciones);
                    tblHabitacion.refresh();

                }else{
                    mensajeAlerta("Error al buscar", "El dia seleccionado ya paso");
                }
            }else {
                mensajeAlerta("Error al buscar", "El dia inicial es mayor que el dia final");
            }
        }
    }

    private boolean verificarEspacios(LocalDate fechaInicio, LocalDate fechaFinal) {
        if(fechaInicio == null){
            return false;
        }
        if(fechaFinal == null){
            return false;
        }
        return true;
    }

    @FXML
    void crearReserva(ActionEvent event) {
        crearReservaAction();
    }

    private void crearReservaAction() {
        String cantidadPersonas = tfCantidadPersonas.getText();
        double subTotal = 0;
        boolean isCamaExtra = false;
        if(verificarDatos(cantidadPersonas)){
            if(cbCamaExtra.getValue().equals("Si")){
                isCamaExtra = true;
            }
            if(modelFactoryController.verificarCamasDisponibles(isCamaExtra, habitacionSeleccionada.getCapacidad(), fechaNueva)){
                if(reserva == null){
                    reserva = modelFactoryController.crearReserva();
                }
                modelFactoryController.aniadirCamas(habitacionSeleccionada, isCamaExtra, fechaNueva); //se añade la cama
                subTotal =calcularPrecios(habitacionSeleccionada, isCamaExtra);
                reserva.setCantidadPersonas(Integer.parseInt(cantidadPersonas));
                DetalleReserva detalleReserva = reserva.crearDetalleReserva(subTotal, isCamaExtra, fechaNueva);
                detalleReserva.setHabitacion(habitacionSeleccionada);
                habitacionSeleccionada.getListaDetalleReserva().add(detalleReserva);
                buscarHabitacionesDisponiblesAction();
                mensajeInfo("Se completo la reserva", "Se completo la reserva, puedes agregar mas habitaciones");

            }else {
                mensajeAlerta("Error en reserva", "no hay camas disponibles");
            }
        }
    }

    private double calcularPrecios(Habitacion habitacionSeleccionada, boolean isCamaExtra) {
        long diferenciaEnDias = ChronoUnit.DAYS.between(dpFechaInicio.getValue(), dpFechaFinal.getValue());
        int diferenciaEnDiasInt = (int) diferenciaEnDias;
        double subTotal=50000 ;

        if(habitacionSeleccionada.getTipoHabitacion().equals(TIPO_HABITACION.HABITACION_DOBLE)){
            subTotal += 50000;
        }
        if(isCamaExtra){
            subTotal+=25000;
        }

        return subTotal * diferenciaEnDiasInt;
    }

    private boolean verificarDatos(String cantidadPersonas) {
        if(isNumero(cantidadPersonas)){
            mensajeAlerta("Error al crear la reserva", "el campo de la cantidad de personas no es un numero");
            return false;
        }
        if(cbCamaExtra.getValue()==null){
            mensajeAlerta("Error al crear la reserva", "El campo de camas esta vació");
            return false;
        } 
        if(habitacionSeleccionada == null){
            mensajeAlerta("Error al crear la reserva", "La habitación no se a seleccionado");
            return false;
        }
        return true;
    }

    private boolean isNumero(String cantidadPersonas) {
        try {
            int aux = Integer.parseInt(cantidadPersonas);
        }
        catch (NumberFormatException e){
            return true;
        }
        return false;
    }

    @FXML
    void initialize() {
        this.modelFactoryController = ModelFactoryController.getInstance();
        cbCamaExtra.getItems().addAll("Si", "No");
        inicializarTabla();
    }

    private void inicializarTabla() {
        this.colTipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tblHabitacion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            habitacionSeleccionada= newSelection;
        });
    }

    public void setApplication(Application application, Cliente clienteLogueado) {
        this.application = application;
        this.clienteLogueado = clienteLogueado;
    }

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

}
