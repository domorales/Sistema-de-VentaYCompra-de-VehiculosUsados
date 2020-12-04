package com.espol.model.vehiculos;

public class Camion extends Vehiculo {

    public Camion(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, String transmision, double precio, byte[] imagen) {
        super("Camion", placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, true, transmision, precio, imagen);

    }//Se realizo el constructor de la clase Camion que recibe sus atributos y los de la super clase Vehiculo utilizando el super

}
