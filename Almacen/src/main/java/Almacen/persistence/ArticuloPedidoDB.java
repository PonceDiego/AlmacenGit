package main.java.Almacen.persistence;

import java.util.ArrayList;
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

	public static boolean actualizarStock(Pedido p) {
		Session sess = null;
		List<Integer> pass= new ArrayList<Integer>();

		try {
			sess = HibernateUtils.openSession();
			List<Pedidoxarticulos> articulos = getArticulosPedidosByPedido(p.getPedidoId());
			Articulo a = null;
			Transaction tran = null;
			for (int i = 0; i < articulos.size(); i++) {
				Pedidoxarticulos pxa = articulos.get(i);
				a = pxa.getArticulo();
				if (a.getStock() >= pxa.getCantidad()) {
					pass.add(0);
				} else {
					pass.add(1);
				}
			}
			int total = 0;
			for (int i = 0; i < pass.size(); i++) {
				total += pass.get(i);
			}
			if (total == 0) {
				for (Pedidoxarticulos pxa : articulos) {
					tran=sess.beginTransaction();
					a=pxa.getArticulo();
					sess.update(a);
					a.setStock(a.getStock() - pxa.getCantidad());
					tran.commit();
					return true;

				}
			}
			return false;
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

	public static void editarArticulosPedidos(int idP, String[] cantidad, String[] nombreA) {
		Session sess = null;
		Transaction tran = null;
		List<Pedidoxarticulos> pxa = null;
		try {
			sess = HibernateUtils.openSession();
			pxa = getArticulosPedidosByPedido(idP);
			Pedidoxarticulos PXA = null;
			for (int i = 0; i < pxa.size(); i++) {
				tran = sess.beginTransaction();
				PXA = pxa.get(i);
				sess.update(PXA);
				int cantidadI = Integer.valueOf(cantidad[i]);
				PXA.setCantidad(cantidadI);
				PXA.setArticulo(ArticuloDB.getArticuloByNombre(nombreA[i]));
				tran.commit();
			}

		} finally {
			sess.close();
		}
	}

}
