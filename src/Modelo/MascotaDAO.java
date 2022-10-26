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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class MascotaDAO {

    ResultSet rs;
    Connection con;
    PreparedStatement ps;
    Conexion conectar = new Conexion();
    Mascota m = new Mascota();

    public int agregarMascota(Mascota mascota) {
        int r = 0;
        String sql = "INSERT INTO Mascota(idDueno, nombreMascota, genero, tipo, raza, activo) VALUES (?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mascota.getDueno().getIdDueno());
            ps.setString(2, mascota.getNombreMascota());
            ps.setString(3, mascota.getGenero());
            ps.setString(4, mascota.getTipo());
            ps.setString(5, mascota.getRaza());
            ps.setBoolean(6, mascota.isActivo());
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

    public List listarMascota() {

        List<Mascota> datos = new ArrayList<>();
        String sql = "SELECT * FROM Mascota;";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt(0));
                m.getDueno().setIdDueno(rs.getInt(1));
                m.setNombreMascota(rs.getString(2));
                m.setGenero(rs.getString(3));
                m.setTipo(rs.getString(4));
                m.setRaza(rs.getString(5));
                m.setActivo(rs.getBoolean(6));
                datos.add(m);
            }

        } catch (SQLException e) {

        }

        return datos;
    }

    public void cargarIdMascota(JTable table, String nombre) {

        String sql = "SELECT idMascota FROM Mascota WHERE nombreMascota LIKE '%" + nombre + "%'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            rs.getInt(nombre);
        } catch (SQLException e) {

        }

    }

    public void filtrarTablaMascota(JTable table, String filtro) {
        String[] titulos = {"idMascota", "idDueno", "nombreMascota", "genero", "tipo", "raza", "activo"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM Mascota WHERE nombreMascota LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idMascota");
                registros[1] = rs.getString("idDueno");
                registros[2] = rs.getString("nombreMascota");
                registros[3] = rs.getString("genero");
                registros[4] = rs.getString("tipo");
                registros[5] = rs.getString("raza");
                registros[6] = rs.getString("activo");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS" + e.getMessage());
        }
    }

    public int eliminarMascota(int idMascota) {
        int r = 0;
        String sql = "DELETE FROM Mascota WHERE idMascota=" + idMascota;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public int actualizarMascota(Mascota pet) {
        int r = 0;
        String sql = "UPDATE Mascota SET idDueno=?, nombreMascota=?, genero=?, tipo=?, raza=?, activo=? WHERE idMascota=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pet.getDueno().getIdDueno());
            ps.setString(2, pet.getNombreMascota());
            ps.setString(3, pet.getGenero());
            ps.setString(4, pet.getTipo());
            ps.setString(5, pet.getRaza());
            ps.setBoolean(6, pet.isActivo());
            ps.setInt(7, pet.getIdMascota());
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
}
