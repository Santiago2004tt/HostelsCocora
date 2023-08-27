package com.example.hostelscocora.model;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * CLASE QUE SE ENCARGA DE DARLE FORMA A LOS ELEMENTOS DEL LISTVIEW DE HABITACIONES
 */
public class CeldaHabitacion extends ListCell<Habitacion> {

    private final HBox contente;
    private final ImageView imagen;
    private final Text idHabitacion;
    private final Text tipoHabitacion;
    private final Text estadoHabitacion;

    public CeldaHabitacion() {
        imagen = new ImageView();
        idHabitacion = new Text();
        tipoHabitacion = new Text();
        estadoHabitacion = new Text();
        VBox vBox = new VBox(idHabitacion, tipoHabitacion, estadoHabitacion);
        contente = new HBox(imagen, vBox);
        contente.setSpacing(10);
    }

    @Override
    protected void updateItem(Habitacion habitacion, boolean empty) {
        super.updateItem(habitacion, empty);
        if (habitacion != null && !empty) {
            imagen.setImage(new Image(habitacion.getImagen(), 100, 100, true, true));
            idHabitacion.setText("N° " + habitacion.getId());
            tipoHabitacion.setText(habitacion.getTipoHabitacion().toString());
            estadoHabitacion.setText(habitacion.getEstadoHabitacion().toString());
            setGraphic(contente);
        } else {
            setGraphic(null);
        }
    }
}