package main.java.Almacen.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.Almacen.model.Pedidoxarticulos;

public class ArticuloPedidoDB {

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

}
