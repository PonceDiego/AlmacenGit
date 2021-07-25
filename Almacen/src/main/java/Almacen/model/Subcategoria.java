package main.java.Almacen.model;
// Generated 25/07/2021 16:27:06 by Hibernate Tools 5.2.12.Final

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

/**
 * Subcategoria generated by hbm2java
 */
@Entity
@Table(name = "subcategoria", catalog = "almacen")
public class Subcategoria implements java.io.Serializable {

	private Integer id;
	private Categoria categoria;
	private String nombre;
	private boolean activo;
	private Set<Articulo> articulos = new HashSet<Articulo>(0);

	public Subcategoria() {
	}

	public Subcategoria(Categoria categoria, String nombre, boolean activo) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Subcategoria(Categoria categoria, String nombre, boolean activo, Set<Articulo> articulos) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.activo = activo;
		this.articulos = articulos;
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
	@JoinColumn(name = "padre", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "activo", nullable = false)
	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategoria")
	public Set<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

}
