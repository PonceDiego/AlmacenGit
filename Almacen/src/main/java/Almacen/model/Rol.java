package main.java.Almacen.model;
// Generated 27/06/2021 17:05:15 by Hibernate Tools 5.2.12.Final

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
import javax.persistence.UniqueConstraint;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol", catalog = "almacen", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Rol implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Rol() {
	}

	public Rol(String nombre) {
		this.nombre = nombre;
	}

	public Rol(String nombre, Set<Usuario> usuarios) {
		this.nombre = nombre;
		this.usuarios = usuarios;
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

	@Column(name = "nombre", unique = true, nullable = false, length = 30)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
