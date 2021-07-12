package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.PedidoDB;
import main.java.Almacen.utils.Utils;

/**
 * Servlet implementation class ServletListaPedido
 */
@WebServlet("/ListaPedidos")
public class ServletListaPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaPedidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = Utils.GetSession(request);

		System.out.println(session != null);

		if (session.getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			Usuario user = (Usuario) session.getAttribute("usuarioActual");
			if (user.getRol().getNombre().equals("Administrador") || user.getRol().getNombre().equals("SuperAdmin")) {
				session.setAttribute("pedidosCompleto", PedidoDB.getPedidosCompleto());
				session.setAttribute("bool", true);
				response.sendRedirect("view/listaDePedidosCompleta.jsp");
			} else {
				session.setAttribute("pedidosCompleto", PedidoDB.getPedidosIndividual(user.getNombreUsuario()));
				session.setAttribute("bool", false);
				response.sendRedirect("view/listaDePedidosCompleta.jsp");
			}
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
