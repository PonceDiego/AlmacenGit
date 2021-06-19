package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.LugarManager;

/**
 * Servlet implementation class ServletTipo
 */
@WebServlet("/Lugar")
public class ServletLugar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLugar() {
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
			request.getSession().setAttribute("listaLugares", LugarManager.getLugares());
			response.sendRedirect("view/agregarNuevoLugar.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.setCharacterEncoding("UTF-8");
			String nombre = request.getParameter("inputNombre");
			String descripcion = request.getParameter("inputDes");
			LugarManager.createLugar(nombre, descripcion);
			response.sendRedirect("ListaEquipos");
		}
	}

}