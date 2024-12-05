package modelo;

public class DetallePedido {
	private Producto producto;
	private double subtotal;
	private int cantidad;
	
	public DetallePedido(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		calcularSubtotal();
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public void calcularSubtotal() {
		this.subtotal = cantidad * producto.getPrecio();
	}
}
