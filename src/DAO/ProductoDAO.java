package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class ProductoDAO {
    public static void agregarProducto(String nombre, double precio, String descripcion) {
        String sql = "INSERT INTO productos (nombre, precio, descripcion) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setString(3, descripcion);
            stmt.executeUpdate();
            System.out.println("Producto agregado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Producto> mostrarProductos() {
    	List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                double calificacion = rs.getDouble("calificacion");
                
                productos.add(new Producto(id, nombre, precio, descripcion, calificacion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    public static Producto seleccionarProducto(int idProductoSeleccionado) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProductoSeleccionado);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_producto");
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble("precio");
                    String descripcion = rs.getString("descripcion");
                    double calificacion = rs.getDouble("calificacion");
                    
                    producto = new Producto(id, nombre, precio, descripcion, calificacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
    
    public static void actualizarProducto(int id, String nombre, double precio, String descripcion) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, descripcion = ? WHERE id_producto = ?";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setString(3, descripcion);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Producto actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void actualizarCalificacion(int id, double calificacion) {
    	String sql = "UPDATE productos SET calificacion = ? WHERE id_producto = ?";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, calificacion);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Calificación actualizada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Producto eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*public static double obtenerPrecioProducto(int idProductoSeleccionado) {
    	String sql = "SELECT precio FROM productos WHERE id_producto = ?";
    	double precio = -1;

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProductoSeleccionado);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    precio = rs.getDouble("precio");
                } else {
                    System.out.println("Producto con ID " + idProductoSeleccionado + " no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return precio;
    }*/
}
