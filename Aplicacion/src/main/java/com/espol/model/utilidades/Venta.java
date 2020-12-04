package com.espol.model.utilidades;

import com.espol.model.datos.Usuario;
import com.espol.model.datos.Vendedor;
import com.espol.model.vehiculos.Vehiculo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Venta implements Serializable {

    private int codVenta;
    private String correo;
    private String placaVehiculo;
    private ArrayList<Oferta> ofertas;
    private Vehiculo vehiculo;
    private Vendedor vendedor;
    private static final long serialVersionUID = 1L;
//constructor de venta que recibe codigo de venta, la cedula del vendedor, la placa del vehiculo e inicializa la lista de ofertas

    public Venta(int codVenta, String correo, String placaVehiculo) {
        this.codVenta = codVenta;
        this.correo = correo;
        this.placaVehiculo = placaVehiculo;
        this.ofertas = new ArrayList<>();
    }
//sobrecarga del constructor venta que recibe solo el codigo de venta

    public Venta(int codVenta) {
        this.codVenta = codVenta;
    }
//funcion get del codigo de venta

    public int getCodVenta() {
        return codVenta;
    }
//funcionn set del codigo de venta

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

//funcion get de la placa de vehiculo
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
//funcion set de la placa del vehiculo

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
//funcion get de la lista oferta

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }
//funcion set de la lista oferta

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
//funcion get del vehiculo 

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
//funcion set del vehiculo

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
//funcion get del vendedor

    public Vendedor getVendedor() {
        return vendedor;
    }
//funcion set del vendedor

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void registrarVentas(String path) {
        ArrayList<Venta> ventas = desearilzarVentas(path);
        ventas.add(this);
        serializarVentas(ventas, path);
    }//Este metodo procede a guardar una venta en especifico en el archivo que el archivo recibe como parámetro.

    public static void serializarVentas(ArrayList<Venta> listaVentas, String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
                ObjectOutputStream datos = new ObjectOutputStream(archivo);) {
            datos.writeObject(listaVentas);
        } catch (Exception e) {
        }
    }

    public static ArrayList<Venta> desearilzarVentas(String path) {
        ArrayList<Venta> listaVentas = new ArrayList<>();
        try (FileInputStream archivo = new FileInputStream(path);
                ObjectInputStream datos = new ObjectInputStream(archivo)) {
            listaVentas = (ArrayList<Venta>) datos.readObject();
        } catch (Exception e) {
        }
        return listaVentas;
    }

//vincula las clases usuario,venta y vehiculo y se asegura de que no tire una excepcion, no retorna nada
    public static void vincular(ArrayList<Usuario> usuarios, ArrayList<Venta> listaVenta, ArrayList<Vehiculo> vehiculos) throws NoSuchAlgorithmException {
        for (Usuario user : usuarios) {//explora la lista usuarios
            if (user instanceof Vendedor) {//pregunta si el usuario es un Vendedor
                for (Venta r : listaVenta) {//recorre la lista de venta 
                    String placa = r.getPlacaVehiculo();//obtiene la placa del vehiculo 
                    String correo = r.getCorreo();// obtiene la cedula 
                    Vehiculo v = new Vehiculo(placa);//crea un nuevo vehiculo con la placa antes obtenida
                    v = vehiculos.get(vehiculos.indexOf(v)); //busca el indice del vehiculo y luego obtiene el mismo para almacenarlo en la nueva variable
                    Vendedor u = new Vendedor(correo);//crea un nuevo vendedor con la cedula obtenida anteriormente
                    Vendedor vendedor = (Vendedor) user; //realiza un downcasting de usuario a vendedor
                    if (u.equals(user)) {//pregunta si los vendedores son iguales
                        v.getListaVentas().add(r);//agrega el objeto venta "r" a la lista de ventas del vehiculo
                        vendedor.getListaVenta().add(r); //agrega el objeto venta "r" a la lista venta del vendedor
                        r.setVehiculo(v); // cambia el vehiculo del objeto venta "r" por el vehisulo "v" creado con la placa antes obtenida
                        r.setVendedor(vendedor); //cambia el vendedor del objeto venta "r" por el vendedor creado anteriormente 
                    }

                }
            }

        }
    }
//da el formato en el que se quiere se impriman o se retornen los datos

    @Override
    public String toString() {
        return codVenta + "," + correo + "," + placaVehiculo;
    }
//transforma los objetos en un entero unico 

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
//compara los objetos para saber si son iguales

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Venta other = (Venta) obj;

        return other.codVenta == this.codVenta; //retorna un booleano (True or False) sobre el atribito (codigo de venta) de ambos objetos
    }

}
