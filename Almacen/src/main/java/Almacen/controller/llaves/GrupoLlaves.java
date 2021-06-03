package main.java.Almacen.controller.llaves;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.llaves.LlaveManager;

@WebServlet("/GrupoLlaves")
public class GrupoLlaves extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GrupoLlaves() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		String nombreGrupoLlaves = req.getParameter("nombreGrupo");

		main.java.Almacen.model.GrupoLlaves grupoLlaves = LlaveManager.getGrupoLlavesByNombre(nombreGrupoLlaves);

		req.getSession().setAttribute("grupoLlaves", grupoLlaves);
		req.getSession().setAttribute("grupoLlavesEstado", LlaveManager.getGrupoLlavesEstado(grupoLlaves));

		resp.sendRedirect("view/grupoLlavesEspecifico.jsp");

	}

}
