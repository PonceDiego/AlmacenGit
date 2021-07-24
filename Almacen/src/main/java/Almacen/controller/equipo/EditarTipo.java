package main.java.Almacen.controller.equipo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.TipoManager;

@WebServlet("/EditarTipo")
public class EditarTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarTipo() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
		} else {
			String nombreTipo = req.getParameter("tipoEditar");
			req.getSession().setAttribute("listaTipos", TipoManager.getTipos());
			req.getSession().setAttribute("nombreTipoEditar", nombreTipo);
			resp.sendRedirect("view/editarTipo.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
		} else {
			String nombreNuevo = req.getParameter("inputNombre");
			String nombreEditar = (String) req.getSession().getAttribute("nombreTipoEditar");
			TipoManager.editTipo(nombreEditar, nombreNuevo);
			resp.sendRedirect("ListaTipos");
		}
	}
}
