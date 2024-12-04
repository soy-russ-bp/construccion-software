package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
	private int id;
	private Cliente cliente;
	private List<Producto> productos;
	private List<DetallePedido> detallePedido;
	private String estado;
	private double total;
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		this.productos = new ArrayList<>();
		this.detallePedido = new ArrayList<>();
		calcularTotal();
	}
	
	public Map<Producto, Integer> contarFrecuencias(List<Producto> productos) {
        Map<Producto, Integer> frecuencias = new HashMap<>();

        for (Producto producto : productos) {
            frecuencias.put(producto, 0);
        }
        
        

        return frecuencias;
    }
	
	public void calcularTotal() {
		this.total = 0;
		for (DetallePedido producto : detallePedido) {
		    total += producto.getSubtotal();
		}
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public void actualizarEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
}
