package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.Almacen.manager.LugarManager;
import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Lugar;

@WebServlet("/NuevaLlave")
public class ServletNuevaLlave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletNuevaLlave() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}

		List<Llave> listaLlaves = LlaveManager.getAllLlaves();
		List<Lugar> ubicaciones = LugarManager.getLugares();

		req.getSession().setAttribute("listaLlaves", listaLlaves);
		List<String> llavesCopias = new ArrayList<String>();
		for (Llave llave : listaLlaves) {
			llavesCopias.add(llave.getNombre() + " - " + llave.getCopia());
		}
		String gson = new Gson().toJson(llavesCopias);
		System.out.println(gson);
		req.getSession().setAttribute("llavesCopias", gson);
		req.getSession().setAttribute("ubicaciones", ubicaciones);

		resp.sendRedirect("view/agregarNuevaLlave.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {

			resp.sendRedirect("Index");
			return;
		} else {
			String nombre, copia, ubicacion, observaciones;
			req.setCharacterEncoding("UTF-8");
			nombre = req.getParameter("inputNombre");
			copia = req.getParameter("inputCopia");
			ubicacion = req.getParameter("inputUbicacion");
			System.out.println(ubicacion);
			observaciones = req.getParameter("inputObservaciones");
			LlaveManager.crearLlave(nombre, copia, ubicacion, observaciones);
			resp.sendRedirect("ListaLlaves");
		}

	}
}
