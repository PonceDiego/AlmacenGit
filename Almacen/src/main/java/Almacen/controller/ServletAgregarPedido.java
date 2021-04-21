package main.java.Almacen.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.Almacen.manager.MailManager;
import main.java.Almacen.manager.PedidoManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.utils.Utils;

/**
 * Servlet implementation class ServletAgregarPedido
 */
@WebServlet("/AgregarPedido")
public class ServletAgregarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAgregarPedido() {
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

		if (session.getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			Usuario u = (Usuario) session.getAttribute("usuarioActual");
			String user, obser, user2;

			user = request.getParameter("UserId");
			user2 = u.getUsuarioId().toString();
			if (user.equals(user2)) {
				obser = request.getParameter("textAreaObservaciones");
			} else {
				// Para dejar un registro de quién subió el pedido a nombre de otro usuario se
				// escribe al final de las observaciones.
				obser = request.getParameter("textAreaObservaciones") + " ##Generado por el usuario "
						+ u.getNombreUsuario();
			}
			int idP = PedidoManager.createPedido(obser, user, request.getParameter("inputArt"),
					request.getParameter("inputCantidad"));
			try {
				MailManager.enviarMail(user, idP);
			} catch (MessagingException | GeneralSecurityException e) {
				e.printStackTrace();
			}
			response.sendRedirect("ListaPedidos");
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
