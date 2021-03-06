package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Usuario;

@WebServlet("/ListaLlaves")
public class ServletListaLlaves extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletListaLlaves() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		List<Llave> llaves = LlaveManager.getAllLlaves();
		List<String> usuarios = UsuarioManager.listarUsuariosString();

		Usuario actual = (Usuario) req.getSession().getAttribute("usuarioActual");
		req.getSession().setAttribute("usuarioActual", actual);
		req.getSession().setAttribute("llaves", llaves);
		req.getSession().setAttribute("usuarios", usuarios);

		resp.sendRedirect("view/listaDeLlaves.jsp");
	}
}
