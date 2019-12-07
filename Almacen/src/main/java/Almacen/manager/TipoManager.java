package main.java.Almacen.manager;

import java.util.List;

import main.java.Almacen.model.Tipo;
import main.java.Almacen.persistence.TipoDB;

public class TipoManager {
	public static void createTipo(String nombre) {
		Tipo tipo= new Tipo();
		tipo.setNombre(nombre);
		TipoDB.crearTipo(tipo);
	}
	public static List<Tipo> getTipos(){
		return TipoDB.getTipos();
	}
//	public static void editTipo(String idEditar) {
//		int id=Integer.parseInt(idEditar);
//		TipoDB.editarTipo(nombre, idT);
//	}

}
