package com.example.hostelscocora.aplication;

import com.example.hostelscocora.controllers.*;
import com.example.hostelscocora.model.Cliente;
import com.example.hostelscocora.model.Reserva;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
//        mostrarVentanaClientelogin();
        mostrarVentanaLoginRecepcionista();
    }

    /**
     * Activa la ventana de login cliente
     */
    public void mostrarVentanaClientelogin() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/login-cliente-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            LoginClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * funcion crea la cuenta del cliente
     */
    public void mostrarVentanaCrearCuentaCliente() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/crear-cuenta-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            CrearCuentaClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ventana para mostrar perfil de los clientes
     *
     * @param cliente
     */
    public void mostrarPerfilCliente(Cliente cliente) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/perfil-cliente-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            PerfilClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, cliente);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * muestra las reservaciones
     *
     * @param cliente
     */
    public void mostrarReservacionesCliente(Cliente cliente) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/reservaciones-cliente-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            ReservacionesClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, cliente);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * muestra los detalles de cada reservacion
     *
     * @param cliente
     * @param reserva
     */
    public void mostrarDetallesReservacionesCliente(Cliente cliente, Reserva reserva) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/detalle-reserva-cliente-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            DetalleReservaClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, cliente, reserva);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarHabitacionReservaCliente(Cliente cliente) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/habitacion-reserva-cliente-view.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            HabitacionReservaClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, cliente);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarVentanaLoginRecepcionista() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-login-recepcionista.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaLoginRecepcionista controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarVentanaAdministrar() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-administrar.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaAdministrarController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarVentanaReservas() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-reservas.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaReservasController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarGenerarReservas(String ventanaAnterior) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-generar-reservas.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaGenerarReservasController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, ventanaAnterior);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearCliente(String ventanaAnterior) {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-crear-cliente.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            CrearClienteController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this, ventanaAnterior);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarVentanaHabitaciones() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-habitaciones.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaHabitacionesController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarVentanaCamas() {
        try {
            stage.close();
            stage = new Stage();
            //Loader lee cada línea de código y la vuelve objetos en memoria
            FXMLLoader loader = new FXMLLoader();
            //Dirección del paquete donde está la interfaz
            loader.setLocation(Application.class.getResource("/views/ventana-camas.fxml"));
            AnchorPane rootLayout = loader.load();
            //Carga los controladores
            VentanaCamasController controller = loader.getController();//Obtenemos el controlador
            controller.setApplication(this);
            Scene scene = new Scene(rootLayout);//Carga la escena Principal. En este caso carga el anchor-pane
            // de cambiar de ventana con escape
            stage.setScene(scene);//Al escenario principal se le dice que cargue la escena
            stage.show();//Muestra el escenario principal
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}