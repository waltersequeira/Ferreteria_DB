/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Empleado;

import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pro650
 */
public class EmpleadoDAO {

    public void crearEmpleado(Empleado empleado) throws SQLException {
        String sql = """
        INSERT INTO Empleados (
            primer_nombre, 
            segundo_nombre, 
            primer_apellido, 
            segundo_apellido, 
            celular, 
            cargo, 
            fecha_contratacion
        ) VALUES (?, ?, ?, ?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, empleado.getPrimerNombre());
            stmt.setString(2, empleado.getSegundoNombre());
            stmt.setString(3, empleado.getPrimerApellido());
            stmt.setString(4, empleado.getSegundoApellido());
            stmt.setString(5, empleado.getCelular());
            stmt.setString(6, empleado.getCargo());
            stmt.setDate(7, new java.sql.Date(empleado.getFechaContratacion().getTime()));
            stmt.executeUpdate();
        }
    }
    
    public Empleado obtenerEmpleadoPorId(int idEmpleado) throws SQLException {
    String sql = "SELECT * FROM Empleados WHERE id_empleado = ?";
    Empleado empleado = null;

    try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idEmpleado);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setPrimerNombre(rs.getString("primer_nombre"));
                empleado.setSegundoNombre(rs.getString("segundo_nombre"));
                empleado.setPrimerApellido(rs.getString("primer_apellido"));
                empleado.setSegundoApellido(rs.getString("segundo_apellido"));
                empleado.setCelular(rs.getString("celular"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
            }
        }
    }
    return empleado;
}

    public List<Empleado> leerTodosEmpleados() throws SQLException {
        String sql = "SELECT * FROM Empleados";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setPrimerNombre(rs.getString("primer_nombre"));
                empleado.setSegundoNombre(rs.getString("segundo_nombre"));
                empleado.setPrimerApellido(rs.getString("primer_apellido"));
                empleado.setSegundoApellido(rs.getString("segundo_apellido"));
                empleado.setCelular(rs.getString("celular"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String sql = "UPDATE Empleados SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, celular = ?, cargo = ?, fecha_contratacion = ? WHERE id_empleado = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, empleado.getPrimerNombre());
            stmt.setString(2, empleado.getSegundoNombre());
            stmt.setString(3, empleado.getPrimerApellido());
            stmt.setString(4, empleado.getSegundoApellido());
            stmt.setString(5, empleado.getCelular());
            stmt.setString(6, empleado.getCargo());
            stmt.setDate(7, new java.sql.Date(empleado.getFechaContratacion().getTime()));
            stmt.setInt(8, empleado.getIdEmpleado());
            stmt.executeUpdate();
        }
    }

    public void eliminarEmpleado(int idEmpleado) throws SQLException {
        String sql = "DELETE FROM Empleados WHERE id_empleado = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            EmpleadoDAO dao = new EmpleadoDAO();
            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(1); // ID existente
            empleado.setPrimerNombre("Ana");
            empleado.setSegundoNombre("María");
            empleado.setPrimerApellido("López");
            empleado.setSegundoApellido("Martínez");
            empleado.setCelular("987654321");
            empleado.setCargo("Gerente");
            empleado.setFechaContratacion(new java.util.Date());
            dao.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
