package main.java.Almacen.controller.equipo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;

@WebServlet("/BuscarGrupoEquipos")
public class BuscarGrupoEquipos extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public BuscarGrupoEquipos() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		
		List<String> nombresGruposEquipos = EquipoManager.listarNombresGrupoEquipos();
		
		req.getSession().setAttribute("nombresGruposEquipos", nombresGruposEquipos);
		
		resp.sendRedirect("view/buscarGrupoEquipos.jsp");
	}
}
