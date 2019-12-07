package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Lugar;

public class LugarDB {
	public static void crearLugar(Lugar tipo) {
		Session sess=null;
		Transaction tran=null;
		try {
			sess=HibernateUtils.openSession();
			tran=sess.beginTransaction();
			sess.save(tipo);
			tran.commit();
		}finally {
			sess.close();
		}
	}

	public static Lugar getLugarByNombre(String lugar) {
		Session sess=null;
		Lugar l=null;
		try {
			sess=HibernateUtils.openSession();
			Query<Lugar> query=sess.createQuery("select l from Lugar l where l.nombre='"+lugar+"'");
			l=query.getSingleResult();
			return l;
		}finally {
			sess.close();
		}
	}
	public static List<Lugar> getLugares(){
		Session sess=null;
		List<Lugar> l;
		try {
			sess=HibernateUtils.openSession();
			Query query=sess.createQuery("select l from Lugar l");
			l=query.getResultList();
			for(Lugar lu:l) {
				System.out.println(lu.getNombre());
				Hibernate.initialize(lu);
			}
			return l;
		}finally {
			sess.close();
		}
	}
}
