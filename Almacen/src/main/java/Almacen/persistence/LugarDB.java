package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Lugar;

public class LugarDB {
	public static void crearLugar(Lugar tipo) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(tipo);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static Lugar getLugarByNombre(String lugar) {
		Session sess = null;
		Lugar l = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Lugar> query = sess.createQuery("select l from Lugar l where l.nombre='" + lugar + "'");
			l = query.getSingleResult();
			return l;
		} finally {
			sess.close();
		}
	}

	public static List<Lugar> getLugares() {
		Session sess = null;
		List<Lugar> l;
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select l from Lugar l");
			l = query.getResultList();
			for (Lugar lu : l) {
				Hibernate.initialize(lu);
			}
			return l;
		} finally {
			sess.close();
		}
	}

	public static void editarLugar(Lugar lugar, String nombre, String descripcion) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.update(lugar);
			lugar.setDescripcion(descripcion);
			lugar.setNombre(nombre);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static Lugar getLugarById(String ids) {
		Lugar lugar = null;
		Session sess = null;
		int id = Integer.parseInt(ids);
		try {
			sess = HibernateUtils.openSession();
			lugar = sess.get(Lugar.class, id);
		} finally {
			sess.close();
		}

		return lugar;
	}
}
