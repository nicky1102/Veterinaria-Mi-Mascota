/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class CitaDAO {

    PreparedStatement ps;
    Connection con;
    Conexion conectar = new Conexion();
    Cita cita = new Cita();
    ResultSet rs;

    public int agregarCita(Cita citas) {

        int r = 0;
        String sql = "INSERT INTO Cita(idDueno, idVeterinario, idUsuario, asunto, fecha, hora, fechaCreacion) VALUES (?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, citas.getDueno().getIdDueno());
            ps.setInt(2, citas.getVeterinario().getIdVeterinario());
            ps.setInt(3, citas.getUsuario().getIdUsuario());
            ps.setString(4, citas.getAsunto());
            ps.setString(5, citas.getFechaCita());
            ps.setString(6, citas.getHoraCita());
            ps.setDate(7, citas.getFechaCreacion());
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

    public int eliminarCita(int idCita) {
        int r = 0;
        String sql = "DELETE FROM Cita WHERE idCita=" + idCita;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    //Para reportes
    public void filtrarTablaPorReportesDueno(JTable table, String filtro) {
        String[] titulos = {"idCita", "idDueno", "idVeterinario", "idUsuario", "asunto", "fecha", "hora", "fechaCreacion"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM Cita WHERE asunto  LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idCita");
                registros[1] = rs.getString("idDueno");
                registros[2] = rs.getString("idVeterinario");
                registros[3] = rs.getString("idUsuario");
                registros[4] = rs.getString("asunto");
                registros[5] = rs.getString("fecha");
                registros[6] = rs.getString("hora");
                registros[7] = rs.getString("fechaCreacion");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS" + e.getMessage());
        }
    }

    //Para reportes
    public void filtrarTablaPorFecha(JTable table, String filtro) {
        String[] titulos = {"idCita", "idDueno", "idVeterinario", "idUsuario", "asunto", "fecha", "hora", "fechaCreacion"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM Cita WHERE fecha  LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idCita");
                registros[1] = rs.getString("idDueno");
                registros[2] = rs.getString("idVeterinario");
                registros[3] = rs.getString("idUsuario");
                registros[4] = rs.getString("asunto");
                registros[5] = rs.getString("fecha");
                registros[6] = rs.getString("hora");
                registros[7] = rs.getString("fechaCreacion");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS" + e.getMessage());

        }
    }
}
