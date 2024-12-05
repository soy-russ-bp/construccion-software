package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.DetallePedido;
import modelo.Pedido;
import modelo.Producto;

public class PedidoDAO {
	private static List<Pedido> pedidos = new ArrayList<>();
	
	public static int hacerPedido(Pedido pedido) throws SQLException {
		String sql = "INSERT INTO pedidos (id_cliente, total) VALUES (?, ?)";
		int idPedidoGenerado = -1;

		try (Connection conn = ConexionBaseDatos.obtenerConexion();
	    	PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			
	        pstmt.setInt(1, pedido.getCliente().getId());
	        pstmt.setDouble(2, pedido.getTotal());

	        int filasAfectadas = pstmt.executeUpdate();

	        if (filasAfectadas > 0) {
	        	try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	        		if (generatedKeys.next()) {
	        			idPedidoGenerado = generatedKeys.getInt(1);
	        		}
	        	}
	        }
	        
	        pedido.setId(idPedidoGenerado);
	        
	        for(DetallePedido producto : pedido.getDetallePedido()) {
	    		agregarProductoAlPedido(pedido.getId(), producto);
	    	}
	        
		} catch (SQLException e) {
	    	throw e;
		}

		return idPedidoGenerado;
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
    
    public static List<Pedido> seleccionarPedidosPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE fecha_pedido >= ? AND fecha_pedido <= ?";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
            pstmt.setDate(2, java.sql.Date.valueOf(fechaFin));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idPedido = rs.getInt("id_pedido");
                    LocalDate fechaPedido = rs.getDate("fecha_pedido").toLocalDate();
                    double total = rs.getDouble("total");

                    pedidos.add(new Pedido(idPedido, fechaPedido, total));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
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
            stmt.setInt(1, pedido.getId());
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
    
 // Agregar un pedido, cuando se reaaliza un pedido en la vista cliente, se agrega a la lista de pedidos
    public static void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}
