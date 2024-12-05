package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VistaMenuProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private final Color BEIGE = new Color(199, 193, 183);
    private final Color CAFE_CLARO = new Color(199, 154, 108);
    private final Color CAFE = new Color(144, 120, 91);
    private final Color AZUL_MARINO = new Color(46, 65, 83);

    private JTextField nombreProductoTxt;
    private JTextField precioProductoTxt;
    private JTextField descripcionProductoTxt;
    private JButton botonAgregar;
    private JButton botonCancelar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaMenuProductos frame = new VistaMenuProductos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VistaMenuProductos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);
        getContentPane().setBackground(CAFE);

        // Título
        JLabel titulo = new JLabel("Crear producto nuevo");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        titulo.setForeground(AZUL_MARINO);
        titulo.setBounds(50, 20, 400, 40);
        getContentPane().add(titulo);

        // Nombre del producto
        JLabel nombreProductoLabel = new JLabel("Nombre del producto:");
        nombreProductoLabel.setBounds(50, 80, 200, 25);
        nombreProductoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreProductoLabel.setForeground(AZUL_MARINO);
        getContentPane().add(nombreProductoLabel);

        nombreProductoTxt = new JTextField();
        inicializarCampoTexto(nombreProductoTxt, 250, 80);

        // Precio del producto
        JLabel precioProductoLabel = new JLabel("Precio:");
        precioProductoLabel.setBounds(50, 130, 200, 25);
        precioProductoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        precioProductoLabel.setForeground(AZUL_MARINO);
        getContentPane().add(precioProductoLabel);

        precioProductoTxt = new JTextField();
        inicializarCampoTexto(precioProductoTxt, 250, 130);

        // Descripción del producto
        JLabel descripcionProductoLabel = new JLabel("Descripción:");
        descripcionProductoLabel.setBounds(50, 180, 200, 25);
        descripcionProductoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descripcionProductoLabel.setForeground(AZUL_MARINO);
        getContentPane().add(descripcionProductoLabel);

        descripcionProductoTxt = new JTextField();
        inicializarCampoTexto(descripcionProductoTxt, 250, 180);

        // Botón cancelar
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(80, 250, 150, 30);
        inicializarBoton(botonCancelar);

        // Botón agregar
        botonAgregar = new JButton("Agregar");
        botonAgregar.setBounds(270, 250, 150, 30);
        inicializarBoton(botonAgregar);

        // Mostrar ventana
        setVisible(true);
    }

    private void inicializarCampoTexto(JTextField campo, int x, int y) {
        campo.setBounds(x, y, 200, 25);
        campo.setBackground(BEIGE);
        campo.setForeground(AZUL_MARINO);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        getContentPane().add(campo);
    }

    private void inicializarBoton(JButton boton) {
        boton.setBorder(null);
        boton.setBackground(CAFE_CLARO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        getContentPane().add(boton);
    }
}