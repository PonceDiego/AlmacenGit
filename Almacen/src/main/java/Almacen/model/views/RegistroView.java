package main.java.Almacen.model.views;

import java.util.List;

import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Registro;

public class RegistroView{
	
	private Equipo equipo;
	private Llave llave;
	private GrupoEquipos grupoEquipos;
	private GrupoLlaves grupoLlaves;
	
	private List<Registro> registros;
	
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Llave getLlave() {
		return llave;
	}
	public void setLlave(Llave llave) {
		this.llave = llave;
	}
	public GrupoEquipos getGrupoEquipos() {
		return grupoEquipos;
	}
	public void setGrupoEquipos(GrupoEquipos grupoEquipos) {
		this.grupoEquipos = grupoEquipos;
	}
	public GrupoLlaves getGrupoLlaves() {
		return grupoLlaves;
	}
	public void setGrupoLlaves(GrupoLlaves grupoLlaves) {
		this.grupoLlaves = grupoLlaves;
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
}
