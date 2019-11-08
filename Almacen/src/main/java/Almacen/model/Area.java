package main.java.Almacen.model;
// Generated 4/11/2019 12:28:02 PM by Hibernate Tools 5.0.6.Final

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
@Table(name = "area", catalog = "almacen", uniqueConstraints = @UniqueConstraint(columnNames = "NombreArea"))
public class Area implements java.io.Serializable {

	private Integer areaId;
	private Usuario usuario;
	private String nombreArea;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Area() {
	}

	public Area(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public Area(Usuario usuario, String nombreArea, Set<Usuario> usuarios) {
		this.usuario = usuario;
		this.nombreArea = nombreArea;
		this.usuarios = usuarios;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "AreaID", unique = true, nullable = false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioJefe")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "NombreArea", unique = true, nullable = false, length = 30)
	public String getNombreArea() {
		return this.nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
