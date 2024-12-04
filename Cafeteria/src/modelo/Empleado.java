/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

public class Empleado extends Usuario {
    public Empleado(int id, String nombre) {
        super(id, nombre, "Empleado");
    }

    public List<Pedido> verPedidosPendientes() {
        
        return null;
    }

    public void registrarCobro(Pedido pedido) {
        pedido.setEstado("Pagado");
        System.out.println("Pedido con ID: " + pedido.getId() + " marcado como pagado.");
    }

    public void marcarPedidoEntregado(Pedido pedido) {
        pedido.setEstado("Entregado");
        System.out.println("Pedido con ID: " + pedido.getId() + " marcado como entregado.");
    }

    @Override
    public boolean autenticar(String contrasena) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


