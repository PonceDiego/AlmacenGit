package main.java.Almacen.modelviews;

import main.java.Almacen.model.Articulo;

public class ArticuloView {
	
	private ProveedorView proveedor;
	private String nombre;
	private String subcategoria;
	private String costo;
	private String stockMinimo, stock;
	
	public ProveedorView getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorView proveedor) {
		this.proveedor = proveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(String stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
	//Aca haces el parseo manual
	public static Articulo getArticuloFromView(ArticuloView view) {
		Articulo art = new Articulo();
		
		//No pudo pasar a int
		
		
		return null;
	}
	
	
}
