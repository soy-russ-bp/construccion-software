/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;

public class ControladorUsuario {
    public boolean autenticarUsuario(Usuario usuario, String contrasena) {
        return usuario.autenticar(contrasena);
    }
}

