package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VistaIngreso extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color BEIGE = new Color(199, 193, 183);
    private final Color CAFE_CLARO = new Color(199, 154, 108);
    private final Color CAFE = new Color(144, 120, 91);
    private final Color AZUL = new Color(51, 102, 255);
    private final Color AZUL_MARINO = new Color(46, 65, 83);

    private JTextField textoCorreoIngreso;
    private JTextField textoContrasenaIngreso;
    private JButton botonIngresar;
    private JButton botonNoTengoCuenta;
    private JLabel mensajeLabel; // Para mostrar el mensaje

    /**
     * Create the frame.
     */
    public VistaIngreso() {
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 500, 400);
        getContentPane().setBackground(CAFE);
        getContentPane().setLayout(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(BEIGE);
        panel.setBounds(50, 50, 400, 300);
        panel.setLayout(null);
        getContentPane().add(panel);

        // Título
        JLabel lblTitulo = new JLabel("Ingresar");
        lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        lblTitulo.setForeground(AZUL_MARINO);
        lblTitulo.setBounds(140, 20, 120, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);

        // Etiqueta correo
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        lblCorreo.setForeground(AZUL_MARINO);
        lblCorreo.setBounds(30, 70, 340, 20);
        panel.add(lblCorreo);

        // Campo correo
        textoCorreoIngreso = new JTextField();
        inicializarCampoTexto(textoCorreoIngreso, 30, 90, panel);

        // Etiqueta contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        lblContrasena.setForeground(AZUL_MARINO);
        lblContrasena.setBounds(30, 130, 340, 20);
        panel.add(lblContrasena);

        // Campo contraseña
        textoContrasenaIngreso = new JTextField();
        inicializarCampoTexto(textoContrasenaIngreso, 30, 150, panel);

        // Botón ingresar
        botonIngresar = new JButton("Ingresar");
        inicializarBoton(botonIngresar, CAFE_CLARO, 30, 200, panel);

        // Etiqueta de mensaje
        mensajeLabel = new JLabel();
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        mensajeLabel.setForeground(Color.RED);
        mensajeLabel.setBounds(30, 270, 340, 20);
        panel.add(mensajeLabel);

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(30, 240, 340, 10);
        panel.add(separador);

        // Botón "No tengo cuenta"
        botonNoTengoCuenta = new JButton("Aún no tengo una cuenta");
        inicializarBotonTextoPlano(botonNoTengoCuenta, AZUL, 30, 250, panel);

        // Fondo
        JLabel fondo = new JLabel();
        fondo.setIcon(
                new ImageIcon("src/vista/fondo-cafe.jpg"));
        fondo.setBounds(0, 0, 500, 400);
        getContentPane().add(fondo);

        // Mostrar ventana
        setVisible(true);
    }

    private void inicializarCampoTexto(JTextField campo, int coordenadaX, int coordenadaY, JPanel panel) {
        campo.setBounds(coordenadaX, coordenadaY, 340, 25);
        campo.setBackground(Color.WHITE);
        campo.setForeground(AZUL_MARINO);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(null);
        panel.add(campo);
    }

    private void inicializarBoton(JButton boton, Color colorFondo, int coordenadaX, int coordenadaY, JPanel panel) {
        boton.setBounds(coordenadaX, coordenadaY, 340, 30);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        boton.setBorder(null);
        panel.add(boton);
    }

    private void inicializarBotonTextoPlano(JButton boton, Color colorTexto, int coordenadaX, int coordenadaY,
            JPanel panel) {
        boton.setBounds(coordenadaX, coordenadaY, 340, 20);
        boton.setForeground(colorTexto);
        boton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        boton.setBackground(BEIGE);
        boton.setBorder(null);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(boton);
    }

    public String getCorreo() {
        return textoCorreoIngreso.getText();
    }

    public String getContraseña() {
        return textoContrasenaIngreso.getText();
    }

    public void setMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    public void addIngresoListener(ActionListener listener) {
        botonIngresar.addActionListener(listener);
    }
}
