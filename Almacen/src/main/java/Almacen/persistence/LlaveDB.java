package main.java.Almacen.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;

public class LlaveDB {

	public static Llave getLlaveById(int id) {
		Session sess = null;
		Llave llave;

		try {
			sess = HibernateUtils.openSession();
			llave = sess.get(Llave.class, id);
			Hibernate.initialize(llave.getLugar());
			return llave;

		} finally {
			sess.close();
		}
	}

	public static List<Llave> getAllLlaves() {
		Session sess = null;
		List<Llave> llaves;

		try {
			sess = HibernateUtils.openSession();
			Query<Llave> query = sess.createQuery("select a from Llave a where a.llaveId!=null");
			llaves = query.getResultList();
			for (Llave llave : llaves) {
				Hibernate.initialize(llave.getLugar());
			}
			return llaves;
		} finally {
			sess.close();
		}
	}

	public static List<GrupoLlaves> getAllGrupoLlaves() {
		Session sess = null;
		List<GrupoLlaves> llaves;

		try {
			sess = HibernateUtils.openSession();
			Query<GrupoLlaves> query = sess.createQuery("select a from GrupoLlaves a where a.grupoId != null");
			llaves = query.getResultList();
			return llaves;
		} finally {
			sess.close();
		}
	}

	public static GrupoLlaves getGrupoLlavesById(String id) {
		Session sess = null;
		GrupoLlaves grupoLlaves = null;

		try {
			sess = HibernateUtils.openSession();
			sess.get(GrupoLlaves.class, id);
			return grupoLlaves;
		} finally {
			sess.close();
		}
	}

	public static void crearGrupoLlave(String nombre, String[] llaves) {
		GrupoLlaves grupo = new GrupoLlaves();
		grupo.setNombre(nombre);
		Session sess = null;
		Transaction tran = null;
		Serializable s = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			s = sess.save(grupo);
			tran.commit();

		} finally {
			for (String string : llaves) {
				asignarGrupoToLlave(string, sess.get(GrupoLlaves.class, s));
			}
			sess.close();
		}
	}

	private static void asignarGrupoToLlave(String nombreEquipo, GrupoLlaves grupo) {
		Session sess = null;
		Transaction tran = null;
		Llave llave;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Query<Llave> query = sess.createQuery("select e from Llave e where e.nombre ='" + nombreEquipo + "'");
			llave = query.getSingleResult();
			sess.update(llave);
			llave.setGrupoLlaves(grupo);
			tran.commit();
		} finally {
			sess.close();
		}
	}
}
