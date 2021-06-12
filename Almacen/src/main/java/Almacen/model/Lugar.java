package main.java.Almacen.model;
// Generated 12/06/2021 00:13:05 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Lugar generated by hbm2java
 */
@Entity
@Table(name = "lugar", catalog = "almacen")
public class Lugar implements java.io.Serializable {

	private Integer lugarId;
	private String nombre;
	private String descripcion;
	private Set<Llave> llaves = new HashSet<Llave>(0);
	private Set<Equipo> equipos = new HashSet<Equipo>(0);

	public Lugar() {
	}

	public Lugar(String descripcion) {
		this.descripcion = descripcion;
	}

	public Lugar(String nombre, String descripcion, Set<Llave> llaves, Set<Equipo> equipos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.llaves = llaves;
		this.equipos = equipos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "lugar_id", unique = true, nullable = false)
	public Integer getLugarId() {
		return this.lugarId;
	}

	public void setLugarId(Integer lugarId) {
		this.lugarId = lugarId;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", nullable = false, length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	public Set<Llave> getLlaves() {
		return this.llaves;
	}

	public void setLlaves(Set<Llave> llaves) {
		this.llaves = llaves;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

}
