package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.ProductoDAO;
import modelo.Administrador;
import vista.VistaAdministrador;
import vista.VistaAgregarProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DAO.ProductoDAO;
import modelo.Administrador;
import modelo.Producto;
import modelo.Reporte;


public class AdministradorControlador implements ActionListener{
	private Administrador administrador;
	private VistaAdministrador vista;
	private VistaAgregarProducto vistaAgregarProducto;
	private ControladorAgregarProducto controladorAgregarProducto;
	
	public AdministradorControlador(Administrador administrador, VistaAdministrador vista) {
		this.vista = vista;
		this.administrador = administrador;
		
		this.vista.getBotonCrear().addActionListener(this);
		this.vista.getBotonModificar().addActionListener(this);
		this.vista.getBotonVerDetalles().addActionListener(this);
		this.vista.getBotonEliminar().addActionListener(this);
		this.vista.getBotonGenerarReporte().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBotonCrear()) {
			if(this.vistaAgregarProducto == null) {
				this.vistaAgregarProducto = new VistaAgregarProducto();
			} 
			
			if(this.controladorAgregarProducto == null) {
				this.controladorAgregarProducto = new ControladorAgregarProducto(administrador, this.vistaAgregarProducto );
			}
			
        }
		
		if (e.getSource() == this.vista.getBotonModificar()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonVerDetalles()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonEliminar()) {
        	
        }
		
		if (e.getSource() == this.vista.getBotonGenerarReporte()) {
        	generarReporte();
        }
	}

    private void generarReporte() {
        String periodo = (String) vista.getSelectorMes().getSelectedItem();
        if ("Mes".equals(periodo)) {
            vista.mostrarMensaje("Selecciona un periodo válido para el reporte.");
            return;
        }

        // Obtener productos vendidos desde el modelo
        Reporte reporte = administrador.verReportes(periodo);

        // Generar y guardar el reporte
        String filePath = "reporte_" + periodo + ".txt";
        reporte.guardarComoTxt(filePath);
        vista.mostrarMensaje("Reporte generado correctamente: " + filePath);

        // Actualizar la tabla de historial en la vista
        List<Producto> productosVendidos = ProductoDAO.obtenerProductosVendidos();
        Object[][] datosTabla = productosVendidos.stream()
                .map(p -> new Object[]{p.getId(), p.getNombre(), p.getCantidadVendida(), p.getTotalVendido()})
                .toArray(Object[][]::new);
        vista.actualizarHistorial(datosTabla);
    }

}