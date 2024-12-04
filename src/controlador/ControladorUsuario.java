package controlador;

import java.sql.Connection;

import DAO.UsuarioDAO;
import modelo.Administrador;
import modelo.Usuario;
import vista.VistaAdministrador;
import vista.VistaIngreso;
import vista.VistaRegistro;

public class ControladorUsuario {
    private VistaIngreso vistaIngreso;
    private VistaRegistro vistaRegistro;
    private UsuarioDAO repositorio;

    public ControladorUsuario(VistaIngreso vistaIngreso, VistaRegistro vistaRegistro, UsuarioDAO repositorio) {
        this.vistaIngreso = vistaIngreso;
        this.vistaRegistro = vistaRegistro;

        this.repositorio = repositorio;
        vistaIngreso.setVisible(true);

        // Agregar un listener al botón de login
        this.vistaIngreso.addIngresoListener(evento -> validarUsuario());

        // Agregar un listener al botón de registro
        this.vistaRegistro.addRegistroListener(evento -> registrarUsuario());

        // Agregar un listener al botón de redirigir

    }

    private void validarUsuario() {
        String correo = vistaIngreso.getCorreo();
        String contraseña = vistaIngreso.getContraseña();

        try {
            Usuario usuario = repositorio.buscarUsuarioPorCorreoYContraseña(correo, contraseña);
            if (usuario != null) {
                vistaIngreso.setMensaje("Bienvenido " + usuario.getTipo());
                redirigirUsuario(usuario);

            } else {
                vistaIngreso.setMensaje("Credenciales incorrectas.");
            }
        } catch (Exception e) {
            vistaIngreso.setMensaje("Error: " + e.getMessage());
        }
    }

    private void registrarUsuario() {
        String correo = vistaRegistro.getCorreo();
        String contrasena = vistaRegistro.getContrasena();
        String contrasenaConfirmada = vistaRegistro.getConfirmarContrasena();

        boolean correoValido = correo.contains("@");
        if (!correoValido) {
            vistaRegistro.setMensaje("Ingresa un correo válido.");
            return; 
        }

        boolean contrasenaVacia = contrasena.equals("");
        if (contrasenaVacia) {
            vistaRegistro.setMensaje("Ingresa una contraseña.");
            return;
            
        }

        boolean contrasenasIguales = contrasena.equals(contrasenaConfirmada);
        if (!contrasenasIguales) {
            vistaRegistro.setMensaje("Las contraseñas no coinciden.");
            return;
        }
    
        try {
            repositorio.agregarCliente(correo, contrasena);
            vistaRegistro.setMensaje("Usuario registrado correctamente.");
        } catch (Exception e) {
            vistaRegistro.setMensaje("Error: " + e.getMessage());
        }
    }

    private void redirigirUsuario(Usuario usuario) {
        if (usuario.getTipo().equals("Cliente")) {
            // Redirigir a la vista de cliente
        } else if (usuario.getTipo().equals("Empleado")) {
            // Redirigir a la vista de empleado
        } else if (usuario.getTipo().equals("Administrador")) {
            // Redirigir a la vista de administrador
            Administrador administrador = new Administrador();
            VistaAdministrador vistaAdministrador = new VistaAdministrador();
		    ControladorAdministrador administradorControlador = new ControladorAdministrador(administrador, vistaAdministrador);
        }
    }
}
