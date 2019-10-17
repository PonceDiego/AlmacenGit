package main.java.Almacen.manager;

import main.java.Almacen.model.Area;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.UsuarioDB;

public class AreaManager {
	
	public static void createArea(String nombre, String user) {
		Area area=new Area();
		area.setNombreArea(nombre);
		area.setUsuario(UsuarioDB.getUsuarioByNombre(user));
		AreaDB.agregarAreaNueva(area);
	}

}
