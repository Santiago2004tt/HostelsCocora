module com.example.hostelscocora {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hostelscocora to javafx.fxml;
    exports com.example.hostelscocora;
    exports com.example.hostelscocora.controllers;
    opens com.example.hostelscocora.controllers to javafx.fxml;
}