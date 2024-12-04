package controlador;

import java.sql.Connection;

import DAO.UsuarioDAO;
import modelo.Usuario;
import vista.VistaIngreso;

public class ControladorUsuario {
    private VistaIngreso vista;
    private UsuarioDAO repositorio;

    public ControladorUsuario(VistaIngreso vista, UsuarioDAO repositorio) {
        this.vista = vista;
        this.repositorio = repositorio;
        vista.setVisible(true);

        // Agregar un listener al botón de login
        this.vista.addLoginListener(e -> validarUsuario());
    }

    private void validarUsuario() {
        String correo = vista.getCorreo();
        String contraseña = vista.getContraseña();

        try {
            Usuario usuario = repositorio.buscarUsuarioPorCorreoYContraseña(correo, contraseña);
            if (usuario != null) {
                vista.setMensaje("Bienvenido " + usuario.getTipo());
                // Aquí se puede redirigir a la vista correspondiente




            } else {
                vista.setMensaje("Credenciales incorrectas.");
            }
        } catch (Exception e) {
            vista.setMensaje("Error: " + e.getMessage());
        }
    }
}
