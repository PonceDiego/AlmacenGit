package main.java.Almacen.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.java.Almacen.model.Tipo;

public class TipoDB {

	public static void crearTipo(Tipo tipo) {
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

	public static void editarTipo(String nombre, int idT) {
		Session sess = null;
		Transaction tran = null;
		Tipo tipo;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			tipo = TipoDB.getTipoByID(idT);
			sess.update(tipo);
			tipo.setNombre(nombre);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	private static Tipo getTipoByID(int idT) {
		Session sess = null;
		Tipo tipo;
		try {
			sess = HibernateUtils.openSession();
			tipo = sess.get(Tipo.class, idT);
			return tipo;
		} finally {
			sess.close();
		}
	}

	public static Tipo getTipoByNombre(String tipo) {
		Session sess = null;
		Tipo t;
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select t from Tipo t where t.nombre='" + tipo + "'");
			t = (Tipo) query.getSingleResult();
			return t;
		} finally {
			sess.close();
		}
	}

	public static List<Tipo> getTipos() {
		Session sess = null;
		List<Tipo> tipos;
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select t from Tipo t where t.activo=1");
			tipos = query.getResultList();
			for (Tipo t : tipos) {
				Hibernate.initialize(t);
			}
			return tipos;
		} finally {
			sess.close();
		}
	}

	public static void deleteTipo(int parseInt) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Tipo tipo = getTipoByID(parseInt);
			sess.update(tipo);
			tipo.setActivo(false);
			tran.commit();
		} finally {
			sess.close();
		}
	}

}
