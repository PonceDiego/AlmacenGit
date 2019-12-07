package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.AreaManager;
import main.java.Almacen.model.Area;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletEditarProveedor
 */
@WebServlet("/EditarArea")
public class ServletEditarArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditarArea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			String idAreaEditar= request.getParameter("ArEdId");
			Area area= AreaDB.getAreaByStringId(idAreaEditar);
			request.getSession().setAttribute("areaEditar", area);
			request.getSession().setAttribute("usuarios", UsuarioDB.getUsersActivos());
			response.sendRedirect("view/editarArea.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			String nombre, user, id;
			request.setCharacterEncoding("UTF-8");
			nombre = request.getParameter("inputNombre");
			user = request.getParameter("inputJefe");
			id= request.getParameter("inputId");
			AreaManager.EditArea(id,nombre, user);
			response.sendRedirect("ListaAreas");
		}

	}

}
