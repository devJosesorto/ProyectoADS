/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jose
 */
public class Cliente {

    private String idCliente;
    private String nombre;
    private String nit;
    private String direccion;
    private String ncr;
    private String giro;
    private String tipo;

    public Cliente() {
    }

    public Cliente(String idCliente, String nombre, String nit, String direccion, String ncr, String giro, String tipo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.ncr = ncr;
        this.giro = giro;
        this.tipo = tipo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idcliente) {
        this.idCliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNcr() {
        return ncr;
    }

    public void setNcr(String ncr) {
        this.ncr = ncr;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
