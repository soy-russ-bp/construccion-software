package test;

import controlador.ControladorAdministrador;
import controlador.ControladorCliente;
import modelo.Administrador;
import modelo.Cliente;
import vista.VistaAdministrador;
import vista.VistaCliente;

public class Main {
	public static void main(String[] args) {
		Administrador administrador = new Administrador();
		VistaAdministrador vistaAdministrador = new VistaAdministrador();
		ControladorAdministrador administradorControlador = new ControladorAdministrador(administrador, vistaAdministrador);
		vistaAdministrador.setVisible(true);
		
		Cliente cliente = new Cliente();
		VistaCliente vistaCliente = new VistaCliente();
		ControladorCliente controladorCliente = new ControladorCliente(cliente, vistaCliente);
		vistaCliente.setVisible(true);
	}
}
