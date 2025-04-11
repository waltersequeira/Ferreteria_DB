/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.DetalleVenta;

import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Pro650
 */
public class DetalleVentaDAO {

    public void crearDetalleVenta(DetalleVenta detalle) throws SQLException {
        String sql = """
        INSERT INTO Detalles_Ventas (
            id_venta, 
            id_producto, 
            cantidad, 
            precio_unitario
        ) VALUES (?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdVenta());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setFloat(4, detalle.getPrecioUnitario());
            stmt.executeUpdate();
        }
    }

    public void actualizarDetalleVenta(DetalleVenta detalle) throws SQLException {
        String sql = "UPDATE Detalles_Ventas SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id_detalle_venta = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdVenta());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setFloat(4, detalle.getPrecioUnitario());
            stmt.setInt(5, detalle.getIdDetalleVenta());
            stmt.executeUpdate();
        }
    }

    public void eliminarDetalleVenta(int idDetalleVenta) throws SQLException {
        String sql = "DELETE FROM Detalles_Ventas WHERE id_detalle_venta = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDetalleVenta);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DetalleVentaDAO dao = new DetalleVentaDAO();
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdDetalleVenta(1); // ID existente
            detalle.setIdVenta(1);
            detalle.setIdProducto(3);
            detalle.setCantidad(2);
            detalle.setPrecioUnitario(200.0f);
            dao.actualizarDetalleVenta(detalle);
            System.out.println("Detalle de venta actualizado.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
