package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Usuario;

/**
 * Servlet implementation class ServletCambioEstado
 */
@WebServlet("/CambioEstado")
public class ServletCambioEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCambioEstado() {
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
			Usuario actual = (Usuario) request.getSession().getAttribute("usuarioActual");
			String id = (String) request.getParameter("cambioId");
			String entidad = (String) request.getParameter("entidad");
			String idUsuarioSolicitante = (String) request.getParameter("solicitanteId");
			if (entidad.equals("Llave")) {
				LlaveManager.changeStatus(actual.getId(), id, "13");
				response.sendRedirect("ListaLlaves");
			} else if (entidad.equals("Equipo")) {

				EquipoManager.changeStatus(actual.getId(), Integer.parseInt(id));
				response.sendRedirect("ListaEquipos");
			}
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
