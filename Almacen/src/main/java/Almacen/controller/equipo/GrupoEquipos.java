package main.java.Almacen.controller.equipo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;

@WebServlet("/GrupoEquipos")
public class GrupoEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GrupoEquipos() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		String nombre = req.getParameter("nombreGrupo");
		boolean mostrarBoton = false;

		main.java.Almacen.model.GrupoEquipos grupoEquipos = EquipoManager.getGrupoEquipoByNombre(nombre);

		if (grupoEquipos.getEquipos() != null && grupoEquipos.getEquipos().size() > 0) {
			mostrarBoton = true;
		}
		req.getSession().setAttribute("mostrarBoton", mostrarBoton);
		req.getSession().setAttribute("grupoEquipos", grupoEquipos);
		req.getSession().setAttribute("grupoEquiposEstado", EquipoManager.getGrupoEquipoEstado(grupoEquipos));

		resp.sendRedirect("view/grupoEquiposEspecifico.jsp");

	}
}
