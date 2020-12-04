package com.espol.model.datos;

import com.espol.model.utilidades.Codigo;
import com.espol.model.utilidades.Venta;
import com.espol.model.vehiculos.Vehiculo;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class Vendedor extends Usuario {

    private ArrayList<Venta> listaVenta;
    
    public Vendedor (String correo){
        super(correo);
    }
    
    public Vendedor(String nombre, String apellido, String correoE, String organizacion, String usuario, String clave) throws NoSuchAlgorithmException {
        super(nombre, apellido, correoE, organizacion, usuario, clave);
        this.listaVenta = new ArrayList<>();
    }

    public ArrayList<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(ArrayList<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public void registrarVenta(Vehiculo vehiculo, String pathVehiculo,String pathVenta) {//El metodo registrarVenta recibe un objeto de tipo Vehiculo
        vehiculo.registrarVehiculo(pathVehiculo);//Este vehiculo se lo procede a registar utilizando un metodo de la clase vehiculo
        int codigo = Codigo.generarCodigo(pathVenta);//Se determina el codigo con el metodo generarCodigo de la clase Codigo que recibe el archivo ventas.txt
        Venta v = new Venta(codigo, this.correoE, vehiculo.getPlaca());//Se crea un objeto de tipo Venta que recibe el codigo generado anteriormente, la cedula del vendedor y la placa del vehiculo
        v.registrarVentas(pathVenta);//Se procede a registrar este objeto v con un metodo de la clase venta
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
        Vendedor other = (Vendedor) o;
        return this.correoE.equals(other.correoE);//El metodo equals determina si dos vendedores son iguales viendo si sus cedulas son las mismas
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.listaVenta);//El metodo HashCode se lo determina con el atributo listaVenta de esta clase Vendedor
        return hash;
    }

}
