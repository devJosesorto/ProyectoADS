/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import Modelo.Documento;

import Conexion.Conexion;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose
 */
public class DocumentoSQL extends Conexion {

    public int count() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(*) as idDocumento FROM documento";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return Integer.parseInt(rs.getString("idComprobante")) + 1;
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ComprobanteSQL:count(): " + e);
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public void showLongTextMessageInDialog(String longMessage) {
        Frame frame = new Frame();
        JTextArea textArea = new JTextArea(6, 25);
        textArea.setText(longMessage);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Error en sentencia sql Desktop", JOptionPane.WARNING_MESSAGE);
    }

    public DefaultTableModel mostrarRegistros() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        DefaultTableModel tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false 
                return false;
            }
        };

        tablemodel.addColumn("CODIGO");
        tablemodel.addColumn("Cliente");
        tablemodel.addColumn("Monto");
        tablemodel.addColumn("Corelativo");
        tablemodel.addColumn("Fecha");
        tablemodel.addColumn("Estado");

        String sql = "SELECT idDocumento, cliente.nombre,monto,numero_doc,fecha,estado \n"
                + "FROM documento,cliente\n"
                + "where documento.idCliente=cliente.idCliente";
        String[] columnas = new String[10];
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                columnas[0] = rs.getString(1);
                columnas[1] = rs.getString(2);
                columnas[2] = rs.getString(3);
                columnas[3] = rs.getString(4);
                columnas[4] = rs.getString(5);
                columnas[5] = rs.getString(6);

                tablemodel.addRow(columnas);
            }
            return tablemodel;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            showLongTextMessageInDialog("Fallo en la conexion en CompanySQL:mostrarRegistros(): " + e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean registrar(Documento documento) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO documento (idDocumento, idCliente, monto, numero_doc, fecha,estado) VALUES(?,?,?,?,?,?)";

        try {
            int i = 1;

            ps = con.prepareStatement(sql);
            ps.setString(i++, documento.getIdDocumento() + count());
            ps.setString(i++, documento.getIdCliente());
            ps.setString(i++, documento.getMonto());
            ps.setString(i++, documento.getCorrelativo());
            ps.setString(i++, documento.getFecha());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en CompanySQL:registrar: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificar(Documento documento) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE comprobante SET idCliente=?, monto=?, numero_doc=?, fecha=?,estado=? WHERE idDocumento=? ";

        try {
            int i = 0;
            ps = con.prepareStatement(sql);
            ps.setString(i++, documento.getIdCliente());
            ps.setString(i++, documento.getMonto());
            ps.setString(i++, documento.getCorrelativo());
            ps.setString(i++, documento.getFecha());
            ps.setString(i++, documento.getEstado());
            ps.setString(i++, documento.getIdDocumento());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en CompanySQL:modificar: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Documento documento) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM comprobante WHERE idComprobante=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, documento.getIdDocumento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ClienteSQL:eliminar: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
