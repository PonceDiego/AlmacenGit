package main.java.Almacen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.EquipoManager;
import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.manager.UsuarioManager;
import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;

/**
 * Servlet implementation class ServletCambioEstado
 */
@WebServlet("/CambioEstado")
public class ServletCambioEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCambioEstado() {
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
			String id = (String) request.getParameter("cambioId");
			String entidad = (String) request.getParameter("entidad");
			if (entidad.equals("Llave")) {
				String idSolicitante = null;
				if (request.getParameter("solicitanteId") != null) {
					String idUsuarioSolicitante = (String) request.getParameter("solicitanteId");
					idSolicitante = UsuarioManager.getIdByNombre(idUsuarioSolicitante);

				} else {
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(Integer.parseInt(id));
					List<Registro> registros = RegistroManager.getLastRegistrosByEntidadAndId(TIPO_REGISTRO.LLAVE, ids);
					idSolicitante = registros.get(0).getUsuarioByUsuario().getId().toString();
				}
				int salida = Integer.parseInt(request.getParameter("salida"));
				if (salida == 1) {
					LlaveManager.changeStatus(actual.getId(), Integer.parseInt(id), idSolicitante, true);

				} else {
					LlaveManager.changeStatus(actual.getId(), Integer.parseInt(id), idSolicitante, false);

				}
				response.sendRedirect("ListaLlaves");
			} else if (entidad.equals("Equipo")) {
				int salida = Integer.parseInt(request.getParameter("salida"));

				if (salida == 1) {
					String usuarioSolicitante = (String) request.getParameter("solicitanteId");
					int user = Integer.parseInt(UsuarioManager.getIdByNombre(usuarioSolicitante));
					EquipoManager.changeStatus(user, Integer.parseInt(id), actual.getId());
				} else {
					EquipoManager.changeStatus(actual.getId(), Integer.parseInt(id), null);
				}
				response.sendRedirect("ListaEquipos");
			} else if (entidad.equals("GrupoEquipos")) {
				EquipoManager.changeStatusGrupo(actual.getId(), id, null);
				response.sendRedirect("BuscarGrupoEquipos");
			} else if (entidad.equals("GrupoLlaves")) {
				// int idEncargado, String idGrupo, String idUserString, bool salida
				String idSolicitante = null;
				if (request.getParameter("solicitanteId") != null) {
					String idUsuarioSolicitante = (String) request.getParameter("solicitanteId");
					idSolicitante = UsuarioManager.getIdByNombre(idUsuarioSolicitante);
				} else {
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(Integer.parseInt(id));
					List<Registro> registros = RegistroManager.getLastRegistrosByEntidadAndId(TIPO_REGISTRO.GRUPO_LLAVE,
							ids);
					idSolicitante = registros.get(0).getUsuarioByUsuario().getId().toString();
				}
				int salida = Integer.parseInt(request.getParameter("salida"));
				if (salida == 1) {
					LlaveManager.changeStatusGrupo(actual.getId(), id, idSolicitante, true);
				} else {
					LlaveManager.changeStatusGrupo(actual.getId(), id, idSolicitante, false);
				}
				response.sendRedirect("BuscarGrupoLlaves");
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
