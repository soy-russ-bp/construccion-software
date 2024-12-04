package controlador;

import java.sql.Connection;

import DAO.UsuarioDAO;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Usuario;
import vista.VistaAdministrador;
import vista.VistaCliente;
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

        // Agregar un listener al botón de "no tengo cuenta"
        this.vistaIngreso.addBotonNoTengoCuentaListener(evento -> mostrarVistaRegistro());

        // Agregar un listener al botón de "ya tengo cuenta"
        this.vistaRegistro.addBotonYaTengoCuentaListener(evento -> mostrarVistaIngreso());

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

        validarCorreo(correo);
        validarContrasena(contrasena, contrasenaConfirmada);
    
        try {
            repositorio.agregarCliente(correo, contrasena);
            vistaRegistro.setMensaje("Usuario registrado correctamente.");
            
            redirigirVistaCliente();
        } catch (Exception e) {
            vistaRegistro.setMensaje("Error: " + e.getMessage());
        }
    }

    private void validarCorreo(String correo){
        boolean correoValido = correo.contains("@");
        if (!correoValido) {
            vistaRegistro.setMensaje("Ingresa un correo válido.");
            return; 
        }
    }

    private void validarContrasena(String contrasena, String contrasenaConfirmada){
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
    }

    private void mostrarVistaRegistro() {
        vistaIngreso.setVisible(false);
        vistaRegistro.setVisible(true);
        
    }

    private void mostrarVistaIngreso() {
        vistaRegistro.setVisible(false);
        vistaIngreso.setVisible(true);
    }

    private void redirigirUsuario(Usuario usuario) {
        if (usuario.getTipo().equals("Cliente")) {
            redirigirVistaCliente();   

        } else if (usuario.getTipo().equals("Empleado")) {
            redirigirVistaEmpleado();

        } else if (usuario.getTipo().equals("Administrador")) {
            redirigirVistaAdministrador();

        }
    }

    private void redirigirVistaCliente() {
        //TODO
    }
    private void redirigirVistaEmpleado() {
        //TODO
    }
    private void redirigirVistaAdministrador() {
        //TODO
    }

}
