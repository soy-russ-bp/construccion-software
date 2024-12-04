package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;

public class VistaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(199, 193, 183);
	private final Color CAFE_CLARO = new Color(199, 154, 108);
	private final Color CAFE = new Color(144, 120, 91);
	private final Color AZUL_MARINO = new Color(46, 65, 83);

	JButton botonCrear;
	JButton botonModificar;
	JButton botonEliminar;
	JButton botonVerDetalles;
	JButton botonGenerarReporte;
	JLabel totalVentas;
	JTable tablaProductos;
	JTable tablaReporte;
	DefaultTableModel modeloTablaProductos;
	DefaultTableModel modeloTablaReporte;
	JTextField fechaInicio;
	JTextField fechaFin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdministrador frame = new VistaAdministrador();
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
	public VistaAdministrador() {
		setTitle("Administración de cafetería");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 875, 443);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		/**
		 * Sección para administrar productos
		 */

		// Total de ventas
		totalVentas = new JLabel("0.00");
		totalVentas.setBounds(602, 307, 126, 30);
		totalVentas.setBackground(BEIGE);
		totalVentas.setBorder(null);
		totalVentas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		totalVentas.setForeground(AZUL_MARINO);
		totalVentas.setOpaque(true);
		getContentPane().add(totalVentas);

		// Etiqueta "Productos"
		JLabel tituloProductos = new JLabel("Productos");
		tituloProductos.setForeground(AZUL_MARINO);
		tituloProductos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloProductos.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		tituloProductos.setBounds(20, 10, 450, 30);
		getContentPane().add(tituloProductos);

		// Tabla de productos
		String[] columnasTablaProductos = { "id", "Nombre", "Precio", "Calificación" };
		modeloTablaProductos = new DefaultTableModel(columnasTablaProductos, 0);
		tablaProductos = new JTable(modeloTablaProductos);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inicializarTabla(tablaProductos);

		JScrollPane scrollProductos = new JScrollPane(tablaProductos);
		scrollProductos.setBounds(20, 51, 450, 256);
		inicializarScroll(scrollProductos);

		JTableHeader header = tablaProductos.getTableHeader();
		inicializarEncabezadoTabla(header);

		// Botones debajo de la tabla de productos
		botonCrear = new JButton("Crear");
		inicializarBoton(botonCrear, 20, 349);

		botonModificar = new JButton("Modificar");
		inicializarBoton(botonModificar, 130, 349);
		botonModificar.setEnabled(false);

		botonEliminar = new JButton("Eliminar");
		inicializarBoton(botonEliminar, 240, 349);
		botonEliminar.setEnabled(false);

		botonVerDetalles = new JButton("Ver detalles");
		inicializarBoton(botonVerDetalles, 350, 349);
		botonVerDetalles.setEnabled(false);

		/**
		 * Sección para consultar el historial y el reporte
		 */

		// Etiqueta reporte
		JLabel tituloReporte = new JLabel("Reporte");
		tituloReporte.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		tituloReporte.setForeground(AZUL_MARINO);
		tituloReporte.setBounds(540, 19, 100, 20);
		getContentPane().add(tituloReporte);

		// Campo para fecha de inicio del periodo del reporte
		fechaInicio = new JTextField();
		inicializarCampoTexto(fechaInicio, 540, 79);
		getContentPane().add(fechaInicio);

		// Campo para fecha de fin del periodo del reporte
		fechaFin = new JTextField();
		inicializarCampoTexto(fechaFin, 672, 79);
		getContentPane().add(fechaFin);

		// Tabla del reporte
		String[] columnasTablaReporte = { "ID Pedido", "Fecha", "Total" };
		modeloTablaReporte = new DefaultTableModel(columnasTablaReporte, 0);
		tablaReporte = new JTable(modeloTablaReporte);
		tablaReporte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inicializarTabla(tablaReporte);

		JScrollPane scrollReporte = new JScrollPane(tablaReporte);
		scrollReporte.setBounds(540, 110, 300, 185);
		inicializarScroll(scrollReporte);

		JTableHeader encabezadoTablaReporte = tablaReporte.getTableHeader();
		inicializarEncabezadoTabla(encabezadoTablaReporte);

		// Etiqueta total: $
		JLabel totalLabel = new JLabel(" Total:  $");
		totalLabel.setBounds(540, 307, 180, 30);
		totalLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		totalLabel.setForeground(AZUL_MARINO);
		totalLabel.setBackground(BEIGE);
		totalLabel.setBorder(null);
		totalLabel.setOpaque(true);
		getContentPane().add(totalLabel);

		JLabel labelFechaInicio = new JLabel("Fecha de inicio");
		labelFechaInicio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		labelFechaInicio.setForeground(AZUL_MARINO);
		labelFechaInicio.setBounds(540, 57, 110, 14);
		getContentPane().add(labelFechaInicio);

		JLabel labelFechaFin = new JLabel("Fecha de fin");
		labelFechaFin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		labelFechaFin.setForeground(AZUL_MARINO);
		labelFechaFin.setBounds(672, 55, 110, 14);
		getContentPane().add(labelFechaFin);

		// Botón para generar reporte
		botonGenerarReporte = new JButton("Generar reporte");
		inicializarBoton(botonGenerarReporte, 540, 349);

		// Mostrar la ventana
		setVisible(true);
	}

	private void inicializarBoton(JButton boton, int coordenadaX, int coordenadaY) {
		boton.setBorder(null);
		boton.setBackground(CAFE_CLARO);
		boton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		boton.setBounds(coordenadaX, coordenadaY, 100, 30);
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
		scroll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		getContentPane().add(scroll);
	}

	private void inicializarEncabezadoTabla(JTableHeader encabezado) {
		encabezado.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		encabezado.setBackground(BEIGE);
		encabezado.setForeground(AZUL_MARINO);
		encabezado.setOpaque(true);
	}

	private void inicializarCampoTexto(JTextField campo, int coordenadaX, int coordenadaY) {
		campo.setFont(new Font("Arial", Font.PLAIN, 14));
		campo.setForeground(AZUL_MARINO);
		campo.setBackground(BEIGE);
		campo.setBorder(null);
		campo.setBounds(coordenadaX, coordenadaY, 100, 20);
		campo.setColumns(10);
		campo.setText("DD-MM-YYYY");
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public JButton getBotonCrear() {
		return botonCrear;
	}

	public JButton getBotonModificar() {
		return botonModificar;
	}

	public JButton getBotonEliminar() {
		return botonEliminar;
	}

	public JButton getBotonVerDetalles() {
		return botonVerDetalles;
	}

	public JButton getBotonGenerarReporte() {
		return botonGenerarReporte;
	}

	public JLabel getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(JLabel totalVentas) {
		this.totalVentas = totalVentas;
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public DefaultTableModel getModeloTablaProductos() {
		return modeloTablaProductos;
	}

	public JTable getTablaReporte() {
		return tablaReporte;
	}

	public DefaultTableModel getModeloTablaReporte() {
		return modeloTablaReporte;
	}

	public JTextField getFechaInicio() {
		return fechaInicio;
	}

	public JTextField getFechaFin() {
		return fechaFin;
	}
}
