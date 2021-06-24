package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Usuario;

@WebServlet("/NuevoGrupo")
public class NuevoGrupoLlave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NuevoGrupoLlave() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		List<Llave> llaves = LlaveManager.getAllLlaves();

		req.getSession().setAttribute("llaves", llaves);

		resp.sendRedirect("view/agregarNuevoGrupoLlaves.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		} else {

			String nombre;
			String llaves;
			req.setCharacterEncoding("UTF-8");
			nombre = req.getParameter("inputNombre");
			llaves = req.getParameter("inputLlaves");
			String[] llavesDivididas = llaves.split(",");
			
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioActual");
			
			LlaveManager.createGrupoLlaves(nombre, llavesDivididas, usuario);

			resp.sendRedirect("BuscarGrupoLlaves");
		}

	}

}
