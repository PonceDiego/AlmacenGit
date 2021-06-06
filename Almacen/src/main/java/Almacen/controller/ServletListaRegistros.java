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

import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.views.RegistroView;

/**
 * Servlet implementation class ServletListaRegistros
 */
@WebServlet("/ListaRegistros")
public class ServletListaRegistros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaRegistros() {
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
			if (request.getParameter("and") != null) {
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				ArrayList<Registro> respuesta = new ArrayList<Registro>();
				List<RegistroView> e = RegistroManager.getListaRegistros();
				for (RegistroView art : e) {
					respuesta.add(art);
				}
				out.print(gson.toJson(respuesta));
				out.flush();
			} else {

				request.getSession().setAttribute("registros", RegistroManager.getListaRegistros());

				response.sendRedirect("view/listaDeRegistros.jsp");
			}
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
