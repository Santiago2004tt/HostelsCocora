package com.example.hostelscocora.controllers;

import com.example.hostelscocora.aplication.Application;
import com.example.hostelscocora.exceptions.ClienteException;
import com.example.hostelscocora.exceptions.ValorRequeridoException;
import com.example.hostelscocora.model.*;
import com.example.hostelscocora.util.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class VentanaGenerarReservasController {

    @FXML
    private Button btnAgregarReserva;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnCrearCliente;

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
    private Label lblTotal;

    @FXML
    private ListView<Habitacion> listViewHabitaciones;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfCantidadPersonas;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNombre;

    /**
     * ATRIBUTOS
     */
    private Application application;
    private List<String> historial;
    private final ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    private final ObservableList<Habitacion> listaHabitacionesData = FXCollections.observableArrayList();
    private final Hotel hotel = modelFactoryController.getHotel();
    private Cliente cliente = null;
    private Habitacion habitacionSeleccionada = null;
    private Reserva reserva = null;

    /**
     * DEVUELVE UNA LISTA DE HABITACIONES FILTRADA EN FECHA INICIO Y FECHA FINAL
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    private ObservableList<Habitacion> getListaHabitacionesData(LocalDate fechaInicial, LocalDate fechaFinal) {
        listaHabitacionesData.clear();
        listaHabitacionesData.addAll(filtrarHabitacionesFecha(fechaInicial, fechaFinal));
        listaHabitacionesData.removeAll();
        if (cbTipoHabitacion.getValue() != null) {
            filtrarHabitacionesTipoHabitacion();
        }
        return listaHabitacionesData;
    }

    private List<Habitacion> filtrarHabitacionesFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
        return (modelFactoryController.obtenerHabitaciones().stream().filter( x -> {
            for (DetalleReserva detalleReserva : x.getListaDetalleReserva()) {
                Fecha fecha = detalleReserva.getFecha();

                LocalDate fechIni = fecha.obtenerFechaInicio();
                LocalDate fechFin = fecha.obtenerFechaFinal();

                if (fechaInicial.isAfter(fechIni.minusDays(1)) && fechaInicial.isBefore(fechFin.plusDays(1)) ||
                        fechaFinal.isAfter(fechIni.minusDays(1)) && fechaFinal.isBefore(fechFin.plusDays(1))) {
                    return false;
                }
            }
            return true;
        }).toList());
    }

    private void filtrarHabitacionesTipoHabitacion() {
        List<Habitacion> lista = listaHabitacionesData.stream()
                .filter(x -> x.getTipoHabitacion() == cbTipoHabitacion.getValue()).toList();
        listaHabitacionesData.clear();
        listaHabitacionesData.addAll(lista);
    }

    /**
     * ACTIONS DE LOS BOTONES
     * @param event
     */
    @FXML
    void agregarReservaAction(ActionEvent event) {
        try {
            agregarReserva();
            MensajeUtil.mensajeInformacion("Éxito", "Se agrego correctamente a la reserva");
        } catch (Exception e) {
            MensajeUtil.mensajeAlerta("Alerta", e.getMessage());
        }
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
        historial.add("ventana-generar-reservas");
        application.mostrarCrearCliente(historial);
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

    @FXML
    void regresarAction(ActionEvent event) {
        int ultimo = historial.size() - 1;
        if (historial.get(ultimo).equals("ventana-reservas")) {
            historial.remove(ultimo);
            application.mostrarVentanaReservas(historial);
        } else if (historial.get(ultimo).equals("ventana-administrar")) {
            historial.remove(ultimo);
            application.mostrarVentanaAdministrar();
        }
    }

    /**
     * METODOS PARA QUE FUNCIONEN LOS ACTIONS
     * @throws ValorRequeridoException
     */
    private void agregarReserva() throws Exception {
        if (habitacionSeleccionada == null)
            throw new ValorRequeridoException("Seleccione una habitación");
        if (cliente == null)
            throw new ValorRequeridoException("Es necesario que busque el cliente");
        if (dpFechaInicial.getValue() == null)
            throw new ValorRequeridoException("El valor fecha inicio es requerido");
        if (dpFechaFinal.getValue() == null)
            throw new ValorRequeridoException("El valor fecha final es requerido");
        if (cbCamaAdicional.getValue() == null)
            throw new ValorRequeridoException("El campo cama extra es requerido");
        if (tfCantidadPersonas.getText().isEmpty())
            throw new ValorRequeridoException("El campo cantidad de personas es requerido");

        Fecha fechaNueva = new Fecha(dpFechaInicial.getValue().toString(), dpFechaFinal.getValue().toString());

        boolean isCamaExtra  = cbCamaAdicional.getValue().equals("Sí");

        if (!modelFactoryController.verificarCamasDisponibles(isCamaExtra, habitacionSeleccionada.getCapacidad(), fechaNueva))
            throw new Exception("No hay camas disponibles");


        if (reserva == null) {
            reserva = hotel.crearReserva();
            reserva.setCliente(cliente);
            hotel.getListaReserva().add(reserva);
            cliente.getListaReserva().add(reserva);
        }

        modelFactoryController.aniadirCamas(habitacionSeleccionada, isCamaExtra, fechaNueva);
        double subTotal = calcularSubtotal(habitacionSeleccionada, isCamaExtra, dpFechaInicial.getValue(), dpFechaFinal.getValue());
        reserva.setCantidadPersonas(Integer.parseInt(tfCantidadPersonas.getText()));
        DetalleReserva detalleReserva = reserva.crearDetalleReserva(subTotal, isCamaExtra, fechaNueva);
        detalleReserva.setHabitacion(habitacionSeleccionada);
        habitacionSeleccionada.getListaDetalleReserva().add(detalleReserva);

        listViewHabitaciones.getSelectionModel().clearSelection();
        listaHabitacionesData.remove(habitacionSeleccionada);

        habitacionSeleccionada = null;

        modelFactoryController.guardarResourceXmlService();
        modelFactoryController.guardarResourceSerializableService();

        lblTotal.setText("Total: " + reserva.obtenerTotal());
        reserva.setTotal(reserva.obtenerTotal());
    }

    /**
     * CALCULA EL SUBTOTAL QUE DEBE TENER UN DETALLE RESERVA
     * @param habitacion
     * @param isCamaExtra
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    private Double calcularSubtotal(Habitacion habitacion, boolean isCamaExtra, LocalDate fechaInicial, LocalDate fechaFinal) {
        int cantidadDias = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        double subTotal = 50000;

        if (habitacion.getTipoHabitacion() == TIPO_HABITACION.HABITACION_SIMPLE)
            subTotal += 50000;

        if (isCamaExtra)
            subTotal += 25000;

        return subTotal * cantidadDias;
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

        reserva = null;
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
        cbCamaAdicional.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                cbCamaAdicional.setPromptText("Seleccionar");
            }
        });


        cbTipoHabitacion.setItems(FXCollections.observableArrayList(TIPO_HABITACION.values()));
        cbTipoHabitacion.setPromptText("Seleccionar");


        listViewHabitaciones.setItems(listaHabitacionesData);
        listViewHabitaciones.setCellFactory(new Callback<ListView<Habitacion>, ListCell<Habitacion>>() {
            @Override
            public ListCell<Habitacion> call(ListView<Habitacion> habitacionListView) {
                return new CeldaHabitacion();
            }
        });

        listViewHabitaciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                habitacionSeleccionada = newValue;
            }
        });

        dpFechaInicial.setDayCellFactory(dayCellFactoryInicial);
        dpFechaFinal.setDayCellFactory(dayCellFactoryFinal);

    }

    Callback<DatePicker, DateCell> dayCellFactoryInicial = dp -> new DateCell() {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
            if (dpFechaFinal.getValue() != null && item.isAfter(dpFechaFinal.getValue()) || item.isBefore(LocalDate.now())) {
                this.setDisable(true);
            }
        }
    };

    Callback<DatePicker, DateCell> dayCellFactoryFinal = dp -> new DateCell() {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
            if (dpFechaInicial.getValue() != null && item.isBefore(dpFechaInicial.getValue()) || item.isBefore(LocalDate.now())) {
                this.setDisable(true);
            }
        }
    };

    public void setApplication(Application application, List<String> historial) {
        this.application = application;
        this.historial = historial;
    }

}
