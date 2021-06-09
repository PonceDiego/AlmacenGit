package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.ArticuloManager;
import main.java.Almacen.model.Articulo;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.ProveedoresDB;
import main.java.Almacen.persistence.SubcategoriaDB;

/**
 * Servlet implementation class ServletEditarArticulo
 */
@WebServlet("/EditarArticulo")
public class ServletEditarArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEditarArticulo() {
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
			String nombre = request.getParameter("nombreEditado");
			request.getSession().setAttribute("categoriasListadas", SubcategoriaDB.getCategorias());
			request.getSession().setAttribute("proveedores", ProveedoresDB.getProveedores());
			request.getSession().setAttribute("subCats", SubcategoriaDB.getSubcategorias());
			Articulo artEdit = ArticuloDB.getArticuloByNombre(nombre);
			request.getSession().setAttribute("artEdit", artEdit);
			response.sendRedirect("view/editarArticulo.jsp");
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

			String id, proveedor, nombre, stockMinimo, stockMaximo, costo, subc;
			request.setCharacterEncoding("UTF-8");
			id = request.getParameter("editadoId");
			proveedor = request.getParameter("inputProveedor");
			stockMinimo = request.getParameter("inputSMinimo");
			stockMaximo = request.getParameter("inputStock");
			costo = request.getParameter("inputCosto");
			subc = request.getParameter("inputSub");

			ArticuloManager.editarArticulo(id, subc, proveedor, stockMinimo, stockMaximo, costo);
			response.sendRedirect("ListaArticulos");
		}

	}

}
