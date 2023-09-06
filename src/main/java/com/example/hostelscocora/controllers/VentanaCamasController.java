package com.example.hostelscocora.controllers;

import com.example.hostelscocora.CustomListCamas;
import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.model.*;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class  VentanaCamasController {

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
    private Cama camaSeleccionada;
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
        try {
            actualizarEstado(camaSeleccionada);
            MensajeUtil.mensajeInformacion("Éxito", "El estado de la cama fue actualizado correctamente");
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        application.mostrarVentanaAdministrar();
    }

    /**
     * ACTUALIZA EL ESTADO DE UNA CAMA
     */
    private void actualizarEstado(Cama cama) throws ValorRequeridoException {
        if (cama == null)
            throw new ValorRequeridoException("Es necesario que selecciona una cama");
        if (cbEstadoCama.getValue() == null)
            throw new ValorRequeridoException("El valor estado es requerido");

        cama.setEstadoCama(cbEstadoCama.getValue());
        listViewCamas.refresh();

        modelFactoryController.guardarResourceXmlService();
        modelFactoryController.guardarResourceSerializableService();
    }

    private Optional<Habitacion> habitacionActual(Cama cama) {
        Optional<DetalleReserva> detalleReservaHoy;

        List<Habitacion> listaHabitaciones = cama.getListaHabitaciones();
        for (Habitacion habitacion : listaHabitaciones) {
            List<DetalleReserva> detalleReservas = habitacion.getListaDetalleReserva();
            detalleReservaHoy = detalleReservas.stream().filter(x -> {
                LocalDate fechaInicial = x.getFecha().obtenerFechaInicio();
                LocalDate fechaFinal = x.getFecha().obtenerFechaFinal();
                LocalDate fechaHoy = LocalDate.now();
                return fechaHoy.isAfter(fechaInicial.minusDays(1)) && fechaHoy.isBefore(fechaFinal.plusDays(1));
            }).findFirst();
            if (detalleReservaHoy.isPresent()) {
                return Optional.of(habitacion);
            }
        }
        return Optional.empty();
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
                camaSeleccionada = newValue;
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

        Optional<Habitacion> habitacion = habitacionActual(cama);

        if (habitacion.isPresent())
            tfHabitacion.setText(habitacion.get().getId());
        else
            tfHabitacion.setText("No se encuentra en ninguna habitación");

        tfIdCamas.setText(cama.getId());
        cbEstadoCama.setValue(cama.getEstadoCama());
        tfTipoCama.setText(cama.getTipoCama().toString());
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
