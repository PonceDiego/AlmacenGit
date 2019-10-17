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

	public static void createArticulo(String categoria, String proveedor, String nombre,
			String stockMinimo, String stockMaximo, String costo) {

		Articulo articuloNuevo = new Articulo();


//TODO: validar si está cargando un número.		
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

		String[] inputProv = proveedor.split(" ");
		Proveedor prov = ProveedoresDB.getProveedorByStringId(inputProv[0].toString());
		articuloNuevo.setProveedor(prov);

		articuloNuevo.setNombre(nombre);

		String[] input = categoria.split(" ");
		System.out.println(categoria); System.out.println(input[0]);
		Subcategoria cat = SubcategoriaDB.getCategoriaById(input[0].toString());
		articuloNuevo.setSubcategoria(cat);

		if (stock > stockM) {
			articuloNuevo.setEstadoarticulo(EstadoArticuloDB.getEstadoById(1));
		} else
			articuloNuevo.setEstadoarticulo(EstadoArticuloDB.getEstadoById(2));
		
		Date date=new Date();
		articuloNuevo.setFechaAgregado(date);

		ArticuloDB.agregarArticuloNuevo(articuloNuevo);

	}
}
