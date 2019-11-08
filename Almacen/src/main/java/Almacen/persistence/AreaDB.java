package main.java.Almacen.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Area;

public class AreaDB {

	public static List<Area> getAreas() {
		Session sess = null;
		List<Area> roles = null;

		try {

			sess = HibernateUtils.openSession();
			Query<Area> query = sess.createQuery("select r from Area r");
			roles = query.getResultList();
			for(Area a:roles) {
				
				Hibernate.initialize(a.getUsuario());
			}
			return roles;
		} finally {
			sess.close();
		}
	}
	public static Area getAreaByNombre(String nombre) {
		Session sess=null;
		Area rol=null;
		try {
			sess=HibernateUtils.openSession();
			Query<Area> query=sess.createQuery("select r from Area r where r.nombreArea='"+nombre+"'");
			rol=query.getSingleResult();
			Hibernate.initialize(rol.getUsuario());
			return rol;
		}finally {
			sess.close();
		}
	}
	public static void agregarAreaNueva (Area a) {
		Session sess=null;
		try {
			sess=HibernateUtils.openSession();
			sess.save(a);
		}finally {
			sess.close();
		}
	}
	public static List<Area> getAreasByUsuarioJefe(int jefe){
		Session sess=null;
		
		try {
			sess=HibernateUtils.openSession();
			Query query= sess.createQuery("select a from Area a where usuario='"+jefe+"'");
			
			if(query.getResultList()!=null) {
				List<Area> areas = new ArrayList<Area>();
				areas=query.getResultList();
				return areas;
				
			}else return null;
		
		}finally {
			sess.close();
		}
	}


}