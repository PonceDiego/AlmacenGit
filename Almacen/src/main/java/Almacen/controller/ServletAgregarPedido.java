package main.java.Almacen.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.GmailManager;
import main.java.Almacen.manager.PedidoManager;
import main.java.Almacen.model.Usuario;

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
		if (request.getSession().getAttribute("usuarioActual") == null) {
			response.sendRedirect("Index");
		} else {
			Usuario u =(Usuario) request.getSession().getAttribute("usuarioActual");
			String user, obser;
			
			//Si 'UserId' est� vac�o quiere decir que carga el pedido a su nombre, por lo que se pasa el id del usuarioActual.
			if(request.getParameter("UserId")=="") {
				user= u.getUsuarioId().toString();
				obser=request.getParameter("textAreaObservaciones");
			}else {
				user=request.getParameter("UserId");
				//Para dejar un registro de qui�n subi� el pedido a nombre de otro usuario se escribe al final de las observaciones.
				obser=request.getParameter("textAreaObservaciones")+" ##Generado por el usuario "+u.getNombreUsuario();
			}
			int idP=PedidoManager.createPedido(obser, user,
					request.getParameter("inputArt"), request.getParameter("inputCantidad"));
			try {
				GmailManager.enviarMail(user,idP);
			} catch (MessagingException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
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
