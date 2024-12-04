package modelo;

import DAO.PedidoDAO;

public class Cliente extends Usuario {
	public void calificarProducto(Producto producto, String retroalimentacion) {
		
	}
	
	public void darRetroalimentacion(Producto producto, String retroalimentacion) {
		
	}
	
	public void hacerPedido(Pedido pedido) {
		PedidoDAO.hacerPedido(pedido);
	}
	
	
}
