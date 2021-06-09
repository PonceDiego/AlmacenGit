package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Llave;

@WebServlet("/EditarGrupoLlaves")
public class EditarGrupoLlaves extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarGrupoLlaves() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		}
		String id = req.getParameter("idAEditar");
		main.java.Almacen.model.GrupoLlaves grupo = LlaveManager.getGrupoLlaveById(id);
		List<Llave> allLlaves = LlaveManager.getAllLlaves();
		List<Llave> llaves = new ArrayList<Llave>();
		if (grupo.getLlaves() != null || grupo.getLlaves().size() != 0) {
			for (Llave llave : allLlaves) {
				int contador = 0;
				for (Llave llaveGrupo : grupo.getLlaves()) {
					if (llaveGrupo.getLlaveId() == llave.getLlaveId()) {
						contador++;
					}
				}
				if (contador == 0) {
					llaves.add(llave);
				}
			}
		} else {
			llaves = allLlaves;
		}
		req.getSession().setAttribute("llaves", llaves);
		req.getSession().setAttribute("grupo", grupo);

		resp.sendRedirect("view/editarGrupoLlave.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioActual") == null) {
			resp.sendRedirect("Index");
			return;
		} else {

			String llaves;
			req.setCharacterEncoding("UTF-8");
			llaves = req.getParameter("inputLlaves");
			String id = req.getParameter("inputId");
			String[] llavesDivididas = llaves.split(",");
			LlaveManager.editGrupoLlave(id, llavesDivididas);

			resp.sendRedirect("BuscarGrupoLlaves");
		}

	}

}
