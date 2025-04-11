/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.DetalleCompra;

import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pro650
 */
public class DetalleCompraDAO {

    public void crearDetalleCompra(DetalleCompra detalle) throws SQLException {
        String sql = """
        INSERT INTO Detalles_Compras (
            id_compra, 
            id_producto, 
            cantidad, 
            precio_unitario
        ) VALUES (?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdCompra());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setFloat(4, detalle.getPrecioUnitario());
            stmt.executeUpdate();
        }
    }

    public List<DetalleCompra> leerTodosDetallesCompra() throws SQLException {
        String sql = "SELECT * FROM Detalles_Compras";
        List<DetalleCompra> detalles = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DetalleCompra detalle = new DetalleCompra();
                detalle.setIdDetalleCompra(rs.getInt("id_detalle_compra"));
                detalle.setIdCompra(rs.getInt("id_compra"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getFloat("precio_unitario"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }

    public void actualizarDetalleCompra(DetalleCompra detalle) throws SQLException {
        String sql = "UPDATE Detalles_Compras SET id_compra = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id_detalle_compra = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdCompra());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setFloat(4, detalle.getPrecioUnitario());
            stmt.setInt(5, detalle.getIdDetalleCompra());
            stmt.executeUpdate();
        }
    }

    public void eliminarDetalleCompra(int idDetalleCompra) throws SQLException {
        String sql = "DELETE FROM Detalles_Compras WHERE id_detalle_compra = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDetalleCompra);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DetalleCompraDAO dao = new DetalleCompraDAO();
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdDetalleCompra(1); // ID existente
            detalle.setIdCompra(1);
            detalle.setIdProducto(2);
            detalle.setCantidad(5);
            detalle.setPrecioUnitario(100.0f);
            dao.actualizarDetalleCompra(detalle);
            System.out.println("Detalle de compra actualizado.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
