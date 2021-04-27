package main.java.Almacen.persistence;

import org.hibernate.Session;

import main.java.Almacen.model.Llave;

public class LlaveDB {

	public static Llave getLlaveById(int id) {
		Session sess = null;
		Llave llave;

		try {
			sess = HibernateUtils.openSession();
			llave = sess.get(Llave.class, id);

		} finally {
			sess.close();
		}
		return llave;
	}

}
