/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.exceptionsP;

/**
 *
 * @author Fernando
 */
public class UsuarioIncorrectoException extends Exception {

    public UsuarioIncorrectoException(String msg) {
        super(msg);
    }//Esta excepción se utilizará cuando no sea posible encontrar un usuario en el archivo de usuarios
}
