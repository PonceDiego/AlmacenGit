package main.java.Almacen.manager;

import java.util.Date;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.model.Proveedor;
import main.java.Almacen.model.Subcategoria;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.EstadoArticuloDB;
import main.java.Almacen.persistence.ProveedoresDB;
import main.java.Almacen.persistence.SubcategoriaDB;

public class ArticuloManager {
	public static void editArticuloQr(String nombre, String qr) {
		ArticuloDB.editarArticuloQr(nombre, qr);
	}
	public static void editArticuloStock(String id,String cantidad) {
		ArticuloDB.editarArticuloStock(id, cantidad);
	}
	public static void createArticulo(String categoria, String proveedor, String nombre,
			String stockMinimo, String stockMaximo, String costo) {

		Articulo articuloNuevo = new Articulo();


		double costoFloat = -1;
		try {
			costoFloat = Double.parseDouble(costo);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		articuloNuevo.setCosto(costoFloat);

		int stockM = 0;
		try {
			stockM = Integer.parseInt(stockMinimo);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		articuloNuevo.setStockMinimo(stockM);

		int stock = 0;
		try {
			stock = Integer.parseInt(stockMaximo);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		articuloNuevo.setStock(stock);
		Proveedor prov=null;
		if(proveedor.contains("-")) {
			String[] inputProv = proveedor.split(" ");
			prov = ProveedoresDB.getProveedorByStringId(inputProv[0].toString());
			
			
		}else {
			prov=ProveedoresDB.getProveedorByStringId(proveedor);
			
		}
		articuloNuevo.setProveedor(prov);

		articuloNuevo.setNombre(nombre);
		
		Subcategoria cat =null;
		if(categoria.contains("-")) {
			String[] input = categoria.split(" ");
			cat = SubcategoriaDB.getCategoriaById(input[0].toString());
			
		}else {
			cat=SubcategoriaDB.getCategoriaById(categoria);
		}

		articuloNuevo.setSubcategoria(cat);

		if (stock > stockM) {
			articuloNuevo.setEstadoarticulo(EstadoArticuloDB.getEstadoById(1));
		} else
			articuloNuevo.setEstadoarticulo(EstadoArticuloDB.getEstadoById(2));
		
		Date date=new Date();
		articuloNuevo.setFechaAgregado(date);
		
		String qr="https://api.qrserver.com/v1/create-qr-code/?data="+nombre;
		articuloNuevo.setCodigoQr(qr);

		ArticuloDB.agregarArticuloNuevo(articuloNuevo);

	}
}
