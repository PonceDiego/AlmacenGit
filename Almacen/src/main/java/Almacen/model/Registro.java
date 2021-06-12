package main.java.Almacen.model;
// Generated 12/06/2021 00:13:05 by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Registro generated by hbm2java
 */
@Entity
@Table(name = "registro", catalog = "almacen")
public class Registro implements java.io.Serializable {

	private Integer id;
	private Usuario usuarioByUsuario;
	private Usuario usuarioByEncargado;
	private Date fecha;
	private Boolean entrada;
	private int entidadId;
	private String entidad;

	public Registro() {
	}

	public Registro(int entidadId, String entidad) {
		this.entidadId = entidadId;
		this.entidad = entidad;
	}

	public Registro(Usuario usuarioByUsuario, Date fecha, Boolean entrada, int entidadId, String entidad) {
		this.usuarioByUsuario = usuarioByUsuario;
		this.fecha = fecha;
		this.entrada = entrada;
		this.entidadId = entidadId;
		this.entidad = entidad;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	public Usuario getUsuarioByUsuario() {
		return this.usuarioByUsuario;
	}

	public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
		this.usuarioByUsuario = usuarioByUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encargado")
	public Usuario getUsuarioByEncargado() {
		return this.usuarioByEncargado;
	}

	public void setUsuarioByEncargado(Usuario usuarioByEncargado) {
		this.usuarioByEncargado = usuarioByEncargado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", length = 19)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "entrada")
	public Boolean getEntrada() {
		return this.entrada;
	}

	public void setEntrada(Boolean entrada) {
		this.entrada = entrada;
	}

	@Column(name = "entidad_id", nullable = false)
	public int getEntidadId() {
		return this.entidadId;
	}

	public void setEntidadId(int entidadId) {
		this.entidadId = entidadId;
	}

	@Column(name = "entidad", nullable = false, length = 20)
	public String getEntidad() {
		return this.entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

}
