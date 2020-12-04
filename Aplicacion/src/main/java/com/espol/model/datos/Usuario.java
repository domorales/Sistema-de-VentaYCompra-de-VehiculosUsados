package com.espol.model.datos;

import com.espol.model.utilidades.Encriptar;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario implements Serializable {

    protected String nombre;
    protected String apellido;
    protected String correoE;
    protected String organizacion;
    protected String usuario;
    protected String clave;
    protected boolean comprador;
    protected boolean vendedor;
    private static final long serialVersionUID = 3L;

    public Usuario(String correo) {
        this.correoE = correo;
    }

    public Usuario(String nombre, String apellido, String correoE, String organizacion, String usuario, String clave) throws NoSuchAlgorithmException {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
        this.correoE = correoE;
        this.organizacion = organizacion;
        this.usuario = usuario;
        this.clave = Encriptar.toHexString(Encriptar.getSHA(clave));

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isComprador() {
        return comprador;
    }

    public void setComprador(boolean comprador) {
        this.comprador = comprador;
    }

    public boolean isVendedor() {
        return vendedor;
    }

    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }

    public void registrarUsuario(String path) {
        ArrayList<Usuario> usuarios = deserealizarUsuarios(path);
        usuarios.add(this);
        serializarUsuarios(usuarios, path);
    }//Este metodo recibe un string que es el nombre del archivo y lo que hace es registrar un usuario en ese archivo enviado.

    public static void serializarUsuarios(ArrayList<Usuario> listaUsuarios, String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
                ObjectOutputStream datos = new ObjectOutputStream(archivo);) {
            datos.writeObject(listaUsuarios);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//Este metodo recibe una lista de usuarios y un string que es el nombre del archivo y procede a guardar los datos de esa lista de usuarios en el archivo enviado al metodo.

    public static ArrayList<Usuario> deserealizarUsuarios(String path) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try (FileInputStream archivo = new FileInputStream(path);
                ObjectInputStream datos = new ObjectInputStream(archivo)) {
            listaUsuarios = (ArrayList<Usuario>) datos.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return listaUsuarios;
    }//Este metodo devuelve una lista de usuarios deserializando un archivo binario

    public static ArrayList<Usuario> validarUsuario(ArrayList<Usuario> usuarios, String mail, String clave) {
        ArrayList<Usuario> users = new ArrayList<>();
        for (Usuario us : usuarios) {
            if (us.getCorreoE().equals(mail) && us.getClave().equals(clave)) {
                users.add(us);
            }
        }
        return users;
    }//Este metodo devuelve una lista de usuarios y recibe una lista de usuarios, un mail y una clave. Lo que hace es verificar si ese usuario existe en la lista enviada al metodo.

    @Override
    public String toString() {
        return nombre + "," + apellido + "," + correoE + "," + organizacion + "," + usuario + "," + clave + "," + comprador + "," + vendedor;
    } //Se realiza el toString con todos los datos del usuario

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
        Usuario other = (Usuario) o;
        return this.correoE.equals(other.correoE);//El equals comprobara si dos usuarios son iguales chequeando si sus cedulas son iguales
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.correoE);
        hash = 37 * hash + Objects.hashCode(this.usuario);
        return hash;//El hashCode se lo realizo utilizando los atributos del usuario que son unicos como su cedula, correoE y su usuario
    }

}
