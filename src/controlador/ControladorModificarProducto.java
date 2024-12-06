package controlador;

import modelo.Administrador;
import vista.VistaRegistrarProducto;

public class ControladorModificarProducto extends ControladorRegistrarProducto{

	public ControladorModificarProducto(Administrador administrador, VistaRegistrarProducto vista,
			ControladorAdministrador controladorAdministrador) {
		super(administrador, vista, controladorAdministrador);
	}
	
	public void inicializarControlador(Administrador administrador, VistaRegistrarProducto vista,
			ControladorAdministrador controladorAdministrador) {
		setAdministrador(administrador);
		setVista(vista);
		setControladorAdministrador(controladorAdministrador);
		
		getVista().getBotonCancelar().addActionListener(this);
		getVista().getBotonGuardar().addActionListener(this);
		getVista().getNombreProducto().setText(
				String.valueOf(getControladorAdministrador().getProductoSeleccionado().getNombre()));
		getVista().getPrecioProducto().setText(
				String.valueOf(getControladorAdministrador().getProductoSeleccionado().getPrecio()));
		getVista().getDescripcionProducto().setText(
				String.valueOf(getControladorAdministrador().getProductoSeleccionado().getDescripcion()));
	}
	
	public void guardarDatos() {
		int id = getControladorAdministrador().getProductoSeleccionado().getId();
		String nombreProducto = getVista().getNombreProducto().getText();
		double precio = Double.parseDouble(getVista().getPrecioProducto().getText());
		String descripcionProducto = getVista().getDescripcionProducto().getText();
		
    	getAdministrador().actualizarProducto(id, nombreProducto, precio, descripcionProducto);
    	getControladorAdministrador().actualizarTablaProductos();
    	getVista().dispose();
	}

}