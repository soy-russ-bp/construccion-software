package controlador;


import DAO.UsuarioDAO;
import modelo.Administrador;
import modelo.Empleado;
import modelo.Usuario;
import vista.VistaAdministrador;
import vista.VistaCliente;
import vista.VistaDetalles;
import vista.VistaDetallesHecho;
import vista.VistaEmpleado;
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
    
        try {
            validarCorreo(correo);
            validarContrasena(contrasena, contrasenaConfirmada);
        
            repositorio.agregarCliente(correo, contrasena);
            vistaRegistro.setMensaje("Usuario registrado correctamente.");
            
            redirigirVistaCliente();
        } catch (IllegalArgumentException e) {
            vistaRegistro.setMensaje(e.getMessage());
        } catch (Exception e) {
            vistaRegistro.setMensaje("Error: " + e.getMessage());
        }
    }
    
    private void validarCorreo(String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Ingresa un correo válido.");
        }
    }
    
    private void validarContrasena(String contrasena, String contrasenaConfirmada) {
        if (contrasena.isEmpty()) {
            throw new IllegalArgumentException("Ingresa una contraseña.");
        }
    
        if (!contrasena.equals(contrasenaConfirmada)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
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
        int id = usuario.getId();
        String correo = usuario.getCorreo();
        String contraseña = usuario.getContraseña();
        String tipo = usuario.getTipo();

        if (usuario.getTipo().equals("Cliente")) {
            redirigirVistaCliente();   

        } else if (usuario.getTipo().equals("Empleado")) {
            redirigirVistaEmpleado(id, correo, contraseña, tipo);

        } else if (usuario.getTipo().equals("Administrador")) {
            redirigirVistaAdministrador(id, correo, contraseña, tipo);

        }
    }

    private void redirigirVistaCliente() {
        //TODO
    }
    
    private void redirigirVistaEmpleado(int id, String correo, String contraseña, String tipo) {
        this.vistaIngreso.setVisible(false);
        VistaEmpleado vistaEmpleado = new VistaEmpleado();
        VistaCliente vistaCliente = new VistaCliente();
        VistaDetallesHecho vistaDetalles = new VistaDetallesHecho();
		Empleado empleado = new Empleado(id, correo, contraseña, tipo);
        new ControladorEmpleado(vistaEmpleado, vistaCliente, vistaDetalles, empleado);

    }
    private void redirigirVistaAdministrador(int id, String correo, String contraseña, String tipo) {
        this.vistaIngreso.setVisible(false);
        VistaAdministrador vistaAdministrador = new VistaAdministrador();
        Administrador administrador = new Administrador( id, correo, contraseña, tipo);
        new ControladorAdministrador(administrador, vistaAdministrador);
    }

}
