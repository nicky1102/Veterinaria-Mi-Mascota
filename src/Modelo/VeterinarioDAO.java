/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class VeterinarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Veterinario v = new Veterinario();

    /*
    SE ELIMINÓ EL MÉTODO LISTAR YA QUE PARA AHORRAR CÓDIGO SE PUEDE USAR FILTRAR.
     */
    public int agregarVeterinario(Veterinario vet) {
        int r = 0;
        String sql = "INSERT INTO Veterinario (nombre, apellido1, apellido2, cedula, codProfesional, email, telefono, direccion, activo) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getApellido1());
            ps.setString(3, vet.getApellido2());
            ps.setString(4, vet.getCedula());
            ps.setString(5, vet.getCodProfesional());
            ps.setString(6, vet.getEmail());
            ps.setString(7, vet.getTelefono());
            ps.setString(8, vet.getDireccion());
            ps.setBoolean(9, vet.isActivo());
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

    public int actualizarVeterinario(Veterinario vet) {
        int r = 0;
        String sql = "UPDATE Veterinario SET nombre=?, apellido1=?, apellido2=?, cedula=?, codProfesional=?, email=?, telefono=?, direccion=?, activo=? WHERE idVeterinario=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getApellido1());
            ps.setString(3, vet.getApellido2());
            ps.setString(4, vet.getCedula());
            ps.setString(5, vet.getCodProfesional());
            ps.setString(6, vet.getEmail());
            ps.setString(7, vet.getTelefono());
            ps.setString(8, vet.getDireccion());
            ps.setBoolean(9, vet.isActivo());
            ps.setInt(10, vet.getIdVeterinario());
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

    public int eliminarVeterinario(int id) {
        int r = 0;
        String sql = "DELETE FROM Veterinario WHERE idVeterinario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;

    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"ID", "Nombre", "Primer Apellido", "Segundo Apellido", "Cedula", "CodProfesional", "Email", "Telefono", "Direccion", "Activo"};
        String[] registros = new String[10];
        String sql = "SELECT * FROM Veterinario WHERE nombre LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idVeterinario");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("cedula");
                registros[5] = rs.getString("codProfesional");
                registros[6] = rs.getString("email");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("direccion");
                registros[9] = rs.getString("activo");
                model.addRow(registros);

            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al buscar datos: " + e.getMessage());

        }
    }

    public void cargarComboVeterinario(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("Seleccionar Veterinario");
        String sql = "SELECT nombre FROM Veterinario";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {

        }

    }

    public void cargarIdVeterinario(JComboBox combo, JTextField texto) {

        String sql = "SELECT idVeterinario FROM Veterinario WHERE nombre = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("idVeterinario")));
        } catch (SQLException e) {
        }
    }
}
