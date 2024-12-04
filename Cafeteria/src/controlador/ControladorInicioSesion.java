/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import vista.VistaIngreso;
import vista.VistaRegistro;


public class ControladorInicioSesion {
    private final VistaIngreso vista;

    public ControladorInicioSesion(VistaIngreso vista) {
        this.vista = vista;
        this.vista.addNoCuentaListener(e -> abrirVistaRegistro());
    }

    private void abrirVistaRegistro() {
        vista.dispose();
        VistaRegistro vistaRegistro = new VistaRegistro();
        vistaRegistro.setVisible(true);
    }


}
