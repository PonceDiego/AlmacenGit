package main.java.Almacen.model;
// Generated 16-may-2021 18:14:35 by Hibernate Tools 5.2.12.Final

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
 * GrupoLlaves generated by hbm2java
 */
@Entity
@Table(name = "grupo_llaves", catalog = "almacen")
public class GrupoLlaves implements java.io.Serializable {

	private Integer grupoId;
	private String nombre;
	private Set<Llave> llaves = new HashSet<Llave>(0);

	public GrupoLlaves() {
	}

	public GrupoLlaves(String nombre) {
		this.nombre = nombre;
	}

	public GrupoLlaves(String nombre, Set<Llave> llaves) {
		this.nombre = nombre;
		this.llaves = llaves;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "grupo_id", unique = true, nullable = false)
	public Integer getGrupoId() {
		return this.grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	@Column(name = "nombre", nullable = false, length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupoLlaves")
	public Set<Llave> getLlaves() {
		return this.llaves;
	}

	public void setLlaves(Set<Llave> llaves) {
		this.llaves = llaves;
	}

}
