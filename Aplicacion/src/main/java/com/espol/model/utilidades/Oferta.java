package com.espol.model.utilidades;

import com.espol.model.datos.Comprador;
import com.espol.model.datos.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Oferta implements Serializable {

    private int codigoOferta;
    private int codVenta;
    private String correo;
    private Comprador comprador;
    private Venta venta;
    private double cantidadOfertada;
//constructor de la clase oferta recibe sus atributos (codigoOferta,codVenta, cedula y la cantidadOfertada en formato double

    public Oferta(int codigoOferta, int codVenta, String correo, double cantidadOfertada) {
        this.codigoOferta = codigoOferta;
        this.codVenta = codVenta;
        this.correo = correo;
        this.cantidadOfertada = cantidadOfertada;
    }
//sbrecarga al constructor para que solo reciba el atributo codigoOferta

    public Oferta(int codigoOferta) {
        this.codigoOferta = codigoOferta;
    }
//funcion get del codigo oferta

    public int getCodigoOferta() {
        return codigoOferta;
    }
//funcion set del codigo oferta

    public void setCodigoOferta(int codigoOferta) {
        this.codigoOferta = codigoOferta;
    }
//funcion get del codigo venta

    public int getCodVenta() {
        return codVenta;
    }
//funcion set del codigo venta

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Comprador getComprador() {
        return comprador;
    }
//funcion set del comprador

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
//funcion get de venta

    public Venta getVenta() {
        return venta;
    }
//funcion set de venta

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
//funcion get de cantidad ofertada

    public double getCantidadOfertada() {
        return cantidadOfertada;
    }
//funcion set de cantidad ofertada

    public void setCantidadOfertada(double cantidadOfertada) {
        this.cantidadOfertada = cantidadOfertada;
    }

    public void registrarOfertas(String path) {
        ArrayList<Oferta> ofertas = desearilzarOfertas(path);
        ofertas.add(this);
        serializarOfertas(ofertas, path);
    }//Este metodo recibe un nombre de archivo como parametro y procede a guardar en ese archivo las ofertas respectivas.

    public static void serializarOfertas(ArrayList<Oferta> listaOfertas, String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
                ObjectOutputStream datos = new ObjectOutputStream(archivo);) {
            datos.writeObject(listaOfertas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//Este metodo recibe una lista de ofertas y un nombre de archivo y almacena las ofertas de esta lista en el archivo binario.

    public static ArrayList<Oferta> desearilzarOfertas(String path) {
        ArrayList<Oferta> listaOfertas = new ArrayList<>();
        try (FileInputStream archivo = new FileInputStream(path);
                ObjectInputStream datos = new ObjectInputStream(archivo)) {
            listaOfertas = (ArrayList<Oferta>) datos.readObject();
        } catch (Exception e) {
        }
        return listaOfertas;

    }//Este metodo recibe el nombre del archivo y deserealiza el archivo y devuelve una lista de ofertas.

//vincula las clases usuario, oferta, venta y se asegura que no haya excepciones, no retorna nada
    public static void vincular(ArrayList<Usuario> usuarios, ArrayList<Oferta> listaOfertas, ArrayList<Venta> venta) throws NoSuchAlgorithmException {
        for (Usuario user : usuarios) {
            if (user instanceof Comprador) { //pregunta si el usuario pertenece a la clase comprador
                for (Oferta o : listaOfertas) { //recorrela lista ofertas
                    int codigo = o.getCodVenta();
                    String cedula = o.getCorreo();
                    Venta v = new Venta(codigo);
                    v = venta.get(venta.indexOf(v));
                    Comprador c = new Comprador(cedula);
                    Comprador comprador = (Comprador) user;
                    if (c.equals(user)) { //pregunta si ambos compradores son iguales 
                        v.getOfertas().add(o);
                        comprador.getListaOferta().add(o);
                        o.setVenta(v);
                        o.setComprador(comprador);
                    }
                }
            }
        }
    }
//da el formato en el que se quiere se impriman o se retornen los datos

    @Override
    public String toString() {
        return codigoOferta + "," + codVenta + "," + correo + "," + cantidadOfertada;
    }

}
