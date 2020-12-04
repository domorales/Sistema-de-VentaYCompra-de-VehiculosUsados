package com.espol.model.utilidades;

import com.espol.model.datos.Usuario;
import com.espol.model.datos.Vendedor;
import com.espol.model.vehiculos.Vehiculo;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Buscar {
//buscar_vehiculo busca el vehiculo en la lista de ventas  acorde a los parametros o caracteristicas que el usuario ingrese para ver la disponibilidad del mismo

    public static ArrayList<Vehiculo> buscar_vehiculo(ArrayList<Venta> ventas, String tipo_vehiculo, double recorrido_min,
            double recorrido_max, int año_min, int año_max, double precio_min, double precio_max) {
        ArrayList<Vehiculo> lista = new ArrayList<>(); // inicializa una nueva lista con objetos venntas
        for (Venta v : ventas) {// uso del for each para recorrer la lista ingresada como parametro
            Vehiculo vehiculo = v.getVehiculo();// almacena el vehiculo obtenido de la lista de ventas en otra variable
            if (vehiculo.getTipo().equals(tipo_vehiculo) && ((vehiculo.getAño() >= año_min && vehiculo.getAño() <= año_max) || (vehiculo.getPrecio() >= precio_min && vehiculo.getPrecio() <= precio_max)
                    || (vehiculo.getRecorrido() >= recorrido_min && vehiculo.getRecorrido() <= recorrido_max))) { //si esta acorde a los requisitos del comprador se almacena en la nueva lista
                lista.add(vehiculo);
            } else if (tipo_vehiculo.equals("Todos") && (vehiculo.getAño() >= año_min || vehiculo.getAño() <= año_max || vehiculo.getPrecio() >= precio_min || vehiculo.getPrecio() <= precio_max
                    || vehiculo.getRecorrido() >= recorrido_min || vehiculo.getRecorrido() <= recorrido_max)) {
                lista.add(vehiculo);
            }
        }
        return lista;

    }//Este metodo devuelve una lista de vehiculos y recibe una lista de ventas, un tipo de vehiculo, el recorrido minimo, maximo, entre otros datos del vehiculo. Se recorre la lista de ventas y se obtiene el vehiculo, luego se realizan ciertas verificaciones con respecto al vehiculo y si se cumplen se lo agrega a una lista vacia y se la retorna.

    public static Vendedor obtenerUsuario(Usuario user, ArrayList<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            if (u instanceof Vendedor && u.equals(user)) {
                return (Vendedor) u;
            }
        }
        return null;
    }//Este metodo devuelve un objeto de tipo Vendedor y recibe un objeto de tipo Usuario y una lista de usuarios. El metodo recorre la lista de usuarios y utiliza un if para comprobar si los usuarios dentro de la lista coinciden con el usuario enviado y si es de tipo vendedor lo devuelve. 

    public static boolean comprobarCorreo(String email, String path) {
        ArrayList<Usuario> usuarios = Usuario.deserealizarUsuarios(path);
        int i = 0;
        for (Usuario u : usuarios) {
            if (u.getCorreoE().equals(email)) {
                i++;
            }
        }
        return i > 0;
    }//Este metodo devuelve un booleano y recibe un mail y un archivo. Primero deserealiza un archivo y obtiene una lista de usuarios, luego la recorre y si un usuario de la lista coincide con el mail enviado al metodo, aumenta el contador y retorna un valor entero mayor que 0. 

    public static boolean comprobarUsuarioBD(String correo, String contraseña, String path) throws NoSuchAlgorithmException {
        ArrayList<Usuario> usuarios = Usuario.deserealizarUsuarios(path);
        String pass = Encriptar.toHexString(Encriptar.getSHA(contraseña));
        int contador = 0;
        for (Usuario u : usuarios) {
            if (u.getClave().equals(pass) && u.getCorreoE().equals(correo)) {
                DatosLogin.guardar(u);
                contador++;
            }
        }
        return contador > 0;
    }

//en la lista ofertas del vendedor se crea otra lista "ofertas", se busca en
    public static ArrayList<Oferta> Ofertas_de_vendedor(Usuario vendedor, String placa) {
        ArrayList<Oferta> ofertas = new ArrayList<>();

        for (Venta v : ((Vendedor) vendedor).getListaVenta()) { //explora la lista venta que se encuentra en la clase vendedor
            if (placa.equals(v.getVehiculo().getPlaca())) { //compara que los vehiculos sean iguales
                for (Oferta o : v.getOfertas()) {//explora la lista de oferta
                    ofertas.add(o);//agrega el objeto "o" a la lista ofertas
                }
            }
        }
        return ofertas;//retorna la lista
    }

    public static Venta buscarVenta(Vehiculo vehiculo, ArrayList<Venta> ventas) {
        Venta venta = null;
        for (Venta v : ventas) {
            if (v.getVehiculo().equals(vehiculo)) {
                venta = v;
            }
        }
        return venta;
    }

    public static boolean verificarVehiculoBD(String placa, ArrayList<Vehiculo> vehiculos) {
        int contador = 0;
        for (Vehiculo v : vehiculos) {
            if (placa.equals(v.getPlaca())) {
                contador++;
            }
        }
        return contador > 0;
    }

}
