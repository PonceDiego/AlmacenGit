package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.SubcategoriaDB;

/**
 * Servlet implementation class ServletPedidoNuevo
 */
@WebServlet("/GenerarPedido")
public class ServletPedidoNuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPedidoNuevo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("subcategorias", SubcategoriaDB.getSubcategorias());
		request.getSession().setAttribute("articulosStock", ArticuloDB.getArticulosEnStock());
		response.sendRedirect("view/agregarNuevoPedido.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
