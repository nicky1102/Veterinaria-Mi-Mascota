/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author nicol
 */
public class DuenoDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Dueno d = new Dueno();

    public List listarDueno() {

        List<Dueno> datos = new ArrayList<>();
        String sql = "SELECT * FROM Dueno";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Dueno d = new Dueno();
                d.setIdDueno(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setApellidoI(rs.getString(3));
                d.setApellidoII(rs.getString(4));
                d.setCedula(rs.getString(5));
                d.setGenero(rs.getString(6));
                d.setEmail(rs.getString(7));
                d.setTel(rs.getString(8));
                d.setDireccion(rs.getString(9));
                datos.add(d);
            }

        } catch (SQLException e) {

        }

        return datos;
    }

    public int agregarDueno(Dueno d) {

        int r = 0;
        String sql = "INSERT INTO Dueno(nombreDueno, apellido1, apellido2, cedula, genero, email, telefono, direccion) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellidoI());
            ps.setString(3, d.getApellidoII());
            ps.setString(4, d.getCedula());
            ps.setString(5, d.getGenero());
            ps.setString(6, d.getEmail());
            ps.setString(7, d.getTel());
            ps.setString(8, d.getDireccion());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
        }
        return r;
    }

    public int actualizarDueno(Dueno d) {

        int r = 0;
        String sql = "UPDATE Dueno SET nombreDueno = ?,apellido1 = ?,apellido2 = ?,cedula = ?,genero = ?,email = ?,telefono = ?, direccion = ? WHERE idDueno = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellidoI());
            ps.setString(3, d.getApellidoII());
            ps.setString(4, d.getCedula());
            ps.setString(5, d.getGenero());
            ps.setString(6, d.getEmail());
            ps.setString(7, d.getTel());
            ps.setString(8, d.getDireccion());
            ps.setInt(9, d.getIdDueno());
            r = ps.executeUpdate();

            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
        }
        return r;

    }

    public int eliminarDueno(int id) {
        int r = 0;
        String sql = "DELETE FROM Dueno WHERE idDueno=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombreDueno(JTable table, String filtro) {

        String[] titulos = {"idDueno", "nombreDueno", "apellido1", "apellido2", "cedula", "genero", "email", "telefono", "direccion"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM Dueno WHERE nombreDueno LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idDueno");
                registros[1] = rs.getString("nombreDueno");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("cedula");
                registros[5] = rs.getString("genero");
                registros[6] = rs.getString("email");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("direccion");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

    public void cargarComboDueno(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("Seleccionar Dueno");
        String sql = "SELECT nombreDueno FROM Dueno";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nombreDueno"));
            }
        } catch (SQLException e) {

        }
    }

    public void cargarIdDueno(JComboBox combo, JTextField texto) {

        String sql = "SELECT idDueno FROM Dueno WHERE nombreDueno = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("idDueno")));
        } catch (SQLException e) {

        }
    }
}
