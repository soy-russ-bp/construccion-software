package DAO;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private static List<Pedido> pedidos = new ArrayList<>();

    // Agregar un pedido
    public static void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Obtener todos los pedidos
    public static List<Pedido> obtenerPedidos() {
        return new ArrayList<>(pedidos); // Retorna una copia de la lista
    }
}