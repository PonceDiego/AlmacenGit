package main.java.Almacen.controller.categoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.model.Subcategoria;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.SubcategoriaDB;

/**
 * Servlet implementation class ServletEditarArticulo
 */
@WebServlet("/EditarSubcategoria")
public class EditarSubcategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarSubcategoria() {
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
			Usuario actual = (Usuario) request.getSession().getAttribute("usuarioActual");
			
			String id = request.getParameter("subcategoriaId");
			Subcategoria subcategoria = SubcategoriaDB.getCategoriaById(id);
			
			request.getSession().setAttribute("usuarioActual", actual);
			request.getSession().setAttribute("subcategoria", subcategoria);
			request.getSession().setAttribute("categorias", SubcategoriaDB.getCategorias());
			
			response.sendRedirect("view/editarSubcategoria.jsp");
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
			Usuario actual = (Usuario) request.getSession().getAttribute("usuarioActual");
			request.getSession().setAttribute("usuarioActual", actual);
			
			String id = request.getParameter("subcategoriaId");
			String nombre = request.getParameter("inputNombre");
			String categoria = request.getParameter("inputCat");
			
			SubcategoriaDB.editSubcategoria(nombre, id, categoria);
			
			List<Subcategoria> subCats = SubcategoriaDB.getSubcategorias();
			
			request.getSession().setAttribute("subcategorias", subCats);
			
			response.sendRedirect("view/listaDeSubcategorias.jsp");
		}
	}

}
