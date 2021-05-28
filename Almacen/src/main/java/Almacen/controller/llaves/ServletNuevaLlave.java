package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		
		List<Llave> listaLlaves = LlaveManager.getAllLlaves();
		List<Lugar> ubicaciones = LugarManager.getLugares();
		
		req.getSession().setAttribute("listaLlaves", listaLlaves);
		req.getSession().setAttribute("ubicaciones", ubicaciones);
		
		resp.sendRedirect("view/agregarNuevaLlave.jsp");
	}
}
