package main.java.Almacen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.EquipoDB;

/**
 * Servlet implementation class ServletListaEquipos
 */
@WebServlet("/ListaEquipos")
public class ServletListaEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

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
			if(request.getParameter("and")!=null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				ArrayList<Equipo> respuesta = new ArrayList<Equipo>();
				List<Equipo> e = EquipoDB.getListaEquipos();
				for (Equipo art : e) {
					respuesta.add(art);
				}
				out.print(gson.toJson(respuesta));
				out.flush();
			}else {
				Usuario actual=(Usuario)request.getSession().getAttribute("usuarioActual");
				
				List<Equipo> equipos = EquipoManager.listarEquipos();
				List<Integer> ids = equipos.stream().map(x -> x.getEquipoId()).collect(Collectors.toList());
				
				request.getSession().setAttribute("usuarioActual", actual);
				request.getSession().setAttribute("equipos", equipos);
				request.getSession().setAttribute("registros", RegistroManager.getLastRegistrosByEntidadAndId(TIPO_REGISTRO.EQUIPO, ids)) ;
			}

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
