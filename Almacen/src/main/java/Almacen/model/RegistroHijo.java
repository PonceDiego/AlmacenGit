package main.java.Almacen.model;

public class RegistroHijo extends Registro {
	
	private Equipo equipo;
	private Llave llave;
	private GrupoEquipos grupoEquipos;
	private GrupoLlaves grupoLlaves;
	
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
