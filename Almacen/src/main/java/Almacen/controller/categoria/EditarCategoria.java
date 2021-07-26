package main.java.Almacen.controller.categoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.model.Categoria;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.SubcategoriaDB;

/**
 * Servlet implementation class ServletEditarArticulo
 */
@WebServlet("/EditarCategoria")
public class EditarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarCategoria() {
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
			request.getSession().setAttribute("usuarioActual", actual);
			
			String strId = request.getParameter("categoriaId");
			int id = -1;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				return;
			}
			
			Categoria cat = SubcategoriaDB.getCategoriaById(id);
			
			request.getSession().setAttribute("categoria", cat);
			
			response.sendRedirect("view/editarCategoria.jsp");
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
			
			String id = request.getParameter("categoriaId");
			String nombre = request.getParameter("inputNombre");
			
			
			SubcategoriaDB.editCategoria(nombre, id);
			
			List<Categoria> cats = SubcategoriaDB.getCategorias();
			
			request.getSession().setAttribute("categorias", cats);
			
			response.sendRedirect("view/listaDeCategorias.jsp");
		}
	}

}
