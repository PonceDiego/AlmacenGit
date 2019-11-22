package com.c12.almacenapp.data;

public class Usuario {
    String token;
    String nombre;
    String username;
    String area;
    String rol;

    public Usuario(String token,  String nombre,
            String username,
            String area,
            String rol){
        this.area=area;
        this.nombre=nombre;
        this.rol=rol;
        this.token=token;
        this.username=username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
