package modelo;

import java.util.List;

public class Pedido {
	private int id;
	private Cliente cliente;
	private List<Producto> productos;
	private String estado;
	private double total;
	
	 public Pedido(int id, Cliente cliente, List<Producto> productos, String estado, double total) {
	        this.id = id;
	        this.cliente = cliente;
	        this.productos = productos;
	        this.estado = estado;
	        this.total = total;
	    }

	    public int getId() {
	        return id;
	    }

	    public double getTotal() {
	        return total;
	    }
	
	
	public void confirmarPedido() {
		
	}
}
