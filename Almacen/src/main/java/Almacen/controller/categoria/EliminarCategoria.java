package main.java.Almacen.controller.categoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.model.Categoria;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.SubcategoriaDB;
import main.java.Almacen.utils.Utils;

/**
 * Servlet implementation class ServletListaCategorias
 */
@WebServlet("/EliminarCategoria")
public class EliminarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = Utils.GetSession(request);
		
		if (session.getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			Usuario actual = (Usuario) request.getSession().getAttribute("usuarioActual");
			
			String strId = request.getParameter("id");
			int id = -1;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				return;
			}
			SubcategoriaDB.deleteCategoriaById(id);
			
			List<Categoria> cats = SubcategoriaDB.getCategorias();
			
			request.getSession().setAttribute("usuarioActual", actual);
			request.getSession().setAttribute("categorias", cats);
			
			response.sendRedirect("view/listaDeCategorias.jsp");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
