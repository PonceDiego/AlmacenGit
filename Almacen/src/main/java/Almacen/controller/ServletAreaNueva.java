package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.AreaManager;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletAreaNueva
 */
@WebServlet("/AreaNueva")
public class ServletAreaNueva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAreaNueva() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession().setAttribute("usuarios", UsuarioDB.getUsersActivos());
			response.sendRedirect("view/agregarNuevaArea.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre, user;
		request.setCharacterEncoding("UTF-8");
		nombre = request.getParameter("inputNombre");
		user = request.getParameter("inputJefe");
		AreaManager.createArea(nombre, user);
		response.sendRedirect("ListaAreas");
	}

}
