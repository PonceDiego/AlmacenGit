package main.java.Almacen.manager.llaves;

import main.java.Almacen.model.Llave;
import main.java.Almacen.persistence.LlaveDB;

public class LlaveManager {
	public static Llave getLlaveById(String id) {
		int idi = Integer.parseInt(id);
		return LlaveDB.getLlaveById(idi);
	}
}