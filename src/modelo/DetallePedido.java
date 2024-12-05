package modelo;

public class DetallePedido {
	private Producto producto;
	private double subtotal;
	private int cantidad;
	private String nombreProducto;
	
	public DetallePedido(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		calcularSubtotal();
	}
	
	public DetallePedido(String nombreProducto, int cantidad) {
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
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
	
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
}
