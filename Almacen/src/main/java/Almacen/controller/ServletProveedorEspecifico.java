package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.model.Proveedor;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.ProveedoresDB;

/**
 * Servlet implementation class ServletProveedorEspecifico
 */
@WebServlet("/Proveedor")
public class ServletProveedorEspecifico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProveedorEspecifico() {
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
			request.getSession(false);
			String ids = request.getParameter("proveedorId");
			int id = Integer.parseInt(ids);

			Proveedor p = ProveedoresDB.getProveedorByID(id);
			request.getSession().setAttribute("provID", p.getId());
			request.getSession().setAttribute("proveedorNombre", p.getNombre());
			request.getSession().setAttribute("provTel", p.getTelefono());
			request.getSession().setAttribute("provContacto", p.getContacto());
			request.getSession().setAttribute("provDireccion", p.getDireccion());
			request.getSession().setAttribute("proveedorMail", p.getMail());
			request.getSession().setAttribute("proveedorArticulos", ArticuloDB.getArticulosByProveedor(id));

			response.sendRedirect("view/proveedorEspecifico.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(false);
		String nombre = request.getParameter("nombreProveedor");
		Proveedor prov = new Proveedor();
		prov = ProveedoresDB.getProveedorByNombre(nombre);
		if (prov != null) {
			int id = prov.getId();
			response.sendRedirect("Proveedor?proveedorId=" + id);

		} else
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);

	}

}
