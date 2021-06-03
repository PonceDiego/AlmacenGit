package main.java.Almacen.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.model.views.RegistroView;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LlaveDB;
import main.java.Almacen.persistence.RegistroDB;
import main.java.Almacen.persistence.UsuarioDB;

public class RegistroManager {
	
	public enum TIPO_REGISTRO {
			EQUIPO("Equipo"), 
			LLAVE("Llave"), 
			GRUPO_EQUIPO("Grupo equipo"), 
			GRUPO_LLAVE("Grupo llave");
		
			public final String label;
	
		    private TIPO_REGISTRO(String label) {
		        this.label = label;
		    }
		}

	public static void createRegistro(boolean entrada, int user, TIPO_REGISTRO tipo, int idEntidad) {
		System.out.println("Creando registro");
		
		//Creamos el registro
		Registro registro = new Registro();
		registro.setFecha(new Date());
		registro.setEntrada(entrada);
		
		//Traemos el user 
		Usuario usuario = UsuarioDB.getUsuarioByID(user);
		registro.setUsuario(usuario);
		
		Object entidad = null;
		
		//Buscamos el tipo de entidad y la entidad
		switch(tipo) {
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
		
		if(entidad == null) {
			System.err.println("Entidad no encontrada");
			return;
		}
		
		registro.setEntidadId(idEntidad);
		registro.setEntidad(tipo.label);
		
		RegistroDB.crearRegistro(registro);
	}

	public static RegistroView getRegistrosByTipoAndId(TIPO_REGISTRO tipo, int id) {
		RegistroView registroView = new RegistroView();
		
		List<Registro> registros = RegistroDB.getRegistrosByTipoAndId(tipo, id);
		
		registroView.setRegistros(registros);
		
		switch (tipo) {
			case EQUIPO:
				registroView.setEquipo(EquipoManager.getEquipo(id));
				break;
			case LLAVE:
				registroView.setLlave(LlaveManager.getLlaveById(id + ""));
				break;
			case GRUPO_EQUIPO:
				registroView.setGrupoEquipos(EquipoManager.getGrupoEquipo(id + ""));
				break;
			case GRUPO_LLAVE:
				registroView.setGrupoLlaves(LlaveManager.getGrupoLlaveById(id + ""));
				break;
		}
		
		return registroView;
	}
	
	public static List<Registro> getLastRegistrosByEntidadAndId(TIPO_REGISTRO tipo, List<Integer> ids){
		List<Registro> registros = new ArrayList<Registro>();
		
		for (Integer id : ids) {
			registros.add(RegistroDB.getLastRegistroByIdAndTipo(tipo, id));
		}
		
		return registros;
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
