package com.espol.model.utilidades;

import java.util.ArrayList;

public class Codigo {
//lee el archivo y genera un codigo de enteros en la linea[0] en el archivo enviado por los parametros 

    public static int generarCodigo(String archivo) {
        int codigo = 0;
        try {
            ArrayList<Oferta> ofertas = Oferta.desearilzarOfertas(archivo);
            ArrayList<Venta> ventas = Venta.desearilzarVentas(archivo);
            if (ofertas.get(0) instanceof Oferta) {
                for (Oferta o : ofertas) {
                    codigo = o.getCodigoOferta();
                }
                return codigo + 1;
            } else {
                for (Venta v : ventas) {
                    codigo = v.getCodVenta();
                }
                return codigo + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());//manda un mensaje en caso de qu no se encuentre el archivo
        }
        return 1; //retorna un numero entero en caso de que haya una excepcion 
    }
}
