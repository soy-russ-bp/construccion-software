package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DAO.ConexionBaseDatos;
import DAO.ProductoDAO;

public class Administrador extends Usuario {
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
	
	

}
