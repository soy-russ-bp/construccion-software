/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private String estado;
    private double total;

    public Pedido(int id, Cliente cliente, List<Producto> productos) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.estado = "Pendiente";
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void confirmarPedido() {
        System.out.println("Pedido con ID " + id + " confirmado. Total: $" + total);
    }

}


