package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.ProveedorManager;
import main.java.Almacen.model.Proveedor;
import main.java.Almacen.persistence.ProveedoresDB;

/**
 * Servlet implementation class ServletEditarProveedor
 */
@WebServlet("/EditarProveedor")
public class ServletEditarProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditarProveedor() {
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
			String idProveedorEditar= request.getParameter("PvI");
			Proveedor proveedor = ProveedoresDB.getProveedorByStringId(idProveedorEditar);
			request.getSession().setAttribute("proveedorE", proveedor);
			response.sendRedirect("view/editarProveedor.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.setCharacterEncoding("UTF-8");
			String id, nombre, direccion, mail, telefono, contacto;
			id= request.getParameter("PvI");
			nombre= request.getParameter("PvN");
			direccion= request.getParameter("PvD");
			mail= request.getParameter("PvM");
			telefono = request.getParameter("PvT");
			contacto= request.getParameter("PvC");
			
			ProveedorManager.editarProveedor(id,nombre,direccion,mail,telefono,contacto);
			response.sendRedirect("ListaProveedores");
		}

	}

}
