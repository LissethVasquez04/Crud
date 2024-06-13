/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoaDatos;

import Entidades.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MINEDUCYT
 */
public class ClientesDAL {
    
    public static ArrayList<Clientes> obtenerTodos() {
        ArrayList<Clientes> listaclientes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT ClienteID, Nombre, Apellido, Telefono, Direccion, Ciudad, Pais FROM Clientes";           
            try (PreparedStatement statement = conn.prepareStatement(sql)) {                              
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int ClienteID = resultSet.getInt("ClienteID");
                        String Nombre = resultSet.getString("Nombre");
                        String Apellido = resultSet.getString("Apellido");  
                        int Telefono = resultSet.getInt("Telefono"); 
                        String Direccion = resultSet.getString("Direccion");
                        String Ciudad = resultSet.getString("Ciudad");
                        String Pais = resultSet.getString("Pais");
                        Clientes clientes = new Clientes(ClienteID,Nombre,Apellido,Telefono,Direccion,Ciudad,Pais);
                        listaclientes.add(clientes);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al obtener los clientes", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return listaclientes;
    }
     public static ArrayList<Clientes> buscar(Clientes empleadoSearch) {
        ArrayList<Clientes> empleados = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT ClienteID, Nombre, Apellido,Telefono,Direccion,Ciudad,Pais FROM Clientes";
            sql+=" WHERE Nombre LIKE ? ";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "%" + empleadoSearch.getNombre() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Clientes empleado = new Clientes();
                        empleado.setClienteID(resultSet.getInt("ClienteID"));
                        empleado.setNombre(resultSet.getString("Nombre"));
                        empleado.setApellido(resultSet.getString("Apellido"));
                        empleado.setTelefono(resultSet.getInt("Telefono"));
                        empleado.setDireccion(resultSet.getString("Direccion"));
                        empleado.setCiudad(resultSet.getString("Ciudad"));
                        empleado.setPais(resultSet.getString("Pais"));
                        empleados.add(empleado);
                        
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar productos", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return empleados;
    }
     
     public static int crear(Clientes clientes) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "INSERT INTO Clientes (Nombre, Apellido, Telefono, Direccion, Ciudad, Pais) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, clientes.getNombre());
                statement.setString(2, clientes.getApellido());
                statement.setInt(3, clientes.getTelefono());
                statement.setString(4, clientes.getDireccion());
                statement.setString(5, clientes.getCiudad());
                statement.setString(6, clientes.getPais());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }
     public static int modificar(Clientes clientes) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "UPDATE Clientes SET Nombre=?, Apellido=?, Telefono=?, Direccion=?, Ciudad=?, Pais=? WHERE ClienteID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, clientes.getNombre());
                statement.setString(2, clientes.getApellido());
                statement.setInt(3, clientes.getTelefono());
                 statement.setString(4, clientes.getDireccion());
                statement.setString(5, clientes.getCiudad());
                 statement.setString(6, clientes.getPais());
               statement.setInt(7, clientes.getClienteID());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }
     
     public static int eliminar(Clientes cliente) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "DELETE FROM Clientes WHERE ClienteID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, cliente.getClienteID());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al elimar el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }
}
