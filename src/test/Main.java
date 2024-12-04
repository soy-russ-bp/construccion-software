package test;

import java.sql.Connection;
import java.sql.DriverManager;

import DAO.UsuarioDAO;
import controlador.ControladorAdministrador;
import controlador.ControladorUsuario;
import modelo.Administrador;
import vista.VistaAdministrador;
import vista.VistaIngreso;

public class Main {
	public static void main(String[] args) {
		//Administrador administrador = new Administrador();
		//VistaAdministrador vistaAdministrador = new VistaAdministrador();
		//ControladorAdministrador administradorControlador = new ControladorAdministrador(administrador, vistaAdministrador);
		//vistaAdministrador.setVisible(true);

		//Ingreso:
		
        UsuarioDAO repositorio = new UsuarioDAO();
        VistaIngreso vista = new VistaIngreso();
        new ControladorUsuario(vista, repositorio);

	}
}
