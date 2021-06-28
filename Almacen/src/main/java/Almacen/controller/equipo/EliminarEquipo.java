package main.java.Almacen.controller.equipo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;

@WebServlet("/EliminarEquipo")
public class EliminarEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EliminarEquipo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {

			request.getSession(false);
			String id = request.getParameter("idEliminado");
			EquipoManager.eliminarEquipo(id);

			response.sendRedirect("ListaEquipos");
		}
	}
}
