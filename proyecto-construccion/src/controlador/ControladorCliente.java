package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.ProductoDAO;
import modelo.Cliente;
import modelo.DetallePedido;
import modelo.Pedido;
import modelo.Producto;
import vista.VistaCliente;
import vista.VistaConfirmarProducto;
import vista.VistaDetalles;

public class ControladorCliente implements ActionListener, ListSelectionListener{
	private Cliente cliente;
	private VistaCliente vista;
	
	private VistaConfirmarProducto vistaAgregarProducto;
	private ControladorConfirmarProducto controladorAgregarProducto;
	
	private VistaDetalles vistaDetalles;
	private Producto productoSeleccionado;
	private Pedido pedido;
	private Map<Producto, Integer> productosAComprar;
	
	public ControladorCliente(Cliente cliente, VistaCliente vista) {
		this.vista = vista;
		this.cliente = cliente;
		this.pedido = new Pedido(cliente);
		this.productosAComprar = pedido.contarFrecuencias(consultarMenu());
		
		
		this.vista.getBotonEnviarPedido().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonAgregarProducto().addActionListener(this);
		
		this.vista.getTablaProductos().getSelectionModel().addListSelectionListener(this);
		
		actualizarTablaProductos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonAgregarProducto()) {
			agregarProducto();
        }
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	verDetalle();
        }
		
		if (e.getSource() == this.vista.getBotonEnviarPedido()) {
			enviarPedido();
        }
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
	        int selectedRow = this.vista.getTablaProductos().getSelectedRow();
	        if (selectedRow != -1) {
	        	int idProductoSeleccionado = (int) vista.getModeloTablaProductos().getValueAt(selectedRow, 0);
	        	this.productoSeleccionado = ProductoDAO.seleccionarProducto(idProductoSeleccionado);
	        	
	        	this.vista.getBotonVerDetalles().setEnabled(true);
	            this.vista.getBotonAgregarProducto().setEnabled(true);
	        } else {
	        	productoSeleccionado = null;
	        	this.vista.getBotonVerDetalles().setEnabled(false);
	            this.vista.getBotonAgregarProducto().setEnabled(false);
	        }
	    }
	}
	
	private void agregarProducto() {
		if(this.vistaAgregarProducto == null) {
			this.vistaAgregarProducto = new VistaConfirmarProducto();
		} 
		this.vistaAgregarProducto.setVisible(true);
		
		if(this.controladorAgregarProducto == null) {
			this.controladorAgregarProducto = new ControladorConfirmarProducto(this.vistaAgregarProducto, 
					this.productoSeleccionado, this);
		}
		this.controladorAgregarProducto.setProducto(productoSeleccionado);
	}
	
	private void verDetalle() {
		if(this.vistaDetalles == null) {
			this.vistaDetalles = new VistaDetalles();
		} 
		
		vistaDetalles.setVisible(true);
		vistaDetalles.getNombreProducto().setText(String.valueOf(productoSeleccionado.getNombre()));
		vistaDetalles.getCalificacion().setText(String.valueOf(productoSeleccionado.getCalificacion()));
		vistaDetalles.getPrecio().setText(String.valueOf(productoSeleccionado.getPrecio()));
		vistaDetalles.getDescripcion().setText(String.valueOf(productoSeleccionado.getDescripcion()));
	}
	
	private void enviarPedido() {
		int confirmacion = JOptionPane.showConfirmDialog(
                this.vista,
                "¿Estás seguro de realizar el pedido?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );
		
		if (confirmacion == JOptionPane.YES_OPTION) {
		    try {
				this.cliente.hacerPedido(this.pedido);
				productoSeleccionado = null;
			    actualizarPedido();
			    actualizarTablaProductos();
			    this.vista.getTotalPedido().setText("0.00");
			} catch (SQLException e) {
				this.vista.mostrarMensaje("No se pudo realizar el pedido.");
			}
		}
		this.vista.borrarTabla(this.vista.getModeloTablaPedidos());
	}
	
	public void actualizarPedido() {
		this.vista.getModeloTablaPedidos().setRowCount(0);
		this.pedido.getDetallePedido().clear();
		
		for (Map.Entry<Producto, Integer> productoSeleccionado : getProductosAComprar().entrySet()) {
			Producto producto = productoSeleccionado.getKey();
			int cantidad = getProductosAComprar().get(producto);
			
			this.pedido.agregarProductoAPedido(new DetallePedido(producto, cantidad));
		    if (cantidad >= 1) {
		    	this.vista.getModeloTablaPedidos().addRow(new Object[]{producto.getNombre(), cantidad,
		    			producto.getPrecio() * cantidad});
		    }
		}
		
		Iterator<DetallePedido> iterator = this.pedido.getDetallePedido().iterator();
        while (iterator.hasNext()) {
            DetallePedido elemento = iterator.next();
            if (elemento.getCantidad() < 1) {
                iterator.remove();
            }
        }
		
		this.pedido.calcularTotal();
		this.vista.getTotalPedido().setText(String.valueOf(pedido.getTotal()));
	}
	
	public void actualizarTablaProductos() {
		vista.getModeloTablaProductos().setRowCount(0);
		for (Producto producto : consultarMenu()) {
		    vista.getModeloTablaProductos().addRow(new Object[]{
		    		producto.getId(), producto.getNombre(), producto.getPrecio(), 
		    		producto.getCalificacion()});
		}
	}
	
	public List<Producto> consultarMenu() {
		return ProductoDAO.mostrarProductos();
	}
	
	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}
	
	public Map<Producto, Integer> getProductosAComprar(){
		return productosAComprar;
	}

}
