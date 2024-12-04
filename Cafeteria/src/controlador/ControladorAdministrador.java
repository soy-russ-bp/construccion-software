/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Administrador;
import modelo.Pedido;
import modelo.Producto;
import modelo.Reporte;
import modelo.Retroalimentacion;

import java.util.List;

public class ControladorAdministrador {
    public List<Pedido> consultarHistorialPedidos(List<Pedido> pedidos) {
        return pedidos;
    }

    public void gestionarProducto(Producto producto, String accion) {
        switch (accion.toLowerCase()) {
            case "agregar":
                System.out.println("Producto agregado: " + producto.getNombre());
                break;
            case "eliminar":
                System.out.println("Producto eliminado: " + producto.getNombre());
                break;
            case "modificar":
                System.out.println("Producto modificado: " + producto.getNombre());
                break;
            default:
                System.out.println("Acción no reconocida.");
        }
    }

    public Reporte consultarReportes(String periodo, List<Pedido> pedidos) {
        Reporte reporte = new Reporte(periodo, pedidos);
        reporte.generarReporte();
        return reporte;
    }

    public List<Retroalimentacion> gestionarRetroalimentaciones(List<Retroalimentacion> retroalimentaciones) {
        retroalimentaciones.forEach(System.out::println);
        return retroalimentaciones;
    }
}

