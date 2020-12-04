/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.aplicacion.App;
import com.espol.model.datos.Comprador;
import com.espol.model.datos.Usuario;
import com.espol.model.utilidades.Buscar;
import com.espol.model.utilidades.DatosLogin;
import com.espol.model.utilidades.Validar;
import com.espol.model.utilidades.Venta;
import com.espol.model.vehiculos.Vehiculo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VistaMenuCompradorController implements Initializable {

    @FXML
    private ComboBox comoBoxTipoUsuario;
    @FXML
    private ComboBox comboBoxTipoVehiculo;
    @FXML
    private Button bttBuscar;
    @FXML
    private TableView tablaVehiculo;
    @FXML
    private Button bttCerrarSesion;
    @FXML
    private Pane panelComprador;
    @FXML
    private Button bttOfertar;
    @FXML
    private Pane panelOfertar;
    @FXML
    private TextField textRecorridomax;
    @FXML
    private TextField textRecorridomin;
    @FXML
    private TextField textAñomax;
    @FXML
    private TextField textAñomin;
    @FXML
    private TextField textPreciomax;
    @FXML
    private TextField textPreciomin;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textUsuario;
    @FXML
    private ToolBar barComprador;
    @FXML
    private Button bttPerfilComprador;
    @FXML
    private Pane panelPerfilComprador;
    @FXML
    private TableColumn columTipoVehiculo;
    @FXML
    private TableColumn columRecorrido;
    @FXML
    private TableColumn columAño;
    @FXML
    private TableColumn columPrecio;
    @FXML
    private TableColumn columPlaca;
    @FXML
    private TableColumn columTipoMarca;
    @FXML
    private TableColumn columTipoModelo;
    @FXML
    private TableColumn columTipoMotor;
    @FXML
    private TableColumn columColor;
    @FXML
    private TableColumn columTipoCombustible;
    @FXML
    private TableColumn columTransmmision;
    @FXML
    private ComboBox comboxOrdenarTabla;
    @FXML
    private ImageView ImgCarro;
    @FXML
    private Button bttRealizarOferta;
    @FXML
    private TextField textConOferta;

    private Usuario user;
    private Vehiculo vehiculo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Venta> ventas;
    private ArrayList<Vehiculo> listaVehiculos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columTipoVehiculo.setCellValueFactory(new PropertyValueFactory("tipo"));
        columPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
        columTipoMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        columTipoModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        columTipoMotor.setCellValueFactory(new PropertyValueFactory("tipoMotor"));
        columAño.setCellValueFactory(new PropertyValueFactory("año"));
        columRecorrido.setCellValueFactory(new PropertyValueFactory("recorrido"));
        columColor.setCellValueFactory(new PropertyValueFactory("color"));
        columTipoCombustible.setCellValueFactory(new PropertyValueFactory("tipoCombustible"));
        columTransmmision.setCellValueFactory(new PropertyValueFactory("transmision"));
        columPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        textAñomin.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textAñomax.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textRecorridomin.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textRecorridomax.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textPreciomax.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textPreciomin.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textConOferta.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        ArrayList<Usuario> usuario = DatosLogin.obtenerUsuario();
        if ((usuario.get(0) instanceof Comprador)) {
            comoBoxTipoUsuario.getItems().clear();
            comoBoxTipoUsuario.getItems().add("Comprador");
            comboxOrdenarTabla.getItems().addAll("Precio", "Año");
            comboBoxTipoVehiculo.getItems().addAll("Auto", "Camion", "Camioneta",
                    "Motocicleta", "Todos");
            comboBoxTipoVehiculo.getSelectionModel().select("Todos");
            user = usuario.get(0);
            panelComprador.setVisible(true);
        }
        if ((usuario.get(1) instanceof Comprador)) {//si el usuario es comprador procede a mostrar las obtiones de vehiculos que hay 
            comoBoxTipoUsuario.getItems().clear();
            comoBoxTipoUsuario.getItems().add("Comprador");
            comboxOrdenarTabla.getItems().addAll("Precio", "Año");//agrega a la tabla para que ordene por precio o por anio
            comboBoxTipoVehiculo.getItems().addAll("Auto", "Camion", "Camioneta",
                    "Motocicleta", "Todos");//ingresa los tipos de vehiculos que existen
            comboBoxTipoVehiculo.getSelectionModel().select("Todos");
            user = usuario.get(1);
            panelComprador.setVisible(true);//se muestra el panel de comprador
        }

        if (usuario.size() == 2) {
            comoBoxTipoUsuario.getItems().clear();
            comoBoxTipoUsuario.getItems().addAll("Comprador", "Vendedor");
            comboBoxTipoVehiculo.getItems().clear();
            comboBoxTipoVehiculo.getItems().addAll("Auto", "Camion", "Camioneta",
                    "Motocicleta", "Todos");
            comboBoxTipoVehiculo.getSelectionModel().select("Todos");
            panelComprador.setVisible(true);
        }
    }

    @FXML
    private void buscarVehiculo(ActionEvent event) throws NoSuchAlgorithmException {//busca el vehiculo
        usuarios = Usuario.deserealizarUsuarios("usuarios.ser");
        vehiculos = Vehiculo.deserealizarVehiculos("vehiculos.ser");
        ventas = Venta.desearilzarVentas("venta.ser");
        Venta.vincular(usuarios, ventas, vehiculos);//vincula los tres archivos
        String tipoVehiculo = comboBoxTipoVehiculo.getSelectionModel().getSelectedItem().toString();
        double recorridoMax = Double.parseDouble(Validar.nullA0(textRecorridomax.getText()));//obtiene el recorrido maximo ingresado por el comprador
        double recorridoMin = Double.parseDouble(Validar.nullA0(textPreciomin.getText()));//obtiene el recorrido minimo ingresado por el comprador
        int añoMax = Integer.parseInt(Validar.nullA0(textAñomax.getText()));//obtiene el anio maximo ingresado por el comprador
        int añoMin = Integer.parseInt(Validar.nullA0(textAñomin.getText()));//obtiene el anio minimo ingresado por el comprador
        double precioMax = Double.parseDouble(Validar.nullA0(textPreciomax.getText()));//obtiene el precio maximo ingresado por el comprador
        double precioMin = Double.parseDouble(Validar.nullA0(textPreciomin.getText()));//obtiene el precio minimo ingresado por el comprador
        listaVehiculos = Buscar.buscar_vehiculo(ventas, tipoVehiculo,
                recorridoMin, recorridoMax, añoMin, añoMax, precioMin, precioMax);//busca el vehiculo segun los parametros ingresados por el comprador
        if (listaVehiculos.size() == 0) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("No hay vehiculos disponibles");
            alerta.show();
        } else {
            ObservableList v = FXCollections.observableArrayList(listaVehiculos);
            tablaVehiculo.getItems().clear();
            tablaVehiculo.setItems(v);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        DatosLogin.borrar();
        App.setRoot("vistaLogin");//manda a la vistaLogin
    }

    @FXML
    private void mostarPanelOfertar(ActionEvent event) {//muestra el panel oferta 
        bttOfertar.setStyle("-fx-background-color : #00a8e8");
        bttPerfilComprador.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        panelPerfilComprador.setVisible(false);
        panelOfertar.setVisible(true);
    }

    @FXML
    private void boxTipoUsuario(ActionEvent event) throws IOException {//dependiendo del tipo de usuario que eligan
        ComboBox cb = (ComboBox) event.getSource();//obtiene el tipo de usuario elegido
        String opcion = (String) cb.getValue();//procede a guardar el tipo de usuario obtenido anteriormente
        if (opcion.equals("Comprador")) {//si es comprador se procede a habilitar el panel comprador
            panelComprador.setVisible(true);
        } else {
            App.setRoot("vistaMenuVendedor");// en caso de ser vendedor muestra el panel vendedor

        }
    }

    @FXML
    private void mostrarPanelPerfil(ActionEvent event) {//muestra los datos ingresador por el usuario comprador al registrarse exceptuando la clave
        bttPerfilComprador.setStyle("-fx-background-color : #00a8e8");
        bttOfertar.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        ArrayList<Usuario> usuario = DatosLogin.obtenerUsuario();
        Usuario datos = usuario.get(0);
        textNombre.setText(datos.getNombre());
        textApellido.setText(datos.getApellido());
        textCorreo.setText(datos.getCorreoE());
        textUsuario.setText(datos.getUsuario());
        panelOfertar.setVisible(false);
        panelPerfilComprador.setVisible(true);
    }

    @FXML
    private void ordenarTabla(ActionEvent event) {//ordena la tabla por el precio o anio segun lo elegido por el comprador
        ComboBox cb = (ComboBox) event.getSource();
        String dato = (String) cb.getValue();
        if (dato.equals("Precio")) {
            tablaVehiculo.getSortOrder().add(columPrecio);
        } else {
            tablaVehiculo.getSortOrder().add(columAño);
        }

    }

    @FXML
    private void realizarOferta(ActionEvent event) {//comprador procede a realizar oferta
        try {
            Comprador comprador = (Comprador) user;
            Venta venta = Buscar.buscarVenta(vehiculo, ventas);//busca el vehiculo y el valor
            comprador.realizarOferta(venta, Double.parseDouble(textConOferta.getText()), "ofertas.ser");//oferta un valor 
            usuarios = Usuario.deserealizarUsuarios("usuarios.ser");
            vehiculos = Vehiculo.deserealizarVehiculos("vehiculos.ser");
            ventas = Venta.desearilzarVentas("venta.ser");
            Venta.vincular(usuarios, ventas, vehiculos);//vincula la venta
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("Oferta realizada exitosamente");//muestra un mensaje en caso de haber realizado exitosamente la oferta
            alerta.show();
            vehiculo = null;
            tablaVehiculo.getItems().clear();
            ImgCarro.setImage(null);
            textConOferta.clear();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Seleccione un vehiculo o ingrese un valor a ofertar");
            alerta.show();
        }
    }

    @FXML
    private void obtenerDatosTabla(MouseEvent event) {//segun los datos de la tabla obtenidos se muestra la imagen del vehiculo 
        vehiculo = (Vehiculo) tablaVehiculo.getSelectionModel().getSelectedItem();
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(vehiculo.getImagen());
            Image imagen = new Image(bais);
            ImgCarro.setImage(imagen);
        } catch (Exception e) {
            ImgCarro.setImage(null);
        }

    }

}
