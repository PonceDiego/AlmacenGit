package main.java.Almacen.model;
// Generated 22/06/2021 08:43:07 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Llave generated by hbm2java
 */
@Entity
@Table(name = "llave", catalog = "almacen")
public class Llave implements java.io.Serializable {

	private Integer llaveId;
	private GrupoLlaves grupoLlaves;
	private Lugar lugar;
	private String copia;
	private String nombre;
	private String observaciones;
	private String estado;

	public Llave() {
	}

	public Llave(Lugar lugar, String copia, String nombre, String estado) {
		this.lugar = lugar;
		this.copia = copia;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Llave(GrupoLlaves grupoLlaves, Lugar lugar, String copia, String nombre, String observaciones,
			String estado) {
		this.grupoLlaves = grupoLlaves;
		this.lugar = lugar;
		this.copia = copia;
		this.nombre = nombre;
		this.observaciones = observaciones;
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "llave_id", unique = true, nullable = false)
	public Integer getLlaveId() {
		return this.llaveId;
	}

	public void setLlaveId(Integer llaveId) {
		this.llaveId = llaveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupo_id")
	public GrupoLlaves getGrupoLlaves() {
		return this.grupoLlaves;
	}

	public void setGrupoLlaves(GrupoLlaves grupoLlaves) {
		this.grupoLlaves = grupoLlaves;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ubicacion", nullable = false)
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@Column(name = "copia", nullable = false, length = 3)
	public String getCopia() {
		return this.copia;
	}

	public void setCopia(String copia) {
		this.copia = copia;
	}

	@Column(name = "nombre", nullable = false, length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "observaciones", length = 140)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "estado", nullable = false, length = 20)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
