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

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletListaUsuarios
 */
@WebServlet("/ListaUsuarios")
public class ServletListaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("and") != null) {
			PrintWriter out = response.getWriter();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			List<String> respuesta = new ArrayList<String>();
			respuesta = UsuarioManager.listarUsuariosString();
			out.print(gson.toJson(respuesta));
			out.flush();
		} else {

			if (request.getSession().getAttribute("usuarioActual") == null) {
				response.sendRedirect("Index");
			} else {
				request.getSession(false);
				request.getSession().setAttribute("listaUsuarios", UsuarioDB.getUsersActivos());
				response.sendRedirect("view/listaDeUsuarios.jsp");
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
