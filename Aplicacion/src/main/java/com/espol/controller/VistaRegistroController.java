
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.aplicacion.App;
import com.espol.model.datos.Comprador;
import com.espol.model.datos.Usuario;
import com.espol.model.datos.Vendedor;
import com.espol.model.utilidades.Buscar;
import com.espol.model.utilidades.Validar;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VistaRegistroController implements Initializable {

    @FXML
    private TextField textNombres;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textOrganizacion;
    @FXML
    private PasswordField textClave;
    @FXML
    private RadioButton radioBttVendedor;
    @FXML
    private RadioButton radioBttComprador;
    @FXML
    private Button bttRegistrar;
    ArrayList<TextField> txfield;
    @FXML
    private Button bttIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfield = new ArrayList<>();
        txfield.add(textNombres);
        txfield.add(textApellidos);
        txfield.add(textCorreo);
        txfield.add(textUsuario);
        textNombres.setTextFormatter(new TextFormatter<>(change -> 
                (change.getControlNewText().matches("[a-zA-Z ]*")? change : null)));
        textApellidos.setTextFormatter(new TextFormatter<>(change -> 
                (change.getControlNewText().matches("[a-zA-Z ]*")? change : null)));
        textCorreo.setTextFormatter(new TextFormatter<>(change -> 
                (change.getControlNewText().matches("[a-zA-Z0-9@._-]*")? change : null)));
        //Especificamos el formato de los nombres, apellidos y correos 
    }    

    @FXML
    private void registrarUsuario(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        if (!(radioBttVendedor.isSelected() || radioBttComprador.isSelected()) ){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Debe seleccionar el tipo de usuario");
            alerta.show();
            //Si el usuario no selecciona el tipo de usuario al registrarse se mostrara una alerta para notificarle que debe escoger uno
        }
        else if (!Validar.validarCorreo(textCorreo.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Correo invalido");
            alerta.show();
            //Si el correo que ingreso no es valido, se mostrará una alerta diciendo que el correo es inválido
        }
        else if (Buscar.comprobarCorreo(textCorreo.getText(), "usuarios.ser")){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("El correo ya se encuentra registrado");
            alerta.show();          
            //Si el correo que ingreso ya se cuentra registrado, se le avisará al usuario que ya está registrado y debe usar otro
        }
        else {
            String nombres= textNombres.getText();
            String apellidos= textApellidos.getText();
            String correo= textCorreo.getText();
            String organizacion =textOrganizacion.getText();
            String usuario = textUsuario.getText();
            String contraseña = textClave.getText();
            //Se extrae la información ingresada por el usuario con el getText

            if(radioBttVendedor.isSelected()) {
                Usuario vendedor  = new Vendedor(nombres,apellidos,correo,organizacion,usuario, contraseña);
                vendedor.setVendedor(true);
                vendedor.registrarUsuario("usuarios.ser");
                //Si se selecciono vendedor como tipo de usuario, se procederá a registrarlo en el archivo usuarios.ser
            }
            if (radioBttComprador.isSelected()) {
                Usuario vendedor  = new Comprador(nombres,apellidos,correo,organizacion,usuario, contraseña);
                vendedor.setComprador(true);
                vendedor.registrarUsuario("usuarios.ser");
                //Si se selecciono comprador como tipo de usuario, se procederá a registrarlo en el archivo usuarios.ser
            }
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario registrado exitosamente");
            alerta.show();
            App.setRoot("vistaLogin");
             
            
            //Luego se mostrará una alerta diciendo que el usuario fue registrado exitosamente
        }
        
    }

    @FXML
    private void ingresarUsuario(ActionEvent event) throws IOException {
        App.setRoot("vistaLogin");//Cambia de controlador al Vistalogin
    }

}
