package main.java.Almacen.controller;

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
@WebServlet("/ListaCategorias")
public class ServletListaCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaCategorias() {
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
