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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession(true);
		String ids = request.getParameter("proveedorId");
		int id= Integer.parseInt(ids);
		
		Proveedor p=ProveedoresDB.getProveedorByID(id);
		request.getSession().setAttribute("provID", p.getProvId());
		request.getSession().setAttribute("proveedorNombre", p.getProvNombre());
		request.getSession().setAttribute("provTel", p.getProvTel());
		request.getSession().setAttribute("provContacto", p.getProvContacto());
		request.getSession().setAttribute("provDireccion", p.getProvDireccion());
		request.getSession().setAttribute("proveedorMail", p.getProvMail());
		request.getSession().setAttribute("proveedorArticulos", ArticuloDB.getArticulosByProveedor(id));
	
		response.sendRedirect("view/proveedorEspecifico.jsp");
		
		request.getSession(false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true);
		String nombre = request.getParameter("nombreProveedor");
		Proveedor prov = new Proveedor();
		prov = ProveedoresDB.getProveedorByNombre(nombre);
		if (prov != null) {
			int id = prov.getProvId();
			response.sendRedirect("Proveedor?proveedorId=" + id);

		} else
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);

		request.getSession(false);
	}

}
