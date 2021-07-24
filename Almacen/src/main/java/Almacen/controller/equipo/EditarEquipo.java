package main.java.Almacen.controller.equipo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.LugarManager;
import main.java.Almacen.manager.TipoManager;
import main.java.Almacen.persistence.UsuarioDB;

@WebServlet("/EditarEquipo")
public class EditarEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarEquipo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession().setAttribute("listaEquipos", EquipoManager.listarTodosEquipos());
			request.getSession().setAttribute("listaTipos", TipoManager.getTipos());
			request.getSession().setAttribute("listaLugares", LugarManager.getLugares());
			request.getSession().setAttribute("listaUsuarios", UsuarioDB.getUsersActivos());
			String id = (String) request.getParameter("equipoEditar");
			request.getSession().setAttribute("Equipo", EquipoManager.getEquipo(Integer.parseInt(id)));
			response.sendRedirect("view/editarEquipo.jsp");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
