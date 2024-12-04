package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaDetallesHecho extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color BEIGE = new Color(253, 234, 216);
	private final Color NARANJA = new Color(255, 153, 102);
	private final Color AZUL_OSCURO = new Color(0, 51, 102);

	private JLabel lblIdLabel;
	private JLabel lblIdProducto;
	private JTable tablaProductos;
	private JButton btnVolver;
	private JButton btnMarcarHecho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				VistaDetallesHecho frame = new VistaDetallesHecho();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaDetallesHecho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		getContentPane().setLayout(null);

		// ID Label
		lblIdLabel = new JLabel("Id:");
		lblIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblIdLabel.setForeground(AZUL_OSCURO);
		lblIdLabel.setBounds(30, 20, 30, 25);
		getContentPane().add(lblIdLabel);

		// ID Producto
		lblIdProducto = new JLabel("----");
		lblIdProducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblIdProducto.setForeground(AZUL_OSCURO);
		lblIdProducto.setBounds(70, 20, 100, 25);
		getContentPane().add(lblIdProducto);

		// Tabla Productos
		tablaProductos = new JTable();
		tablaProductos
				.setModel(
						new DefaultTableModel(
								new Object[][] { { "Producto 1", false }, { "Producto 2", true },
										{ "Producto 3", false }, { "Producto 4", true } },
								new String[] { "Producto", "Hecho" }) {
							Class[] columnTypes = new Class[] { String.class, Boolean.class };

							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
		tablaProductos.setBackground(BEIGE);
		tablaProductos.setSelectionBackground(BEIGE);
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBounds(30, 60, 320, 200);
		getContentPane().add(scrollPane);

		// Botón Volver
		btnVolver = new JButton("Volver");
		inicializarBoton(btnVolver, 30, 280, NARANJA);

		// Botón Marcar Hecho
		btnMarcarHecho = new JButton("Marcar como hecho");
		inicializarBoton(btnMarcarHecho, 200, 280, NARANJA);

		// Mostrar ventana
		setVisible(true);
	}

	private void inicializarBoton(JButton boton, int x, int y, Color colorFondo) {
		boton.setBounds(x, y, 150, 30);
		boton.setBackground(colorFondo);
		boton.setForeground(AZUL_OSCURO);
		boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		boton.setBorder(null);
		getContentPane().add(boton);
	}

	// Métodos públicos para acceder a componentes
	public String getIdProducto() {
		return lblIdProducto.getText();
	}

	public void setIdProducto(String id) {
		lblIdProducto.setText(id);
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public void addVolverListener(java.awt.event.ActionListener actionListener) {
		btnVolver.addActionListener(actionListener);
	}

	public void addMarcarHechoListener(java.awt.event.ActionListener actionListener) {
		btnMarcarHecho.addActionListener(actionListener);
	}
}