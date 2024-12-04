/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Retroalimentacion {
    private int id;
    private Cliente cliente;
    private Producto producto;
    private String comentario;
    private int calificacion;

    public Retroalimentacion(int id, Cliente cliente, Producto producto, String comentario, int calificacion) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getComentario() {
        return comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        return "Retroalimentación de " + cliente.getNombre() + " para " + producto.getNombre() + 
               ": " + comentario + " (Calificación: " + calificacion + " estrellas)";
    }
}


