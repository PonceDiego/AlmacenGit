package main.java.Almacen.manager.llaves;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import main.java.Almacen.manager.MailManager;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.persistence.LlaveDB;

public class LlaveManager {

	public static void crearLlave(String nombre, String copia, String ubicacion, String observaciones) {
		LlaveDB.crearLlave(nombre, copia, ubicacion, observaciones);
	}

	public static Llave getLlaveById(String id) {
		int idi = Integer.parseInt(id);
		return LlaveDB.getLlaveById(idi);
	}

	public static List<Llave> getAllLlaves() {
		return LlaveDB.getAllLlaves();
	}

	public static List<String> listarNombresGrupoLlaves() {
		List<GrupoLlaves> llaves = LlaveDB.getAllGrupoLlaves();

		return llaves.stream().map(x -> x.getNombre()).collect(Collectors.toList());
	}

	public static void createGrupoLlaves(String nombre, String[] llaves) {
		LlaveDB.crearGrupoLlave(nombre, llaves);
	}

	public static GrupoLlaves getGrupoLlaveById(String id) {
		return LlaveDB.getGrupoLlavesById(id);
	}

	public static String getGrupoLlavesEstado(GrupoLlaves llaves) {
		String estado = "Disponible";

		int contadorDisponible = llaves.getLlaves().stream().filter(o -> o.getEstado().equals("Disponible"))
				.collect(Collectors.toList()).size();
		int contadorUso = llaves.getLlaves().stream().filter(o -> o.getEstado().equals("En uso"))
				.collect(Collectors.toList()).size();
		int sizeEquipos = llaves.getLlaves().size();

		if (contadorDisponible == sizeEquipos) {
			estado = "Disponible";
		} else if (contadorUso == sizeEquipos) {
			estado = "En uso";
		} else {
			estado = "Parcial";
		}

		return estado;
	}

	public static void changeStatus(int idEncargadoString, String idLlave, String idUserString) {
//		LlaveDB.cambiarEstado(idUserString, idLlave, idEncargadoString);
		try {
			MailManager.mailLlaves(idEncargadoString, idLlave, idUserString);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public static GrupoLlaves getGrupoLlavesByNombre(String nombreGrupoLlaves) {
		return LlaveDB.getGrupoLllavesByNombre(nombreGrupoLlaves);
	}

	public static Llave getLlaveByNombre(String nombreEditado, String copia) {
		return LlaveDB.getLlaveByNombre(nombreEditado, copia);
	}

	public static void editLlave(String inputId, String inputNombre, String inputCopia, String inputUbicacion,
			String inputObservaciones) {
		LlaveDB.editLlave(inputId, inputCopia, inputNombre, inputObservaciones, inputUbicacion);

	}

	public static void editGrupoLlave(String id, String[] llaves) {
		LlaveDB.editGrupoLlave(id, llaves);
	}

}
