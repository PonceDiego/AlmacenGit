package main.java.Almacen.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionCollector implements HttpSessionListener {
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
        System.out.println("Created: " + session.getId());
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    public static HttpSession find(String sessionId) {
        return sessions.get(sessionId);
    }

}