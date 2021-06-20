package main.java.Almacen.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.views.RegistroFilter;

public class RegistroDB {

	public static List<Registro> getRegistrosByTipoAndId(TIPO_REGISTRO tipo, int id) {
		Session sess = null;
		List<Registro> registros = new ArrayList<Registro>();
		try {
			sess = HibernateUtils.openSession();
			Query<Registro> query = sess.createQuery(
					"select r from Registro r where r.entidad='" + tipo.label + "' and r.entidadId = '" + id + "'");
			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r.getUsuarioByEncargado());
				Hibernate.initialize(r.getUsuarioByUsuario());
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	public static List<Registro> getRegistrosByUsuario(int user, RegistroFilter filter) {
		Session sess = null;
		List<Registro> registros;

		try {
			sess = HibernateUtils.openSession();
			String queryStr = "select r from Registro r ";
			queryStr += "where r.usuarioByUsuario='" + user + "' ";

			queryStr += concatenarFiltro(filter);

			queryStr += "order by r.fecha desc";

			Query<Registro> query = sess.createQuery(queryStr);

			query = aplicarFiltro(query, filter);

			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r.getUsuarioByEncargado());
				Hibernate.initialize(r.getUsuarioByUsuario());
				Hibernate.initialize(r);
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	public static List<Registro> getRegistros(RegistroFilter filter) {
		Session sess = null;
		List<Registro> registros = new ArrayList<Registro>();
		try {
			sess = HibernateUtils.openSession();

			StringBuilder builder = new StringBuilder();

			builder.append("select r from Registro r ");

			if (filter.tieneFiltro()) {
				builder.append("where id != null ");
			}

			builder.append(concatenarFiltro(filter));

			Query<Registro> query = sess.createQuery(builder.toString());

			query = aplicarFiltro(query, filter);

			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r);
				Hibernate.initialize(r.getUsuarioByEncargado());
				Hibernate.initialize(r.getUsuarioByUsuario());
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	public static void crearRegistro(Registro registro) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(registro);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static Registro getLastRegistroByIdAndTipo(TIPO_REGISTRO tipo, int id) {
		Session sess = null;
		Registro registro = null;
		try {
			sess = HibernateUtils.openSession();

			Query<Registro> query = sess.createQuery("select r from Registro r where r.entidad='" + tipo.label
					+ "' and r.entidadId = '" + id + "' order by r.fecha desc");
			query.setMaxResults(1);

			registro = query.getSingleResult();

			Hibernate.initialize(registro.getUsuarioByEncargado());
			Hibernate.initialize(registro.getUsuarioByUsuario());

			return registro;
		} finally {
			sess.close();
		}
	}

	public static List<Registro> listarRecursosPorEquipo() {
		Session sess = null;
		List<Registro> registros = new ArrayList<Registro>();
		List<Equipo> equipos = EquipoDB.getListaEquiposCompleta();
		try {
			sess = HibernateUtils.openSession();
			for (Equipo e : equipos) {

				Query<Registro> query = sess
						.createQuery("select r from Registro r where r.entidad = 'Equipo' and r.entidadId='"
								+ e.getEquipoId() + "' order by r.fecha desc");
				query.setMaxResults(1);
				registros.add(query.getSingleResult());
			}
			for (Registro r : registros) {
				Hibernate.initialize(r.getUsuarioByEncargado());
				Hibernate.initialize(r.getUsuarioByUsuario());
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	private static String concatenarFiltro(RegistroFilter filter) {
		String queryStr = "";
		if (filter.tieneFiltro()) {
			if (!TextUtils.isEmpty(filter.getFiltroDesde())) {
				queryStr += "and r.fecha > :filtroDesde ";
			}

			if (!TextUtils.isEmpty(filter.getFiltroHasta())) {
				queryStr += "and r.fecha < :filtroHasta ";
			}

			if (!TextUtils.isEmpty(filter.getFiltroEntidad())) {
				queryStr += "and r.entidad = :filtroEntidad ";
			}

			if (!TextUtils.isEmpty(filter.getFiltroUsuario())) {
				queryStr += "and r.usuarioByUsuario.nombre like :filtroUsuario ";
			}

			if (!TextUtils.isEmpty(filter.getFiltroEstado())) {

				if (filter.getFiltroEstado().equals("Entrada")) {
					queryStr += "and r.entrada = 1";
				} else if (filter.getFiltroEstado().equals("Salida")) {
					queryStr += "and r.entrada = 0";
				}

			}
		}

		return queryStr;
	}

	private static Query<Registro> aplicarFiltro(Query<Registro> query, RegistroFilter filter) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.tieneFiltro()) {
			if (!TextUtils.isEmpty(filter.getFiltroDesde())) {
				try {
					query.setParameter("filtroDesde", format.parse(filter.getFiltroDesde()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (!TextUtils.isEmpty(filter.getFiltroHasta())) {
				try {
					query.setParameter("filtroHasta", format.parse(filter.getFiltroHasta()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (!TextUtils.isEmpty(filter.getFiltroEntidad())) {
				query.setParameter("filtroEntidad", filter.getFiltroEntidad());
			}

			if (!TextUtils.isEmpty(filter.getFiltroUsuario())) {
				query.setParameter("filtroUsuario", "%" + filter.getFiltroUsuario() + "%");
			}
		}

		return query;
	}
}
