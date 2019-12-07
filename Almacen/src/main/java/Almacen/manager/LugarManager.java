package main.java.Almacen.manager;

import java.util.List;

import main.java.Almacen.model.Lugar;
import main.java.Almacen.persistence.LugarDB;

public class LugarManager {
	public static void createLugar(String nombre,String des) {
		Lugar lugar= new Lugar();
		lugar.setNombre(nombre);
		lugar.setDescripcion(des);
		LugarDB.crearLugar(lugar);
	}

	public static List<Lugar> getLugares() {
		return LugarDB.getLugares();
	}
	public static Lugar getLugarByNombre(String nombre) {
		return LugarDB.getLugarByNombre(nombre);
	}
}
