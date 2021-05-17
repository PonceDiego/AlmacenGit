package main.java.Almacen.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.Usuario;

public class PedidoDB {
	public static void eliminarPedidoById(int id) {
		ArticuloPedidoDB.borrarArticulosPedidos(id);
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			sess.delete(getPedidoByID(id));
			tran.commit();
		} finally {
			sess.close();
		}
	}

	public static Pedido getPedidoByID(int id) {

		Session sess = null;
		Pedido pedido;

		try {
			sess = HibernateUtils.openSession();
			pedido = sess.get(Pedido.class, id);
			Hibernate.initialize(pedido.getEstadopedido());
			Hibernate.initialize(pedido.getUsuario());
			Hibernate.initialize(pedido.getUsuario().getArea());

			return pedido;
		}

		finally {

			sess.close();
		}

	}

	public static List<Pedido> getPedidosPendientes() {

		Session sess = null;
		List<Pedido> pP = null;

		try {
			sess = HibernateUtils.openSession();
			Query<Pedido> query = sess.createQuery("select p from Pedido p where p.estadopedido!=2");
			pP = query.getResultList();
			for (Pedido pedido : pP) {

				Hibernate.initialize(pedido.getEstadopedido());
				Hibernate.initialize(pedido.getFecha());
				Hibernate.initialize(pedido.getObservaciones());
				Hibernate.initialize(pedido.getUsuario());
				Hibernate.initialize(pedido.getUsuario().getArea());
			}

			return pP;

		} finally {
			sess.close();
		}
	}

	public static List<Pedido> getPedidosCompleto() {
		Session sess = null;
		List<Pedido> pP = null;
		try {
			sess = HibernateUtils.openSession();
			Query<Pedido> query = sess.createQuery("select p from Pedido p where p.pedidoId!=null");
			pP = query.getResultList();
			for (Pedido pedido : pP) {
				Hibernate.initialize(pedido.getEstadopedido());
				Hibernate.initialize(pedido.getFecha());
				Hibernate.initialize(pedido.getObservaciones());
				Hibernate.initialize(pedido.getUsuario());
				Hibernate.initialize(pedido.getUsuario().getArea());
			}
			return pP;
		} finally {
			sess.close();
		}
	}
	public static List<Pedido> getPedidosIndividual(String username){
		Session sess=null;
		List<Pedido> p=null;
		try {
			sess=HibernateUtils.openSession();
			Usuario u=UsuarioDB.getUsuarioByNombreUsuario(username);
			int id= u.getId();
			Query<Pedido> query= sess.createQuery("select p from Pedido p where p.usuario='"+id+"'");
			p=query.getResultList();
			for (Pedido pedido : p) {
				Hibernate.initialize(pedido.getEstadopedido());
				Hibernate.initialize(pedido.getFecha());
				Hibernate.initialize(pedido.getObservaciones());
				Hibernate.initialize(pedido.getUsuario());
				Hibernate.initialize(pedido.getUsuario().getArea());
			}
			return p;
		} finally {
			sess.close();
		}
	}

	public static void entregaPedido(int ids) {
		Session sess = null;
		Pedido p = null;
		try {
			sess = HibernateUtils.openSession();
			Transaction tran = sess.beginTransaction();

			p = PedidoDB.getPedidoByID(ids);
			sess.saveOrUpdate(p);
			if (ArticuloPedidoDB.actualizarStock(p)) {
				p.setEstadopedido(EstadoPedidoDB.getEstadoById(2));

			} else {
				p.setEstadopedido(EstadoPedidoDB.getEstadoById(3));
				if (!p.getObservaciones().contains(" ##En espera por falta de stock.")) {

					p.setObservaciones(p.getObservaciones() + " ##En espera por falta de stock.");
				}
			}
			tran.commit();

		} finally {

			sess.close();

		}
	}

	public static Serializable crearPedido(Pedido p) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Serializable ret = sess.save(p);
			tran.commit();
			return ret;

		} finally {
			sess.close();
		}

	}

	public static void editarPedido(int idP, String estado, String observaciones) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			Pedido a = getPedidoByID(idP);
			tran = sess.beginTransaction();
			sess.update(a);
			a.setEstadopedido(EstadoPedidoDB.getEstadoByNombre(estado));
			a.setObservaciones(observaciones);
			tran.commit();
		} finally {
			sess.close();
		}

	}

}
