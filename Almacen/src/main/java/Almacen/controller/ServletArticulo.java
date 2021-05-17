package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.ProveedoresDB;
import main.java.Almacen.utils.Utils;

@WebServlet("/Articulo")
public class ServletArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletArticulo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = Utils.GetSession(request);
		
		if (session.getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			Usuario user = (Usuario) session.getAttribute("usuarioActual");
			String idS = request.getParameter("articuloID");
			int id = Integer.parseInt(idS);

			request.getSession().setAttribute("articuloId", id);
			request.getSession().setAttribute("articuloCat",
					ArticuloDB.getArticuloByID(id).getSubcategoria().getNombre());
			request.getSession().setAttribute("articuloCatPadre",
					ArticuloDB.getArticuloByID(id).getSubcategoria().getCategoria().getNombre());
			request.getSession().setAttribute("articuloNombre", ArticuloDB.getArticuloByID(id).getNombre());
			request.getSession().setAttribute("articuloCosto", ArticuloDB.getArticuloByID(id).getCosto());
			request.getSession().setAttribute("articuloFecha", ArticuloDB.getArticuloByID(id).getFechaAgregado());

			request.getSession().setAttribute("articuloStockMinimo", ArticuloDB.getArticuloByID(id).getStockMinimo());
			request.getSession().setAttribute("articuloStock", ArticuloDB.getArticuloByID(id).getStock());
			request.getSession().setAttribute("articuloEstado", ArticuloDB.getArticuloByID(id).getEstadoarticulo());
			request.getSession().setAttribute("articuloProveedor",
					ArticuloDB.getArticuloByID(id).getProveedor().getNombre());

			request.getSession().setAttribute("artProveedores",
					ProveedoresDB.getProveedoresPorArt(ArticuloDB.getArticuloByID(id).getNombre()));
			request.getSession().setAttribute("proveedorArticuloID",
					ArticuloDB.getArticuloByID(id).getProveedor().getId());

			if (user.getRol().getNombre().equals("SuperAdmin")
					|| user.getRol().getNombre().equals("Administrador")) {
				response.sendRedirect("view/articuloEspecifico.jsp");

			} else
				response.sendRedirect("view/articuloEspecificoSimplificado.jsp");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = Utils.GetSession(request);
		
		if (session.getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			request.getSession(false);
			String nombreArt = request.getParameter("nombreArticulo");
			Articulo articulo = new Articulo();
			articulo = ArticuloDB.getArticuloByNombre(nombreArt);
			if (articulo != null) {
				int id = articulo.getArticuloId();
				response.sendRedirect("Articulo?articuloID=" + id);

			} else
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
