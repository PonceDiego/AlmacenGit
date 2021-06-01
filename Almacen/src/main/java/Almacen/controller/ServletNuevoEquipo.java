package main.java.Almacen.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.LugarManager;
import main.java.Almacen.manager.TipoManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletNuevoEquipo
 */
@WebServlet("/NuevoEquipo")
public class ServletNuevoEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletNuevoEquipo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Enumeration<String> lista = request.getSession().getAttributeNames();
		while (lista.hasMoreElements()) {
			String attrName = lista.nextElement();
			if (!attrName.equals("usuarioActual") && !attrName.equals("mensaje")) {
				request.getSession().removeAttribute(attrName);
			}
		}
		request.getSession().setAttribute("listaEquipos", EquipoManager.listarTodosEquipos());
		request.getSession().setAttribute("listaTipos", TipoManager.getTipos());
		request.getSession().setAttribute("listaLugares", LugarManager.getLugares());
		request.getSession().setAttribute("listaUsuarios", UsuarioDB.getUsersActivos());

		response.sendRedirect("view/agregarNuevoEquipo.jsp");
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

			Usuario actual = (Usuario) request.getSession().getAttribute("usuarioActual");

			request.setCharacterEncoding("UTF-8");
			String nombre, tipo, lugar, accesorios, serial, modelo, user, observaciones;
			nombre = (String) request.getParameter("inputNombre");

			System.out.println(nombre + " NOMBRE");

			tipo = (String) request.getParameter("inputTipo");

			System.out.println(tipo + " TIPO");
			lugar = (String) request.getParameter("inputLugar");

			System.out.println(lugar + " LUGAR");

			accesorios = (String) request.getParameter("inputAccesorios");
			serial = (String) request.getParameter("inputSerial");
			modelo = (String) request.getParameter("inputModelo");
			user = (String) request.getParameter("inputUsuario");
			observaciones = (String) request.getParameter("inputObservaciones");
			EquipoManager.createEquipo(serial, nombre, tipo, lugar, modelo, user, observaciones, accesorios, actual);
			response.sendRedirect("ListaEquipos");
		}

	}

}
