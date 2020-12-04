/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model.utilidades;

import com.espol.model.datos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author dilan
 */
public class DatosLogin {

    private final static ArrayList<Usuario> usuario = new ArrayList<>();

    public static void guardar(Usuario usuario) {
        DatosLogin.usuario.add(usuario);
    }//Este metodo recibe un usuario y luego se agrega use usuario en la lista de usuarios que esta clase tiene como atributo.

    public static ArrayList<Usuario> obtenerUsuario() {
        return DatosLogin.usuario;
    }//Este metodo devuelve una lista de usuarios.

    public static void borrar() {
        DatosLogin.usuario.clear();
    }//Este metodo borra todos los usuarios almacenados en lista usuario de atributo de la clase.
}
