package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Area;
import main.java.Almacen.model.Rol;
import main.java.Almacen.model.Usuario;

public class UsuarioDB {

	public static List<Usuario> getUsersActivos() {
		Session sess = null;
		List<Usuario> usuario;
		try {
			sess = HibernateUtils.openSession();
			Query<Usuario> query = sess.createQuery("select u from Usuario u where u.activo=1");
			usuario = query.getResultList();
			for (Usuario u : usuario) {
				Hibernate.initialize(u.getArea());
				Hibernate.initialize(u.getRol());
			}
			return usuario;
		} finally {
			sess.close();
		}

	}

	public static Usuario getUsuarioByNombreUsuario(String nombre) {
		Session sess = null;
		Usuario usuario;
		try {
			sess = HibernateUtils.openSession();
			Query<Usuario> query = sess.createQuery("select u from Usuario u where u.nombreUsuario='" + nombre + "'");
			usuario = query.getSingleResult();
			Hibernate.initialize(usuario.getArea());
			Hibernate.initialize(usuario.getArea().getAreaId());
			Hibernate.initialize(usuario.getArea().getNombreArea());
			
			Hibernate.initialize(usuario.getRol());
			Hibernate.initialize(usuario.getRol().getNombreRol());
			Hibernate.initialize(usuario.getRol().getRolId());
			return usuario;
		} finally {
			sess.close();
		}
	}

	public static Usuario getUsuarioByNombre(String nombre) {
		Session sess = null;
		Usuario usuario;
		try {
			sess = HibernateUtils.openSession();
			Query<Usuario> query = sess.createQuery("select u from Usuario u where u.nombre='" + nombre + "'");
			usuario = query.getSingleResult();
			Hibernate.initialize(usuario.getArea());
			Hibernate.initialize(usuario.getRol());
			return usuario;
		} finally {
			sess.close();
		}
	}

	public static Usuario getUsuarioByID(int id) {
		Session sess = null;
		Usuario usuario;
		try {
			sess = HibernateUtils.openSession();

			usuario = sess.get(Usuario.class, id);
			Hibernate.initialize(usuario.getRol());
			Hibernate.initialize(usuario.getRol().getNombreRol());
			Hibernate.initialize(usuario.getArea());
			Hibernate.initialize(usuario.getArea().getNombreArea());
			Hibernate.initialize(usuario.getPedidos());
			return usuario;

		} finally {
			sess.close();
		}
	}

	public static void agregarUsuarioNuevo(Usuario user) {
		Session sess = null;
		Transaction tran=null;
		try {
			sess = HibernateUtils.openSession();
			tran=sess.beginTransaction();
			sess.save(user);
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static void eliminarUsuarioById(int id) {
		Session sess = null;
		Transaction tran = null;
		Usuario user = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			user = getUsuarioByID(id);
			sess.update(user);
			user.setActivo(false);

			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static boolean validar(String username, String pass) {
		Session sess = null;
		Usuario user = new Usuario();
		try {
			sess = HibernateUtils.openSession();
			Query query = sess.createQuery("select u from Usuario u where u.nombreUsuario='" + username + "'");
			if (query.uniqueResult() == null) {
				System.out.println("El usuario ingresado no es correcto.");
				return false;
			} else {
				user = (Usuario) query.getSingleResult();
				if (LdapAuthentication.validarLDAP(username, pass)) {
					System.out.println("El usuario " + username + " se logeó correctamente.");
					return true;
				} else {
					return false;
				}
			}

		} finally {
			sess.close();
		}

	}

	public static void editarUsuario(int id, Rol r, Area a, String nombre, String apellido, String email) {
		Usuario u = null;
		Session sess = null;
		Transaction tran = null;
		try {
			sess=HibernateUtils.openSession();
			tran=sess.beginTransaction();
			u=getUsuarioByID(id);
			sess.update(u);
			u.setArea(a);
			u.setRol(r);
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setEmail(email);
			tran.commit();
		} finally {
			sess.close();
		}
	}

}