package main.java.Almacen.manager;

import java.util.Date;
import java.util.List;

import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LlaveDB;
import main.java.Almacen.persistence.RegistroDB;
import main.java.Almacen.persistence.UsuarioDB;

public class RegistroManager {

	public static void createRegistro(boolean entrada, int user, int equip) {
		System.out.println("Creando registro");
		Registro registro= new Registro();
		registro.setFecha(new Date());
		registro.setEntrada(entrada);
		
		Equipo equipo=EquipoDB.getEquipoByID(equip);
		Usuario usuario=UsuarioDB.getUsuarioByID(user);
		registro.setEntidad("Equipo");
		registro.setEntidadId(equipo.getEquipoId());
		registro.setUsuario(usuario);
		
		RegistroDB.crearRegistro(registro);
	}

	public static List<Registro> getRegistrosByEquipo(int id) {
		return RegistroDB.getRegistrosByEquipo(id);
	}

	public static List<Registro> getListaRegistros() {
		
		List<Registro> registros = RegistroDB.getRegistros();
		for (Registro registro : registros) {
			switch (registro.getEntidad()) {
			case "Equipo":
				EquipoDB.getEquipoByID(registro.getEntidadId());
				break;
			case "Llave":
				LlaveDB.getLlaveById(registro.getEntidadId());
				break;
			default:
				break;
			}
		}
		
		return null;
	}
}
