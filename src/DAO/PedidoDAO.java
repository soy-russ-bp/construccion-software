package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.DetallePedido;
import modelo.Pedido;
import modelo.Producto;

public class PedidoDAO {
    public static void hacerPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_cliente, total) VALUES (?, ?)";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getCliente().getId());
            stmt.setDouble(2, pedido.getTotal());
            stmt.executeUpdate();
            System.out.println("Pedido hecho exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void agregarProductoAlPedido(int idPedido, DetallePedido producto) {
    	String sql = "INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, producto.getProducto().getId());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getSubtotal());
            stmt.executeUpdate();
            System.out.println("Producto agregado al pedido exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Producto> mostrarPedidos() {
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
    
    public static Producto seleccionarPedido(int idProductoSeleccionado) {
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
    
    public static void actualizarEstadoPedido(int id, double calificacion) {
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
}
