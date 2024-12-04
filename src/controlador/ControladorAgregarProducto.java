package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Administrador;
import vista.VistaAgregarProducto;

public class ControladorAgregarProducto implements ActionListener{
	private Administrador administrador; 
	private VistaAgregarProducto vista;
	private ControladorAdministrador controladorAdministrador;
	
	public ControladorAgregarProducto(Administrador administrador, VistaAgregarProducto vista, 
			ControladorAdministrador controladorAdministrador) {
		this.administrador = administrador;
		this.vista = vista;
		this.controladorAdministrador = controladorAdministrador;
		
		this.vista.getBotonCancelar().addActionListener(this);
		this.vista.getBotonAgregar().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.vista.getBotonCancelar()) {
        	vista.dispose();
        }
		
		if (e.getSource() == this.vista.getBotonAgregar()) {
			
			String nombreProducto = this.vista.getNombreProducto().getText();
			double precio = Double.parseDouble(this.vista.getPrecioProducto().getText());
			String descripcionProducto = this.vista.getDescripcionProducto().getText();
			
        	administrador.agregarProducto(nombreProducto, precio, descripcionProducto);
        	this.controladorAdministrador.actualizarTablaProductos();
        	vista.dispose();
        }
	}

}