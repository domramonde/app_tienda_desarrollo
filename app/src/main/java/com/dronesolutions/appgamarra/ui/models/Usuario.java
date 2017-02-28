package com.dronesolutions.appgamarra.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by VANESSA on 28/01/2017.
 */
public class Usuario {
    private String usuario;
    private  String nombre;
    private String apellidos;
    private String correo;

    //solo para enviar el usuario al registrar
    private String contrasena;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
