package com.example.hostelscocora.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * SE ENCARGA DE LA LA FORMA QUE SE DISPONEN LOS ELEMENTOS DEL LISTVIEW DE LA VENTANA RESERVAS
 */
public class CustomListCell extends ListCell<Habitacion> {

    private final HBox content;
    private final ImageView imagen;
    private final Text idHabitacion;
    private final Text estadoHabitacion;
    private final ObservableList<DetalleReserva> listaReservasData = FXCollections.observableArrayList();
    private final ListView<DetalleReserva> listviewReservas;

    public CustomListCell() {
        super();
        imagen = new ImageView();
        idHabitacion = new Text();
        estadoHabitacion = new Text();
        VBox vBox = new VBox(idHabitacion, estadoHabitacion);
        listviewReservas = new ListView<>();
        listviewReservas.setMaxHeight(200);
        listviewReservas.setMaxWidth(200);
        listviewReservas.setCellFactory(new Callback<ListView<DetalleReserva>, ListCell<DetalleReserva>>() {
            @Override
            public ListCell call(ListView listView) {
                return new ListaReservas();
            }
        });
        content = new HBox(imagen, vBox, listviewReservas);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Habitacion habitacion, boolean empty) {
        super.updateItem(habitacion, empty);
        if (habitacion != null && !empty) {
            imagen.setImage(new Image(habitacion.getImagen(), 200, 200, true, true));
            listviewReservas.setPrefHeight(imagen.getFitHeight());
            idHabitacion.setText(habitacion.getId());
            estadoHabitacion.setText(habitacion.getEstadoHabitacion().toString().toLowerCase());
            listaReservasData.clear();
            listaReservasData.addAll(habitacion.getListaDetalleReserva());
            listviewReservas.setItems(listaReservasData);
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }

    /**
     * SE ENCARGA DE LA FORMA DE LA VISUALIZACION DEL LISTVIEW DE FECHAS DE RESERVAS
     */
    public static class ListaReservas extends ListCell<DetalleReserva> {
        private final Text fecha;

        public ListaReservas() {
            fecha = new Text();
        }

        @Override
        protected void updateItem(DetalleReserva detalleReserva, boolean empty) {
            super.updateItem(detalleReserva, empty);
            if (detalleReserva != null && !empty) {
                String fechaInicio = detalleReserva.getFecha().getFechaInicio();
                String fechaFinal = detalleReserva.getFecha().getFechaFinal();
                fecha.setText(String.format("%s : %s", fechaInicio, fechaFinal));
                setGraphic(fecha);
            } else {
                setGraphic(null);
            }
        }
    }
}