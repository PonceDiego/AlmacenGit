package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.RolDB;

/**
 * Servlet implementation class ServlerUsuarioNuevo
 */
@WebServlet("/UsuarioNuevo")
public class ServletUsuarioNuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUsuarioNuevo() {
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
			request.getSession().setAttribute("roles", RolDB.getRoles());
			request.getSession().setAttribute("areas", AreaDB.getAreas());
			response.sendRedirect("view/agregarNuevoUsuario.jsp");
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
			String username, rol, area, nombre, apellido, email;
			request.setCharacterEncoding("UTF-8");
			username = request.getParameter("inputUsername");
			rol = request.getParameter("inputRol");
			area = request.getParameter("inputArea");
			nombre = request.getParameter("inputNombre");
			apellido = request.getParameter("inputApellido");
			email = request.getParameter("inputMail");

			UsuarioManager.createUsuario(username, rol, area, nombre, apellido, email);
			response.sendRedirect("ListaUsuarios");
		}

	}
}
