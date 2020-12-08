/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import Modelo.Cliente;

import Conexion.Conexion;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose
 */
public class ClienteSQL extends Conexion {

    public int count() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(*) as idCliente FROM cliente";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return Integer.parseInt(rs.getString("idCliente")) + 1;
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ConsultaDesktop:count(): " + e);
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
        tablemodel.addColumn("Tipo");

        String sql = "Select* from cliente";
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
                columnas[6] = rs.getString(7);

                tablemodel.addRow(columnas);
            }
            return tablemodel;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            showLongTextMessageInDialog("Fallo en la conexion en ConsultaDesktop:mostrarRegistros(): " + e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean registrar(Cliente cliente) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO cliente (idCliente, nombre, NIT, direccion, ncr, giro, tipo) VALUES(?,?,?,?,?,?,?)";

        try {
            int i = 1;

            ps = con.prepareStatement(sql);
            ps.setString(i++, cliente.getIdCliente() + count());
            ps.setString(i++, cliente.getNombre());
            ps.setString(i++, cliente.getNit());
            ps.setString(i++, cliente.getDireccion());
            ps.setString(i++, cliente.getNcr());
            ps.setString(i++, cliente.getGiro());
            ps.setString(i++, cliente.getTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ClienteSQL:registrar: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificar(Cliente cliente) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE cliente SET nombre=?, NIT=?, direccion=?, ncr=?, giro=?, tipo=? WHERE idCliente=? ";

        try {
            int i = 0;
            ps = con.prepareStatement(sql);
            ps.setString(i++, cliente.getNombre());
            ps.setString(i++, cliente.getNit());
            ps.setString(i++, cliente.getDireccion());
            ps.setString(i++, cliente.getNcr());
            ps.setString(i++, cliente.getGiro());
            ps.setString(i++, cliente.getTipo());
            ps.setString(i++, cliente.getIdCliente());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ClienteSQL:modificar: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Cliente cliente) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM cliente WHERE idCliente=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getIdCliente());
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

    public void llenarcombocox(JComboBox combobox,int columna) {

        combobox.removeAllItems();
        combobox.addItem("Seleccione una opción");

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
                String sql = "Select* from cliente";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {             
                combobox.addItem(rs.getString(columna));
            }
            //return tablemodel;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            showLongTextMessageInDialog("Fallo en la conexion en ConsultaDesktop:mostrarRegistros(): " + e);
            //return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

}
