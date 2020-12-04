/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.exceptionsP;

import java.io.IOException;

/**
 *
 * @author Fernando
 */
public class ImposibleswitchsceneException extends IOException {

    public ImposibleswitchsceneException(String msg) {
        super(msg);
    }//Esta excepción será utilizada cuando no se pueda cambiar de escena
}
