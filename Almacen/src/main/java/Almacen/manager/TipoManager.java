package main.java.Almacen.manager;

import java.util.List;

import main.java.Almacen.model.Tipo;
import main.java.Almacen.persistence.TipoDB;

public class TipoManager {
	public static void createTipo(String nombre) {
		Tipo tipo = new Tipo();
		tipo.setNombre(nombre);
		tipo.setActivo(true);
		TipoDB.crearTipo(tipo);
	}

	public static List<Tipo> getTipos() {
		return TipoDB.getTipos();
	}

	public static void editTipo(String nombreEditar, String nombreNuevo) {
		Tipo tipo = TipoDB.getTipoByNombre(nombreEditar);
		TipoDB.editarTipo(nombreNuevo, tipo.getId());
	}

	public static void deleteTipo(String id) {
		TipoDB.deleteTipo(Integer.parseInt(id));
	}

}
