package main.java.Almacen.manager;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LugarDB;
import main.java.Almacen.persistence.RegistroDB;
import main.java.Almacen.persistence.TipoDB;
import main.java.Almacen.persistence.UsuarioDB;

public class EquipoManager {
	public static void createEquipo(String serial, String nombre, String tipo, String lugar, String modelo,
			String usuario, String observaciones, String accesorios, Usuario userActual) {
		Equipo equipo = new Equipo();
		equipo.setLugar(LugarDB.getLugarByNombre(lugar));
		equipo.setTipo(TipoDB.getTipoByNombre(tipo));
		equipo.setNombre(nombre);

		if (serial == null) {
			equipo.setSerial("N/A");
		} else
			equipo.setSerial(serial);

		if (modelo == null) {
			equipo.setModelo("N/A");
		} else
			equipo.setModelo(modelo);

		if (usuario != null) {
			equipo.setUsuario(UsuarioDB.getUsuarioByNombreUsuario(usuario));
		}
		if (observaciones == null) {
			equipo.setObservaciones("N/A");
		} else
			equipo.setObservaciones(observaciones);

		if (accesorios == null) {
			equipo.setAccesorios("N/A");
		} else
			equipo.setAccesorios(accesorios);

		equipo.setEstado("Disponible");

		Serializable idS = EquipoDB.crearEquipo(equipo);
		int id = (int) idS;
		Equipo e = EquipoDB.getEquipoByID(id);
		System.out.println(e.getNombre());

		RegistroDB.crearRegistro(e, userActual);

	}

	public static void changeStatus(int user, int id) {
		System.out.println("Cambiando el estado del equipo número " + id);
		EquipoDB.cambiarEstado(user, id);
	}

	public static List<Equipo> listarEquipos() {
		return EquipoDB.getListaEquipos();
	}

	public static List<Equipo> listarTodosEquipos() {
		return EquipoDB.getListaEquiposCompleta();
	}

	public static void createGrupoEquipos(String nombre, String[] equipos) {
		EquipoDB.crearGrupoEquipo(nombre, equipos);
	}

	public static List<String> listarNombresGrupoEquipos() {
		List<GrupoEquipos> equipos = EquipoDB.getListaGrupoEquipos();

		return equipos.stream().map(x -> x.getNombre()).collect(Collectors.toList());
	}

	public static GrupoEquipos getGrupoEquipo(String id) {
		return EquipoDB.getGrupoEquipoById(id);
	}

	public static String getGrupoEquipoEstado(GrupoEquipos equipos) {
		String estado = "Disponible";

		int contadorDisponible = equipos.getEquipos().stream().filter(o -> o.getEstado().equals("Disponible"))
				.collect(Collectors.toList()).size();
		int contadorUso = equipos.getEquipos().stream().filter(o -> o.getEstado().equals("En uso"))
				.collect(Collectors.toList()).size();
		int sizeEquipos = equipos.getEquipos().size();

		if (contadorDisponible == sizeEquipos) {
			estado = "Disponible";
		} else if (contadorUso == sizeEquipos) {
			estado = "En uso";
		} else {
			estado = "Parcial";
		}

		return estado;
	}

	public static Equipo getEquipo(int id) {
		return EquipoDB.getEquipoByID(id);
	}

	public static GrupoEquipos getGrupoEquipoByNombre(String nombre) {
		return EquipoDB.getGrupoEquipoByNombre(nombre);
	}
}
