/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.CategoriaDAO;
import Modelo.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Pro650
 */
public class CategoriaControlador {
    
    private final CategoriaDAO categoriaDAO;

    public CategoriaControlador() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    public void crearCategoria(String nombre, String descripcion) {
        try {
            Categoria categoria = new Categoria();
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcionCategoria(descripcion);
            categoriaDAO.crearCategoria(categoria);
            JOptionPane.showMessageDialog(null, "Categoría creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<Categoria> obtenerTodasCategorias() {
        try {
            return categoriaDAO.leerTodasCategorias();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void actualizarCategoria(int id, String nombre, String descripcion) {
        try {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(id);
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcionCategoria(descripcion);
            categoriaDAO.actualizarCategoria(categoria);
            JOptionPane.showMessageDialog(null, "Categoría actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        public void eliminarCategoria(int id) {
        try {
            categoriaDAO.eliminarCategoria(id);
            JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CategoriaControlador controlador = new CategoriaControlador();
        // Probar crear categoría
        controlador.crearCategoria("Jardinería", "Productos para el jardin.");
        // Probar leer categorías
        List<Categoria> categorias = controlador.obtenerTodasCategorias();
        for (Categoria cat : categorias) {
            System.out.println("ID: " + cat.getIdCategoria() + ", Nombre: " + cat.getNombreCategoria());
        }
    }

}
