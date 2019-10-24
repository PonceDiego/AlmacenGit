package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.ProveedorManager;

/**
 * Servlet implementation class ServletProveedorNuevo
 */
@WebServlet("/ProveedorNuevoAndroid")
public class ServletProveedorNuevoAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProveedorNuevoAndroid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nombre, mail, telefono, contacto, direccion;
		request.setCharacterEncoding("UTF-8");
		nombre = request.getParameter("provNombre");
		mail = request.getParameter("provMail");
		telefono = request.getParameter("provTel");
		contacto = request.getParameter("provCont");
		direccion = request.getParameter("provDire");

		ProveedorManager.createProveedor(nombre, mail, telefono, contacto, direccion);
		response.getWriter().print("Proveedor cargado");
		
		
		
		
		
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
