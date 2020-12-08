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
public class Comprobante {

    private String idComprobante;
    private String idCliente;
    private String idDocumento;
    private String idCompany;
    private String fecha;
    private String retencion;
    private String corelativo;

    public Comprobante() {
    }

    public Comprobante(String idComprobante, String idCliente, String idDocumento, String idCompany, String fecha, String retencion, String corelativo) {
        this.idComprobante = idComprobante;
        this.idCliente = idCliente;
        this.idDocumento = idDocumento;
        this.idCompany = idCompany;
        this.fecha = fecha;
        this.retencion = retencion;
        this.corelativo = corelativo;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String retencion) {
        this.retencion = retencion;
    }

    public String getCorelativo() {
        return corelativo;
    }

    public void setCorelativo(String corelativo) {
        this.corelativo = corelativo;
    }
    
    
    
}
