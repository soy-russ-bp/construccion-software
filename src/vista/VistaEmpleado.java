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

public class VistaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(199, 193, 183);
	private final Color CAFE_CLARO = new Color(199, 154, 108);
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	JButton botonVerDetalles;
	JTable tablaProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEmpleado frame = new VistaEmpleado();
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
	public VistaEmpleado() {
		setTitle("Pedidos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(50, 50, 610, 500);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		// Etiqueta "Pedidos"
		JLabel tituloProductos = new JLabel("Pedidos");
		tituloProductos.setForeground(AZUL_MARINO);
		tituloProductos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloProductos.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		tituloProductos.setBounds(20, 10, 550, 30);
		getContentPane().add(tituloProductos);

		// Tabla de productos
		String[] columnasTablaProductos = { "Cliente", "Folio Pedido", "Total Hechos", "Total Compra", "Estado" };
		tablaProductos = new JTable(new DefaultTableModel(null, columnasTablaProductos));
		inicializarTabla(tablaProductos);

		JScrollPane scrollProductos = new JScrollPane(tablaProductos);
		scrollProductos.setBounds(20, 51, 550, 320);
		inicializarScroll(scrollProductos);

		JTableHeader header = tablaProductos.getTableHeader();
		inicializarEncabezadoTabla(header);

		// Boton para ver detalles de un pedido
		botonVerDetalles = new JButton("Ver detalles");
		botonVerDetalles.setBounds(245, 400, 120, 30);
		inicializarBoton(botonVerDetalles);

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
		tabla.setIntercellSpacing(new Dimension(1, 1));
	}

	private void inicializarScroll(JScrollPane scroll) {
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		getContentPane().add(scroll);
	}

	private void inicializarEncabezadoTabla(JTableHeader encabezado) {
		encabezado.setFont(new Font("Arial", Font.BOLD, 14));
		encabezado.setBackground(BEIGE);
		encabezado.setForeground(AZUL_MARINO);
		encabezado.setOpaque(true);
	}

	public JButton getBotonVerDetalles() {
		return botonVerDetalles;
	}
}
