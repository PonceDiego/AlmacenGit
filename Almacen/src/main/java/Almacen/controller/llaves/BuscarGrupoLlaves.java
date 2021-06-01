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

@WebServlet("/BuscarGrupoLlaves")
public class BuscarGrupoLlaves extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public BuscarGrupoLlaves() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		
		List<Llave> llaves = LlaveManager.getAllLlaves();
		
		List<String> nombresGruposLlaves = LlaveManager.listarNombresGrupoLlaves();
		
		req.getSession().setAttribute("nombresGruposLlaves", nombresGruposLlaves);
		
		resp.sendRedirect("view/buscarGrupoLlaves.jsp");
		
	}
	
	
}
