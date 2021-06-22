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

@WebServlet("/ListaLlavesI")
public class ServletListaLlavesIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletListaLlavesIndividual() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		Usuario actual = (Usuario) req.getSession().getAttribute("usuarioActual");
		List<Llave> llaves = LlaveManager.getLlavesByUser(actual.getId());

		req.getSession().setAttribute("llaves", llaves);
		resp.sendRedirect("view/listaDeLlavesEnPosesion.jsp");
	}
}
