package DAO;

import modelo.DetallePedido;
import modelo.Pedido;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private static List<Pedido> pedidos = new ArrayList<>();

    // Agregar un pedido, cuando se reaaliza un pedido en la vista cliente, se agrega a la lista de pedidos
    public static void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerPedidos() {
        String sql = "SELECT * FROM pedidos, usuarios WHERE pedidos.id_cliente = usuarios.id_usuario";
        pedidos.clear(); // Evitar duplicados
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("usuarios.nombre");
                int id = rs.getInt("pedidos.id_pedido");
                String totalHecho = Integer.toString(this.obtenerCantidadProductosHecho(id)) +"/"+Integer.toString(this.obtenerCantidadProductosPorPedido(id)); 
                float pagoTotal = rs.getFloat("pedidos.total");
                String estado = rs.getString("pedidos.estado");
    
                pedidos.add(new Pedido(id, nombre, totalHecho, pagoTotal, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    // Marcar un pedido como Completado
    public void marcarPedidoCompletado(int idPedido) {
        String sql = "UPDATE pedidos SET estado = 'Completado' WHERE id_pedido = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Marcar un pedido como Entregado
    public void marcarPedidoEntregado(Pedido pedido) {
        String sql = "UPDATE pedidos SET estado = 'Entregado' WHERE id_pedido = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdPedido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener la cantidad de productos pedidos por id_pedido
    public int obtenerCantidadProductosPorPedido(int idPedido) {
        String sql = "SELECT cantidad FROM detalle_pedidos WHERE id_pedido = ?";
        int total = 0;
        // hay una columna llamada cantidad en la tabla detalle_pedido, debo sumarla, 
        //hasta que el id_pedido sea diferente al id_pedido que se pasa como parametro
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                total += rs.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    //obtener la cantidad de productos hechos
    public int obtenerCantidadProductosHecho(int id){
        String sql = "SELECT cantidad_hechos FROM detalle_pedidos WHERE id_pedido = ?";
        int total = 0;
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                total += rs.getInt("cantidad_hechos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Obtener los productos de un pedido por id_pedido
    public List<DetallePedido> obtenerProductosPorPedido(int idPedido) {
        String sql = "SELECT * FROM productos, detalle_pedidos WHERE productos.id_producto = detalle_pedidos.id_producto AND detalle_pedidos.id_pedido = ?";
        List<DetallePedido> detallesPedido = new ArrayList<>();
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("productos.nombre");
                int cantidad = rs.getInt("detalle_pedidos.cantidad");
                detallesPedido.add(new DetallePedido( nombre, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesPedido;
    }

    // sumar la cantidad de productos hechos
    public void sumarCantidadProductosHechos(int idPedido, int cantidad) {
        String sql = "UPDATE detalle_pedidos,  SET cantidad_hechos = cantidad_hechos + ? WHERE id_pedido = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    




    
}