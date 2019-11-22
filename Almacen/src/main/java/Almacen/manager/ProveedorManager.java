package main.java.Almacen.manager;

import main.java.Almacen.model.Proveedor;
import main.java.Almacen.persistence.ProveedoresDB;

public class ProveedorManager {

	public static void createProveedor(String nombre, String mail, String telefono, String contacto, String direccion) {

		Proveedor proveedorNuevo = new Proveedor();

		proveedorNuevo.setProvNombre(nombre);
		proveedorNuevo.setProvContacto(contacto);
		proveedorNuevo.setProvDireccion(direccion);
		proveedorNuevo.setProvMail(mail);
		proveedorNuevo.setProvTel(telefono);
		ProveedoresDB.agregarProveedorNuevo(proveedorNuevo);
	}

	public static void editarProveedor(String id, String nombre, String direccion, String mail, String telefono,
			String contacto) {
		ProveedoresDB.editarProveedor(id, nombre, direccion, mail, telefono, contacto);
		
	}

}
