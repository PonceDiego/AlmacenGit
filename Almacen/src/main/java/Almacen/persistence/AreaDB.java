package main.java.Almacen.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Area;
import main.java.Almacen.model.Usuario;

public class AreaDB {

	public static List<Area> getAreas() {
		Session sess = null;
		List<Area> roles = null;

		try {

			sess = HibernateUtils.openSession();
			Query<Area> query = sess.createQuery("select r from Area r");
			roles = query.getResultList();
			for (Area a : roles) {

				Hibernate.initialize(a.getUsuario());
			}
			return roles;
		} finally {
			sess.close();
		}
	}

	public static Area getAreaByNombre(String nombre) {
		Session sess = null;
		Area rol = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Area> query = sess.createQuery("select r from Area r where r.nombre='" + nombre + "'");
			rol = query.getSingleResult();
			Hibernate.initialize(rol.getUsuario());
			return rol;
		} finally {
			sess.close();
		}
	}

	public static void agregarAreaNueva(Area a) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(a);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static List<Area> getAreasByUsuarioJefe(int jefe) {
		Session sess = null;

		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select a from Area a where usuario='" + jefe + "'");

			if (query.getResultList() != null) {
				List<Area> areas = new ArrayList<Area>();
				areas = query.getResultList();
				return areas;

			} else
				return null;

		} finally {
			sess.close();
		}
	}

	public static Usuario getUsuarioJefeArea(Integer areaId) {
		Session sess = null;
		Usuario u = null;
		Area a = null;

		try {
			sess = HibernateUtils.openSession();
			a = sess.get(Area.class, areaId);
			u = a.getUsuario();
			Hibernate.initialize(u);
			Hibernate.initialize(u.getEmail());

			return u;
		} finally {
			sess.close();
		}

	}

	public static Area getAreaByStringId(String idAreaEditar) {
		Session sess = null;
		Area area = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Area> query = sess.createQuery("select r from Area r where r.id='" + idAreaEditar + "'");
			area = query.getSingleResult();
			Hibernate.initialize(area.getUsuario());
			Hibernate.initialize(area.getUsuario().getNombre());
			return area;
		} finally {
			sess.close();
		}
	}

	public static void editarArea(int idI, String nombre, String user) {
		Session sess = null;
		Transaction tran = null;
		Area area = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			area = sess.get(Area.class, idI);
			sess.update(area);
			area.setNombre(nombre);
			area.setUsuario(UsuarioDB.getUsuarioByNombreUsuario(user));
			tran.commit();

		} finally {
			sess.close();
		}

	}
}