package main.java.Almacen.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class PedidoRs {
	@Expose (serialize = true, deserialize = true) 
	int id;
	@Expose (serialize = true, deserialize = true) 
	String estado;
	@Expose (serialize = true, deserialize = true) 
	String usuario;
	@Expose (serialize = true, deserialize = true) 
	Date fecha;
	@Expose (serialize = true, deserialize = true) 
	String observaciones;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public PedidoRs(Pedido p) {
		id=p.getPedidoId();
		fecha=p.getFecha();
		usuario=p.getUsuario().getNombreUsuario();
		estado=p.getEstadopedido().getNombreEstado();
		observaciones=p.getObservaciones();
	}
}
