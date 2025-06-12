/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.ProductoDAO;
import Modelo.Producto;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pro650
 */
public class ProductoControlador {
    
     private final ProductoDAO productoDAO;

    public ProductoControlador() {
        this.productoDAO = new ProductoDAO();
    }
    
    public void crearProducto(String nombreProducto, String descripcionProducto, int idCategoria,
            float precioUnitario, int stock, String imagen) {
        try {
            Producto producto = new Producto();
            producto.setNombreProducto(nombreProducto);
            producto.setDescripcionProducto(descripcionProducto);
            producto.setIdCategoria(idCategoria);
            producto.setPrecioUnitario(precioUnitario);
            producto.setStock(stock);
            producto.setImagen(imagen);
            productoDAO.crearProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<Producto> obtenerTodosProductos() {
        try {
            return productoDAO.leerTodosProductos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
public Producto obtenerProductoPorId(int id_producto) {
        try {
            return productoDAO.obtenerProductoPorId(id_producto);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void actualizarProducto(int id_producto, String nombreProducto, String descripcionProducto, int idCategoria,
            float precioUnitario, int stock, String imagen) {
        try {
            Producto producto = new Producto();
            producto.setId_producto(id_producto);
            producto.setNombreProducto(nombreProducto);
            producto.setDescripcionProducto(descripcionProducto);
            producto.setIdCategoria(idCategoria);
            producto.setPrecioUnitario(precioUnitario);
            producto.setStock(stock);
            producto.setImagen(imagen);
            productoDAO.actualizarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarProducto(int id_producto) {
        try {
            productoDAO.eliminarProducto(id_producto);
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public static void main(String[] args) {
        ProductoControlador controlador = new ProductoControlador();
        
        controlador.crearProducto("Laptop", "Laptop de alta gama", 1, 1500.99f, 10, "ruta/laptop.jpg");
        
         List<Producto> productos = controlador.obtenerTodosProductos();
        if (productos != null) {
            System.out.println("Lista de productos:");
            for (Producto p : productos) {
                System.out.println("ID: " + p.getId_producto()
                        + ", Nombre: " + p.getNombreProducto()
                        + ", Precio: " + p.getPrecioUnitario());
            }
        }
        
         controlador.actualizarProducto(1, "Laptop Pro", "Laptop mejorada", 1, 1800.50f, 8, "ruta/laptop_pro.jpg");

        // Eliminar un producto
        controlador.eliminarProducto(1);
    }
}
