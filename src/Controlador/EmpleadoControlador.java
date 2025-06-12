/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.EmpleadoDAO;
import Modelo.Empleado;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Pro650
 */
public class EmpleadoControlador {
    
    private final EmpleadoDAO empleadoDAO;

    public EmpleadoControlador() {
        this.empleadoDAO = new EmpleadoDAO();
    }

 public void crearEmpleado(String primerNombre, String segundoNombre, String primerApellido, 
                              String segundoApellido, String celular, String cargo, Date fechaContratacion) {
        try {
            Empleado empleado = new Empleado();
            empleado.setPrimerNombre(primerNombre);
            empleado.setSegundoNombre(segundoNombre);
            empleado.setPrimerApellido(primerApellido);
            empleado.setSegundoApellido(segundoApellido);
            empleado.setCelular(celular);
            empleado.setCargo(cargo);
            empleado.setFechaContratacion(fechaContratacion);
            empleadoDAO.crearEmpleado(empleado);
            JOptionPane.showMessageDialog(null, "Empleado creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
  public List<Empleado> obtenerTodosEmpleados() {
        try {
            return empleadoDAO.leerTodosEmpleados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
  
  public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        try {
            return empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el empleado: " + e.getMessage());
            return null;
        }
    }
  
   public void actualizarEmpleado(int idEmpleado, String primerNombre, String segundoNombre, String primerApellido, 
                                   String segundoApellido, String celular, String cargo, Date fechaContratacion) {
        try {
            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(idEmpleado);
            empleado.setPrimerNombre(primerNombre);
            empleado.setSegundoNombre(segundoNombre);
            empleado.setPrimerApellido(primerApellido);
            empleado.setSegundoApellido(segundoApellido);
            empleado.setCelular(celular);
            empleado.setCargo(cargo);
            empleado.setFechaContratacion(fechaContratacion);
            empleadoDAO.actualizarEmpleado(empleado);
            JOptionPane.showMessageDialog(null, "Empleado actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void eliminarEmpleado(int idEmpleado) {
        try {
            empleadoDAO.eliminarEmpleado(idEmpleado);
            JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public static void main(String[] args) {
        EmpleadoControlador controlador = new EmpleadoControlador();
        
         controlador.crearEmpleado("María", "Luisa", "Rodríguez", "López", "87654321", "Vendedor", new Date());
         
          List<Empleado> empleados = controlador.obtenerTodosEmpleados();
        if (empleados != null) {
            System.out.println("Lista de empleados:");
            for (Empleado e : empleados) {
                System.out.println("ID: " + e.getIdEmpleado() + 
                                   ", Nombre: " + e.getPrimerNombre() + " " + e.getPrimerApellido() + 
                                   ", Cargo: " + e.getCargo());
            }
        }
        
         controlador.actualizarEmpleado(1, "María", "Elena", "Rodríguez", "Gómez", "12345678", "Gerente", new Date());
         
         controlador.eliminarEmpleado(1);
    }
}
