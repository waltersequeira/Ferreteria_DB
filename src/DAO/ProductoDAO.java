/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Producto;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pro650
 */
public class ProductoDAO {
    public List<Producto> leerTodosProductos() throws SQLException {
        String sql = "SELECT * FROM Productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setDescripcionProducto(rs.getString("descripcion_producto"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setPrecioUnitario(rs.getFloat("precio_unitario"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public static void main(String[] args) {
        try {
            ProductoDAO dao = new ProductoDAO();
            List<Producto> productos = dao.leerTodosProductos();
            System.out.println("Lista de productos:");
            for (Producto prod : productos) {
                System.out.println("ID: " + prod.getIdProducto() + 
                                 ", Nombre: " + prod.getNombreProducto() + 
                                 ", Descripción: " + prod.getDescripcionProducto() + 
                                 ", Categoría ID: " + prod.getIdCategoria() + 
                                 ", Precio: " + prod.getPrecioUnitario() + 
                                 ", Stock: " + prod.getStock() + 
                                 ", Imagen: " + prod.getImagen());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
