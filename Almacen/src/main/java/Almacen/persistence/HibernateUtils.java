package main.java.Almacen.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	public static SessionFactory factory = null;
	
	public static Session openSession() {
		if(factory == null) {
			Configuration con = new Configuration().configure("hibernate.cfg.xml");
			factory = con.buildSessionFactory();
		}
		return factory.openSession();
	}
}
