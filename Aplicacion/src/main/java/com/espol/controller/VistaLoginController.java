/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.aplicacion.App;
import com.espol.exceptionsP.ProblemaMensajeException;
import com.espol.model.datos.Comprador;
import com.espol.model.datos.Usuario;
import com.espol.model.utilidades.Buscar;
import com.espol.model.utilidades.Correo;
import com.espol.model.utilidades.DatosLogin;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VistaLoginController implements Initializable {

    @FXML
    private Button bttIngresar;
    @FXML
    private PasswordField textContraseña;
    @FXML
    private TextField textCorreo;
    @FXML
    private Button bttRegistrarse;
    private ArrayList<Usuario> usuarios;
    @FXML
    private Button claveInvalida;
    @FXML
    private Pane cambioContrase;
    @FXML
    private PasswordField nuevaContra;
    @FXML
    private PasswordField confirmaContra;
    @FXML
    private Pane vistaLogin1;
    @FXML
    private Pane vistaLogin;
    @FXML
    private Button bttnCambiar;
    @FXML
    private PasswordField textContraseña1;
    @FXML
    private TextField textCorreo1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = Usuario.deserealizarUsuarios("usuarios.ser");

    }

    @FXML
    private void ingresarUsuario(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String correo = textCorreo.getText();//obtiene el texto de la textfield y los guarda en una variable string
        String contraseña = textContraseña.getText();
        if (Buscar.comprobarUsuarioBD(correo, contraseña, "usuarios.ser")) {//comprueba que el usuario exista para realizar el ingreso
            ArrayList<Usuario> usuario = DatosLogin.obtenerUsuario();//obtiene los datos que el usuario ingreso al registrarse
            if (usuario.get(0) instanceof Comprador) {
                App.setRoot("vistaMenuComprador");//si el usuario es un comprador lo manda a la escena de comprador
            } else {
                App.setRoot("vistaMenuVendedor");//en caso de ser vendedor le muestra la escena de vendedor
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);// si el usuario no existe o su clave es incorrecta manda una alerta
            claveInvalida.setVisible(true);//aparece un boton para cambiar la clave y lo redirecciona a otro panel
            alerta.setHeaderText(null);
            alerta.setContentText("Correo invalido, correo no registrado o contraseña invalida");
            alerta.show();
        }
    }

    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {
        App.setRoot("vistaRegistro");//cambia a la escena para que el usuario proceda a registrarse
    }

    @FXML
    private void cambiar(MouseEvent e) throws NullPointerException {
        vistaLogin1.setVisible(false);//al hacer clic en el boton Has olvidado tu contrasenia se procede a mostrar el panel de cambio de contrasenia
        cambioContrase.setVisible(true);

    }

    @FXML
    private void cambiarClave(MouseEvent event) throws NoSuchAlgorithmException, IOException, ProblemaMensajeException {
        String correo = textCorreo1.getText();
        String contraseña = textContraseña1.getText();
        if (!Buscar.comprobarUsuarioBD(correo, contraseña, "usuarios.ser")) {//comprueba que el usuario exista

            String nuevaContraseña = nuevaContra.getText();
            String confirmacion = confirmaContra.getText();
            for (Usuario u : usuarios) {//recorre la lista de usuarios
                if (u.getCorreoE().equals(correo)) {//verifica el correo del usuario
                    if (nuevaContraseña.equals(confirmacion)) {//verifica que ambas nuevas claves ingresadas sean iguales para proceder el cambio
                        usuarios.remove(u);
                        Usuario user = new Usuario(u.getNombre(), u.getApellido(), correo, u.getOrganizacion(), u.getUsuario(), confirmacion);
                        usuarios.add(user);
                        Usuario.serializarUsuarios(usuarios, "usuarios.ser");
                        String destinatario = correo;
                        String asunto = "Cambio de clave";
                        String cuerpo = "Se ha cambiado la clave exitosamente";
                        Correo.enviarConGMail(destinatario, asunto, cuerpo);//envia un correo al usuario sobre el cambio de clave
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setHeaderText(null);
                        alerta.setContentText("Clave cambiada exitosamente\n " + "Se le acaba de enviar el correo de confirmación.");
                        alerta.show();//se procede a notificar mediante una alerta que la clave ha sido cambiada
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Las claves ingresadas no son iguales");
                        a.show();
                    }

                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);//en caso de no poder cambiar la clave procede a notificar el 
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo cambiar la clave");
            alerta.show();
        }

    }

    @FXML
    private void ingresar(ActionEvent event) throws IOException {//manda al usuario a la vista login apenas abre el programa
        vistaLogin1.setVisible(true);
        vistaLogin.setVisible(true);
        cambioContrase.setVisible(false);
    }
}
