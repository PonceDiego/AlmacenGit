package main.java.Almacen.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.GrupoEquipos;

public class EquipoDB {

	public static Serializable crearEquipo(Equipo equipo) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Serializable s = sess.save(equipo);
			tran.commit();
			return s;

		} finally {
			sess.close();
		}
	}

	public static void cambiarEstado(int user, int id) {
		Session sess = null;
		Transaction tran = null;
		Equipo e = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			e = sess.get(Equipo.class, id);
			sess.update(e);
			if (e.getEstado().equals("En uso")) {
				RegistroManager.createRegistro(true, user, id, "Equipo");
				e.setEstado("Disponible");
			} else {
				RegistroManager.createRegistro(false, user, id, "Equipo");
				e.setEstado("En uso");
			}
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static void bajaEstado(int id) {
		Session sess = null;
		Transaction tran = null;
		Equipo e = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			e = sess.get(Equipo.class, id);
			sess.update(e);
			e.setEstado("Baja");
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static Equipo getEquipoByID(int equip) {
		Session sess = null;
		Equipo e;
		try {
			sess = HibernateUtils.openSession();
			e = sess.get(Equipo.class, equip);
			Hibernate.initialize(e.getTipo().getNombre());
			Hibernate.initialize(e.getLugar().getNombre());
			if (e.getUsuario() != null) {

				Hibernate.initialize(e.getUsuario().getNombre());
			}
			return e;
		} finally {
			sess.close();
		}
	}

	public static List<Equipo> getListaEquiposCompleta() {
		Session sess = null;
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
			sess = HibernateUtils.openSession();
			Query<Equipo> query = sess.createQuery("select e from Equipo e");
			lista = query.getResultList();
			for (Equipo e : lista) {
				Hibernate.initialize(e.getLugar());
				Hibernate.initialize(e.getTipo());
				Hibernate.initialize(e.getUsuario());
				Hibernate.initialize(e);
			}
			return lista;
		} finally {
			sess.close();
		}
	}

	public static List<Equipo> getListaEquipos() {
		Session sess = null;
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
			sess = HibernateUtils.openSession();
			Query<Equipo> query = sess.createQuery("select e from Equipo e where e.estado!='" + "Baja" + "'");
			lista = query.getResultList();
			for (Equipo e : lista) {
				Hibernate.initialize(e.getLugar());
				Hibernate.initialize(e.getTipo());
				Hibernate.initialize(e.getUsuario());
				Hibernate.initialize(e);
			}
			return lista;
		} finally {
			sess.close();
		}
	}

	public static List<GrupoEquipos> getListaGrupoEquipos() {
		Session sess = null;
		List<GrupoEquipos> lista = new ArrayList<GrupoEquipos>();
		try {
			sess = HibernateUtils.openSession();
			Query<GrupoEquipos> query = sess.createQuery("select e from GrupoEquipos e");
			lista = query.getResultList();
			for (GrupoEquipos e : lista) {
				Hibernate.initialize(e.getEquipos());
			}
			return lista;
		} finally {
			sess.close();
		}
	}

	public static GrupoEquipos getGrupoEquipoById(String id) {
		Session sess = null;
		GrupoEquipos equipo;
		try {
			sess = HibernateUtils.openSession();
			equipo = sess.get(GrupoEquipos.class, id);
			Hibernate.initialize(equipo.getEquipos());
			return equipo;
		} finally {
			sess.close();
		}
	}

	public static GrupoEquipos getGrupoEquipoByNombre(String nombre) {
		Session sess = null;
		GrupoEquipos equipo;
		try {
			sess = HibernateUtils.openSession();
			Query<GrupoEquipos> query = sess
					.createQuery("select g from GrupoEquipos g where g.nombre ='" + nombre + "'");
			equipo = query.getSingleResult();
			Hibernate.initialize(equipo.getEquipos());
			for (Equipo e : equipo.getEquipos()) {
				Hibernate.initialize(e.getTipo());
			}
			return equipo;
		} finally {
			sess.close();
		}
	}

	public static void crearGrupoEquipo(String nombre, String[] equipos) {
		GrupoEquipos grupo = new GrupoEquipos();
		grupo.setNombre(nombre);
		Session sess = null;
		Transaction tran = null;
		Serializable s = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			s = sess.save(grupo);
			for (String string : equipos) {
				asignarGrupoToEquipo(sess, string, sess.get(GrupoEquipos.class, s));
			}
			tran.commit();

		} finally {
			sess.close();
		}
	}

	private static void asignarGrupoToEquipo(Session sess, String nombreEquipo, GrupoEquipos grupo) {
		Equipo equipo;
		Query<Equipo> query = sess.createQuery("select e from Equipo e where e.nombre ='" + nombreEquipo + "'");
		equipo = query.getSingleResult();
		sess.update(equipo);
		equipo.setGrupoEquipos(grupo);
	}
}
