package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Categoria;
import main.java.Almacen.model.Subcategoria;

public class SubcategoriaDB {

	public static List<Categoria> getCategorias() {
		Session sess = null;
		List<Categoria> cats = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Categoria> query = sess.createQuery("select c from Categoria c where c.categoriaId!=null");
			cats = query.getResultList();
			for (Categoria c : cats) {
				for (Subcategoria s : c.getSubcategorias()) {
					Hibernate.initialize(s);
				}
			}
			return cats;
		} finally {
			sess.close();
		}
	}

	public static List<Subcategoria> getSubcategorias() {
		Session sess = null;
		List<Subcategoria> cats = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess.createQuery("from Subcategoria");
			cats = query.getResultList();
			for (Subcategoria c : cats) {
				Hibernate.initialize(c.getCategoria());
				Hibernate.initialize(c.getArticulos());
			}
			return cats;
		} finally {
			sess.close();
		}
	}

	public static List<Subcategoria> getSubcategoriasByCat(String catN) {
		Session sess = null;
		List<Subcategoria> subc = null;

		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query1 = sess
					.createQuery("select s from Subcategoria s where s.categoria='" + catN + "'");
			subc = query1.getResultList();
			for (Subcategoria c : subc) {
				Hibernate.initialize(c.getArticulos());
				Hibernate.initialize(c.getCategoria());
			}
			return subc;
		} finally {
			sess.close();
		}

	}

	public static Subcategoria getSubcategoriaByNombre(String nombre) {
		Session sess = null;
		Subcategoria c = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess
					.createQuery("select s from Subcategoria s where s.nombre='" + nombre + "'");
			c = (Subcategoria) query.getSingleResult();
			return c;
		} finally {
			sess.close();
		}
	}

	public static Subcategoria getCategoriaById(String nombre) {
		Session sess = null;
		Subcategoria c = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess.createQuery("select c from Subcategoria c where c.id='" + nombre + "'");
			c = (Subcategoria) query.getSingleResult();
			return c;
		} finally {
			sess.close();
		}
	}

	public static Categoria getCategoriaById(int id) {
		Session sess = null;
		Categoria c = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Categoria> query = sess.createQuery("select c from Categoria c where c.categoriaId='" + id + "'");
			c = query.getSingleResult();
			return c;
		} finally {
			sess.close();
		}
	}

	public static Categoria getCategoriaBySub(int idS) {
		Session sess = null;
		Categoria c = null;
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select s.categoria from Subcategoria s where s.id='" + idS + "'");
			int id = (int) query.getSingleResult();
			c = getCategoriaById(id);
			return c;
		} finally {
			sess.close();
		}
	}

	public static void createSubcategoria(String nombre, String inputCat) {
		Session sess = null;
		Transaction tran = null;
		Subcategoria subcategoria = new Subcategoria();
		Categoria categoria = getCategoriaById(Integer.parseInt(inputCat));

		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			subcategoria.setNombre(nombre);
			subcategoria.setCategoria(categoria);
			subcategoria.setActivo(true);
			sess.save(subcategoria);
			tran.commit();
		} finally {
			sess.close();
		}

	}

	public static void createCategoria(String nombre) {
		Session sess = null;
		Transaction tran = null;
		Categoria categoria = new Categoria(nombre, true);
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.save(categoria);
			tran.commit();
		} finally {
			sess.close();
		}
	}

}
