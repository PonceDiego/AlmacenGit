package main.java.Almacen.controller.equipo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.TipoManager;

@WebServlet("/EliminarTipo")
public class EliminarTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EliminarTipo() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
		} else {

			req.getSession(false);
			String id = req.getParameter("idEliminado");
			TipoManager.deleteTipo(id);

			resp.sendRedirect("ListaTipos");
		}
	}
}
