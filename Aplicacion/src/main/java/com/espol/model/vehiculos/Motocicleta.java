package com.espol.model.vehiculos;

public class Motocicleta extends Vehiculo {

    public Motocicleta(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, String transmision, double precio, byte[] imagen) {
        super("Motocicleta", placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, false, transmision, precio, imagen);
    }//Se realizo el constructor de la clase Motocileta que recibe sus atributos y los de la super clase Vehiculo utilizando el super

}
