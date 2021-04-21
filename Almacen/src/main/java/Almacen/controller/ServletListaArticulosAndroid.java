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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.utils.Utils;;

/**
 * Servlet implementation class ServletListaArticulos
 */
@WebServlet("/ListaArticulosAndroid")
public class ServletListaArticulosAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaArticulosAndroid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// TODO: incorporar subcategoría object y estado, como anidados en el json.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = Utils.GetSession(request);
		
		List<Articulo> cats = ArticuloDB.getListadoArticulos();
		ArrayList<Object> array = new ArrayList<Object>();

		Articulo a = new Articulo();

		String categoriaJson = new String();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		for (int i = 0; i < cats.size(); i++) {
			a = cats.get(i);
			array.add(a);
		}

		categoriaJson += gson.toJson(array);
		out.print(categoriaJson);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
