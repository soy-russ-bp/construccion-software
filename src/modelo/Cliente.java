package modelo;

public class Cliente extends Usuario {
	public Cliente(int id, String correo, String contraseña, String tipo) {
		super(id, correo, contraseña, tipo);
	}
	public void calificarProducto(Producto producto, String retroalimentacion) {
		
	}
	
	public void darRetroalimentacion(Producto producto, String retroalimentacion) {
		
	}
	
	public void hacerPedido(Pedido pedido) {
		
	}
}