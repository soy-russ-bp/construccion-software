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
	JTable tablaHistorial;
	DefaultTableModel modeloTablaProductos;
	JComboBox<String> selectorMes;
	

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
		setBounds(100, 100, 778, 407);
		getContentPane().setLayout(null);
		getContentPane().setBackground(CAFE);

		/**
		 * Sección para administrar productos
		 */

		// Total de ventas
		totalVentas = new JLabel("0.00");
		totalVentas.setBounds(604, 269, 126, 30);
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
		scrollProductos.setBounds(20, 51, 450, 236);
		inicializarScroll(scrollProductos);

		JTableHeader header = tablaProductos.getTableHeader();
		inicializarEncabezadoTabla(header);

		// Botones debajo de la tabla de productos
		botonCrear = new JButton("Crear");
		inicializarBoton(botonCrear, 20, 309);

		botonModificar = new JButton("Modificar");
		inicializarBoton(botonModificar, 130, 309);
		botonModificar.setEnabled(false);

		botonEliminar = new JButton("Eliminar");
		inicializarBoton(botonEliminar, 240, 309);
		botonEliminar.setEnabled(false);

		botonVerDetalles = new JButton("Ver detalles");
		inicializarBoton(botonVerDetalles, 350, 309);
		botonVerDetalles.setEnabled(false);

		/**
		 * Sección para consultar el historial y el reporte
		 */

		// Etiqueta historial
		JLabel tituloHistorial = new JLabel("Historial");
		tituloHistorial.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		tituloHistorial.setForeground(AZUL_MARINO);
		tituloHistorial.setBounds(540, 19, 100, 20);
		getContentPane().add(tituloHistorial);

		// Lista desplegable para seleccionar mes
		selectorMes = new JComboBox<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" });
		selectorMes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		selectorMes.setBounds(540, 51, 190, 25);
		selectorMes.setForeground(AZUL_MARINO);
		selectorMes.setBackground(BEIGE);
		getContentPane().add(selectorMes);

		// Tabla del historial
		String[] columnasTablaHistorial = { "Mes", "Ganancias" };
		tablaHistorial = new JTable(new DefaultTableModel(null, columnasTablaHistorial));
		inicializarTabla(tablaHistorial);

		JScrollPane scrollHistorial = new JScrollPane(tablaHistorial);
		scrollHistorial.setBounds(540, 85, 190, 170);
		inicializarScroll(scrollHistorial);

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
		botonGenerarReporte = new JButton("Generar reporte");
		inicializarBoton(botonGenerarReporte, 540, 309);

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
	
	public void mostrarMensaje(String mensaje) {
	    JOptionPane.showMessageDialog(this, mensaje);
	}

	public void actualizarHistorial(Object[][] datos) {
		   String[] columnas = { "ID", "Nombre", "Cantidad", "Total" };
		    DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
		    tablaHistorial.setModel(modelo);
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

	public JTable getTablaHistorial() {
		return tablaHistorial;
	}

	public JComboBox<String> getSelectorMes() {
		return selectorMes;
	}

}
