/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;
import Modelo.Company;

import Conexion.Conexion;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose
 */
public class CompanySQL extends Conexion{
    
    public int count() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(*) as idcomapany FROM comapany";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return Integer.parseInt(rs.getString("idcomapany")) + 1;
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en CompanySQL:count(): " + e);
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
        tablemodel.addColumn("Nombre");
        tablemodel.addColumn("NIT");
        tablemodel.addColumn("Direccion");
        tablemodel.addColumn("NCR");
        tablemodel.addColumn("Giro");

        String sql = "Select* from comapany";
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

    public boolean registrar(Company company) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO comapany (idcomapany, nombre, NIT, direccion, ncr, giro) VALUES(?,?,?,?,?,?)";

        try {
            int i = 1;

            ps = con.prepareStatement(sql);
            ps.setString(i++, company.getIdCompany() + count());
            ps.setString(i++, company.getNombre());
            ps.setString(i++, company.getNit());
            ps.setString(i++, company.getDireccion());
            ps.setString(i++, company.getNcr());
            ps.setString(i++, company.getGiro());
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

    public boolean modificar(Company company) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE comapany SET nombre=?, NIT=?, direccion=?, ncr=?, giro=? WHERE idcomapany=? ";

        try {
            int i = 0;
            ps = con.prepareStatement(sql);
            ps.setString(i++, company.getNombre());
            ps.setString(i++, company.getNit());
            ps.setString(i++, company.getDireccion());
            ps.setString(i++, company.getNcr());
            ps.setString(i++, company.getGiro());
            ps.setString(i++, company.getIdCompany());

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
    
    
            public boolean eliminar(Company company) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM comapany WHERE idcomapany=? ";

        try {
            ps = con.prepareStatement(sql);
           ps.setString(1, company.getIdCompany());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ClienteSQL:eliminar: "+e);
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
