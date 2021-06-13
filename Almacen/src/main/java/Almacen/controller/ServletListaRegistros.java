package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.model.views.RegistroFilter;

/**
 * Servlet implementation class ServletListaRegistros
 */
@WebServlet("/ListaRegistros")
public class ServletListaRegistros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaRegistros() {
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
			String rol = actual.getRol().getNombre();
			
			String filtroDesde = request.getParameter("filtroDesde");
			String filtroHasta = request.getParameter("filtroHasta");
			String filtroUsuario = request.getParameter("filtroUsuario");
			String filtroEntidad = request.getParameter("filtroEntidad");
			String filtroEstado = request.getParameter("filtroEstado") == null ? "" : request.getParameter("filtroEstado");
			
			request.getSession().setAttribute("filtroDesde", filtroDesde);
			request.getSession().setAttribute("filtroHasta", filtroHasta);
			request.getSession().setAttribute("filtroUsuario", filtroUsuario);
			request.getSession().setAttribute("filtroEntidad", filtroEntidad);
			request.getSession().setAttribute("filtroEstado", filtroEstado);
			
			RegistroFilter filter = new RegistroFilter(filtroDesde, filtroHasta, filtroUsuario, filtroEntidad, filtroEstado);
			
			if (rol.equals("SuperAdmin") || rol.equals("Administrador Técnica")) {
				request.getSession().setAttribute("registros", RegistroManager.getListaRegistros(filter));
			} else {
				request.getSession().setAttribute("registros", RegistroManager.getListaRegistrosByUser(actual.getId(), filter));
			}

			response.sendRedirect("view/listaDeRegistros.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
