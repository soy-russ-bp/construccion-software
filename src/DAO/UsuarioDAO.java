package DAO;

import java.sql.*;

import modelo.Usuario;

public class UsuarioDAO {

    public static Usuario buscarUsuario(String correo, String contrasena) throws SQLException {
        String sql = "SELECT id_usuario, nombre, contrasena, tipo_usuario FROM usuarios WHERE nombre = ? AND contrasena = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String tipo = rs.getString("tipo_usuario");
                return new Usuario(id, correo, contrasena, tipo);
            } 
        }
        return null; // Usuario no encontrado
    }

    public static void agregarCliente(String correo, String contraseña) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, contrasena, tipo_usuario) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);
            stmt.setString(3, "Cliente");
            stmt.executeUpdate();
        }  
    }


}