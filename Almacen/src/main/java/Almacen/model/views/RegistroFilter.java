package main.java.Almacen.model.views;

public class RegistroFilter {
	private String filtroDesde;
	private String filtroHasta;
	private String filtroUsuario;
	private String filtroEntidad;
	private String filtroEstado;
	
	public RegistroFilter(String filtroDesde, String filtroHasta, String filtroUsuario, String filtroEntidad,
			String filtroEstado) {
		super();
		this.filtroDesde = filtroDesde;
		this.filtroHasta = filtroHasta;
		this.filtroUsuario = filtroUsuario;
		this.filtroEntidad = filtroEntidad;
		this.filtroEstado = filtroEstado;
	}
	
	public boolean tieneFiltro() {
		return ( (filtroDesde != null && !filtroDesde.equals("")) ||
				 (filtroHasta != null && !filtroHasta.equals("")) ||
				 (filtroUsuario != null && !filtroUsuario.equals("")) ||
				 (filtroEntidad != null && !filtroEntidad.equals("")) ||
				 (filtroEstado != null && !filtroEstado.equals(""))
				);
	}
	

	public String getFiltroDesde() {
		return filtroDesde;
	}

	public void setFiltroDesde(String filtroDesde) {
		this.filtroDesde = filtroDesde;
	}

	public String getFiltroHasta() {
		return filtroHasta;
	}

	public void setFiltroHasta(String filtroHasta) {
		this.filtroHasta = filtroHasta;
	}

	public String getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(String filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public String getFiltroEntidad() {
		return filtroEntidad;
	}

	public void setFiltroEntidad(String filtroEntidad) {
		this.filtroEntidad = filtroEntidad;
	}

	public String getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}
	
	
}
