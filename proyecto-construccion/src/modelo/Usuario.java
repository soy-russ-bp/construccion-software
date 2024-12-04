package modelo;

public class Usuario {
	private int id;
	private String nombre;
	private String tipo;
	private String correo;
	private String contrasena;
	
	public Usuario(int id, String correo, String contrasena, String tipo) {
		setId(id);
		setCorreo(correo);
		setContrasena(contrasena);
		setTipo(tipo);
	}

	public void autenticar() {
		
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
