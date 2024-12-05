package modelo;

import DAO.PedidoDAO;

public class Cliente extends Usuario {
	public Cliente(int id, String correo, String contrasena, String tipo) {
		super(id, correo, contrasena, tipo);
	}

	public void calificarProducto(Producto producto, String retroalimentacion) {
		
	}
	
	public void darRetroalimentacion(Producto producto, String retroalimentacion) {
		
	}
	
	public void hacerPedido(Pedido pedido) {
		PedidoDAO.hacerPedido(pedido);
		for(DetallePedido producto : pedido.getDetallePedido()) {
			PedidoDAO.agregarProductoAlPedido(pedido.getId(), producto);
		}
	}
	
	
}
