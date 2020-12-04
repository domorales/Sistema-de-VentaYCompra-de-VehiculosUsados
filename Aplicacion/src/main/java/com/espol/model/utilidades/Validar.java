/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model.utilidades;

import com.espol.model.vehiculos.Auto;
import com.espol.model.vehiculos.Camion;
import com.espol.model.vehiculos.Camioneta;
import com.espol.model.vehiculos.Motocicleta;
import com.espol.model.vehiculos.Vehiculo;

/**
 *
 * @author dilan
 */
public class Validar {

//valida que el correo ingresado contenga el "@" y que termine en ".com" y procede a retornarlo
    public static boolean validarCorreo(String correo) {
        if (correo.contains("@") && correo.contains(".")) {
            return true;
        } else {
            return false;
        }
    }

    public static Vehiculo verificarTipoVehiculo(String tipoVehiculo, String placa, String marca,
            String modelo, String tipoMotor, int año, double recorrido, String color,
            String tipoCombustible, String transmision, double precio, byte[] imagen) {
        Vehiculo vehiculo = null;
        if (tipoVehiculo.equals("Auto")) {
            vehiculo = new Auto(placa, marca, modelo, tipoMotor,
                    año, recorrido, color, tipoCombustible, transmision, precio, imagen);
        } else if (tipoVehiculo.equals("Camion")) {
            vehiculo = new Camion(placa, marca, modelo, tipoMotor,
                    año, recorrido, color, tipoCombustible, transmision, precio, imagen);
        } else if (tipoVehiculo.equals("Camioneta")) {
            vehiculo = new Camioneta(placa, marca, modelo, tipoMotor,
                    año, recorrido, color, tipoCombustible, transmision, precio, imagen);
        } else if (tipoVehiculo.equals("Motocicleta")) {
            vehiculo = new Motocicleta(placa, marca, modelo, tipoMotor,
                    año, recorrido, color, tipoCombustible, transmision, precio, imagen);
        }
        return vehiculo;

    }//Este metodo devuelve un vehiculo y recibe todos los datos respectivos de un vehiculo. Se realizan ciertas verificaciones y depende de esto devuelve el vehiculo ya sea auto, camion, camioneta o motocicleta.

    public static String nullA0(String dato) {
        try {
            int numero = Integer.parseInt(dato);
        } catch (Exception e) {
            return "0";
        }
        return dato;
    }
}
