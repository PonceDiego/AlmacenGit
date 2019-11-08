package main.java.Almacen.persistence;

import org.hibernate.Session;

import main.java.Almacen.model.Estadopedido;

public class EstadoPedidoDB {
	
	public static Estadopedido getEstadoById(int id) {
		Session sess = null;
		Estadopedido c=null;
		try {
			sess = HibernateUtils.openSession();;
			c= sess.get(Estadopedido.class, id);
			return c;
		}finally {
			sess.close();
		}
	}
}
