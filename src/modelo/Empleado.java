package modelo;

import java.util.List;

public class Empleado extends Usuario {
	public Empleado(int id, String correo, String contraseña, String tipo) {
		super(id, correo, contraseña, tipo);
	}
	public void marcarPedidoEntregado(Pedido pedido) {
		
	}
	
	public void registrarCobro(Pedido pedido) {
		
	}
	
	public List<Pedido> verPedidosPendientes() {
		return null;	
	}
}
