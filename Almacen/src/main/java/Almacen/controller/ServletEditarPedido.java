package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.PedidoManager;
import main.java.Almacen.persistence.ArticuloDB;
import main.java.Almacen.persistence.ArticuloPedidoDB;
import main.java.Almacen.persistence.EstadoPedidoDB;
import main.java.Almacen.persistence.PedidoDB;

/**
 * Servlet implementation class ServletEditarPedido
 */
@WebServlet("/EditarPedido")
public class ServletEditarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEditarPedido() {
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
			request.getSession().setAttribute("estadosPedidoPosibles", EstadoPedidoDB.getEstados());
			request.getSession().setAttribute("articulos", ArticuloDB.getArticulosEnStock());
			String idS = request.getParameter("pedidoId");
			int id = Integer.parseInt(idS);
			request.getSession().setAttribute("pedidoId", id);
			request.getSession().setAttribute("pedidoFecha", PedidoDB.getPedidoByID(id).getFecha());
			request.getSession().setAttribute("pedidoArea",
					PedidoDB.getPedidoByID(id).getUsuario().getArea().getNombreArea());
			request.getSession().setAttribute("pedidoUsuario",
					PedidoDB.getPedidoByID(id).getUsuario().getNombreUsuario());
			request.getSession().setAttribute("pedidoEstado",
					PedidoDB.getPedidoByID(id).getEstadopedido().getNombreEstado());
			request.getSession().setAttribute("pedidoObs", PedidoDB.getPedidoByID(id).getObservaciones());
			request.getSession().setAttribute("articulosPPedido", ArticuloPedidoDB.getArticulosPedidosByPedido(id));
			response.sendRedirect("view/editarPedido.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("idPEditado");
		String estado = request.getParameter("estadoPEditado");
		String cantidades = request.getParameter("cantidades");
		String nombres = request.getParameter("nombres");
		String observaciones = request.getParameter("observaciones");
		PedidoManager.editarPedido(id, estado, cantidades, nombres, observaciones);
		response.sendRedirect("ListaPedidos");
	}

}
