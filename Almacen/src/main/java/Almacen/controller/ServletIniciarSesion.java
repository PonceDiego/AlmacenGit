package main.java.Almacen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.model.UsuarioRs;
import main.java.Almacen.persistence.UsuarioDB;

/**
 * Servlet implementation class ServletIniciarSesion
 */
@WebServlet("/IniciarSesion")
public class ServletIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletIniciarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensaje = request.getParameter("mensaje");
		if(request.getSession().getAttribute("usuarioActual")==null) {
			request.getSession().setAttribute("mensaje", mensaje);
			response.sendRedirect("view/iniciarSesion.jsp");
			
		}

		else {
			request.getSession().setAttribute("mensaje", "Iniciada");
			
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		PrintWriter out;
		Gson gson= new Gson();
		
		
		if (username.equals("root") && pass.equals("almacen.C12")) {
			Usuario user= UsuarioDB.getUsuarioByNombreUsuario(username);
			
			System.out.println("\nEl usuario "+user.getNombreUsuario()+" inició sesión desde el sistema.\n");
			
			request.getSession().setAttribute("usuarioActual", user);
			request.getSession().setAttribute("mensaje", "Iniciada");
			
			String sessionid = request.getSession().getId();
			response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly");
			
			if(request.getParameter("and")!=null) {
				response.setCharacterEncoding("UTF-8");
				out = response.getWriter();
				UsuarioRs respuesta= new UsuarioRs();
				respuesta.setArea(user.getArea().getNombreArea());
				respuesta.setNombre(user.getNombre());
				respuesta.setRol(user.getRol().getNombreRol());
				respuesta.setToken(user.getUsuarioId().toString());
				respuesta.setUsername(user.getNombreUsuario());
				
				
				out.print(gson.toJson(respuesta));
				
				out.flush();
				
			}else {
				response.sendRedirect("Index");
			}
		} else {

			if (UsuarioManager.validarCredenciales(username, pass)) {

				
				Usuario user=UsuarioDB.getUsuarioByNombreUsuario(username);
				System.out.println("\nEl usuario "+user.getNombreUsuario()+" inició sesión desde LDAP.\n");
				request.getSession().setAttribute("usuarioActual", user);
				System.out.println("\n"+request.getSession().toString()+"<-- ID de sesión");
				request.getSession().setAttribute("mensaje", "Iniciada");
				
				if(request.getParameter("and")!=null) {
					response.setCharacterEncoding("UTF-8");
					out = response.getWriter();
					
					UsuarioRs respuesta= new UsuarioRs();
					respuesta.setArea(user.getArea().getNombreArea());
					respuesta.setNombre(user.getNombre());
					respuesta.setRol(user.getRol().getNombreRol());
					respuesta.setToken(user.getUsuarioId().toString());
					respuesta.setUsername(user.getNombreUsuario());
					
					
					out.print(gson.toJson(respuesta));
					
					out.flush();
					
				}else {
					response.sendRedirect("Index");
				}
				
			} else {
				if(request.getParameter("and")!=null) {
					out = response.getWriter();
					out.print(0);
					out.flush();
					
				}else {
					
					response.sendRedirect("IniciarSesion?mensaje=Los%20datos%20ingresados%20fueron%20incorrectos.");
				}
			}
		}
	}

}
