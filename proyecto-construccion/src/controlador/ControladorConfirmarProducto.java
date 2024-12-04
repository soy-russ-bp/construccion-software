package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Producto;
import vista.VistaConfirmarProducto;

public class ControladorConfirmarProducto implements ActionListener{
	private VistaConfirmarProducto vista;
	private ControladorCliente controladorCliente;
	private Producto producto;
	
	public ControladorConfirmarProducto(VistaConfirmarProducto vista, 
			Producto producto, ControladorCliente controladorCliente) {
		this.vista = vista;
		this.controladorCliente = controladorCliente;
		this.producto = producto;
		
		this.vista.getBotonAceptar().addActionListener(this);
		this.vista.getBotonCancelar().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.vista.getBotonCancelar()) {
        	vista.dispose();
        }
		
		if (e.getSource() == this.vista.getBotonAceptar()) {
			aceptar();
        }
	}
	
	private void aceptar() {
		int cantidadSeleccionada= (int)this.vista.getCantidad().getValue();
		this.controladorCliente.getProductosAComprar().put(this.producto, cantidadSeleccionada);
		this.controladorCliente.actualizarPedido();
    	this.vista.dispose();
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
