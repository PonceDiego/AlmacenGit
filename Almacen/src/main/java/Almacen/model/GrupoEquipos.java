package main.java.Almacen.model;
// Generated 10/06/2021 17:00:43 by Hibernate Tools 5.2.12.Final

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
 * GrupoEquipos generated by hbm2java
 */
@Entity
@Table(name = "grupo_equipos", catalog = "almacen")
public class GrupoEquipos implements java.io.Serializable {

	private Integer grupoEquipoId;
	private String nombre;
	private Set<Equipo> equipos = new HashSet<Equipo>(0);

	public GrupoEquipos() {
	}

	public GrupoEquipos(String nombre) {
		this.nombre = nombre;
	}

	public GrupoEquipos(String nombre, Set<Equipo> equipos) {
		this.nombre = nombre;
		this.equipos = equipos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "grupo_equipo_id", unique = true, nullable = false)
	public Integer getGrupoEquipoId() {
		return this.grupoEquipoId;
	}

	public void setGrupoEquipoId(Integer grupoEquipoId) {
		this.grupoEquipoId = grupoEquipoId;
	}

	@Column(name = "nombre", nullable = false, length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupoEquipos")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

}
