package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.ProductoDAO;
import modelo.Administrador;
import vista.VistaAdministrador;
import vista.VistaAgregarProducto;

public class AdministradorControlador implements ActionListener{
	private Administrador administrador;
	private VistaAdministrador vista;
	private VistaAgregarProducto vistaAgregarProducto;
	private ControladorAgregarProducto controladorAgregarProducto;
	
	public AdministradorControlador(Administrador administrador, VistaAdministrador vista) {
		this.vista = vista;
		this.administrador = administrador;
		
		this.vista.getBotonCrear().addActionListener(this);
		this.vista.getBotonModificar().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonEliminar().addActionListener(this);
		this.vista.getBotonGenerarReporte().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonCrear()) {
			if(this.vistaAgregarProducto == null) {
				this.vistaAgregarProducto = new VistaAgregarProducto();
			} 
			
			if(this.controladorAgregarProducto == null) {
				this.controladorAgregarProducto = new ControladorAgregarProducto(administrador, this.vistaAgregarProducto);
			}
			
        }
		
		if (e.getSource() == this.vista.getBotonModificar()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonEliminar()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonGenerarReporte()) {
        	
        }
	}

}
