package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {

	
	private int idPedido;
	private Cliente cliente;
	private String nombreCliente;
	private List<Producto> productos;
	private List<DetallePedido> detallePedido;
	private String estado;
	private float pagoTotal;
	private String totalProductosHechos;
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		this.productos = new ArrayList<>();
		this.detallePedido = new ArrayList<>();
		calcularTotal();
	}
	public Pedido(int id, String nombre, String totalHechos, float pago, String estado) {
		this.idPedido = id;
		this.nombreCliente = nombre;
		this.totalProductosHechos = totalHechos;
		this.pagoTotal = pago;
		this.estado = estado;
	}
	
	public Map<Producto, Integer> contarFrecuencias(List<Producto> productos) {
        Map<Producto, Integer> frecuencias = new HashMap<>();

        for (Producto producto : productos) {
            frecuencias.put(producto, 0);
        }

        return frecuencias;
    }
	
	public void calcularTotal() {
		this.pagoTotal = 0;
		for (DetallePedido producto : detallePedido) {
		    pagoTotal += producto.getSubtotal();
		}
	}

	
	
	public float getTotal() {
		return this.pagoTotal;
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTotalProductosHechos() {
		return totalProductosHechos;
	}

	public void setTotalProductosHechos(String totalProductosHechos) {
		this.totalProductosHechos = totalProductosHechos;
	}

	public float getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(float pagoTotal) {
		this.pagoTotal = pagoTotal;
	}




}