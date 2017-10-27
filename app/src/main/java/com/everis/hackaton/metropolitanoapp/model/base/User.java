package com.everis.hackaton.metropolitanoapp.model.base;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class User {
    private String id = "";
    private String nombres = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    private String apellidos = "";
}
