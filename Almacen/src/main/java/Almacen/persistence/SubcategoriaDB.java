package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Categoria;
import main.java.Almacen.model.Subcategoria;

public class SubcategoriaDB {
	
	public static List<Categoria> getCategorias(){
		Session sess = null;
		List<Categoria> cats=null;
		try {
			sess = HibernateUtils.openSession();
			Query<Categoria> query = sess.createQuery("select c from Categoria c where c.categoriaId!=null");
			cats=query.getResultList();
			for(Categoria c:cats) {
				Hibernate.initialize(c.getSubcategorias());
			}
			return cats;
		}finally {
			sess.close();
		}
	}
	public static List<Subcategoria> getSubcategorias(){
		Session sess = null;
		List<Subcategoria> cats=null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess.createQuery("select c from Subcategoria c where c.subId!=null");
			cats=query.getResultList();
			for(Subcategoria c:cats) {
				Hibernate.initialize(c.getArticulos());
				Hibernate.initialize(c.getCategoria());
			}
			return cats;
		}finally {
			sess.close();
		}
	}
	public static List<Subcategoria> getSubcategoriasByCat(String catN){
		Session sess=null;
		List<Subcategoria> subc=null;
		
		try {
			sess=HibernateUtils.openSession();
			Query<Categoria> query=sess.createQuery("select c from Categoria c where c.nombre='"+catN+"'");
			int cat=0;
			List<Categoria> list=query.getResultList();
			cat=list.get(0).getCategoriaId();
			Query<Subcategoria>query1=sess.createQuery("select s from Subcategoria s where s.categoria='"+cat+"'");
			subc=query1.getResultList();
			return subc;
		}finally {
			sess.close();
		}
		
	}
	public static Subcategoria getSubcategoriaByNombre(String nombre) {
		Session sess = null;
		Subcategoria c=null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess.createQuery("select s from Subcategoria s where s.nombre='"+nombre+"'");
			c=(Subcategoria) query.getSingleResult();
			return c;
		}finally {
			sess.close();
		}
	}
	public static Subcategoria getCategoriaById(String nombre) {
		Session sess = null;
		Subcategoria c=null;
		try {
			sess = HibernateUtils.openSession();
			Query<Subcategoria> query = sess.createQuery("select c from Subcategoria c where c.subId='"+nombre+"'");
			c=(Subcategoria) query.getSingleResult();
			return c;
		}finally {
			sess.close();
		}
	}
	
	

}
