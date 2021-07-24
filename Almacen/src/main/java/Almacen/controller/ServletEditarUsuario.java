package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.RolDB;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletEditarUsuario
 */
@WebServlet("/EditarUsuario")
public class ServletEditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEditarUsuario() {
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
			String id = request.getParameter("UsEdId");
			request.getSession().setAttribute("roles", RolDB.getRoles());
			request.getSession().setAttribute("areas", AreaDB.getAreas());
			int idE = Integer.parseInt(id);
			Usuario userE = UsuarioDB.getUsuarioByID(idE);
			request.getSession().setAttribute("usuarioEditar", userE);
			response.sendRedirect("view/editarUsuario.jsp");
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
			int id = Integer.parseInt(request.getParameter("idUEditar"));
			username = request.getParameter("inputUsername");
			rol = request.getParameter("inputRol");
			area = request.getParameter("inputArea");
			nombre = request.getParameter("inputNombre");
			apellido = request.getParameter("inputApellido");
			email = request.getParameter("inputMail");

			UsuarioManager.editUsuario(id, username, rol, area, nombre, apellido, email);
			response.sendRedirect("ListaUsuarios");
		}
	}

}
