/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
import controlador.ControladorInicioSesion;
import vista.VistaIngreso;

public class Main {
    public static void main(String[] args) {
        VistaIngreso vistaIngreso = new VistaIngreso();

        ControladorInicioSesion controladorInicioSesion = new ControladorInicioSesion(vistaIngreso);

        vistaIngreso.setVisible(true);
    }
}

