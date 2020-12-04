package com.espol.model.vehiculos;

public class Camioneta extends Vehiculo {

    public Camioneta(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, String transmision, double precio, byte[] imagen) {
        super("Camioneta", placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, true, transmision, precio, imagen);

    }//Se realizo el constructor de la clase Camioneta que recibe sus atributos y los de la super clase Vehiculo utilizando el super

}
