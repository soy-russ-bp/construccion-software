package test;

import controlador.ControladorAdministrador;
import modelo.Administrador;
import vista.VistaAdministrador;

public class Main {
	public static void main(String[] args) {
		Administrador administrador = new Administrador();
		VistaAdministrador vistaAdministrador = new VistaAdministrador();
		ControladorAdministrador administradorControlador = new ControladorAdministrador(administrador, vistaAdministrador);
		vistaAdministrador.setVisible(true);
	}
}
