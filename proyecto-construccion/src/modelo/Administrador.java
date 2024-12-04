package modelo;

import java.util.List;
import DAO.ProductoDAO;

public class Administrador extends Usuario {
	
	public Administrador(int id, String correo, String contrasena, String tipo) {
		super(id, correo, contrasena, tipo);
	}

	public List<Retroalimentacion> gestionarRetroalimentacion(){
		return null;
	}
	
	public void gestionarMenu(Producto producto) {
		
	}
	
	public List<Pedido> verHistorialPedidos(){
		return null;
	}
	
	public Reporte verReportes(String periodo) {
		return null;
	}
	
	public void agregarProducto(String nombre, double precio, String descripcion) {
		ProductoDAO.agregarProducto(nombre, precio, descripcion);
	}
	
	public void actualizarProducto(int id, String nombre, double precio, String descripcion)  {
		ProductoDAO.actualizarProducto(id, nombre, precio, descripcion);
	}
	
	public void eliminarProducto(int id) {
		ProductoDAO.eliminarProducto(id);
    }
	
	public List<Producto> mostrarProductos() {
		return ProductoDAO.mostrarProductos();
	}
	
	public Producto seleccionarProducto(int id) {
		return ProductoDAO.seleccionarProducto(id);
	}

}
