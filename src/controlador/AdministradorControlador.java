package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.ProductoDAO;
import modelo.Administrador;
import vista.VistaAdministrador;

public class AdministradorControlador implements ActionListener{
	private Administrador administrador; 
	private VistaAdministrador vista;
	
	public AdministradorControlador(Administrador administrador, VistaAdministrador vista) {
		this.administrador = administrador;
		this.vista = vista;
		
		this.vista.getBotonCrear().addActionListener(this);
		this.vista.getBotonModificar().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonEliminar().addActionListener(this);
		this.vista.getBotonGenerarReporte().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonCrear()) {
        	ProductoDAO.agregarProducto("cafe", 60, 0);
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
