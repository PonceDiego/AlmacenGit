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
			Articulo articulo = ArticuloDB.getArticuloByID(id);

			request.getSession().setAttribute("articuloId", id);
			request.getSession().setAttribute("articuloCat", articulo.getSubcategoria().getNombre());
			request.getSession().setAttribute("articuloCatPadre",
					articulo.getSubcategoria().getCategoria().getNombre());
			request.getSession().setAttribute("articuloNombre", articulo.getNombre());
			request.getSession().setAttribute("articuloCosto", articulo.getCosto());
			request.getSession().setAttribute("articuloFecha", articulo.getFechaAgregado());
			request.getSession().setAttribute("articuloStockMinimo", articulo.getStockMinimo());
			request.getSession().setAttribute("articuloStock", articulo.getStock());
			request.getSession().setAttribute("articuloEstado", articulo.getEstadoarticulo());
			request.getSession().setAttribute("articuloProveedor", articulo.getProveedor().getNombre());

			request.getSession().setAttribute("artProveedores",
					ProveedoresDB.getProveedoresPorArt(articulo.getNombre()));
			request.getSession().setAttribute("proveedorArticuloID", articulo.getProveedor().getId());

			if (user.getRol().getNombre().equals("SuperAdmin") || user.getRol().getNombre().equals("Administrador")) {
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
