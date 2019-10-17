package main.java.Almacen.persistence;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Usuario;

public class UsuarioDB {

	public static List<Usuario> getUsers(){
		Session sess= null;
		List<Usuario> usuario ;
		try {
			sess=HibernateUtils.openSession();
			Query<Usuario> query=sess.createQuery("select u from Usuario u where u.usuarioId!=null");
			usuario=query.getResultList();
			for(Usuario u:usuario) {
				Hibernate.initialize(u.getArea());
				Hibernate.initialize(u.getRol());
			}
			return usuario;
		}finally {
			sess.close();
		}
		
	}
	public static Usuario getUsuarioByNombre(String nombre) {
		Session sess= null;
		Usuario usuario ;
		try {
			sess= HibernateUtils.openSession();
			Query<Usuario> query= sess.createQuery("select u from Usuario u where u.nombre='"+nombre+"'");
			usuario =query.getSingleResult();
			Hibernate.initialize(usuario.getArea());
			Hibernate.initialize(usuario.getRol());
			return usuario;
		}finally {
			sess.close();
		}
	}
	
	public static Usuario getUsuarioByID(int id) {
		Session sess= null;
		Usuario usuario ;
		try {
			sess= HibernateUtils.openSession();
	
			usuario = sess.get(Usuario.class, id);
			Hibernate.initialize(usuario.getRol());
			Hibernate.initialize(usuario.getArea());
			Hibernate.initialize(usuario.getPedidos());
			return usuario;
			
		}finally {
			sess.close();
		}
	}
	public static void agregarUsuarioNuevo(Usuario user) {
		Session sess=null;
		try {
			sess=HibernateUtils.openSession();
			sess.save(user);
		}finally {
			sess.close();
		}
	}
}
