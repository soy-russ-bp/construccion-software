package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
	private int id;
	private Cliente cliente;
	private String nombreCliente;
	private LocalDate fecha;
	private List<DetallePedido> detallePedido;
	private String estado;
	private double total;
	private String totalProductosHechos;
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		this.detallePedido = new ArrayList<>();
		calcularTotal();
	}
	
	public Pedido(int id, String nombre, String totalHechos, float pago, String estado) {
		this.id = id;
		this.nombreCliente = nombre;
		this.totalProductosHechos = totalHechos;
		this.total = pago;
		this.estado = estado;
	}
	
	public Pedido(int id, LocalDate fecha, double total) {
		setId(id);
		setFecha(fecha);
		setTotal(total);
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
		    this.total += producto.getSubtotal();
		}
	}
	
	private void setTotal(double total) {
		this.total = total;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void agregarProductoAPedido(DetallePedido producto) {
		this.detallePedido.add(producto);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
