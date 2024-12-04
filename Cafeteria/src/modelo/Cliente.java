package modelo;

public class Cliente extends Usuario {
    public Cliente(int id, String nombre) {
        super(id, nombre, "Cliente");
    }

    public void calificarProducto(Producto producto, int calificacion) {
        System.out.println("Producto " + producto.getNombre() + " calificado con " + calificacion + " estrellas.");
    }

    public void darRetroalimentacion(Producto producto, String retroalimentacion) {
        System.out.println("Retroalimentación del cliente: " + retroalimentacion);
    }
    
    public void hacerPedido(Pedido pedido) {
        System.out.println("Pedido realizado con ID: " + pedido.getId());
    }

    @Override
    public boolean autenticar(String contrasena) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
