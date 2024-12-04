package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VistaConfirmarProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(199, 193, 183);
	private final Color CAFE_CLARO = new Color(199, 154, 108);
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	JButton botonCancelar;
	JButton botonConfirmar;
	JLabel cantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConfirmarProducto frame = new VistaConfirmarProducto();
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
	public VistaConfirmarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 290);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		// Mensaje de confirmación
		JLabel pregunta = new JLabel("<html>¿Desea agregar este<br>producto a su pedido?</html>");
		pregunta.setForeground(AZUL_MARINO);
		pregunta.setHorizontalAlignment(SwingConstants.CENTER);
		pregunta.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
		pregunta.setBounds(20, 30, 450, 72);
		getContentPane().add(pregunta);

		// Etiqueta cantidad
		JLabel cantidadLabel = new JLabel(" Cantidad: ");
		cantidadLabel.setBounds(80, 120, 180, 30);
		cantidadLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		cantidadLabel.setForeground(AZUL_MARINO);
		cantidadLabel.setBorder(null);
		getContentPane().add(cantidadLabel);

		// Cantidad
		cantidad = new JLabel();
		cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad.setHorizontalTextPosition(SwingConstants.LEFT);
		cantidad.setBounds(180, 120, 150, 30);
		cantidad.setBackground(BEIGE);
		cantidad.setForeground(AZUL_MARINO);
		cantidad.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		cantidad.setBorder(null);
		cantidad.setOpaque(true);
		getContentPane().add(cantidad);

		// Botones
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(66, 180, 126, 30);
		inicializarBoton(botonCancelar);

		botonConfirmar = new JButton("Confirmar");
		botonConfirmar.setBounds(291, 180, 126, 30);
		inicializarBoton(botonConfirmar);

		// Mostrar la ventana
		setVisible(true);
	}

	private void inicializarBoton(JButton boton) {
		boton.setBorder(null);
		boton.setBackground(CAFE_CLARO);
		getContentPane().add(boton);
	}
}