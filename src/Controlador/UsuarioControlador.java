/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pro650
 */
public class UsuarioControlador {

    private final UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void crearUsuario(String usuario, String contrasena) {
        try {
            Usuario user = new Usuario();
            user.setUsuario(usuario);
            user.setContrasena(contrasena);
            usuarioDAO.crearUsuario(user);
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Usuario> obtenerTodosUsuarios() {
        try {
            return usuarioDAO.leerTodosUsuarios();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Usuario validarCredenciales(String usuario, String contrasena) {
        try {
            Usuario user = usuarioDAO.validarUsuario(usuario, contrasena);
            if (user != null) {
                System.out.println("Inicio de sesión exitoso.");
                return user;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarUsuario(int idUsuario, String usuario, String contrasena) {
        try {
            Usuario user = new Usuario();
            user.setIdUsuario(idUsuario);
            user.setUsuario(usuario);
            user.setContrasena(contrasena);
            usuarioDAO.actualizarUsuario(user);
            JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarUsuario(int idUsuario) {
        try {
            usuarioDAO.eliminarUsuario(idUsuario);
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        UsuarioControlador controlador = new UsuarioControlador();

        controlador.crearUsuario("ana", "ana2025");

        List<Usuario> usuarios = controlador.obtenerTodosUsuarios();
        if (usuarios != null) {
            System.out.println("Lista de usuarios:");
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getIdUsuario()
                        + ", Usuario: " + u.getUsuario());
            }
        }

        controlador.actualizarUsuario(1, "ana_maria", "nueva2025");

        controlador.eliminarUsuario(1);
    }
}
