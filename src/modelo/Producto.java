package modelo;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private String descripcion;
	private double calificacion;
	private int cantidadVendida;
    private double totalVendido;
	
	public Producto(int id, String nombre, double precio, String descripcion, double calificacion) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setDescripcion(descripcion);
		setCalificacion(calificacion);
	}
	
	public Producto(int id, String nombre, double precio, String descripcion, double calificacion, int cantidadVendida, double totalVendido ) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setDescripcion(descripcion);
		setCalificacion(calificacion);
		setTotalVendido(totalVendido);
		setCantidadVendida(cantidadVendida);
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return this.precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }
	
	
}