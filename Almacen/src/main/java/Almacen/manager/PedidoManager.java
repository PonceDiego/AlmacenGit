package main.java.Almacen.manager;

import java.io.Serializable;
import java.util.Date;

import main.java.Almacen.model.Pedido;
import main.java.Almacen.persistence.ArticuloPedidoDB;
import main.java.Almacen.persistence.EstadoPedidoDB;
import main.java.Almacen.persistence.PedidoDB;
import main.java.Almacen.persistence.UsuarioDB;

public class PedidoManager {

	public static void createPedido(String obser, String user, String arts, String cants) {
		Pedido pedido = new Pedido();
		pedido.setObservaciones(obser);
		int idU=Integer.parseInt(user);
		pedido.setUsuario(UsuarioDB.getUsuarioByID(idU));
		pedido.setFecha(new Date());
		int idEstado=1;
		pedido.setEstadopedido(EstadoPedidoDB.getEstadoById(idEstado));
		
		
		Serializable id=PedidoDB.crearPedido(pedido);
		
		int idP= (int) id;
		
		
		String[] articulos = arts.split(" - ");
		String[] cantidades = cants.split(" - ");
		for (int i = 0; i < articulos.length; i++) {
			ArticuloPedidoDB.crearArticuloPedido(cantidades[i], articulos[i], idP);
		}


	}

	public static void entregarPedido(String id) {

		PedidoDB.entregaPedido(toInt(id));
	}

	public static void eliminarPedido(String id) {
		PedidoDB.eliminarPedidoById(toInt(id));
	}

	private static int toInt(String id) {
		int ids = Integer.parseInt(id);
		return ids;
	}

}
