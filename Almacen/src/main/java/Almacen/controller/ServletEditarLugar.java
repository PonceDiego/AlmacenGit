package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.LugarManager;
import main.java.Almacen.model.Lugar;

@WebServlet("/EditarLugar")
public class ServletEditarLugar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEditarLugar() {
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
			String id = (String) request.getParameter("lugarAEditar");
			Lugar lugar = LugarManager.getLugarById(id);
			request.getSession().setAttribute("lugar", lugar);
			response.sendRedirect("view/editarLugar.jsp");
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
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("hiddenLugarId");
			String nombre = request.getParameter("inputNombre");
			String descripcion = request.getParameter("inputDes");
			LugarManager.editarLugar(LugarManager.getLugarById(id), nombre, descripcion);
			response.sendRedirect("ListaEquipos");
		}
	}

}
