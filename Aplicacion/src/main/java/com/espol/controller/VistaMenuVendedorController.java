/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.aplicacion.App;
import com.espol.exceptionsP.ProblemaMensajeException;
import com.espol.model.datos.Usuario;
import com.espol.model.datos.Vendedor;
import com.espol.model.utilidades.Buscar;
import com.espol.model.utilidades.Correo;
import com.espol.model.utilidades.DatosLogin;
import com.espol.model.utilidades.Oferta;
import com.espol.model.utilidades.Validar;
import com.espol.model.utilidades.Venta;
import com.espol.model.vehiculos.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VistaMenuVendedorController implements Initializable {

    @FXML
    private Button bttCerrarSesion;
    @FXML
    private ComboBox comoBoxTipoUsuario;
    @FXML
    private Pane panelVendedor;
    @FXML
    private ToolBar barVendedor;
    @FXML
    private Button bttIngresarVehiculo;
    @FXML
    private Button bttAceptarOferta;
    @FXML
    private Button bttPerfil;
    @FXML
    private Pane panelIngresarVehiculo;
    @FXML
    private TextField textPlaca;
    @FXML
    private TextField textMarca;
    @FXML
    private TextField textModelo;
    @FXML
    private TextField textTipoMotor;
    @FXML
    private TextField textAño;
    @FXML
    private TextField textRecorrido;
    @FXML
    private TextField textColor;
    @FXML
    private TextField textTipoCombustible;
    @FXML
    private TextField textTransmicion;
    @FXML
    private TextField textPrecio;
    @FXML
    private ComboBox comboBoxTipoVehiculo;
    @FXML
    private Pane panelPerfilVendedor;
    @FXML
    private TextField textNombre1;
    @FXML
    private TextField textApellido1;
    @FXML
    private TextField textCorreo1;
    @FXML
    private TextField textUsuario1;
    @FXML
    private Pane panelAceptarOferta;
    @FXML
    private Button bttSubirImagen;
    @FXML
    private TextField txtPlacaOfertada;
    @FXML
    private Button bttBuscar;
    @FXML
    private TableColumn columnCorreo;
    @FXML
    private TableColumn columnPrecio;
    @FXML
    private TableView tablaAceptarOferta;
    @FXML
    private Button bttPestañaOferta;
    @FXML
    private Button bttPestañaIngresarVehiculo;

    private Vehiculo vehiculo;
    Usuario user;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Oferta> ofertas;
    private ArrayList<Venta> ventas;
    private ArrayList<Oferta> ofertasUsuario;
    private byte[] imagen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Obtenemos los datos de cada usuario de la lista con el metodo obtener usuario
        ArrayList<Usuario> usuario = DatosLogin.obtenerUsuario();
        if ((usuario.get(0) instanceof Vendedor)) {//Realizamos una verificacion para saber si el usuario es de tipo vendedor con el metodo instanceof
            comoBoxTipoUsuario.getItems().clear();
            comoBoxTipoUsuario.getItems().add("Vendedor");
            comboBoxTipoVehiculo.getItems().clear();
            comboBoxTipoVehiculo.getItems().addAll("Auto", "Camion", "Camioneta",
                    "Motocicleta");//Agregamos todos los tipos de vehiculos al combobox
            user = usuario.get(0);
            panelVendedor.setVisible(true);//Hacemos que el panel del vendedor sea visible con el setvisible
        }
        if ((usuario.get(1) instanceof Vendedor)) {//Realizamos una verificacion para saber si el usuario es de tipo vendedor con el metodo instanceof
            comoBoxTipoUsuario.getItems().clear();
            comoBoxTipoUsuario.getItems().add("Vendedor");
            comboBoxTipoVehiculo.getItems().clear();
            comboBoxTipoVehiculo.getItems().addAll("Auto", "Camion", "Camioneta",
                    "Motocicleta");//Agregamos todos los tipos de vehiculos al combobox
            user = usuario.get(1);
            panelVendedor.setVisible(true);//Hacemos que el panel del vendedor sea visible con el setvisible
        }
        if (usuario.size() == 2) { //Si el tamaño de la lista usuario es de 2 se realiza lo siguiente
            comoBoxTipoUsuario.getItems().clear();//Limpiamos los elementos del combobox
            comoBoxTipoUsuario.getItems().addAll("Comprador", "Vendedor");//Agregamos los elementos comprador y vendedor al combobox
            panelVendedor.setVisible(true);//Hacemos que el panel vendedor sea visible
        }
        textPlaca.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[A-Z0-9-]*")) ? change : null));
        textTransmicion.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        textColor.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        textAño.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("-?([1-9][0-9]*)?")) ? change : null));
        textPrecio.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textRecorrido.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        textTipoMotor.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        textTipoCombustible.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        //Se esta validando el formato de lo que se debe ingresar en las casillas de las caracteristicas de los vehiculos

        columnPrecio.setCellValueFactory(new PropertyValueFactory("cantidadOfertada"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory("correo"));

    }

    public void recibirParametros(String txNom, String txAp, String txCorr, String Usu) {
        this.textNombre1.setText(txNom);
        this.textApellido1.setText(txAp);
        this.textCorreo1.setText(txCorr);
        this.textUsuario1.setText(Usu);

    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        DatosLogin.borrar();
        App.setRoot("vistaLogin");//Este metodo me regresa a la ventana de login
    }

    @FXML
    private void boxTipoUsuario(ActionEvent event) throws IOException {
        ComboBox cb = (ComboBox) event.getSource();
        String opcion = (String) cb.getValue();
        if (opcion.equals("Vendedor")) {
            panelVendedor.setVisible(true);
        } else {
            App.setRoot("vistaMenuComprador");

        }
        //Con este metodo verificamos el tipo de usuario que se escogio en el combobox para proceder a mostrar su respectivo panel
    }

    @FXML
    private void cambiarPerfil(ActionEvent event) {
        bttPerfil.setStyle("-fx-background-color : #00a8e8");
        bttPestañaIngresarVehiculo.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        bttPestañaOferta.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        ArrayList<Usuario> usuario = DatosLogin.obtenerUsuario();
        Usuario datos = usuario.get(0);
        textNombre1.setText(datos.getNombre());
        textApellido1.setText(datos.getApellido());
        textCorreo1.setText(datos.getCorreoE());
        textUsuario1.setText(datos.getUsuario());
        panelIngresarVehiculo.setVisible(false);
        panelAceptarOferta.setVisible(false);
        panelPerfilVendedor.setVisible(true);

        //Cuando seleccionamos la opcion de perfil en el toolbar, procedemos a mostrar la ventana del perfil del vendedor
    }

    @FXML
    private void mostarPanelIngresarVehiculo(ActionEvent event) {
        bttPestañaIngresarVehiculo.setStyle("-fx-background-color : #00a8e8");
        bttPerfil.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        bttPestañaOferta.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        panelAceptarOferta.setVisible(false);
        panelPerfilVendedor.setVisible(false);
        panelIngresarVehiculo.setVisible(true);
        //Este metodo me procede a mostrar la ventana para ingresar los datos de un vehiculo cuando seleccionamos la opcion ingresar vehiculo del toolbar
    }

    @FXML
    private void mostrarPanelAceptarOferta(ActionEvent event) {
        bttPestañaOferta.setStyle("-fx-background-color : #00a8e8 ");
        bttPerfil.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        bttPestañaIngresarVehiculo.setStyle("-fx-background-color : #00171f;"
                + " -fx-text-fill: #ffffff ;");
        panelIngresarVehiculo.setVisible(false);
        panelPerfilVendedor.setVisible(false);
        panelAceptarOferta.setVisible(true);
        //Este metodo procede a mostrar la ventana para aceptar una oferta de un vehiculo cuando seleccionamos la opcion aceptar oferta del toolbar
    }

    @FXML
    private void ingresarVehiculo(ActionEvent event) {
        vehiculos = Vehiculo.deserealizarVehiculos("vehiculos.ser");
        try {
            String tipoVehiculo = comboBoxTipoVehiculo.getSelectionModel().getSelectedItem().toString();
            String placa = textPlaca.getText();
            String marca = textMarca.getText();
            String modelo = textModelo.getText();
            String tipoMotor = textTipoMotor.getText();
            int año = Integer.parseInt(textAño.getText());
            double recorrido = Double.parseDouble(textRecorrido.getText());
            String color = textColor.getText();
            String tipoCombustible = textTipoCombustible.getText();
            String transmision = textTransmicion.getText();
            double precio = Double.parseDouble(textPrecio.getText());
            //Vamos sacando los datos del vehiculo como es la placa, modelo, marca, etc. con el metodo getText

            if (!Buscar.verificarVehiculoBD(placa, vehiculos)) {
                vehiculo = Validar.verificarTipoVehiculo(tipoVehiculo, placa, marca,
                        modelo, tipoMotor, año, recorrido, color, tipoCombustible, transmision, precio, imagen);
                ((Vendedor) user).registrarVenta(vehiculo, "vehiculos.ser", "venta.ser");
                //Procedemos a registrar el vehiculo con el metodo registrarVenta en los archivos mencionados
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setContentText("Vehículo registrado exitosamente.");
                alerta.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setContentText("Vehículo ya existente.");
                alerta.show();
            }//Se muestran las respectivas alertas

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Existen campos incompletos");
            alerta.show();
        }//Se muestra la alerta de error notificando que hay campos incompletos.
    }

    @FXML
    private void subirImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File archivo = fileChooser.showOpenDialog(null);
        imagen = Files.readAllBytes(archivo.toPath());

        // Obtener la imagen seleccionada
    }

    @FXML
    private void buscarOferta(MouseEvent event) throws NoSuchAlgorithmException {
        usuarios = Usuario.deserealizarUsuarios("usuarios.ser");
        vehiculos = Vehiculo.deserealizarVehiculos("vehiculos.ser");
        ofertas = Oferta.desearilzarOfertas("ofertas.ser");
        ventas = Venta.desearilzarVentas("venta.ser");
        Oferta.vincular(usuarios, ofertas, ventas);
        Venta.vincular(usuarios, ventas, vehiculos);
        String placa = txtPlacaOfertada.getText().toUpperCase();
        ofertasUsuario = Buscar.Ofertas_de_vendedor(Buscar.obtenerUsuario(user, usuarios), placa);
        if (ofertasUsuario.size() == 0) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("No existen ofertas para este vehiculo");
            alerta.show();
        } else {
            ObservableList lista = FXCollections.observableArrayList(ofertasUsuario);
            tablaAceptarOferta.getItems().clear();
            tablaAceptarOferta.setItems(lista);
            tablaAceptarOferta.getSortOrder().add(columnPrecio);

        }//Este metodo le permite al vendedor buscar si se ha realizado una oferta en un vehiculo en especifico. El vendedor debe ingresar la placa del vehiculo para verficar si es que se ha realizado una oferta. En caso de que si se haya realizado, le debe mostrar al vendedor el precio ofertado y el vehiculo.
    }

    @FXML
    private void aceptarOferta(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("¿Esta seguro de aceptar la oferta?\n" + "Una vez aceptada"
                + " el resto de ofertas seran eliminadas");
        Optional<ButtonType> opcion = alerta.showAndWait();
        Oferta oferta = (Oferta) tablaAceptarOferta.getSelectionModel().getSelectedItem();
        if (opcion.get().getText().equals("Aceptar")
                && oferta != null) {
            String destinatario = oferta.getCorreo();
            String asunto = "OFERTA ACEPETADA";
            String cuerpo = "Estiamado/a " + oferta.getComprador().getNombre() + " "
                    + oferta.getComprador().getApellido() + " ha sido aceptado su oferta por el vehiculo: "
                    + oferta.getVenta().getVehiculo() + "\n" + "Por favor comunicarse al correo del vendedor : "
                    + oferta.getVenta().getVendedor().getCorreoE();
            vehiculos.remove(oferta.getVenta().getVehiculo());
            ventas.remove(oferta.getVenta());
            ofertas.removeAll(ofertasUsuario);
            Venta.serializarVentas(ventas, "venta.ser");
            Vehiculo.serealizarVehiculos(vehiculos, "vehiculos.ser");
            Oferta.serializarOfertas(ofertas, "ofertas.ser");
            tablaAceptarOferta.getItems().clear();
            txtPlacaOfertada.clear();
            try {
                Correo.enviarConGMail(destinatario, asunto, cuerpo);
            } catch (ProblemaMensajeException ex) {
                ex.printStackTrace();
            }
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("Oferta Aceptada\n " + "Se acaba de enviar el correo de confirmación al comprador.");
            alerta.show();
        } else {
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("No escogio una oferta");
            alerta.show();
        }
    }//Este metodo permite que el vendedor acepte la oferta que el comprador ha realizado y le envie un correo de confirmación al comprador de que su oferta fue aceptada.

}
