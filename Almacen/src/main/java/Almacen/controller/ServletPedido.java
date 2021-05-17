package main.java.Almacen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.persistence.ArticuloPedidoDB;
import main.java.Almacen.persistence.PedidoDB;

/**
 * Servlet implementation class ServletPedido
 */
@WebServlet("/Pedido")
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPedido() {
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
			String idS = request.getParameter("pedidoId");
			int id = Integer.parseInt(idS);
			request.getSession().setAttribute("pedidoId", id);
			request.getSession().setAttribute("pedidoFecha", PedidoDB.getPedidoByID(id).getFecha());
			request.getSession().setAttribute("pedidoArea",
					PedidoDB.getPedidoByID(id).getUsuario().getArea().getNombre());
			request.getSession().setAttribute("pedidoUsuario",
					PedidoDB.getPedidoByID(id).getUsuario().getNombreUsuario());
			request.getSession().setAttribute("pedidoEstado",
					PedidoDB.getPedidoByID(id).getEstadopedido().getNombreEstado());
			request.getSession().setAttribute("pedidoObs", PedidoDB.getPedidoByID(id).getObservaciones());
			request.getSession().setAttribute("articulosPPedido", ArticuloPedidoDB.getArticulosPedidosByPedido(id));

			response.sendRedirect("view/pedidoEspecifico.jsp");

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
