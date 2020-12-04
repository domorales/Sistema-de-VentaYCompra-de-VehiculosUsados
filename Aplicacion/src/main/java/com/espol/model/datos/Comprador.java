package com.espol.model.datos;

import com.espol.model.utilidades.Codigo;
import com.espol.model.utilidades.Oferta;
import com.espol.model.utilidades.Venta;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class Comprador extends Usuario {

    private ArrayList<Oferta> listaOferta;
    
    public Comprador (String correo){
        super(correo);
    }

    public Comprador( String nombre, String apellido, String correoE, String organizacion, String usuario, String clave) throws NoSuchAlgorithmException {
        super(nombre, apellido, correoE, organizacion, usuario, clave);
        this.listaOferta = new ArrayList<>();
    }


    public ArrayList<Oferta> getListaOferta() {
        return listaOferta;
    }

    public void setListaOferta(ArrayList<Oferta> listaOferta) {
        this.listaOferta = listaOferta;
    }

    public void realizarOferta(Venta venta, double cantidadOfertada, String path) { //Este metodo recibe un objeto de tipo venta y un double que representa la cantidad ofertada.
        int codigo = Codigo.generarCodigo(path);//Mediante el archivo ofertas.txt determinamos el codigo de tipo entero.
        Oferta oferta = new Oferta(codigo, venta.getCodVenta(), this.correoE, cantidadOfertada);// Creamos un objeto de tipo oferta que recibe el codigo determinado perviamente, el codigo de venta, la cedula del comprador y la cantidad ofertada. 
        oferta.registrarOfertas(path);// Luego la oferta la procedemos a registrar utilizando un metodo de la clase Oferta.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Comprador other = (Comprador) o;
        return (this.correoE.equals(other.correoE)); // Determinamos que para que dos compradores sean iguales, sus cedulas deben ser las mismas.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.listaOferta); // El atributo que utilizamos para el hashCode es el de la listaOferta que posee la clase Comprador.
        return hash;
    }

}
