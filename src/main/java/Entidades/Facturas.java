/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MINEDUCYT
 */
public class Facturas {
    private int FacturaID;
    private Date FechaFactura;
    private int ClienteID;
    private BigDecimal Total;

    
    private Clientes clientes;
            
    public Facturas() {
    }

    public Facturas(int FacturaID, Date FechaFactura, int ClienteID, BigDecimal Total) {
        this.FacturaID = FacturaID;
        this.FechaFactura = FechaFactura;
        this.ClienteID = ClienteID;
        this.Total = Total;
    }

    public int getFacturaID() {
        return FacturaID;
    }

    public void setFacturaID(int FacturaID) {
        this.FacturaID = FacturaID;
    }

    public Date getFechaFactura() {
        return FechaFactura;
    }

    public void setFechaFactura(Date FechaFactura) {
        this.FechaFactura = FechaFactura;
    }

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int ClienteID) {
        this.ClienteID = ClienteID;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    
    
    
}
