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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class UsuarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Usuario u = new Usuario();

    /*
    SE ELIMINÓ EL MÉTODO LISTAR YA QUE PARA AHORRAR CÓDIGO SE PUEDE USAR FILTRAR.
     */
    public int agregarUsuarios(Usuario user) {

        int r = 0;
        String sql = "INSERT INTO Usuario(nomUsuario, apellido1, apellido2, email, nombreUsuario, tipoUsuario, password, activo) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getTipoUsuario());
            ps.setString(7, user.getPassword());
            ps.setBoolean(8, user.isActivo());
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

    public int actualizarUsuario(Usuario user) {

        int r = 0;
        String sql = "UPDATE Usuario set nomUsuario=?, apellido1=?, apellido2=?, email=?, nombreUsuario=?, tipoUsuario=?, password=?, activo=?  WHERE idUsuario=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getTipoUsuario());
            ps.setString(7, user.getPassword());
            ps.setBoolean(8, user.isActivo());
            ps.setInt(9, user.getIdUsuario());
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

    public int eliminarUsuario(int id) {
        int r = 0;
        String sql = "DELETE FROM Usuario WHERE idUsuario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable tabla, String filtro) {

        String[] titulos = {"Id", "Nombre", "Apellido 1", "Apellido 2", "Email", "Nombre Usuario", "Tipo de Usuario", "Password", "Activo"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM Usuario WHERE nomUsuario LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idUsuario");
                registros[1] = rs.getString("nomUsuario");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("email");
                registros[5] = rs.getString("nombreUsuario");
                registros[6] = rs.getString("tipoUsuario");
                registros[7] = rs.getString("password");
                registros[8] = rs.getString("activo");
                model.addRow(registros);
            }
            tabla.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }

    }

    public boolean login(Usuario user) {
        String sql = "SELECT idUsuario, nombreUsuario, password, tipoUsuario FROM Usuario WHERE nombreUsuario = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombreUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {

                if (user.getPassword().equals(rs.getString(3)) && user.getTipoUsuario().equals(rs.getString(4))) {

                    user.setIdUsuario(rs.getInt(1));
                    user.setNombreUsuario(rs.getString(2));
                    user.setTipoUsuario(rs.getString(4));
                    return true;
                } else {
                    return false;
                }
            }

            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    public int cargarIdUsuario(String nombreUsuario) {

        int idUsuario = 0;
        String sql = "SELECT idUsuario FROM Usuario WHERE nombreUsuario = '" + nombreUsuario + "'";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            idUsuario = rs.getInt("idUsuario");
        } catch (SQLException e) {
        }

        return idUsuario;
    }

}
