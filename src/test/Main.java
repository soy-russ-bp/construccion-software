package test;

import java.sql.Connection;
import java.sql.DriverManager;

import DAO.UsuarioDAO;
import controlador.ControladorAdministrador;
import controlador.ControladorUsuario;
import modelo.Administrador;
import vista.VistaAdministrador;
import vista.VistaIngreso;
import vista.VistaRegistro;

public class Main {
	public static void main(String[] args) {

		//Ingreso:
        UsuarioDAO repositorio = new UsuarioDAO();
        VistaIngreso vistaIngreso = new VistaIngreso();
		VistaRegistro vistaRegistro = new VistaRegistro();
        new ControladorUsuario(vistaIngreso, vistaRegistro, repositorio);

		// Registro:


	}
}
