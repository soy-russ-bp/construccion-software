package modelo;

import java.util.List;

import DAO.PedidoDAO;

public class Empleado extends Usuario {
	private PedidoDAO pedidoDAO;
	public Empleado(int id, String correo, String contraseña, String tipo) {
		super(id, correo, contraseña, tipo);
	}

	public void marcarPedidoHecho(Pedido pedido) {
		int idPedido = pedido.getId();
		pedidoDAO.marcarPedidoCompletado(idPedido);
	}
	
	public void marcarPedidoEntregado(Pedido pedido) {
		pedidoDAO.marcarPedidoEntregado(pedido);
	}
	
	public int obtenerCantidadProductosPorPedido(int idPedido) {
		return pedidoDAO.obtenerCantidadProductosPorPedido(idPedido);
	}
}
