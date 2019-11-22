package main.java.Almacen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.persistence.ArticuloDB;

/**
 * Servlet implementation class ServletListaArticulos
 */
@WebServlet("/ListaArticulos")
public class ServletListaArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaArticulos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession(false);
			request.getSession().setAttribute("listaArticulos", ArticuloDB.getListadoArticulos());
			response.sendRedirect("view/listaArticulosAdmin.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> respuesta = new ArrayList<String>();
		List<Articulo> e = ArticuloDB.getListadoArticulos();
		for (Articulo art : e) {
			respuesta.add(art.getNombre());
		}
		response.getWriter().print(respuesta);
	}

}
