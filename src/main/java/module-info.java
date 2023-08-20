module com.example.hostelscocora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    exports com.example.hostelscocora.controllers;
    opens com.example.hostelscocora.controllers to javafx.fxml;
    exports com.example.hostelscocora.aplication;
    opens com.example.hostelscocora.aplication to javafx.fxml;
}