/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;
import modelo.Retroalimentacion;

import java.util.List;

public class ControladorCliente {
    public List<Producto> verMenu(List<Producto> productosDisponibles) {
        return productosDisponibles;
    }

    public Pedido realizarPedido(Cliente cliente, List<Producto> productos) {
        Pedido pedido = new Pedido(1, cliente, productos);
        pedido.confirmarPedido();
        return pedido;
    }

    public void enviarRetroalimentacion(Cliente cliente, Producto producto, String comentario, int calificacion) {
        Retroalimentacion retroalimentacion = new Retroalimentacion(1, cliente, producto, comentario, calificacion);
        System.out.println(retroalimentacion);
    }
}

