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
     public static int crear(Clientes clientes) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "INSERT INTO Clientes (Nombre, Apellido, Telefono,Direccion,Ciudad,Pais) VALUES (?, ?, ?, ?,?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
               statement.setString(1,clientes.getNombre());
                 statement.setString(2, clientes.getApellido());
                 statement.setInt(3, clientes.getTelefono());
                 statement.setString(4, clientes.getDireccion());
                 statement.setString(5, clientes.getCiudad());
                 statement.setString(6, clientes.getPais());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear los Clientes", e);
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
    throw new RuntimeException("Error al actualizar los datos del cliente", e);
}
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static int eliminar(Clientes clientes) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "DELETE FROM Clientes WHERE ClienteID = ?";
try (PreparedStatement statement = conn.prepareStatement(sql)) {
    statement.setInt(1, clientes.getClienteID());
    int rowsAffected = statement.executeUpdate();
    return rowsAffected;
} catch (SQLException e) {
    throw new RuntimeException("Error al eliminar el cliente", e);
}
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static ArrayList<Clientes> buscar(Clientes clientesSearch) {
        ArrayList<Clientes> listaClientes = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion()) {
        String sql = "SELECT c.ClienteID, c.Nombre, c.Apellido, c.Telefono, c.Direccion, c.Ciudad, c.Pais " +
                     "FROM Clientes c " +
                     "WHERE c.Nombre LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + clientesSearch.getNombre() + "%");
           // statement.setString(2, "%" + clientesSearch.getApellido() + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Clientes cliente = new Clientes();
                    cliente.setClienteID(resultSet.getInt("ClienteID"));
                    cliente.setNombre(resultSet.getString("Nombre"));
                    cliente.setApellido(resultSet.getString("Apellido"));
                    cliente.setTelefono(resultSet.getInt("Telefono"));
                    cliente.setDireccion(resultSet.getString("Direccion"));
                    cliente.setCiudad(resultSet.getString("Ciudad"));
                    cliente.setPais(resultSet.getString("Pais"));
                    listaClientes.add(cliente);
                }
        }
    
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar el cliente", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return listaClientes;
    }
}
