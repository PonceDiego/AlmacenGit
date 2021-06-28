package main.java.Almacen.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.manager.RegistroManager;
import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Lugar;
import main.java.Almacen.model.views.RegistroFilter;
import main.java.Almacen.model.views.RegistroView;

public class LlaveDB {

	public static Llave getLlaveById(int id) {
		Session sess = null;
		Llave llave;

		try {
			sess = HibernateUtils.openSession();
			llave = sess.get(Llave.class, id);
			if(llave != null && !llave.isActivo()) {
				return null;
			}
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
			Query<Llave> query = sess.createQuery("select a from Llave a where a.activo = 1 order by a.llaveId desc");
			llaves = query.getResultList();
			for (Llave llave : llaves) {
				Hibernate.initialize(llave.getGrupoLlaves());
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
			Query<GrupoLlaves> query = sess.createQuery("select a from GrupoLlaves a where a.activo = 1");
			llaves = query.getResultList();
			return llaves;
		} finally {
			sess.close();
		}
	}

	public static GrupoLlaves getGrupoLlavesById(String ids) {
		Session sess = null;
		GrupoLlaves grupoLlaves = null;
		int id = Integer.parseInt(ids);

		try {
			sess = HibernateUtils.openSession();
			grupoLlaves = sess.get(GrupoLlaves.class, id);
			if(grupoLlaves != null && !grupoLlaves.isActivo()) {
				return null;
			}
			Hibernate.initialize(grupoLlaves.getLlaves());
			return grupoLlaves;
		} finally {
			sess.close();
		}
	}

	public static int crearGrupoLlave(String nombre, String[] llaves) {
		GrupoLlaves grupo = new GrupoLlaves();
		grupo.setNombre(nombre);
		grupo.setActivo(true);
		Session sess = null;
		Transaction tran = null;
		Serializable s = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			s = sess.save(grupo);
			if (llaves != null) {

				for (String string : llaves) {
					asignarGrupoToLlave(sess, string, sess.get(GrupoLlaves.class, s));
				}
			}
			tran.commit();
			return (int) s;

		} finally {
			sess.close();
		}
	}

	private static void asignarGrupoToLlave(Session sess, String idLlave, GrupoLlaves grupo) {
		Llave llave;
		Query<Llave> query = sess.createQuery("select e from Llave e where e.llaveId ='" + idLlave + "' and a.activo = 1");
		llave = query.getSingleResult();
		sess.update(llave);
		llave.setGrupoLlaves(grupo);
	}

	public static void cambiarEstado(int encargado, int id, String idSolicitante) {
		int user = Integer.parseInt(idSolicitante);
		Session sess = null;
		Transaction tran = null;
		Llave e = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			e = sess.get(Llave.class, id);
			if(e != null && !e.isActivo()) {
				return;
			}
			sess.update(e);
			if (e.getEstado().equals("En uso")) {
				RegistroManager.createRegistro(true, user, TIPO_REGISTRO.LLAVE, id, encargado);
				e.setEstado("Disponible");
			} else {
				RegistroManager.createRegistro(false, user, TIPO_REGISTRO.LLAVE, id, encargado);
				e.setEstado("En uso");
			}
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static void crearLlave(String nombre, String copia, String ubicacion, String observaciones) {
		Lugar lugar = LugarDB.getLugarByNombre(ubicacion);

		Llave llave = new Llave(lugar, copia, nombre, "Disponible", true);
		llave.setObservaciones(observaciones);
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(llave);
			tran.commit();

		} finally {
			sess.close();
		}
	}

	public static GrupoLlaves getGrupoLllavesByNombre(String nombreGrupoLlaves) {
		GrupoLlaves grupo;
		Session sess = null;
		try {

			sess = HibernateUtils.openSession();
			Query<GrupoLlaves> query = sess
					.createQuery("select g from GrupoLlaves g where g.nombre ='" + nombreGrupoLlaves + "' a.activo = 1");
			grupo = query.getSingleResult();
			Hibernate.initialize(grupo.getLlaves());
			return grupo;
		} finally {
			sess.close();
		}

	}

	public static Llave getLlaveByNombre(String nombreEditado, String copia) {
		Llave llaveReturn = new Llave();
		Session sess = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Llave> query = sess.createQuery("select l from Llave l where l.nombre ='" + nombreEditado + "' l.activo = 1");
			List<Llave> llaves = query.getResultList();
			for (Llave llave : llaves) {
				if (llave.getCopia().equals(copia)) {
					Hibernate.initialize(llave.getGrupoLlaves());
					llaveReturn = llave;
					break;
				}
			}
			return llaveReturn;
		} finally {
			sess.close();
		}
	}

	public static List<Llave> getLlavesByUser(Integer actual) {
		List<Llave> llaveReturn = new ArrayList<Llave>();
		Session sess = null;
		try {
			sess = HibernateUtils.openSession();
			RegistroFilter filter = new RegistroFilter(null, null, null, "Llave", null);
			List<RegistroView> registros = RegistroManager.getListaRegistrosByUser(actual, filter);
			for (RegistroView registro : registros) {
				Llave llave = getLlaveByIdAndSession(sess, registro.getEntidadId());
				llaveReturn.add(llave);
			}
			for (Llave llave : llaveReturn) {
				Hibernate.initialize(llave.getGrupoLlaves());
				Hibernate.initialize(llave.getLugar());
			}
			return llaveReturn;
		} finally {
			sess.close();
		}
	}

	private static Llave getLlaveByIdAndSession(Session sess, int id) {
		Llave llave = sess.get(Llave.class, id);
		if(llave != null && !llave.isActivo()) {
			return null;
		}
		return llave;
	}

	public static void editLlave(String inputId, String inputCopia, String inputNombre, String inputObservaciones,
			String inputUbicacion) {

		Llave llave = getLlaveById(Integer.parseInt(inputId));
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.update(llave);
			llave.setCopia(inputCopia);
			llave.setLugar(LugarDB.getLugarByNombre(inputUbicacion));
			llave.setNombre(inputNombre);
			llave.setObservaciones(inputObservaciones);
			tran.commit();

		} finally {
			sess.close();
		}

	}

	public static void editGrupoLlave(String id, String[] llaves) {
		GrupoLlaves grupo = getGrupoLlavesById(id);
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.update(grupo);
			for (String llave : llaves) {
				asignarGrupoToLlave(sess, llave, grupo);
			}
			tran.commit();

		} finally {
			sess.close();
		}

	}

	public static void eliminarLlave(int id) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Llave llave = getLlaveById(id);
			sess.update(llave);
			llave.setActivo(false);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static void eliminarGrupo(String id) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			GrupoLlaves grupo = getGrupoLlavesById(id);
			for (Llave llave : grupo.getLlaves()) {
				llave.setGrupoLlaves(null);
			}
			sess.update(grupo);
			grupo.setActivo(false);
			tran.commit();
		} finally {
			sess.close();
		}
	}

}
