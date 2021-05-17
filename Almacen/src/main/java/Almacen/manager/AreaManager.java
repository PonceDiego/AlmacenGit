package main.java.Almacen.manager;

import main.java.Almacen.model.Area;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.UsuarioDB;

public class AreaManager {
	
	public static void createArea(String nombre, String user) {
		Area area=new Area();
		area.setNombre(nombre);
		area.setUsuario(UsuarioDB.getUsuarioByNombreUsuario(user));
		AreaDB.agregarAreaNueva(area);
	}

	public static void EditArea(String id, String nombre, String user) {
		int idI=Integer.valueOf(id);
		AreaDB.editarArea(idI,nombre,user);
		
	}

}
