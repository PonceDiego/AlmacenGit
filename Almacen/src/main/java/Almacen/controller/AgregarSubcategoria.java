package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.ArticuloManager;
import main.java.Almacen.persistence.SubcategoriaDB;

@WebServlet("/NuevaSubcategoria")
public class AgregarSubcategoria extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarSubcategoria() {
		super();
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
			request.getSession(false);
			request.getSession().setAttribute("cats", SubcategoriaDB.getCategorias());
			response.sendRedirect("view/agregarSubcategoria.jsp");

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
			String nombre, inputCat;
			request.setCharacterEncoding("UTF-8");
			nombre = request.getParameter("inputNombre");
			inputCat = request.getParameter("inputCat");
			ArticuloManager.createSubcategoria(nombre, inputCat);
			response.sendRedirect("Index");
		}
	}
}
