package modelo;

public class DetallePedido {
    private Producto producto;
    private String nombreProducto;
    private double subtotal;
    private int cantidad;
    private Boolean hecho; // Nuevo atributo para almacenar el estado "Hecho"
    
    // Constructor para DetallePedido con Producto y cantidad
    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.hecho = false; // Por defecto, no está hecho
        calcularSubtotal();
    }

    // Constructor para DetallePedido con nombreProducto y cantidad
    public DetallePedido(String nombreProducto, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.hecho = false; // Por defecto, no está hecho
    }

    // Constructor extendido para inicializar "hecho"
    public DetallePedido(String nombreProducto, int cantidad, boolean hecho) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.hecho = hecho; // Asignar el valor inicial de "hecho"
    }
    
    // Getter para Producto
    public Producto getProducto() {
        return producto;
    }

    // Setter para Producto
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Getter para nombreProducto
    public String getNombreProducto() {
        return nombreProducto;
    }

    // Setter para nombreProducto
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    // Getter para cantidad
    public int getCantidad() {
        return cantidad;
    }

    // Setter para cantidad
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter para subtotal
    public double getSubtotal() {
        return subtotal;
    }

    // Método para calcular el subtotal
    public void calcularSubtotal() {
        if (producto != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0;
        }
    }

    // Getter para "hecho"
    public Boolean isHecho() {
        return hecho;
    }

    // Setter para "hecho"
    public void setHecho(Boolean hecho) {
        this.hecho = hecho;
    }
}
