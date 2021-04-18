package main.java.Almacen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.PedidoDB;

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
		
		for (String name : Collections.list(request.getHeaderNames())) {
			System.out.println(name + " = " + request.getHeader(name));
		}
		
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			if (request.getParameter("and") != null) {
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				ArrayList<Pedido> respuesta = new ArrayList<Pedido>();
				List<Pedido> e = PedidoDB.getPedidosCompleto();
				for (Pedido art : e) {
					respuesta.add(art);
				}
				out.print(gson.toJson(respuesta));
				out.flush();
			} else {
				Usuario user = (Usuario) request.getSession().getAttribute("usuarioActual");
				if (user.getRol().getNombreRol().equals("Administrador")
						|| user.getRol().getNombreRol().equals("SuperAdmin")) {
					request.getSession().setAttribute("pedidosCompleto", PedidoDB.getPedidosPendientes());
					response.sendRedirect("view/listaDePedidosPendientes.jsp");
				} else {
					request.getSession().setAttribute("pedidosCompleto",
							PedidoDB.getPedidosIndividual(user.getNombreUsuario()));
					response.sendRedirect("view/listaDePedidosPendientes.jsp");
				}
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
