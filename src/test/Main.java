package test;

import DAO.UsuarioDAO;
import controlador.ControladorEmpleado;
import controlador.ControladorUsuario;
import modelo.Empleado;
import modelo.Producto;
import vista.VistaCliente;
import vista.VistaDetallesHecho;
import vista.VistaEmpleado;
import vista.VistaIngreso;
import vista.VistaRegistro;

public class Main {
	public static void main(String[] args) {

		//Ingreso:
        UsuarioDAO repositorio = new UsuarioDAO();
        VistaIngreso vistaIngreso = new VistaIngreso();
		VistaRegistro vistaRegistro = new VistaRegistro();
        new ControladorUsuario(vistaIngreso, vistaRegistro, repositorio);

	}
}
