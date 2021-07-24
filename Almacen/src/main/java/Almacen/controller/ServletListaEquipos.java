package main.java.Almacen.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Usuario;

/**
 * Servlet implementation class ServletListaEquipos
 */
@WebServlet("/ListaEquipos")
public class ServletListaEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaEquipos() {
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

			List<Equipo> equipos = EquipoManager.listarEquipos();
			List<Integer> ids = equipos.stream().map(x -> x.getEquipoId()).collect(Collectors.toList());

			request.getSession().setAttribute("usuarioActual", actual);
			request.getSession().setAttribute("equipos", equipos);
			request.getSession().setAttribute("registros",
					RegistroManager.getLastRegistrosByEntidadAndId(TIPO_REGISTRO.EQUIPO, ids));
			response.sendRedirect("view/listaDeEquipos.jsp");
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
