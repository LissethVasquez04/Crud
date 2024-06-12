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
}
