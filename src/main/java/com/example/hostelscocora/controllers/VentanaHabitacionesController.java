package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.model.CeldaHabitacion;
import com.example.hostelscocora.model.ESTADO_HABITACION;
import com.example.hostelscocora.model.Habitacion;
import com.example.hostelscocora.util.MensajeUtil;
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

import javax.swing.event.MenuDragMouseEvent;

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

    private Application application;
    private Habitacion habitacionSeleccionada;
    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private final ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
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
        try {
            actualizaEstado();
            MensajeUtil.mensajeInformacion("Éxito", "Se actualizo correctamente el estado");
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        application.mostrarVentanaAdministrar();
    }

    /**
     * METODOS QUE LE DAN FUNCIONALIDAD A LOS ACTION
     */
    private void actualizaEstado() throws ValorRequeridoException {
        if (habitacionSeleccionada == null)
            throw new ValorRequeridoException("Es necesario que selecciona una habitación");
        if (cbEstadoHabitacion.getValue() == null)
            throw new ValorRequeridoException("El valor estado es requerido");

        habitacionSeleccionada.setEstadoHabitacion(cbEstadoHabitacion.getValue());
        listViewHabitaciones.refresh();
        modelFactoryController.guardarResourceXmlService();
        modelFactoryController.guardarResourceSerializableService();
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

        listViewHabitaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                habitacionSeleccionada = newValue;
                llenarCampos(newValue);
            }
        });
    }

    private void llenarCampos(Habitacion habitacion) {
        tfIdHabitacion.clear();
        tfTipoHabitacion.clear();
        tfCantidadReservas.clear();

        tfIdHabitacion.setText(habitacion.getId());
        cbEstadoHabitacion.setValue(habitacion.getEstadoHabitacion());
        tfTipoHabitacion.setText(habitacion.getTipoHabitacion().toString());
        tfCantidadReservas.setText(String.valueOf(habitacion.getListaDetalleReserva().size()));
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
