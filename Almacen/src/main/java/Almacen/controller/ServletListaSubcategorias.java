package main.java.Almacen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.model.Subcategoria;
import main.java.Almacen.persistence.SubcategoriaDB;

/**
 * Servlet implementation class ServletListaCategorias
 */
@WebServlet("/ListaSubcategorias")
public class ServletListaSubcategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaSubcategorias() {
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
			String inputCat;
			inputCat = request.getParameter("inputCat");
			List<Subcategoria> cats = SubcategoriaDB.getSubcategoriasByCat(inputCat);
			ArrayList<Subcategoria> array = new ArrayList<Subcategoria>();
			String categoriaJson = null;
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			for (Subcategoria cat : cats) {
				array.add(cat);

			}
			categoriaJson = gson.toJson(array);
			out.print(categoriaJson);
			out.flush();
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
