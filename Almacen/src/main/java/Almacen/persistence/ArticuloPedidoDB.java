package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.Pedidoxarticulos;

public class ArticuloPedidoDB {

	public static void borrarArticulosPedidos(int p) {
		Session sess = null;
		Transaction tran = null;
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			Query<Pedidoxarticulos> query = sess.createQuery("delete from Pedidoxarticulos where pedido=" + p);
			int i = query.executeUpdate();
			tran.commit();
		} finally {
			sess.close();
		}

	}

	public static void actualizarStock(Pedido p) {
		Session sess = null;

		try {
			sess = HibernateUtils.openSession();
			List<Pedidoxarticulos> articulos = getArticulosPedidosByPedido(p.getPedidoId());
			for (Pedidoxarticulos pxa : articulos) {
				Transaction tran = sess.beginTransaction();

				Articulo a = pxa.getArticulo();

				sess.saveOrUpdate(a);
				a.setStock(a.getStock() - pxa.getCantidad());

				tran.commit();
			}
		} finally {
			sess.close();
		}
	}

	public static List<Pedidoxarticulos> getArticulosPedidosByPedido(int idPedido) {
		Session sess = null;
		List<Pedidoxarticulos> articulo = null;
		try {

			sess = HibernateUtils.openSession();
			Query<Pedidoxarticulos> query = sess.createQuery("from Pedidoxarticulos where pedido=" + idPedido);
			articulo = query.getResultList();

			for (Pedidoxarticulos ar : articulo) {
				Hibernate.initialize(ar.getArticulo());
				Hibernate.initialize(ar.getArticulo().getSubcategoria());
				Hibernate.initialize(ar.getArticulo().getSubcategoria().getCategoria());
				Hibernate.initialize(ar.getPedido());
			}

			return articulo;

		} finally {

			sess.close();
		}
	}

	public static void crearArticuloPedido(String cantidad, String nombreA, int idP) {
		Session sess = null;
		Transaction tran = null;
		Pedidoxarticulos pxa = new Pedidoxarticulos();
		try {
			sess = HibernateUtils.openSession();
			tran = sess.beginTransaction();
			pxa.setArticulo(ArticuloDB.getArticuloByNombre(nombreA));
			int cant = Integer.parseInt(cantidad);
			pxa.setCantidad(cant);
			pxa.setPedido(PedidoDB.getPedidoByID(idP));
			sess.save(pxa);
			tran.commit();
		} finally {
			sess.close();
		}
	}

}
