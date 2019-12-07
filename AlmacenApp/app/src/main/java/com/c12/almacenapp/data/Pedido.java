package com.c12.almacenapp.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Pedido {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private String id,estado,usuario,fecha,observaciones;
    public Pedido(JSONObject pedido) throws JSONException {
        this.estado=pedido.get("estado").toString();
        this.fecha=pedido.get("fecha").toString();
        this.id=pedido.get("id").toString();
        this.observaciones=pedido.get("observaciones").toString();
        this.usuario=pedido.get("usuario").toString();
    }


}
