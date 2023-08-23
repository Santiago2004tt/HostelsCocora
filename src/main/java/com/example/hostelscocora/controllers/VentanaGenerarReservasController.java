package com.example.hostelscocora.controllers;

import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.model.*;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class VentanaGenerarReservasController {

    @FXML
    private Button btnAgregarReserva;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<String> cbCamaAdicional;

    @FXML
    private ComboBox<TIPO_HABITACION> cbTipoHabitacion;

    @FXML
    private DatePicker dpFechaFinal;

    @FXML
    private DatePicker dpFechaInicial;

    @FXML
    private ListView<Habitacion> listViewHabitaciones;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNombre;

    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
    Cliente cliente = null;
    Habitacion habitacionSeleccionada = null;
    Reserva reserva = null;

    /**
     * DEVUELVE UNA LISTA DE HABITACIONES FILTRADA EN FECHA INICIO Y FECHA FINAL
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    private ObservableList<Habitacion> getListaHabitacionesData(LocalDate fechaInicial, LocalDate fechaFinal) {
        listaHabitacionesData.clear();
        listaHabitacionesData.addAll(modelFactoryController.obtenerHabitaciones().stream().filter( x -> {
            for (DetalleReserva detalleReserva : x.getListaDetalleReserva()) {
                Fecha fecha = detalleReserva.getFecha();

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate fechIni = LocalDate.parse(fecha.getFechaInicio(), dateTimeFormatter);
                LocalDate fechFin = LocalDate.parse(fecha.getFechaFinal(), dateTimeFormatter);

                if (fechaInicial.isAfter(fechIni.minusDays(1)) && fechaInicial.isBefore(fechFin.plusDays(1)) ||
                        fechaFinal.isAfter(fechIni.minusDays(1)) && fechaFinal.isBefore(fechFin.plusDays(1))) {
                    return false;
                }
            }
            return true;
        }).toList());
        return listaHabitacionesData;
    }

    @FXML
    void agregarReservaAction(ActionEvent event) {

    }

    @FXML
    void crearReservaAction(ActionEvent event) {

    }

    @FXML
    void buscarClienteAction(ActionEvent event) {
        try {
            buscarClientePorCedula(tfCedula.getText());
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        } catch (ClienteException e) {
            MensajeUtil.mensajeAlerta("Cliente no encontrado", e.getMessage());
        }
    }
    @FXML
    void cedulaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            btnBuscarCliente.fire();
        }
    }

    @FXML
    void crearClienteAction(ActionEvent event) {

    }

    @FXML
    void filtrarAction(ActionEvent event) {
        try {
            filtrarHabitaciones();
            habitacionSeleccionada = null;
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
    }

    // ESTA FUNCION NO SE ENCUENTRA FINALIZADA
    private void agregarReserva() throws ValorRequeridoException {
        if (habitacionSeleccionada == null)
            throw new ValorRequeridoException("Seleccione una habitación");
        if (dpFechaInicial.getValue() == null)
            throw new ValorRequeridoException("El valor fecha inicio es requerido");
        if (dpFechaFinal.getValue() == null)
            throw new ValorRequeridoException("El valor fecha final es requerido");
        if (cbCamaAdicional.getValue() == null)
            throw new ValorRequeridoException("El valor cama extra es requerido");
        if (cliente == null)
            throw new ValorRequeridoException("Es necesario que busque el cliente");
        if (reserva == null) {
            reserva = new Reserva();
            reserva.setCliente(cliente);
        }
        DetalleReserva detalleReserva = new DetalleReserva();
    }

    /**
     * BUSCA UN CLIENTE EN BASE A LA CEDULA PASADA
     * @param cedula
     * @throws ValorRequeridoException
     * @throws ClienteException
     */
    private void buscarClientePorCedula(String cedula) throws ValorRequeridoException, ClienteException {
        if (cedula.isEmpty())
            throw new ValorRequeridoException("El campo cédula es requerido");

        Optional<Cliente> clienteOptional = modelFactoryController.obtenerClientes().stream().filter(x -> x.getCedula().equals(cedula)).findFirst();

        if (clienteOptional.isEmpty()) {
            throw new ClienteException("El cliente no fue encontrado");
        }

        cliente = clienteOptional.get();

        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
        tfEmail.clear();

        tfNombre.setText(cliente.getNombre());
        tfApellido.setText(cliente.getApellido());
        tfTelefono.setText(cliente.getTelefono());
        tfEmail.setText(cliente.getEmail());
    }

    /**
     * FILTRA LAS HABITACIONES TENIENDO EN CUENTA LA FECHA INICIO Y FECHA FINAL
     * @throws ValorRequeridoException
     */
    private void filtrarHabitaciones() throws ValorRequeridoException {
        if (dpFechaInicial.getValue() == null)
            throw new ValorRequeridoException("La fecha inicial es requerida");
        if (dpFechaFinal.getValue() == null)
            throw new ValorRequeridoException("La fecha final es requerida");
        getListaHabitacionesData(dpFechaInicial.getValue(), dpFechaFinal.getValue());
    }

    @FXML
    void initialize() {

        cbCamaAdicional.setItems(FXCollections.observableArrayList("Sí", "No"));
        cbCamaAdicional.setPromptText("Seleccionar");

        cbTipoHabitacion.setItems(FXCollections.observableArrayList(TIPO_HABITACION.values()));
        cbTipoHabitacion.setPromptText("Seleccionar");


        listViewHabitaciones.setItems(listaHabitacionesData);
        listViewHabitaciones.setCellFactory(new Callback<ListView<Habitacion>, ListCell<Habitacion>>() {
            @Override
            public ListCell<Habitacion> call(ListView<Habitacion> habitacionListView) {
                return new celdaReservaHabitacion();
            }
        });

        listViewHabitaciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                habitacionSeleccionada = newValue;
            }
        });

    }

    /**
     * CLASE QUE SE ENCARGA DE DARLE FORMA A LOS ELEMENTOS DEL LISTVIEW DE HABITACIONES
     */
    private class celdaReservaHabitacion extends ListCell<Habitacion> {

        private HBox contente;
        private ImageView imagen;
        private Text tipoHabitacion;

        private celdaReservaHabitacion() {
            imagen = new ImageView();
            tipoHabitacion = new Text();
            contente = new HBox(imagen, tipoHabitacion);
            contente.setSpacing(10);
        }

        @Override
        protected void updateItem(Habitacion habitacion, boolean empty) {
            super.updateItem(habitacion, empty);
            if (habitacion != null && !empty) {
                imagen.setImage(new Image(habitacion.getImagen(), 100, 100, true, true));
                tipoHabitacion.setText(habitacion.getTipoHabitacion().toString());
                setGraphic(contente);
            } else {
                setGraphic(null);
            }
        }
    }

}
