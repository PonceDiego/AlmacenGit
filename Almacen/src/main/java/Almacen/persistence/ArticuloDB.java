package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Articulo;

public class ArticuloDB {

	public static Articulo getArticuloByID(int id) {
		Session sess = null;
		Articulo articulo;
		try {

			sess = HibernateUtils.openSession();
			articulo = sess.get(Articulo.class, id);
			Hibernate.initialize(articulo.getSubcategoria());
			Hibernate.initialize(articulo.getSubcategoria().getCategoria());
			Hibernate.initialize(articulo.getProveedor());
			Hibernate.initialize(articulo.getEstadoarticulo());

			return articulo;

		} finally {

			sess.close();
		}
	}

	public static List<Articulo> getListadoArticulos() {
		Session sess = null;
		List<Articulo> articulos = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Articulo> query = sess.createQuery("select a from Articulo a where a.articuloId!=null");
			articulos = query.getResultList();
			for (Articulo ar : articulos) {
				Hibernate.initialize(ar.getSubcategoria());
				Hibernate.initialize(ar.getSubcategoria().getCategoria());
				Hibernate.initialize(ar.getEstadoarticulo());
				Hibernate.initialize(ar.getProveedor());
			}
			return articulos;
		} finally {
			sess.close();
		}
	}

	public static Articulo getArticuloByNombre(String nombre) {
		Session sess = null;
		Articulo articulo;
		try {

			sess = HibernateUtils.openSession();
			Query<Articulo> query = sess.createQuery("SELECT a FROM Articulo a WHERE a.nombre='" + nombre + "'");
			articulo = (Articulo) query.getSingleResult();

			return articulo;

		} finally {

			sess.close();
		}
	}

	public static List<Articulo> getArticulosEnStock() {
		Session sess = null;
		List<Articulo> articulos = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Articulo> query = sess.createQuery("select a from Articulo a where a.estadoarticulo=1");
			articulos = query.getResultList();
			for (Articulo a:articulos) {
				Hibernate.initialize(a.getEstadoarticulo());
				Hibernate.initialize(a.getPedidoxarticuloses());
				Hibernate.initialize(a.getProveedor());
				Hibernate.initialize(a.getSubcategoria());
			}
			return articulos;
		} finally {
			sess.close();
		}
	}

	public static List<Articulo> getArticulosByProveedor(int prov) {
		Session sess = null;
		List<Articulo> articulos = null;
		
		
		try {
			sess = HibernateUtils.openSession();
			Query<Articulo> query= sess.createQuery("select a from Articulo a where a.proveedor='"+prov+"'");
			articulos= query.getResultList();
			for(Articulo a:articulos) {
				Hibernate.initialize(a.getSubcategoria());			
				Hibernate.initialize(a.getSubcategoria().getCategoria());

				Hibernate.initialize(a.getEstadoarticulo());
			}
			
			return articulos;
		}finally {
			sess.close();
		}
	}
	public static void agregarArticuloNuevo(Articulo ar) {
		Session sess = null;
		try {
			sess = HibernateUtils.openSession();
			sess.save(ar);
			
		} finally {
			sess.close();
		}
	}
	public static void editarArticulo(String nombre, String qr) {
		Session sess= null;
		Articulo articulo = null;
		try {
			sess= HibernateUtils.openSession();
			articulo=sess.get(Articulo.class, getArticuloByNombre(nombre).getArticuloId());
			articulo.setCodigoQr(qr);
			sess.update(articulo);
			
		}finally {
			sess.close();
		}
	}

}
