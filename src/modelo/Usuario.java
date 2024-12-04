package modelo;

public class Usuario {
    private int id;
    private String correo;
    private String contraseña;
    private String tipo;

    public Usuario(int id, String correo, String contraseña, String tipo) {
        this.id = id;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public String getTipo() { return tipo; }
}