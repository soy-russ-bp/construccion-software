/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

public class Administrador extends Usuario {
    public Administrador(int id, String nombre) {
        super(id, nombre, "Administrador");
    }

    public List<Pedido> verHistorialPedidos() {
        // Logica para historial
        return null;
    }

    public void gestionarMenu(Producto producto) {
        System.out.println("Producto gestionado: " + producto.getNombre());
    }

    public Reporte verReportes(String periodo) {
        // Logica para generar un reporte
        return null;
    }

    public List<Retroalimentacion> gestionarRetroalimentaciones() {
        // Logica para gestionar retroalimentaciones
        return null;
    }

    @Override
    public boolean autenticar(String contrasena) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


