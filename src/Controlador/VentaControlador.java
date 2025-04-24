/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.VentaDAO;
import DAO.DetalleVentaDAO;
import Modelo.Venta;
import Modelo.DetalleVenta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pro650
 */
public class VentaControlador {
    
    private final VentaDAO ventaDAO;
    private final DetalleVentaDAO detalleVentaDAO;

    public VentaControlador() {
        this.ventaDAO = new VentaDAO();
        this.detalleVentaDAO = new DetalleVentaDAO();
    }
    
     public void crearVenta(int idCliente, int idEmpleado, Date fechaVenta, float totalVenta, List<DetalleVenta> detalles) {
        try {
            Venta venta = new Venta();
            venta.setIdCliente(idCliente);
            venta.setIdEmpleado(idEmpleado);
            venta.setFechaVenta(fechaVenta);
            venta.setTotalVenta(totalVenta);
            int idVenta = ventaDAO.crearVenta(venta);

            if (idVenta == -1) {
                throw new SQLException("No se pudo obtener el ID de la venta.");
            }
            
            for (DetalleVenta detalle : detalles) {
                detalle.setIdVenta(idVenta);
                detalleVentaDAO.crearDetalleVenta(detalle);
            }

            JOptionPane.showMessageDialog(null, "Venta y detalles creados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
      public List<Venta> obtenerTodasVentas() {
        try {
            return ventaDAO.leerTodasVentas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las ventas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
      
       public void actualizarVenta(int idVenta, int idCliente, int idEmpleado, Date fechaVenta, float totalVenta) {
        try {
            Venta venta = new Venta();
            venta.setIdVenta(idVenta);
            venta.setIdCliente(idCliente);
            venta.setIdEmpleado(idEmpleado);
            venta.setFechaVenta(fechaVenta);
            venta.setTotalVenta(totalVenta);
            ventaDAO.actualizarVenta(venta);
            JOptionPane.showMessageDialog(null, "Venta actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
       public void eliminarVenta(int idVenta) {
        try {
            ventaDAO.eliminarVenta(idVenta);
            JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
        public static void main(String[] args) {
        VentaControlador controlador = new VentaControlador();
        
         List<DetalleVenta> detalles = new ArrayList<>();
        DetalleVenta detalle1 = new DetalleVenta();
        detalle1.setIdProducto(1);
        detalle1.setCantidad(11);
        detalle1.setPrecioUnitario(33.22f);
        detalles.add(detalle1);
        
         controlador.crearVenta(1, 1, new Date(), 555.44f, detalles);
         
         List<Venta> ventas = controlador.obtenerTodasVentas();
        if (ventas != null) {
            System.out.println("Lista de ventas:");
            for (Venta v : ventas) {
                System.out.println("ID: " + v.getIdVenta()
                        + ", Cliente: " + v.getIdCliente()
                        + ", Total: " + v.getTotalVenta());
            }
        }
        
        controlador.actualizarVenta(1, 5, 4, new Date(), 600.75f);
        
        controlador.eliminarVenta(1);
        
    }
}
