package main.java.Almacen.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
	
	public static HttpSession GetSession(HttpServletRequest request)
	{
		//Buscamos el JsessionId en los headers
		String headerJsessionId = request.getHeader("JSESSIONID");
		
		HttpSession session = null;
		
		//Preguntamos si el header contiene algo
		if(headerJsessionId != null && headerJsessionId.isEmpty()) {
			//Obtenemos la session en la lista de sessiones
			session = HttpSessionCollector.find(headerJsessionId);
		}
		
		//Si no encuentra, es porque la session que dice el header no esta.
		//Asique procedemos a buscarla por Cookie (Caso web)
		if(session == null) {
			session = request.getSession();
		}
		
		return session;
	}
}
