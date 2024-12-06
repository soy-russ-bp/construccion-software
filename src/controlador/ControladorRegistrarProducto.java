package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		String nombreProducto = this.vista.getNombreProducto().getText();
		double precio = Double.parseDouble(this.vista.getPrecioProducto().getText());
		String descripcionProducto = this.vista.getDescripcionProducto().getText();
		
    	administrador.agregarProducto(nombreProducto, precio, descripcionProducto);
    	this.controladorAdministrador.actualizarTablaProductos();
    	vista.dispose();
	}

}