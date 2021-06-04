package main.java.Almacen.model.views;

import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Registro;

public class RegistroView extends Registro{
	
	private Equipo equipo;
	private Llave llave;
	private GrupoEquipos grupoEquipos;
	private GrupoLlaves grupoLlaves;
	
	
	
	public RegistroView() {
		super();
	}
	public RegistroView(Registro x) {
		super(x.getUsuario(), x.getFecha(), x.getEntrada(), x.getEntidadId(), x.getEntidad());
	}
	
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
	
}
