package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.EquipoDB;
import main.java.Almacen.persistence.LlaveDB;

/**
 * Servlet implementation class ServletVisualizarQR
 */
@WebServlet("/Qr")
public class ServletVisualizarQR extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVisualizarQR() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession(false);
			String idS = request.getParameter("entidadId");
			String entidad = request.getParameter("entidad");
			int id = Integer.parseInt(idS);
			String urlQr = null;
			switch (entidad) {

			case "Articulo":
				Articulo articulo = ArticuloDB.getArticuloByID(id);
				request.getSession().setAttribute("entidadNombre", articulo.getNombre());
				urlQr = "https://api.qrserver.com/v1/create-qr-code/?data=articulo-" + articulo.getNombre() + "-"
						+ articulo.getArticuloId();
				break;
			case "Equipo":
				Equipo equipo = EquipoDB.getEquipoByID(id);
				request.getSession().setAttribute("entidadNombre", equipo.getNombre());
				urlQr = "https://api.qrserver.com/v1/create-qr-code/?data=equipo-" + equipo.getNombre() + "-"
						+ equipo.getEquipoId();
				break;
			case "GrupoEquipo":
				GrupoEquipos grupoEquipos = EquipoDB.getGrupoEquipoById(idS);
				request.getSession().setAttribute("entidadNombre", grupoEquipos.getNombre());
				urlQr = "https://api.qrserver.com/v1/create-qr-code/?data=grupoE-" + grupoEquipos.getNombre() + "-"
						+ grupoEquipos.getGrupoEquipoId();
				break;
			case "GrupoLlaves":
				main.java.Almacen.model.GrupoLlaves grupoLlaves = LlaveDB.getGrupoLlavesById(idS);
				request.getSession().setAttribute("entidadNombre", grupoLlaves.getNombre());
				urlQr = "https://api.qrserver.com/v1/create-qr-code/?data=grupoL-" + grupoLlaves.getNombre() + "-"
						+ grupoLlaves.getGrupoId();
				break;
			}
			request.getSession().setAttribute("imageSrc", urlQr);

			response.sendRedirect("view/mostrarQr.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
