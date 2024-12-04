package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Administrador;
import modelo.Producto;
import vista.VistaAdministrador;
import vista.VistaRegistrarProducto;
import vista.VistaDetalles;

public class ControladorAdministrador implements ActionListener, ListSelectionListener{
	private Administrador administrador;
	private VistaAdministrador vista;
	
	private VistaRegistrarProducto vistaModificarProducto;
	private VistaRegistrarProducto vistaAgregarProducto;
	private ControladorRegistrarProducto controladorAgregarProducto;
	private ControladorModificarProducto controladorModificarProducto;
	
	private VistaDetalles vistaDetalles;
	private Producto productoSeleccionado;
	
	public ControladorAdministrador(Administrador administrador, VistaAdministrador vista) {
		this.vista = vista;
		this.administrador = administrador;
		
		this.vista.getBotonCrear().addActionListener(this);
		this.vista.getBotonModificar().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonEliminar().addActionListener(this);
		this.vista.getBotonGenerarReporte().addActionListener(this);
		
		this.vista.getTablaProductos().getSelectionModel().addListSelectionListener(this);
		
		actualizarTablaProductos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonCrear()) {
			if(this.vistaAgregarProducto == null) {
				this.vistaAgregarProducto = new VistaRegistrarProducto();
			} 
			this.vistaAgregarProducto.setVisible(true);
			
			if(this.controladorAgregarProducto == null) {
				this.controladorAgregarProducto = new ControladorRegistrarProducto(administrador, 
						this.vistaAgregarProducto, this);
			}
        }
		
		if (e.getSource() == this.vista.getBotonModificar()) {
			if(this.vistaModificarProducto == null) {
				this.vistaModificarProducto = new VistaRegistrarProducto();
				this.vistaModificarProducto.setTitulo("Modificar producto");
				this.vistaModificarProducto.setTitle("Modificar producto");
			} 
			this.vistaModificarProducto.setVisible(true);
			
			if(this.controladorModificarProducto == null) {
				this.controladorModificarProducto = new ControladorModificarProducto(administrador, 
						this.vistaModificarProducto, this);
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
		
		if (e.getSource() == this.vista.getBotonEliminar()) {
			int confirmacion = JOptionPane.showConfirmDialog(
                    this.vista,
                    "¿Estás seguro de que deseas eliminar " +
                    productoSeleccionado.getNombre() + " del menú?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
			
			if (confirmacion == JOptionPane.YES_OPTION) {
			    administrador.eliminarProducto(productoSeleccionado.getId());
			    productoSeleccionado = null;
			    actualizarTablaProductos();
			}
        }
		
		if (e.getSource() == this.vista.getBotonGenerarReporte()) {
        	
        }
	}
	
	public void actualizarTablaProductos() {
		vista.getModeloTablaProductos().setRowCount(0);
		for (Producto producto : administrador.mostrarProductos()) {
		    vista.getModeloTablaProductos().addRow(new Object[]{
		    		producto.getId(), producto.getNombre(), producto.getPrecio(), 
		    		producto.getCalificacion()});
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
	        int selectedRow = this.vista.getTablaProductos().getSelectedRow();
	        if (selectedRow != -1) {
	        	int idProductoSeleccionado = (int) vista.getModeloTablaProductos().getValueAt(selectedRow, 0);
	        	productoSeleccionado = administrador.seleccionarProducto(idProductoSeleccionado);
	        	
	        	this.vista.getBotonVerDetalles().setEnabled(true);
	            this.vista.getBotonModificar().setEnabled(true);
	            this.vista.getBotonEliminar().setEnabled(true);
	        } else {
	        	productoSeleccionado = null;
	        	this.vista.getBotonVerDetalles().setEnabled(false);
	            this.vista.getBotonModificar().setEnabled(false);
	            this.vista.getBotonEliminar().setEnabled(false);
	        }
	    }
	}
	
	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

}
