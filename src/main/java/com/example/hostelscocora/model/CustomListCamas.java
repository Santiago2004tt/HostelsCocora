package com.example.hostelscocora.model;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * SE ENCARGA DE LA LA FORMA EN QUE SE DISPONEN LOS ELEMENTOS DEL LISTVIEW DE LA GESTIONAR CAMAS
 */
public class CustomListCamas extends ListCell<Cama> {

    private final HBox content;
    private final ImageView imagen;
    private final Text id;

    public CustomListCamas() {
        super();
        imagen = new ImageView();
        id = new Text();
        content = new HBox(imagen, id);
        content.setSpacing(50);
    }

    @Override
    protected void updateItem(Cama cama, boolean empty) {
        super.updateItem(cama, empty);
        if (cama != null && !empty) {
            imagen.setImage(new Image(cama.getImagen(), 150, 150, true, true));
            id.setText("ID: " + cama.getId());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
