package modelo;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private double calificacion;
	
	public Producto(int id, String nombre, double precio, double calificacion) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setCalificacion(calificacion);
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
	
	
}