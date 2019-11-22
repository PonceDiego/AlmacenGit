package main.java.Almacen.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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

	public static List<Estadopedido> getEstados() {
		Session sess= null;
		List<Estadopedido> estados=new ArrayList<Estadopedido>();
		try {
			sess= HibernateUtils.openSession();
			Query query=sess.createQuery("from Estadopedido");
			estados=query.getResultList();
			return estados;
		}finally {
			sess.close();
		}
	}

	public static Estadopedido getEstadoByNombre(String estado) {
		Session sess=null;
		Estadopedido ea=null;
		try {
			sess=HibernateUtils.openSession();
			Query query=sess.createQuery("select e from Estadopedido e where e.nombreEstado='"+estado+"'");
			ea=(Estadopedido) query.getSingleResult();
			return ea;
		}finally {
			sess.close();
		}
	}
}
