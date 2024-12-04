package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Administrador;
import vista.VistaRegistrarProducto;

public class ControladorRegistrarProducto implements ActionListener{
	private Administrador administrador; 
	private VistaRegistrarProducto vista;
	private ControladorAdministrador controladorAdministrador;
	
	public ControladorRegistrarProducto(Administrador administrador, VistaRegistrarProducto vista, 
			ControladorAdministrador controladorAdministrador) {
		inicializarControlador(administrador, vista, controladorAdministrador);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.vista.getBotonCancelar()) {
        	vista.dispose();
        }
		
		if (e.getSource() == this.vista.getBotonGuardar()) {
			guardarDatos();
        }
	}
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public VistaRegistrarProducto getVista() {
		return vista;
	}

	public void setVista(VistaRegistrarProducto vista) {
		this.vista = vista;
	}

	public ControladorAdministrador getControladorAdministrador() {
		return controladorAdministrador;
	}

	public void setControladorAdministrador(ControladorAdministrador controladorAdministrador) {
		this.controladorAdministrador = controladorAdministrador;
	}

	public void inicializarControlador(Administrador administrador, VistaRegistrarProducto vista, 
			ControladorAdministrador controladorAdministrador) {
		setAdministrador(administrador);
		setVista(vista);
		setControladorAdministrador(controladorAdministrador);
		
		this.vista.getBotonCancelar().addActionListener(this);
		this.vista.getBotonGuardar().addActionListener(this);
	}
	
	public void guardarDatos() {
	    // Obtener datos de los JTextField
	    String nombreProducto = this.vista.getNombreProducto().getText().trim();
	    String precioTexto = this.vista.getPrecioProducto().getText().trim();
	    String descripcionProducto = this.vista.getDescripcionProducto().getText().trim();
	    
	    // Validar que no estén vacíos
	    if (nombreProducto.isEmpty()) {
	        JOptionPane.showMessageDialog(vista, "El nombre del producto no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (precioTexto.isEmpty()) {
	        JOptionPane.showMessageDialog(vista, "El precio del producto no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (descripcionProducto.isEmpty()) {
	        JOptionPane.showMessageDialog(vista, "La descripción del producto no puede estar vacía.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Validar que el precio sea un número positivo
	    double precio;
	    try {
	        precio = Double.parseDouble(precioTexto);
	        if (precio <= 0) {
	            JOptionPane.showMessageDialog(vista, "El precio debe ser un número positivo.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Si todas las validaciones pasan, agregar el producto
	    administrador.agregarProducto(nombreProducto, precio, descripcionProducto);
	    this.controladorAdministrador.actualizarTablaProductos();
	    vista.dispose();
	}

}
