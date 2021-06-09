package main.java.Almacen.controller.equipo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.model.Equipo;

@WebServlet("/EditarGrupoEquipos")
public class EditarGrupoEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarGrupoEquipos() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		String id = req.getParameter("idAEditar");
		main.java.Almacen.model.GrupoEquipos grupo = EquipoManager.getGrupoEquipo(id);
		List<Equipo> allEquipos = EquipoManager.listarEquipos();
		List<Equipo> equipos = new ArrayList<Equipo>();
		if (grupo.getEquipos() != null || grupo.getEquipos().size() != 0) {
			for (Equipo equipo : allEquipos) {
				int contador = 0;
				for (Equipo equipoGrupo : grupo.getEquipos()) {
					if (equipoGrupo.getEquipoId() == equipo.getEquipoId()) {
						contador++;
					}
				}
				if (contador == 0) {
					equipos.add(equipo);
				}
			}
		} else {
			equipos = allEquipos;
		}
		req.getSession().setAttribute("equipos", equipos);
		req.getSession().setAttribute("grupoEquipos", grupo);

		resp.sendRedirect("view/editarGrupoEquipos.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		} else {

			String equipos;
			req.setCharacterEncoding("UTF-8");
			equipos = req.getParameter("inputEquipos");
			String id = req.getParameter("inputId");
			String[] llavesDivididas = equipos.split(",");
			EquipoManager.editGrupoEquipo(id, llavesDivididas);

			resp.sendRedirect("BuscarGrupoEquipos");
		}

	}
}
