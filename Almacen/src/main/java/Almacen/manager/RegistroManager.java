package main.java.Almacen.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.model.views.RegistroView;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LlaveDB;
import main.java.Almacen.persistence.RegistroDB;
import main.java.Almacen.persistence.UsuarioDB;

public class RegistroManager {

	public static enum TIPO_REGISTRO {
		EQUIPO("Equipo"), LLAVE("Llave"), GRUPO_EQUIPO("Grupo equipo"), GRUPO_LLAVE("Grupo llave");

		public final String label;

		private TIPO_REGISTRO(String label) {
			this.label = label;
		}

		public static TIPO_REGISTRO getByValue(String value) {
			for (TIPO_REGISTRO tradeStatus : values()) {
				if (tradeStatus.label.equals(value)) {
					return tradeStatus;
				}
			}
			return null;
		}

	}

	public static void createRegistro(boolean entrada, int user, TIPO_REGISTRO tipo, int idEntidad, int encargado) {
		System.out.println("Creando registro");

		// Creamos el registro
		Registro registro = new Registro();
		registro.setFecha(new Date());
		registro.setEntrada(entrada);

		// Traemos el user
		Usuario usuario = UsuarioDB.getUsuarioByID(user);
		registro.setUsuario(usuario);
		usuario = UsuarioDB.getUsuarioByID(encargado);
		registro.setEncargado(usuario);

		Object entidad = null;

		// Buscamos el tipo de entidad y la entidad
		switch (tipo) {
		case EQUIPO:
			entidad = EquipoDB.getEquipoByID(idEntidad);
			break;
		case LLAVE:
			entidad = LlaveDB.getLlaveById(idEntidad);
			break;
		case GRUPO_EQUIPO:
			entidad = EquipoDB.getGrupoEquipoById(idEntidad + "");
			break;
		case GRUPO_LLAVE:
			entidad = LlaveDB.getGrupoLlavesById(idEntidad + "");
			break;
		}

		if (entidad == null) {
			System.err.println("Entidad no encontrada");
			return;
		}

		registro.setEntidadId(idEntidad);
		registro.setEntidad(tipo.label);

		RegistroDB.crearRegistro(registro);
	}

	public static List<RegistroView> getRegistrosByTipoAndId(TIPO_REGISTRO tipo, int id) {
		List<RegistroView> registrosViews = new ArrayList<RegistroView>();

		List<Registro> registros = RegistroDB.getRegistrosByTipoAndId(tipo, id);

		registrosViews = registros.stream().map(x -> new RegistroView(x)).collect(Collectors.toList());

		Equipo equipo = null;
		Llave llave = null;
		GrupoEquipos grupoEquipo = null;
		GrupoLlaves grupoLlave = null;

		switch (tipo) {
		case EQUIPO:
			equipo = EquipoManager.getEquipo(id);
			break;
		case LLAVE:
			llave = LlaveManager.getLlaveById(id + "");
			break;
		case GRUPO_EQUIPO:
			grupoEquipo = EquipoManager.getGrupoEquipo(id + "");
			break;
		case GRUPO_LLAVE:
			grupoLlave = LlaveManager.getGrupoLlaveById(id + "");
			break;
		}

		for (RegistroView registro : registrosViews) {
			switch (tipo) {
			case EQUIPO:
				registro.setEquipo(equipo);
				break;
			case LLAVE:
				registro.setLlave(llave);
				break;
			case GRUPO_EQUIPO:
				registro.setGrupoEquipos(grupoEquipo);
				break;
			case GRUPO_LLAVE:
				registro.setGrupoLlaves(grupoLlave);
				break;
			}
		}

		return registrosViews;
	}

	public static List<Registro> getLastRegistrosByEntidadAndId(TIPO_REGISTRO tipo, List<Integer> ids) {
		List<Registro> registros = new ArrayList<Registro>();

		for (Integer id : ids) {
			registros.add(RegistroDB.getLastRegistroByIdAndTipo(tipo, id));
		}

		return registros;
	}

	public static List<RegistroView> getListaRegistros() {
		List<RegistroView> registrosViews = new ArrayList<RegistroView>();

		List<Registro> registros = RegistroDB.getRegistros();

		registrosViews = registros.stream().map(x -> new RegistroView(x, true)).collect(Collectors.toList());

		return registrosViews;
	}

	public static List<RegistroView> getListaRegistrosByUser(Integer id) {
		List<RegistroView> registrosViews = new ArrayList<RegistroView>();

		List<Registro> registros = RegistroDB.getRegistrosByUsuario(id);

		registrosViews = registros.stream().map(x -> new RegistroView(x, true)).collect(Collectors.toList());

		return registrosViews;
	}
}
