package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.ProductoDAO;
import modelo.Administrador;
import modelo.Producto;
import modelo.Reporte;
import vista.VistaAdministrador;
import vista.VistaAgregarProducto;
import vista.VistaDetalles;

public class ControladorAdministrador implements ActionListener, ListSelectionListener{
	private Administrador administrador;
	private VistaAdministrador vista;
	
	private VistaAgregarProducto vistaAgregarProducto;
	private ControladorAgregarProducto controladorAgregarProducto;
	
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
	private void generarReporte() {
        String periodo = (String) vista.getSelectorMes().getSelectedItem();
        if ("Mes".equals(periodo)) {
            vista.mostrarMensaje("Selecciona un periodo válido para el reporte.");
            return;
        }

        // Obtener productos vendidos desde el modelo
        Reporte reporte = administrador.verReportes(periodo);

        // Generar y guardar el reporte
        String filePath = "reporte_" + periodo + ".txt";
        reporte.guardarComoTxt(filePath);
        vista.mostrarMensaje("Reporte generado correctamente: " + filePath);

        // Actualizar la tabla de historial en la vista
        List<Producto> productosVendidos = ProductoDAO.obtenerProductosVendidos();
        Object[][] datosTabla = productosVendidos.stream()
                .map(p -> new Object[]{p.getId(), p.getNombre(), p.getCantidadVendida(), p.getTotalVendido()})
                .toArray(Object[][]::new);
        vista.actualizarHistorial(datosTabla);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonCrear()) {
			if(this.vistaAgregarProducto == null) {
				this.vistaAgregarProducto = new VistaAgregarProducto();
			} 
			
			if(this.controladorAgregarProducto == null) {
				this.controladorAgregarProducto = new ControladorAgregarProducto(administrador, 
						this.vistaAgregarProducto, this);
			}
			
        }
		
		if (e.getSource() == this.vista.getBotonModificar()) {
        	
        }
		
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	
    		if(this.vistaDetalles == null) {
    			this.vistaDetalles = new VistaDetalles();
    			vistaDetalles.getPrecioProducto();
    		} 
    		
        }
		
		if (e.getSource() == this.vista.getBotonEliminar()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonGenerarReporte()) {
        	generarReporte();
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
	        	administrador.seleccionarProducto(idProductoSeleccionado);
	        	
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

}
