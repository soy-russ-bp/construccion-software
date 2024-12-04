package DAO;

import java.sql.*;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/cafeteria";
    private static final String USUARIO = "root";  // Cambia a tu usuario
    private static final String CONTRASENA = "";  // Cambia a tu contraseña

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}