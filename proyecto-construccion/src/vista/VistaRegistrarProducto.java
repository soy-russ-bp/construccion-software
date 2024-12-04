package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VistaRegistrarProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(199, 193, 183);
	private final Color CAFE_CLARO = new Color(199, 154, 108);
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	private JLabel titulo;
	private JTextField textoNombreProducto;
	private JTextField textoPrecioProducto;
	private JTextArea textoDescripcionProducto;
	private JButton botonGuardar;
	private JButton botonCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistrarProducto frame = new VistaRegistrarProducto();
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
	public VistaRegistrarProducto() {
		setTitle("Agregar nuevo producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 500, 380);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		// Título
		titulo = new JLabel("Agregar nuevo producto");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		titulo.setForeground(AZUL_MARINO);
		titulo.setBounds(42, 23, 400, 40);
		getContentPane().add(titulo);

		// Nombre del producto
		JLabel nombreProductoLabel = new JLabel("Nombre del producto:");
		inicializarEtiqueta(nombreProductoLabel, 45, 80);

		textoNombreProducto = new JTextField();
		inicializarCampoTexto(textoNombreProducto, 230, 80);

		// Precio del producto
		JLabel precioProductoLabel = new JLabel("Precio:");
		inicializarEtiqueta(precioProductoLabel, 45, 130);

		textoPrecioProducto = new JTextField();
		inicializarCampoTexto(textoPrecioProducto, 230, 130);

		// Descripción del producto
		JLabel descripcionProductoLabel = new JLabel("Descripción:");
		inicializarEtiqueta(descripcionProductoLabel, 45, 180);

		textoDescripcionProducto = new JTextArea();
		textoDescripcionProducto.setLineWrap(true);
		textoDescripcionProducto.setWrapStyleWord(true);
		textoDescripcionProducto.setEditable(true);
		inicializarAreaTexto(textoDescripcionProducto);

		JScrollPane scrollDescripcion = new JScrollPane(textoDescripcionProducto);
		scrollDescripcion.setVerticalScrollBarPolicy(scrollDescripcion.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDescripcion.setHorizontalScrollBarPolicy(scrollDescripcion.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescripcion.setBounds(230, 180, 190, 80);
		scrollDescripcion.setBorder(null);
		getContentPane().add(scrollDescripcion);

		// Botón cancelar
		botonCancelar = new JButton("Cancelar");
		inicializarBoton(botonCancelar, 80, 280);

		// Botón agregar
		botonGuardar = new JButton("Guardar");
		inicializarBoton(botonGuardar, 270, 280);

		// Mostrar ventana
		setVisible(true);
	}

	private void inicializarCampoTexto(JComponent campo, int coordenadaX, int coordenadaY) {
		campo.setBounds(coordenadaX, coordenadaY, 190, 30);
		inicializarAreaTexto(campo);
		getContentPane().add(campo);
	}

	private void inicializarAreaTexto(JComponent campo) {
		campo.setBackground(BEIGE);
		campo.setForeground(AZUL_MARINO);
		campo.setBorder(null);
		campo.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	private void inicializarBoton(JButton boton, int coordenadaX, int coordenadaY) {
		boton.setBorder(null);
		boton.setBackground(CAFE_CLARO);
		boton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		boton.setBounds(coordenadaX, coordenadaY, 150, 30);
		getContentPane().add(boton);
	}

	private void inicializarEtiqueta(JLabel etiqueta, int coordenadaX, int coordenadaY) {
		etiqueta.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		etiqueta.setForeground(AZUL_MARINO);
		etiqueta.setBounds(coordenadaX, coordenadaY, 200, 25);
		getContentPane().add(etiqueta);
	}

	public void setTitulo(String titulo) {
		this.titulo.setText(titulo);
	}

	public JTextField getNombreProducto() {
		return textoNombreProducto;
	}

	public JTextField getPrecioProducto() {
		return this.textoPrecioProducto;
	}

	public JTextArea getDescripcionProducto() {
		return this.textoDescripcionProducto;
	}

	public JButton getBotonGuardar() {
		return this.botonGuardar;
	}

	public JButton getBotonCancelar() {
		return this.botonCancelar;
	}

}