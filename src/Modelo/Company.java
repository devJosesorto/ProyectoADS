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
public class Company {

    private String idCompany;
    private String nombre;
    private String nit;
    private String direccion;
    private String ncr;
    private String giro;

    public Company() {
    }

    public Company(String idCompany, String nombre, String nit, String direccion, String ncr, String giro) {
        this.idCompany = idCompany;
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.ncr = ncr;
        this.giro = giro;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
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
    
    

}
