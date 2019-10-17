package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.model.Proveedor;

public class ProveedoresDB {

	public static Proveedor getProveedorByID(int id) {

		Session sess = null;
		Proveedor proveedor;

		try {
			sess = HibernateUtils.openSession();

			proveedor = sess.get(Proveedor.class, id);
			Hibernate.initialize(proveedor.getArticulos());

		} finally {

			sess.close();
		}

		return proveedor;
	}

	public static Proveedor getProveedorByNombre(String nombre) {
		Session sess = null;
		Proveedor p = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Proveedor> query = sess.createQuery("from Proveedor where provNombre='"+nombre+"'");
			List<Proveedor> pp= query.getResultList();
			p= pp.get(0);
			Hibernate.initialize(p.getArticulos());
			return p;
		} finally

		{
			sess.close();
		}

	}
	public static Proveedor getProveedorByStringId(String nombre) {
		Session sess = null;
		Proveedor p = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Proveedor> query = sess.createQuery("from Proveedor where provId='"+nombre+"'");
			List<Proveedor> pp= query.getResultList();
			p= pp.get(0);
			Hibernate.initialize(p.getArticulos());
			return p;
		} finally

		{
			sess.close();
		}

	}

	public static List<Proveedor> getProveedores() {

		Session sess = null;
		List<Proveedor> pP = null;

		try {
			sess = HibernateUtils.openSession();
			Query<Proveedor> query = sess.createQuery("select p from Proveedor p where p.id!=null");
			pP = query.getResultList();
			for (Proveedor p : pP) {
				Hibernate.initialize(p.getArticulos());
			}
			return pP;

		} finally {
			sess.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Proveedor> getProveedoresPorArt(String s) {
		Session sess = null;
		List<Proveedor> proveedores = null;

		try {
			sess = HibernateUtils.openSession();

			Query<Proveedor> query = sess
					.createQuery("select distinct p from Proveedor p join p.articulos a where a.nombre='"+s+"'");
			proveedores = query.getResultList();
			for (Proveedor p : proveedores) {
				Hibernate.initialize(p.getArticulos());
				for (Articulo c : p.getArticulos()) {
					Hibernate.initialize(c.getSubcategoria());
				}
			}
			return proveedores;

		} finally {
			sess.close();
		}
	}
	public static void agregarProveedorNuevo(Proveedor p) {
		Session sess=null;
		try {
			sess=HibernateUtils.openSession();
			sess.save(p);
		}finally {
			sess.close();
		}
	}
	

}
