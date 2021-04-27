package main.java.Almacen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "llave", catalog = "almacen")
public class Llave implements java.io.Serializable {

	@Expose(serialize = true, deserialize = true)
	private Integer llaveId;

	private String copia, identificacion, nombre, observaciones, estado;
	private Lugar ubicacion;

	public Llave() {

	}

	public Llave(String copia, String identificacion, String nombre, String estado, String observaciones,
			Lugar ubicacion) {
		this.copia = copia;
		this.estado = estado;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.observaciones = observaciones;
		this.ubicacion = ubicacion;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "llaveId", unique = true, nullable = false)
	public Integer getLlaveId() {
		return llaveId;
	}

	public void setLlaveId(Integer llaveId) {
		this.llaveId = llaveId;
	}

	@Column(name = "copia", nullable = false, length = 3)
	public String getCopia() {
		return copia;
	}

	public void setCopia(String copia) {
		this.copia = copia;
	}

	@Column(name = "identificacion", nullable = false, length = 20)
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Column(name = "nombre", nullable = false, length = 20)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "observaciones", length = 140)
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "estado", nullable = false, length = 20)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ubicacion")
	public Lugar getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Lugar ubicacion) {
		this.ubicacion = ubicacion;
	}

}
