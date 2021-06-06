package main.java.Almacen.controller.llaves;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.LugarManager;
import main.java.Almacen.manager.llaves.LlaveManager;

@WebServlet("/EditarLlave")
public class EditarLlave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarLlave() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		String nombreEditado = req.getParameter("nombreEditado");
		String copia = req.getParameter("copia");

		req.getSession().setAttribute("llaveAEditar", LlaveManager.getLlaveByNombre(nombreEditado, copia));
		req.getSession().setAttribute("ubicaciones", LugarManager.getLugares());
		resp.sendRedirect("view/editarLlave.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		String inputNombre, inputCopia, inputUbicacion, inputObservaciones;
		inputNombre = req.getParameter("inputNombre");
		inputCopia = req.getParameter("inputCopia");
		inputUbicacion = req.getParameter("inputUbicacion");
		inputObservaciones = req.getParameter("inputObservaciones");
		String inputId = req.getParameter("hiddenIdLlave");
		LlaveManager.editLlave(inputId, inputNombre, inputCopia, inputUbicacion, inputObservaciones);
		resp.sendRedirect("ListaLlaves");

	}

}
