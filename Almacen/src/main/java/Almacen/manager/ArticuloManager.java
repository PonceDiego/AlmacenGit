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
			String nombreProv = proveedor.replace("-", "");
			prov = ProveedoresDB.getProveedorByNombre(nombreProv);
			
			
		}else {
			prov=ProveedoresDB.getProveedorByStringId(proveedor);
			
		}
		articuloNuevo.setProveedor(prov);

		articuloNuevo.setNombre(nombre);
		
		Subcategoria cat =null;
		if(categoria.contains("-")) {
			String input = categoria.replace("-", "");
			cat = SubcategoriaDB.getSubcategoriaByNombre(input);
			
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
	public static void editarArticulo(String ids, String subc, String proveedor, String nombre, String stockMinimoS,
			String stockMaximoS, String costoS) {
		int id= Integer.valueOf(ids);
		double costo= Double.valueOf(costoS);
		int stockMaximo= Integer.valueOf(stockMaximoS);
		int stockMinimo= Integer.valueOf(stockMinimoS);
		if(proveedor.contains("-")) {
			proveedor=proveedor.substring(1);
		}
		if(subc.contains("-")) {
			subc=subc.substring(1);
		}
		ArticuloDB.editarArticulo(id, subc, proveedor,nombre,stockMinimo,stockMaximo,costo);
		
	}
}
