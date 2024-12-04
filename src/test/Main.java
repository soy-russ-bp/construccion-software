package test;

import controlador.AdministradorControlador;
import modelo.Administrador;
import vista.VistaAdministrador;

public class Main {
	public static void main(String[] args) {
		Administrador administrador = new Administrador();
		VistaAdministrador vistaAdministrador = new VistaAdministrador();
		AdministradorControlador administradorControlador = new AdministradorControlador(administrador, vistaAdministrador);
		vistaAdministrador.setVisible(true);
	}
}
