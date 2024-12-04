/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Empleado;
import modelo.Pedido;

import java.util.List;

public class ControladorEmpleado {
    public List<Pedido> obtenerPedidosPendientes(List<Pedido> pedidos) {
        return pedidos.stream().filter(p -> p.getEstado().equals("Pendiente")).toList();
    }

    public void registrarCobro(Pedido pedido) {
        pedido.setEstado("Pagado");
        System.out.println("Pedido con ID " + pedido.getId() + " marcado como pagado.");
    }

    public void actualizarEstadoPedido(Pedido pedido, String nuevoEstado) {
        pedido.setEstado(nuevoEstado);
        System.out.println("Estado del pedido con ID " + pedido.getId() + " actualizado a: " + nuevoEstado);
    }
}

