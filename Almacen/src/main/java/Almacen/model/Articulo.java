package main.java.Almacen.model;
// Generated 24/10/2019 01:28:29 PM by Hibernate Tools 5.0.6.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * Articulo generated by hbm2java
 */
@Entity
@Table(name = "articulo", catalog = "almacen")
public class Articulo implements java.io.Serializable {

	@Expose (serialize = true, deserialize = true) 
	private Integer articuloId;
	
	@Expose (serialize = true, deserialize = true)
	private Estadoarticulo estadoarticulo;
	
	
	private Proveedor proveedor;
	
	
	@Expose (serialize = true, deserialize = true) 
	private Subcategoria subcategoria;
	
	
	@Expose (serialize = true, deserialize = true) 
	private String nombre;
	
	
	private double costo;
	
	
	private Date fechaAgregado;
	
	
	private int stockMinimo;
	
	@Expose (serialize = true, deserialize = true) 
	private int stock;
	private String codigoQr;
	private Set<Pedidoxarticulos> pedidoxarticuloses = new HashSet<Pedidoxarticulos>(0);

	public Articulo() {
	}

	public Articulo(Estadoarticulo estadoarticulo, Proveedor proveedor, Subcategoria subcategoria, String nombre,
			double costo, Date fechaAgregado, int stockMinimo, int stock) {
		this.estadoarticulo = estadoarticulo;
		this.proveedor = proveedor;
		this.subcategoria = subcategoria;
		this.nombre = nombre;
		this.costo = costo;
		this.fechaAgregado = fechaAgregado;
		this.stockMinimo = stockMinimo;
		this.stock = stock;
	}

	public Articulo(Estadoarticulo estadoarticulo, Proveedor proveedor, Subcategoria subcategoria, String nombre,
			double costo, Date fechaAgregado, int stockMinimo, int stock, String codigoQr,
			Set<Pedidoxarticulos> pedidoxarticuloses) {
		this.estadoarticulo = estadoarticulo;
		this.proveedor = proveedor;
		this.subcategoria = subcategoria;
		this.nombre = nombre;
		this.costo = costo;
		this.fechaAgregado = fechaAgregado;
		this.stockMinimo = stockMinimo;
		this.stock = stock;
		this.codigoQr = codigoQr;
		this.pedidoxarticuloses = pedidoxarticuloses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ArticuloID", unique = true, nullable = false)
	public Integer getArticuloId() {
		return this.articuloId;
	}

	public void setArticuloId(Integer articuloId) {
		this.articuloId = articuloId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Estado", nullable = false)
	public Estadoarticulo getEstadoarticulo() {
		return this.estadoarticulo;
	}

	public void setEstadoarticulo(Estadoarticulo estadoarticulo) {
		this.estadoarticulo = estadoarticulo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proveedor", nullable = false)
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Categoria", nullable = false)
	public Subcategoria getSubcategoria() {
		return this.subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	@Column(name = "Nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Costo", nullable = false, precision = 22, scale = 0)
	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAgregado", nullable = false, length = 10)
	public Date getFechaAgregado() {
		return this.fechaAgregado;
	}

	public void setFechaAgregado(Date fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
	}

	@Column(name = "StockMinimo", nullable = false)
	public int getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	@Column(name = "Stock", nullable = false)
	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name = "codigoQR", length = 100)
	public String getCodigoQr() {
		return this.codigoQr;
	}

	public void setCodigoQr(String codigoQr) {
		this.codigoQr = codigoQr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo")
	public Set<Pedidoxarticulos> getPedidoxarticuloses() {
		return this.pedidoxarticuloses;
	}

	public void setPedidoxarticuloses(Set<Pedidoxarticulos> pedidoxarticuloses) {
		this.pedidoxarticuloses = pedidoxarticuloses;
	}

}
