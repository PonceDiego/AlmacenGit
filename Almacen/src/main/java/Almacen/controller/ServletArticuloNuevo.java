package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.ArticuloManager;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.ProveedoresDB;
import main.java.Almacen.persistence.SubcategoriaDB;

@WebServlet("/NuevoArticulo")
public class ServletArticuloNuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletArticuloNuevo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession().setAttribute("listaArticulos", ArticuloDB.getListadoArticulos());
			request.getSession().setAttribute("categoriasListadas", SubcategoriaDB.getCategorias());
			request.getSession().setAttribute("proveedores", ProveedoresDB.getProveedores());
			request.getSession().setAttribute("subCats", SubcategoriaDB.getSubcategorias());
			response.sendRedirect("view/agregarNuevoArticulo.jsp");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {

			String proveedor, nombre, stockMinimo, stockMaximo, costo, subc;

			proveedor = request.getParameter("inputProveedor");
			nombre = request.getParameter("inputNombre");
			stockMinimo = request.getParameter("inputSMinimo");
			stockMaximo = request.getParameter("inputStock");
			costo = request.getParameter("inputCosto");
			subc = request.getParameter("inputSub");

			ArticuloManager.createArticulo(subc, proveedor, nombre, stockMinimo, stockMaximo, costo);
			response.sendRedirect("ListaArticulos");
		}
	}

}
