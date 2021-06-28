package main.java.Almacen.controller.equipo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Usuario;

@WebServlet("/NuevoGrupoEquipo")
public class NuevoGrupoEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NuevoGrupoEquipo() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		List<Equipo> equipos = EquipoManager.listarEquipos();

		req.getSession().setAttribute("equipos", equipos);

		resp.sendRedirect("view/agregarNuevoGrupoEquipos.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		} else {

			String nombre;
			String equipos;
			req.setCharacterEncoding("UTF-8");
			nombre = req.getParameter("inputNombre");
			equipos = req.getParameter("inputEquipos");
			String[] equipoDivididos = equipos.split(",");

			Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioActual");

			EquipoManager.createGrupoEquipos(nombre, equipoDivididos, usuario);

			resp.sendRedirect("BuscarGrupoEquipos");
		}

	}

}
