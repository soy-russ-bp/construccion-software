package test;

import controlador.ControladorUsuario;
import vista.VistaIngreso;
import vista.VistaRegistro;

public class Main {
	public static void main(String[] args) {
		// ingreso
        VistaIngreso vistaIngreso = new VistaIngreso();
		VistaRegistro vistaRegistro = new VistaRegistro();
        new ControladorUsuario(vistaIngreso, vistaRegistro);
        vistaRegistro.setVisible(false);
	}
}
