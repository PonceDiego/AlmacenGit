package main.java.Almacen.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Pedido;

public class PedidoDB {
	public static void eliminarPedidoById(int id) {
		ArticuloPedidoDB.borrarArticulosPedidos(id);
		Session sess= null;
		Transaction tran = null;
		try {
			sess=HibernateUtils.openSession();
			tran=sess.beginTransaction();
			sess.delete( getPedidoByID(id));
			tran.commit();
		}finally {
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
			Query<Pedido> query = sess.createQuery("select p from Pedido p where p.estadopedido=1");
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

	public static void entregaPedido(int ids) {
		Session sess = null;
		Pedido p = null;
		try {
			sess = HibernateUtils.openSession();
			Transaction tran = sess.beginTransaction();

			p = PedidoDB.getPedidoByID(ids);
			p.setEstadopedido(EstadoPedidoDB.getEstadoById(2));
			sess.saveOrUpdate(p);
			p.setEstadopedido(EstadoPedidoDB.getEstadoById(2));
			ArticuloPedidoDB.actualizarStock(p);
			tran.commit();
			
		} finally {

			sess.close();

		}
	}
	public static Serializable crearPedido(Pedido p) {
		Session sess=null;
		Transaction tran=null;
		try {
			sess=HibernateUtils.openSession();
			tran=sess.beginTransaction();
			Serializable ret= sess.save(p);
			tran.commit();
			return ret;
			
			
		}finally {
			sess.close();
		}
		
	}

}
