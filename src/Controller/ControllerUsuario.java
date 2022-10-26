/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.UsuarioDAO;
import javax.swing.table.DefaultTableModel;
import Vista.frmUsuario;
import Modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author nicol
 */
public class ControllerUsuario implements ActionListener {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = new Usuario();
    frmUsuario vistaUsuario = new frmUsuario();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerUsuario(frmUsuario frm) {
        this.vistaUsuario = frm;
        this.vistaUsuario.btnGuardar.addActionListener(this);
        this.vistaUsuario.btnCancelar.addActionListener(this);
        this.vistaUsuario.btnBuscar.addActionListener(this);
        this.vistaUsuario.btnEditar.addActionListener(this);
        this.vistaUsuario.btnEliminar.addActionListener(this);
        this.vistaUsuario.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaUsuario.btnGuardar) {
            if (vistaUsuario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un nombre");
            } else if (vistaUsuario.txtApellidoUsuario1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su primer apellido");
            } else if (vistaUsuario.txtApellidoUsuario2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su segundo apellido");
            } else if (vistaUsuario.txtEmailUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su email");
            } else if (vistaUsuario.cmbTipoUsuario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un tipo de usuario");
            } else if (vistaUsuario.txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar su password");
            } else {
                agregarUsuario();
            }
        }
        if (e.getSource() == vistaUsuario.btnEditar) {

            if (vistaUsuario.txtidUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un registro en la tabla");
            }  else if(vistaUsuario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un nombre");
            } else if (vistaUsuario.txtApellidoUsuario1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su primer apellido");
            } else if (vistaUsuario.txtApellidoUsuario2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su segundo apellido");
            } else if (vistaUsuario.txtEmailUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un su email");
            } else if (vistaUsuario.cmbTipoUsuario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un tipo de usuario");
            } else if (vistaUsuario.txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar su password");
            } else {
                
                actualizarUsuario();
            }

        }
        if (e.getSource() == vistaUsuario.btnCancelar) {
            limpiarCampos();
            filtarTablaPorNombre(vistaUsuario.tableUsuario, "");
        }
        if (e.getSource() == vistaUsuario.btnBuscar) {
            filtarTablaPorNombre(vistaUsuario.tableUsuario, vistaUsuario.txtBuscar.getText());
        }
        if (e.getSource() == vistaUsuario.btnEliminar) {
            eliminarUsuario();
        }
        if (e.getSource() == vistaUsuario.btnRefrescar) {
            filtarTablaPorNombre(vistaUsuario.tableUsuario, "");
        }
    }

    public void agregarUsuario() {
        String nombre = vistaUsuario.txtNombre.getText();
        String apellido1 = vistaUsuario.txtApellidoUsuario1.getText();
        String apellido2 = vistaUsuario.txtApellidoUsuario2.getText();
        String email = vistaUsuario.txtEmailUsuario.getText();
        String nombreUsuario = vistaUsuario.txtNombreUsuario.getText();
        String tipoUsuario = vistaUsuario.cmbTipoUsuario.getSelectedItem().toString();
        String password = vistaUsuario.txtPassword.getText();
        Boolean activo = vistaUsuario.cbxActivo.isSelected();
        u.setNombre(nombre);
        u.setApellido1(apellido1);
        u.setApellido2(apellido2);
        u.setEmail(email);
        u.setNombreUsuario(nombreUsuario);
        u.setTipoUsuario(tipoUsuario);
        u.setPassword(password);
        u.setActivo(activo);

        int r = dao.agregarUsuarios(u);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario registrado con exito");
            limpiarCampos();
            filtarTablaPorNombre(vistaUsuario.tableUsuario, "");
        } else {
            JOptionPane.showMessageDialog(vistaUsuario, "El Usuario No fue Registrado con exito");
        }
    }

    /*
    SE ELIMINÓ EL MÉTODO LISTAR YA QUE PARA AHORRAR CÓDIGO SE PUEDE USAR FILTRAR
     */
    public void actualizarUsuario() {

        if (vistaUsuario.txtidUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un registro en la tabla");
        } else {
            int idUsuario = Integer.parseInt(vistaUsuario.txtidUsuario.getText());
            String nombre = vistaUsuario.txtNombre.getText();
            String apellido1 = vistaUsuario.txtApellidoUsuario1.getText();
            String apellido2 = vistaUsuario.txtApellidoUsuario2.getText();
            String email = vistaUsuario.txtEmailUsuario.getText();
            String nombreUsuario = vistaUsuario.txtNombreUsuario.getText();
            String tipoUsuario = vistaUsuario.cmbTipoUsuario.getSelectedItem().toString();
            String password = vistaUsuario.txtPassword.getText();
            Boolean activo = vistaUsuario.cbxActivo.isSelected();
            u.setIdUsuario(idUsuario);
            u.setNombre(nombre);
            u.setApellido1(apellido1);
            u.setApellido2(apellido2);
            u.setEmail(email);
            u.setNombreUsuario(nombreUsuario);
            u.setTipoUsuario(tipoUsuario);
            u.setPassword(password);
            u.setActivo(activo);
            int r = dao.actualizarUsuario(u);

            if (r == 1) {
                JOptionPane.showMessageDialog(vistaUsuario, "El Usuario fue actulizado con éxito");
                limpiarCampos();
                filtarTablaPorNombre(vistaUsuario.tableUsuario, "");

            } else {
                JOptionPane.showMessageDialog(vistaUsuario, "Usuario no actulizado con éxito");
            }

        }

    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void filtarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);

    }

    public void eliminarUsuario() {
        int fila = vistaUsuario.tableUsuario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuario.tableUsuario, "Debe seleccionar un registro");

        } else {
            int id = Integer.parseInt(vistaUsuario.tableUsuario.getValueAt(fila, 0).toString());
            dao.eliminarUsuario(id);
            JOptionPane.showMessageDialog(vistaUsuario.tableUsuario, "Usuario Eliminado con exito ");
            limpiarCampos();
            filtarTablaPorNombre(vistaUsuario.tableUsuario, "");
        }
    }

    public void limpiarCampos() {
        vistaUsuario.txtidUsuario.setText("");
        vistaUsuario.txtNombre.setText("");
        vistaUsuario.txtApellidoUsuario1.setText("");
        vistaUsuario.txtApellidoUsuario2.setText("");
        vistaUsuario.txtEmailUsuario.setText("");
        vistaUsuario.txtNombreUsuario.setText("");
        vistaUsuario.cmbTipoUsuario.setSelectedIndex(0);
        vistaUsuario.txtPassword.setText("");
        vistaUsuario.cbxActivo.setSelected(false);
    }

    public void iniciar() {
        limpiarCampos();
        filtarTablaPorNombre(vistaUsuario.tableUsuario, "");
    }

}
