package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class VistaDetalles extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	private JLabel nombreProducto;
	private JLabel imagenProducto;
	private JLabel descripcionProducto;
	private JLabel precioProducto;
	private JLabel calificacionPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				VistaDetalles frame = new VistaDetalles();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaDetalles() {
		setTitle("Detalles del producto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 486);
		getContentPane().setBackground(CAFE);
		getContentPane().setLayout(null);

		calificacionPrecio = new JLabel("4.5");
		calificacionPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		calificacionPrecio.setForeground(new Color(46, 65, 83));
		calificacionPrecio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		calificacionPrecio.setBounds(50, 273, 35, 20);
		getContentPane().add(calificacionPrecio);

		// Título del producto
		nombreProducto = new JLabel("Producto");
		nombreProducto.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		nombreProducto.setForeground(AZUL_MARINO);
		nombreProducto.setHorizontalAlignment(SwingConstants.CENTER);
		nombreProducto.setBounds(50, 20, 275, 30);
		getContentPane().add(nombreProducto);

		// Imagen del producto
		imagenProducto = new JLabel("Imagen");
		imagenProducto.setIcon(
				new ImageIcon("C:\\Users\\CASA\\eclipse-workspace\\proyecto_construccion\\src\\vista\\fondo-cafe.jpg"));
		imagenProducto.setHorizontalAlignment(SwingConstants.CENTER);
		imagenProducto.setBounds(50, 60, 275, 189);
		imagenProducto.setBorder(null);
		getContentPane().add(imagenProducto);

		// Calificación
		JLabel lblCalificacion = new JLabel("/ 5 estrellas");
		lblCalificacion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblCalificacion.setForeground(AZUL_MARINO);
		lblCalificacion.setBounds(86, 273, 125, 20);
		getContentPane().add(lblCalificacion);

		// Precio
		JLabel lblPrecio = new JLabel("Precio: $");
		lblPrecio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblPrecio.setForeground(AZUL_MARINO);
		lblPrecio.setBounds(50, 303, 81, 20);
		getContentPane().add(lblPrecio);

		precioProducto = new JLabel("0.00");
		precioProducto.setHorizontalAlignment(SwingConstants.LEFT);
		precioProducto.setForeground(new Color(46, 65, 83));
		precioProducto.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		precioProducto.setBounds(129, 304, 221, 19);
		getContentPane().add(precioProducto);

		// Descripción
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblDescripcion.setForeground(AZUL_MARINO);
		lblDescripcion.setBounds(50, 333, 300, 20);
		getContentPane().add(lblDescripcion);

		// Descripción completa
		descripcionProducto = new JLabel(
				"<html>Este es un producto de alta calidad, perfecto para tus necesidades.</html>");
		descripcionProducto.setVerticalAlignment(SwingConstants.TOP);
		descripcionProducto.setFont(new Font("Arial", Font.BOLD, 12));
		descripcionProducto.setForeground(AZUL_MARINO);
		descripcionProducto.setBounds(50, 358, 275, 62);
		getContentPane().add(descripcionProducto);

		// Mostrar ventana
		setVisible(true);
	}

	public JLabel getNombreProducto() {
		return nombreProducto;
	}

	public JLabel getImagenProducto() {
		return imagenProducto;
	}

	public JLabel getDescripcion() {
		return descripcionProducto;
	}

	public JLabel getPrecio() {
		return precioProducto;
	}

	public JLabel getCalificacion() {
		return calificacionPrecio;
	}

}