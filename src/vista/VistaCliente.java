package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VistaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(199, 193, 183);
	private final Color CAFE_CLARO = new Color(199, 154, 108);
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	JButton botonEnviarPedido;
	JButton botonVerDetalles;
	JButton botonAgregarProducto;
	JLabel idPedido;
	JLabel totalPedido;
	JTable tablaProductos;
	JTable tablaHistorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCliente frame = new VistaCliente();
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
	public VistaCliente() {
		setTitle("Pedido");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 407);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		/**
		 * Sección para administrar productos
		 */

		// Total del pedido
		totalPedido = new JLabel("0.00");
		totalPedido.setBounds(604, 269, 126, 30);
		totalPedido.setBackground(BEIGE);
		totalPedido.setForeground(AZUL_MARINO);
		totalPedido.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		totalPedido.setBorder(null);
		totalPedido.setOpaque(true);
		getContentPane().add(totalPedido);

		// Etiqueta "Productos"
		JLabel tituloProductos = new JLabel("Productos");
		tituloProductos.setForeground(AZUL_MARINO);
		tituloProductos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloProductos.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		tituloProductos.setBounds(20, 10, 450, 30);
		getContentPane().add(tituloProductos);

		// Tabla de productos
		String[] columnasTablaProductos = { "Nombre", "Precio", "Calificación" };
		tablaProductos = new JTable(new DefaultTableModel(null, columnasTablaProductos));
		inicializarTabla(tablaProductos);

		JScrollPane scrollProductos = new JScrollPane(tablaProductos);
		scrollProductos.setBounds(20, 51, 450, 236);
		inicializarScroll(scrollProductos);

		JTableHeader header = tablaProductos.getTableHeader();
		inicializarEncabezadoTabla(header);

		// Botones debajo de la tabla de productos
		botonVerDetalles = new JButton("Ver detalles");
		botonVerDetalles.setBounds(66, 309, 126, 30);
		inicializarBoton(botonVerDetalles);

		botonAgregarProducto = new JButton("Agregar al pedido");
		botonAgregarProducto.setBounds(291, 309, 126, 30);
		inicializarBoton(botonAgregarProducto);

		/**
		 * Sección para consultar el historial y el reporte
		 */

		// Etiqueta ID
		JLabel IdLabel = new JLabel("ID: ");
		IdLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		IdLabel.setForeground(AZUL_MARINO);
		IdLabel.setBounds(540, 36, 35, 20);
		getContentPane().add(IdLabel);

		// ID del pedido
		idPedido = new JLabel("--");
		idPedido.setHorizontalAlignment(SwingConstants.LEFT);
		idPedido.setForeground(new Color(46, 65, 83));
		idPedido.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		idPedido.setBounds(572, 36, 158, 20);
		getContentPane().add(idPedido);

		// Tabla del pedido
		String[] columnasTablaPedido = { "Producto", "Precio" };
		tablaHistorial = new JTable(new DefaultTableModel(null, columnasTablaPedido));
		inicializarTabla(tablaHistorial);

		JScrollPane scrollPedido = new JScrollPane(tablaHistorial);
		scrollPedido.setBounds(540, 65, 190, 190);
		inicializarScroll(scrollPedido);

		JTableHeader encabezadoTablaHistorial = tablaHistorial.getTableHeader();
		inicializarEncabezadoTabla(encabezadoTablaHistorial);

		// Etiqueta total: $
		JLabel totalLabel = new JLabel(" Total:  $");
		totalLabel.setBounds(540, 269, 180, 30);
		totalLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		totalLabel.setForeground(AZUL_MARINO);
		totalLabel.setBackground(BEIGE);
		totalLabel.setBorder(null);
		totalLabel.setOpaque(true);
		getContentPane().add(totalLabel);

		// Botón para generar reporte
		botonEnviarPedido = new JButton("Enviar pedido");
		botonEnviarPedido.setBounds(540, 309, 120, 30);
		inicializarBoton(botonEnviarPedido);

		// Mostrar la ventana
		setVisible(true);
	}

	private void inicializarBoton(JButton boton) {
		boton.setBorder(null);
		boton.setBackground(CAFE_CLARO);
		getContentPane().add(boton);
	}

	private void inicializarTabla(JTable tabla) {
		tabla.setShowHorizontalLines(true);
		tabla.setShowVerticalLines(true);
		tabla.setGridColor(Color.WHITE);
		tabla.setBackground(BEIGE);
		tabla.setIntercellSpacing(new Dimension(1, 1));
	}

	private void inicializarScroll(JScrollPane scroll) {
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		scroll.setBackground(BEIGE);
		getContentPane().add(scroll);
	}

	private void inicializarEncabezadoTabla(JTableHeader encabezado) {
		encabezado.setFont(new Font("Arial", Font.BOLD, 14));
		encabezado.setBackground(BEIGE);
		encabezado.setForeground(AZUL_MARINO);
		encabezado.setOpaque(true);
	}
}
