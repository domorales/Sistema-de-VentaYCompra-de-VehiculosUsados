package com.espol.model.vehiculos;

import com.espol.model.utilidades.Venta;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Vehiculo implements Serializable {

    protected String tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoMotor;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected boolean vidrios;
    protected String transmision;
    protected double precio;
    protected byte[] imagen;
    ArrayList<Venta> listaVentas;

    public Vehiculo(String tipo, String placa, String marca, String modelo, String tipoMotor,
            int año, double recorrido, String color, String tipoCombustible, Boolean vidrio,
            String transmision, double precio, byte[] imagen) {
        this.tipo = tipo;
        this.placa = placa.toUpperCase();
        this.marca = marca.toUpperCase();
        this.modelo = modelo.toUpperCase();
        this.tipoMotor = tipoMotor.toUpperCase();
        this.año = año;
        this.recorrido = recorrido;
        this.color = color.toUpperCase();
        this.tipoCombustible = tipoCombustible.toUpperCase();
        this.vidrios = vidrio;
        this.transmision = transmision.toUpperCase();
        this.precio = precio;
        this.imagen = imagen;
        this.listaVentas = new ArrayList<>();
    }

    public Vehiculo(String placa) {
        this.placa = placa.toUpperCase();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public boolean isVidrios() {
        return vidrios;
    }

    public void setVidrios(boolean vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public static void serealizarVehiculos(ArrayList<Vehiculo> vehiculos, String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
                ObjectOutputStream datos = new ObjectOutputStream(archivo)) {
            datos.writeObject(vehiculos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//Este metodo recibe una lista de vehiculos y un nombre de archivo, procede a serealizar la lista de vehiculos y los almacena en un archivo binario.

    public static ArrayList<Vehiculo> deserealizarVehiculos(String path) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try (FileInputStream archivo = new FileInputStream(path);
                ObjectInputStream datos = new ObjectInputStream(archivo)) {
            vehiculos = (ArrayList<Vehiculo>) datos.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }//Este metodo recibe un nombre de archivo y procede a deserealizarlo y devuelve los datos de ese archivo en una lista de vehiculos.

    public void registrarVehiculo(String path) {
        ArrayList<Vehiculo> vehiculos = Vehiculo.deserealizarVehiculos(path);
        vehiculos.add(this);
        Vehiculo.serealizarVehiculos(vehiculos, path);
    }//Este metodo procede a guardar un vehiculo en especifico en un archivo que recibe el método.

    @Override
    public String toString() {
        return tipo + "," + placa + "," + marca + "," + modelo + "," + tipoMotor + "," + año + "," + recorrido + "," + color + "," + tipoCombustible + "," + vidrios + "," + transmision + "," + precio;
    }//Se realiza el toString mostrando todos los atributos que recibe un vehiculo con su respectiva informacion

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        Vehiculo other = (Vehiculo) o;
        return (this.placa.equals(other.placa));//Se vera si dos vehiculos son iguales comparando si sus placas son las mismas
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.placa);
        hash = 59 * hash + this.año;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.recorrido) ^ (Double.doubleToLongBits(this.recorrido) >>> 32));
        hash = 59 * hash + (this.vidrios ? 1 : 0);
        return hash;//Para el hashCode se utilizaran los atributos de placa, año, su recorrido y los vidrios
    }

}
