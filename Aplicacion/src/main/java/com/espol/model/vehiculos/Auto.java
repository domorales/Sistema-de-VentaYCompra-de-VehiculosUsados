package com.espol.model.vehiculos;

public class Auto extends Vehiculo {

    public Auto(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, String transmision, double precio, byte[] imagen) {
        super("Auto", placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, true, transmision, precio, imagen);
    }//Se realizo el constructor de la clase Auto que recibe sus atributos y los de la super clase Vehiculo utilizando el super

}
