package main.java.Almacen.manager;

import java.util.Date;
import java.util.List;

import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LlaveDB;
import main.java.Almacen.persistence.RegistroDB;
import main.java.Almacen.persistence.UsuarioDB;

public class RegistroManager {

	public static void createRegistro(boolean entrada, int user, int id, String entidad) {
		System.out.println("Creando registro");
		Registro registro = new Registro();
		registro.setFecha(new Date());
		registro.setEntrada(entrada);
		registro.setEntidad(entidad);
		Usuario usuario = UsuarioDB.getUsuarioByID(user);
		registro.setUsuario(usuario);
		if (entidad == "Equipo") {
			Equipo equipo = EquipoDB.getEquipoByID(id);
			registro.setEntidadId(equipo.getEquipoId());

		} else if (entidad == "Llave") {
			Llave llave = LlaveDB.getLlaveById(id);
			registro.setEntidadId(llave.getLlaveId());
		} else if (entidad == null) {
			Equipo equipo = EquipoDB.getEquipoByID(id);
			registro.setEntidadId(equipo.getEquipoId());
		}

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
