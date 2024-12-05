package controlador;

import DAO.UsuarioDAO;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Usuario;
import vista.VistaAdministrador;
import vista.VistaCliente;
import vista.VistaDetallesHecho;
import vista.VistaEmpleado;
import vista.VistaIngreso;
import vista.VistaRegistro;

public class ControladorUsuario {
    private VistaIngreso vistaIngreso;
    private VistaRegistro vistaRegistro;

    public ControladorUsuario(VistaIngreso vistaIngreso, VistaRegistro vistaRegistro) {
        this.vistaIngreso = vistaIngreso;
        this.vistaRegistro = vistaRegistro;

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
        String contrasena = vistaIngreso.getContrasena();

        try {
            Usuario usuario = UsuarioDAO.buscarUsuario(correo, contrasena);
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
        	UsuarioDAO.agregarCliente(correo, contrasena);
            vistaRegistro.setMensaje("Usuario registrado correctamente.");
            
            Usuario nuevoUsuario = UsuarioDAO.buscarUsuario(correo, contrasena);
            redirigirVistaCliente(nuevoUsuario.getId(), nuevoUsuario.getCorreo(), 
            		nuevoUsuario.getContrasena(), nuevoUsuario.getTipo());
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
            redirigirVistaCliente(usuario.getId(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo());   

        } else if (usuario.getTipo().equals("Empleado")) {
            redirigirVistaEmpleado(usuario.getId(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo());

        } else if (usuario.getTipo().equals("Administrador")) {
            redirigirVistaAdministrador(usuario.getId(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo());
        }
        	vistaIngreso.setVisible(false);
        	vistaRegistro.setVisible(false);
    }

    private void redirigirVistaCliente(int id, String correo, String contrasena, String tipo) {
    	this.vistaIngreso.setVisible(false);
        VistaCliente vistaCliente = new VistaCliente();
        Cliente cliente = new Cliente(id, correo, contrasena, tipo);
        new ControladorCliente(cliente, vistaCliente);
    }
    
    private void redirigirVistaEmpleado(int id, String correo, String contraseña, String tipo) {
        this.vistaIngreso.setVisible(false);
        VistaEmpleado vistaEmpleado = new VistaEmpleado();
        VistaCliente vistaCliente = new VistaCliente();
        VistaDetallesHecho vistaDetalles = new VistaDetallesHecho();
		Empleado empleado = new Empleado(id, correo, contraseña, tipo);
        new ControladorEmpleado(vistaEmpleado, vistaCliente, vistaDetalles, empleado);

    }
    
    private void redirigirVistaAdministrador(int id, String correo, String contrasena, String tipo) {
        this.vistaIngreso.setVisible(false);
        VistaAdministrador vistaAdministrador = new VistaAdministrador();
        Administrador administrador = new Administrador(id, correo, contrasena, tipo);
        new ControladorAdministrador(administrador, vistaAdministrador);
    }

}
