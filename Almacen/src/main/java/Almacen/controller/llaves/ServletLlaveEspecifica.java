package main.java.Almacen.controller.llaves;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.Llave;
import main.java.Almacen.utils.Utils;

@WebServlet("/getLlave")
public class ServletLlaveEspecifica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLlaveEspecifica() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = Utils.GetSession(request);

		System.out.println(session != null);

		if (request.getParameter("and") != null) {
			PrintWriter out = response.getWriter();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Llave respuesta = new Llave();
			respuesta = LlaveManager.getLlaveById(request.getParameter("id"));
			out.print(gson.toJson(respuesta));
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
