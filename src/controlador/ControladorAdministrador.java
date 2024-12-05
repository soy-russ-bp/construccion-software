package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Administrador;
import modelo.Pedido;
import modelo.Producto;
import modelo.Reporte;
import vista.VistaAdministrador;
import vista.VistaRegistrarProducto;
import vista.VistaDetalles;

public class ControladorAdministrador implements ActionListener, ListSelectionListener{
	private Administrador administrador;
	private VistaAdministrador vista;
	
	private VistaDetalles vistaDetalles;
	private VistaRegistrarProducto vistaModificarProducto;
	private VistaRegistrarProducto vistaAgregarProducto;
	private ControladorRegistrarProducto controladorAgregarProducto;
	private ControladorModificarProducto controladorModificarProducto;
	
	private Producto productoSeleccionado;
	private Reporte reporte;
	
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
			agregarProducto();
        }
		
		if (e.getSource() == this.vista.getBotonModificar()) {
			modificarProducto();
        }
		
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	verDetalles();
        }
		
		if (e.getSource() == this.vista.getBotonEliminar()) {
			eliminarProducto();
        }
		
		if (e.getSource() == this.vista.getBotonGenerarReporte()) {
        	generarReporte();
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
	
	private void agregarProducto() {
		if(this.vistaAgregarProducto == null) {
			this.vistaAgregarProducto = new VistaRegistrarProducto();
		} 
		this.vistaAgregarProducto.setVisible(true);
		
		if(this.controladorAgregarProducto == null) {
			this.controladorAgregarProducto = new ControladorRegistrarProducto(administrador, 
					this.vistaAgregarProducto, this);
		}
	}
	
	private void eliminarProducto() {
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
	
	private void modificarProducto() {
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
		this.controladorModificarProducto.inicializarVista();
	}
	
	private void verDetalles() {
		if(this.vistaDetalles == null) {
			this.vistaDetalles = new VistaDetalles();
		} 
		
		vistaDetalles.setVisible(true);
		vistaDetalles.getNombreProducto().setText(String.valueOf(productoSeleccionado.getNombre()));
		vistaDetalles.getCalificacion().setText(String.valueOf(productoSeleccionado.getCalificacion()));
		vistaDetalles.getPrecio().setText(String.valueOf(productoSeleccionado.getPrecio()));
		vistaDetalles.getDescripcion().setText(String.valueOf(productoSeleccionado.getDescripcion()));
	}
	
	private void generarReporte() {
        try {
        establecerPeriodo(this.vista.getFechaInicio().getText(), this.vista.getFechaFin().getText());
        
        if (!reporte.getPedidos().isEmpty()) {
        	String filePath = "reporte_" + reporte.getFechaInicio() + 
        		"_" + reporte.getFechaFin() + ".txt";
        	reporte.guardarComoTxt(filePath);
        	vista.mostrarMensaje("Reporte generado correctamente: " + filePath);

        	actualizarTablaReporte();
        }	else {
        	vista.mostrarMensaje("No se encontraron resultados.");
        }
        this.vista.getTotalVentas().setText(String.valueOf(reporte.getTotalVentas()));
        } catch (Exception e){
        	 JOptionPane.showMessageDialog(this.vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public void actualizarTablaReporte() {
		vista.getModeloTablaReporte().setRowCount(0);
		for (Pedido pedido : reporte.getPedidos()) {
		    vista.getModeloTablaReporte().addRow(new Object[]{
		    	pedido.getId(), pedido.getFecha(), pedido.getTotal(), 
		    });
		}
	}
	
	private void establecerPeriodo(String fechaInicioTexto, String fechaFinalTexto) throws Exception {
		this.reporte = null;
		String fechaInicioStr = fechaInicioTexto.trim();
        String fechaFinStr = fechaFinalTexto.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);
            
            if (fechaInicio.isAfter(fechaFin)) {
                throw new Exception ("La fecha de inicio no puede ser posterior a la fecha de fin.");
            }
            this.reporte = new Reporte(fechaInicio, fechaFin);

        } catch (DateTimeParseException e) {
        	throw new Exception( "Por favor, introduce las fechas en el formato correcto (yyyy-MM-dd).");
        } catch (Exception ex) {
        	throw ex;
        }
	}
	
	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

}
