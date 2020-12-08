/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import Modelo.User;

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
public class UserSQL extends Conexion {

    public int count() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(*) as iduser FROM user";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return Integer.parseInt(rs.getString("iduser")) + 1;
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
        tablemodel.addColumn("Nombre");
        tablemodel.addColumn("Correo");
        tablemodel.addColumn("Rol");
        tablemodel.addColumn("Compañia");

        String sql = "SELECT iduser,nombre,correo,rol,company\n"
                + "FROM user";
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

    public boolean registrar(User user) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO user (iduser, nombre, correo, contraseña, rol, company) VALUES(?,?,?,?,?,?)";

        try {
            int i = 1;

            ps = con.prepareStatement(sql);
            ps.setString(i++, user.getIdUser() + count());
            ps.setString(i++, user.getNombre());
            ps.setString(i++, user.getCorreo());
            ps.setString(i++, user.getPass());
            ps.setString(i++, user.getIdUser());
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

    public boolean modificar(User user) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE user SET nombre=?, correo=?, contraseña=?, rol=?, company=? WHERE iduser=? ";

        try {
            int i = 0;
            ps = con.prepareStatement(sql);
            ps.setString(i++, user.getNombre());
            ps.setString(i++, user.getCorreo());
            ps.setString(i++, user.getPass());
            ps.setString(i++, user.getIdUser());
            ps.setString(i++, user.getIdUser());

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

    public boolean eliminar(User user) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM user WHERE iduser=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getIdUser());
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

    public boolean validar(String user, String pass) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(*) as idComprobante \n"
                + "FROM user\n"
                + "where  correo='"+user+"' and contraseña='"+pass+"'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                if (Integer.parseInt(rs.getString("idComprobante")) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            showLongTextMessageInDialog("Fallo en la conexion en ComprobanteSQL:count(): " + e);
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
