package com.example.hostelscocora.util;

import javafx.scene.control.Alert;

public class MensajeUtil {

    /**
     * METODOS QUE PROPORCIONAN AYUDA A LA HORA DE MOSTRAR ALERTAS ENTRE OTROS
     * @param mensaje .
     * @param titulo .
     */
    public static void mensajeInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Éxito");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mensajeAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
