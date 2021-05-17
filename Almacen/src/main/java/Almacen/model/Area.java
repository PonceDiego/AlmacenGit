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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Area generated by hbm2java
 */
@Entity
@Table(name = "area", catalog = "almacen", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Area implements java.io.Serializable {

	private Integer id;
	private Usuario usuario;
	private String nombre;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Area() {
	}

	public Area(String nombre) {
		this.nombre = nombre;
	}

	public Area(Usuario usuario, String nombre, Set<Usuario> usuarios) {
		this.usuario = usuario;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_jefe")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "nombre", unique = true, nullable = false, length = 30)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
