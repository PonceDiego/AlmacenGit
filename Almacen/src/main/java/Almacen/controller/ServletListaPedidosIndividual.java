package main.java.Almacen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.PedidoRs;
import main.java.Almacen.persistence.PedidoDB;

/**
 * Servlet implementation class ServletListaPedido
 */
@WebServlet("/ListaPedidosDe")
public class ServletListaPedidosIndividual extends HttpServlet {
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListaPedidosIndividual() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("and") != null) {
			String username=request.getParameter("username");
			List<Pedido> pedidos=PedidoDB.getPedidosIndividual(username);
			ArrayList<PedidoRs> lista= new ArrayList<PedidoRs>();
			for(Pedido p:pedidos) {
				PedidoRs rs=new PedidoRs(p);
				lista.add(rs);
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print(gson.toJson(lista));
			out.flush();
		}else if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			String username=request.getParameter("username");
			List<Pedido> pedidos=PedidoDB.getPedidosIndividual(username);
			request.getSession().setAttribute("listaMisPedidos", pedidos);
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
