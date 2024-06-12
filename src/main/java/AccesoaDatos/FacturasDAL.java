/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoaDatos;

import Entidades.Clientes;
import Entidades.Facturas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MINEDUCYT
 */
public class FacturasDAL {
    
     public static int crear(Facturas factura) {
    try (Connection conn = ComunDB.obtenerConexion()) {
        String sql = "INSERT INTO Facturas (FechaFactura, ClienteID, Total) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(factura.getFechaFactura().getTime())); // Se convierte LocalDate a java.sql.Date
            statement.setInt(2, factura.getClienteID());
            statement.setBigDecimal(3, factura.getTotal());
         
            int rowsAffected = statement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la factura", e);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
    }
}

    public static int modificar(Facturas factura) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "UPDATE Facturas SET FechaFactura=?, ClienteID=?, Total=? WHERE FacturaID=?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
   statement.setDate(1, (Date) factura.getFechaFactura());
    statement.setInt(2, factura.getClienteID()); 
    statement.setBigDecimal(3, factura.getTotal()); 
    statement.setInt(4, factura.getFacturaID()); 
    int rowsAffected = statement.executeUpdate();
    return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el Factura", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static int eliminar(Facturas factura) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "DELETE FROM Facturas WHERE FacturaID =?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, factura.getFacturaID());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el Factura", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static ArrayList<Facturas> buscar(Facturas facturasSearch) {
        ArrayList<Facturas> ListaFacturas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
    String sql = "SELECT f.FacturaID, f.FechaFactura, f.ClienteID, f.Total, c.Nombre AS NombreCliente " +
                 "FROM Facturas f " +
                 "INNER JOIN Clientes c ON c.ClienteID = f.ClienteID " +
                 "WHERE f.FechaFactura LIKE ?";  
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, (Date) facturasSearch.getFechaFactura()); 
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Facturas factura = new Facturas();
                factura.setFacturaID(resultSet.getInt("FacturaID"));
                factura.setFechaFactura(resultSet.getDate("FechaFactura"));
                factura.setClienteID(resultSet.getInt("ClienteID"));
                factura.setTotal(resultSet.getBigDecimal("Total"));
                Clientes cliente= new Clientes();
                cliente.setClienteID(resultSet.getInt("ClienteID"));
                cliente.setNombre(resultSet.getString("Nombre"));
                 factura.setClientes(cliente);
                
                 ListaFacturas.add(factura);
                }
        }
    
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar la factura", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return ListaFacturas;
    }
}

