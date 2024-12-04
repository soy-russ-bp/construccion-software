package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import vista.VistaRegistrarProducto;
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
		this.productosAComprar = pedido.contarFrecuencias(verMenu());
		
		
		this.vista.getBotonEnviarPedido().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonAgregarProducto().addActionListener(this);
		
		this.vista.getTablaProductos().getSelectionModel().addListSelectionListener(this);
		this.vista.getTablaPedidos().getSelectionModel().addListSelectionListener(this);
		
		actualizarTablaProductos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonAgregarProducto()) {
			if(this.vistaAgregarProducto == null) {
				this.vistaAgregarProducto = new VistaConfirmarProducto();
			} 
			this.vistaAgregarProducto.setVisible(true);
			
			if(this.controladorAgregarProducto == null) {
				this.controladorAgregarProducto = new ControladorConfirmarProducto(vistaAgregarProducto, 
						productoSeleccionado, this);
			}
        }
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	
    		if(this.vistaDetalles == null) {
    			this.vistaDetalles = new VistaDetalles();
    		} 
    		
    		vistaDetalles.setVisible(true);
    		vistaDetalles.getNombreProducto().setText(String.valueOf(productoSeleccionado.getNombre()));
			vistaDetalles.getCalificacion().setText(String.valueOf(productoSeleccionado.getCalificacion()));
			vistaDetalles.getPrecio().setText(String.valueOf(productoSeleccionado.getPrecio()));
			vistaDetalles.getDescripcion().setText(String.valueOf(productoSeleccionado.getDescripcion()));
        }
		
		if (e.getSource() == this.vista.getBotonEnviarPedido()) {
			int confirmacion = JOptionPane.showConfirmDialog(
                    this.vista,
                    "¿Estás seguro de realizar el pedido?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
			
			if (confirmacion == JOptionPane.YES_OPTION) {
			    //this.cliente.hacerPedido(this.pedido);
			    productoSeleccionado = null;
			    actualizarTablaProductos();
			}
        }
	}
	
	public void actualizarTablaProductos() {
		vista.getModeloTablaProductos().setRowCount(0);
		for (Producto producto : verMenu()) {
		    vista.getModeloTablaProductos().addRow(new Object[]{
		    		producto.getId(), producto.getNombre(), producto.getPrecio(), 
		    		producto.getCalificacion()});
		}
	}
	
	public void actualizarTablaPedidos() {
		vista.getModeloTablaPedidos().setRowCount(0);
		
		for (Map.Entry<Producto, Integer> entry : getProductosAComprar().entrySet()) {
		    if (getProductosAComprar().get(entry.getKey()) >= 1) {
		    	vista.getModeloTablaPedidos().addRow(new Object[]{
		    			entry.getKey().getNombre(), getProductosAComprar().get(entry.getKey()), 
		    			entry.getKey().getPrecio() * getProductosAComprar().get(entry.getKey())});
		    }
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
	        int selectedRow = this.vista.getTablaProductos().getSelectedRow();
	        if (selectedRow != -1) {
	        	int idProductoSeleccionado = (int) vista.getModeloTablaProductos().getValueAt(selectedRow, 0);
	        	productoSeleccionado = ProductoDAO.seleccionarProducto(idProductoSeleccionado);
	        	
	        	this.vista.getBotonVerDetalles().setEnabled(true);
	            this.vista.getBotonAgregarProducto().setEnabled(true);
	        } else {
	        	productoSeleccionado = null;
	        	this.vista.getBotonVerDetalles().setEnabled(false);
	            this.vista.getBotonAgregarProducto().setEnabled(false);
	        }
	    }
	}
	
	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}
	
	public List<Producto> verMenu() {
		return ProductoDAO.mostrarProductos();
	}
	
	public Map<Producto, Integer> getProductosAComprar(){
		return productosAComprar;
	}

}
