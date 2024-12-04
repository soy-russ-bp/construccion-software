/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

public class Reporte {
    private String periodo;
    private List<Pedido> pedidos;
    private double totalVentas;

    public Reporte(String periodo, List<Pedido> pedidos) {
        this.periodo = periodo;
        this.pedidos = pedidos;
        this.totalVentas = calcularTotalVentas();
    }

    private double calcularTotalVentas() {
        return pedidos.stream().mapToDouble(Pedido::getTotal).sum();
    }

    public String getPeriodo() {
        return periodo;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void generarReporte() {
        System.out.println("Reporte del periodo: " + periodo);
        System.out.println("Total de ventas: $" + totalVentas);
        System.out.println("Pedidos registrados:");
        pedidos.forEach(p -> System.out.println(" - Pedido ID: " + p.getId() + ", Total: $" + p.getTotal()));
    }
}


