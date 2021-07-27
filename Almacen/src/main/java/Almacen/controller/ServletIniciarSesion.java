package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletIniciarSesion
 */
@WebServlet("/IniciarSesion")
public class ServletIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletIniciarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensaje = request.getParameter("mensaje");
		if (request.getSession().getAttribute("usuarioActual") == null) {
			request.getSession().setAttribute("mensaje", mensaje);
			response.sendRedirect("view/iniciarSesion.jsp");

		}

		else {
			request.getSession().setAttribute("mensaje", "Iniciada");

			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");

		if (UsuarioManager.validarCredencialesSys(username, pass)) {

			Usuario user = UsuarioDB.getUsuarioByNombreUsuario(username);

			System.out.println("\nEl usuario " + user.getNombreUsuario() + " inició sesión desde el sistema.\n");

			request.getSession().setAttribute("usuarioActual", user);
			request.getSession().setAttribute("mensaje", "Iniciada");

			response.sendRedirect("Index");

		} else if (UsuarioManager.validarCredenciales(username, pass)) {
			Usuario user = UsuarioDB.getUsuarioByNombreUsuario(username);
			System.out.println("\nEl usuario " + user.getNombreUsuario() + " inició sesión desde LDAP.\n");
			request.getSession().setAttribute("usuarioActual", user);
			System.out.println("\n" + request.getSession().toString() + "<-- ID de sesión");
			request.getSession().setAttribute("mensaje", "Iniciada");

			response.sendRedirect("Index");

		} else {

			response.sendRedirect("IniciarSesion?mensaje=Los%20datos%20ingresados%20fueron%20incorrectos.");
		}

	}

}
