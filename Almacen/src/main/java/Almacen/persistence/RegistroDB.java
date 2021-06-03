package main.java.Almacen.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.manager.RegistroManager.TIPO_REGISTRO;
import main.java.Almacen.model.Equipo;
import main.java.Almacen.model.Registro;
import main.java.Almacen.model.Usuario;

public class RegistroDB {
	public static void crearRegistro(Equipo e, Usuario actual) {
		Session sess = null;
		Transaction tran = null;
		Registro registro = new Registro();
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(registro);
			registro.setEntrada(true);
			registro.setFecha(new Date());
			registro.setUsuario(actual);
			registro.setEntidad("Equipo");
			registro.setEntidadId(e.getEquipoId());
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static List<Registro> getRegistrosByTipoAndId(TIPO_REGISTRO tipo, int id) {
		Session sess = null;
		List<Registro> registros = new ArrayList<Registro>();
		try {
			sess = HibernateUtils.openSession();
			Query<Registro> query = sess.createQuery("select r from Registro r where r.entidad='" + tipo.label + "' and r.entidadId = '"+ id +"'");
			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r.getUsuario());
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	public static List<Registro> getRegistrosByUsuario(int user) {
		Session sess = null;
		List<Registro> registros;
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select r from Registro r where r.usuario='" + user + "'");
			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r.getUsuario());
				Hibernate.initialize(r);
			}
			return registros;
		} finally {
			sess.close();
		}
	}

	public static List<Registro> getRegistros() {
		Session sess = null;
		List<Registro> registros = new ArrayList<Registro>();
		try {
			sess = HibernateUtils.openSession();
			Query<Registro> query = sess.createQuery("select r from Registro r");
			registros = query.getResultList();
			for (Registro r : registros) {
				Hibernate.initialize(r);
				Hibernate.initialize(r.getUsuario());
				Hibernate.initialize(r.getUsuario().getNombreUsuario());
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
			
			Query<Registro> query = sess.createQuery("select r from Registro r where r.entidad='" + tipo.label + "' and r.entidadId = '"+ id +"' order by r.fecha desc");
			query.setMaxResults(1);
			
			registro = query.getSingleResult();
			
			Hibernate.initialize(registro.getUsuario());
			
			return registro;
		} finally {
			sess.close();
		}
	}
	
	
	public static List<Registro> listarRecursosPorEquipo() {
		Session sess=null;
		List<Registro> registros=new ArrayList<Registro>();
		List<Equipo> equipos = EquipoDB.getListaEquiposCompleta();
		try {
			sess=HibernateUtils.openSession();
			for(Equipo e:equipos) {
				
				Query<Registro> query= sess.createQuery("select r from Registro r where r.entidad = 'Equipo' and r.entidadId='"+e.getEquipoId()+"' order by r.fecha desc");
				query.setMaxResults(1);
				registros.add(query.getSingleResult());
			}
			for(Registro r:registros) {
				Hibernate.initialize(r.getUsuario().getNombre());
				Hibernate.initialize(r.getUsuario().getApellido());
			}
			return registros;
		}finally {
			sess.close();
		}
	}
}
