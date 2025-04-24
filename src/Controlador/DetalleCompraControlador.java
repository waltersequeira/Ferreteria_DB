/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DetalleCompraDAO;
import Modelo.DetalleCompra;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pro650
 */
public class DetalleCompraControlador {
    
     private final DetalleCompraDAO detalleCompraDAO;

    public DetalleCompraControlador() {
        this.detalleCompraDAO = new DetalleCompraDAO();
    }
    
     public void crearDetalleCompra(int idCompra, int idProducto, int cantidad, float precioUnitario) {
        try {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdCompra(idCompra);
            detalle.setIdProducto(idProducto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalleCompraDAO.crearDetalleCompra(detalle);
            JOptionPane.showMessageDialog(null, "Detalle de compra creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el detalle de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
      public List<DetalleCompra> obtenerTodosDetallesCompra() {
        try {
            return detalleCompraDAO.leerTodosDetallesCompra();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los detalles de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
      
      public void actualizarDetalleCompra(int idDetalleCompra, int idCompra, int idProducto, int cantidad, float precioUnitario) {
        try {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdDetalleCompra(idDetalleCompra);
            detalle.setIdCompra(idCompra);
            detalle.setIdProducto(idProducto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalleCompraDAO.actualizarDetalleCompra(detalle);
            JOptionPane.showMessageDialog(null, "Detalle de compra actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el detalle de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      
      public void eliminarDetalleCompra(int idDetalleCompra) {
        try {
            detalleCompraDAO.eliminarDetalleCompra(idDetalleCompra);
            JOptionPane.showMessageDialog(null, "Detalle de compra eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el detalle de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      
      public static void main(String[] args) {
        DetalleCompraControlador controlador = new DetalleCompraControlador();

        // Crear un detalle de compra
        controlador.crearDetalleCompra(1, 4, 11, 51.51f);

        // Leer todos los detalles de compra
        List<DetalleCompra> detalles = controlador.obtenerTodosDetallesCompra();
        if (detalles != null) {
            System.out.println("Lista de detalles de compra:");
            for (DetalleCompra d : detalles) {
                System.out.println("ID: " + d.getIdDetalleCompra()
                        + ", Compra: " + d.getIdCompra()
                        + ", Producto: " + d.getIdProducto()
                        + ", Cantidad: " + d.getCantidad());
            }
        }
        
        controlador.actualizarDetalleCompra(1, 1, 4, 15, 55.75f);

        // Eliminar un detalle de compra
        controlador.eliminarDetalleCompra(1);
    }
}
