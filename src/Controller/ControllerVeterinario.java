/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Veterinario;
import Modelo.VeterinarioDAO;
import Vista.frmVeterinario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class ControllerVeterinario implements ActionListener {

    VeterinarioDAO dao = new VeterinarioDAO();
    Veterinario v = new Veterinario();
    frmVeterinario vistaVeterinario = new frmVeterinario();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerVeterinario(frmVeterinario frm) {
        this.vistaVeterinario = frm;
        this.vistaVeterinario.btnGuardar.addActionListener(this);
        this.vistaVeterinario.btnCancelar.addActionListener(this);
        this.vistaVeterinario.btnEliminar.addActionListener(this);
        this.vistaVeterinario.btnBuscar.addActionListener(this);
        this.vistaVeterinario.btnLimpiar.addActionListener(this);
        this.vistaVeterinario.btnEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaVeterinario.btnGuardar) {
            if (vistaVeterinario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un nombre");
            } else if (vistaVeterinario.txtApellido1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar el primer apellido");
            } else if (vistaVeterinario.txtApellido2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar el segundo apellido");
            } else if (vistaVeterinario.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar una cédula");
            } else if (vistaVeterinario.txtCodProfesional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar el Código Profesional");
            } else if (vistaVeterinario.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un email");
            } else if (vistaVeterinario.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un número teléfono");
            } else if (vistaVeterinario.txaDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar una dirección");
            } else {
                agregarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinario.btnBuscar) {
            filtrarTablaPorNombre(vistaVeterinario.tblDatos, vistaVeterinario.txtBuscar.getText());
        }
        if (e.getSource() == vistaVeterinario.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaVeterinario.btnLimpiar) {
            limpiarCampos();
            filtrarTablaPorNombre(vistaVeterinario.tblDatos, "");
        }
        if (e.getSource() == vistaVeterinario.btnEliminar) {
            if (vistaVeterinario.txtIdVeterinario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe seleccionar un registro de la tabla");
            } else {
                eliminarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinario.btnEditar) {
            if (vistaVeterinario.txtIdVeterinario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe seleccionar un registro de la tabla");
            } else if (vistaVeterinario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe ingresar un nombre");
            } else if (vistaVeterinario.txtApellido1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe ingresar un primer apellido");
            } else if (vistaVeterinario.txtApellido2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un segundo apellido");
            } else if (vistaVeterinario.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar una cédula");
            } else if (vistaVeterinario.txtCodProfesional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar el Código Profesional");
            } else if (vistaVeterinario.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un email");
            } else if (vistaVeterinario.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar un número de télefono");
            } else if (vistaVeterinario.txaDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe de ingresar una direccion");
            } else {
                actualizarVeterinario();

            }

        }

    }

    public void agregarVeterinario() {
        String nombre = vistaVeterinario.txtNombre.getText();
        String apellido1 = vistaVeterinario.txtApellido1.getText();
        String apellido2 = vistaVeterinario.txtApellido2.getText();
        String cedula = vistaVeterinario.txtCedula.getText();
        String codProfesional = vistaVeterinario.txtCodProfesional.getText();
        String email = vistaVeterinario.txtEmail.getText();
        String telefono = vistaVeterinario.txtTelefono.getText();
        String direccion = vistaVeterinario.txaDireccion.getText();
        Boolean activo = vistaVeterinario.cbxActivo.isSelected();
        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setCedula(cedula);
        v.setCodProfesional(codProfesional);
        v.setEmail(email);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        v.setActivo(activo);

        int r = dao.agregarVeterinario(v);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaVeterinario.tblDatos, "");
        } else {
            JOptionPane.showMessageDialog(vistaVeterinario, "El veterinario no fue registrado");

        }

    }

    public void actualizarVeterinario() {

        if (vistaVeterinario.txtIdVeterinario.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaVeterinario, "Por favor seleccione un registro en la tabla");
        } else {
            int idVeterinario = Integer.parseInt(vistaVeterinario.txtIdVeterinario.getText());
            String nombre = vistaVeterinario.txtNombre.getText();
            String apellido1 = vistaVeterinario.txtApellido1.getText();
            String apellido2 = vistaVeterinario.txtApellido2.getText();
            String cedula = vistaVeterinario.txtCedula.getText();
            String codProfesional = vistaVeterinario.txtCodProfesional.getText();
            String email = vistaVeterinario.txtEmail.getText();
            String telefono = vistaVeterinario.txtTelefono.getText();
            String direccion = vistaVeterinario.txaDireccion.getText();
            Boolean activo = vistaVeterinario.cbxActivo.isSelected();
            v.setIdVeterinario(idVeterinario);
            v.setNombre(nombre);
            v.setApellido1(apellido1);
            v.setApellido2(apellido2);
            v.setCedula(cedula);
            v.setCodProfesional(codProfesional);
            v.setEmail(email);
            v.setTelefono(telefono);
            v.setDireccion(direccion);
            v.setActivo(activo);
            int r = dao.actualizarVeterinario(v);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario actualizado con exito");
                limpiarCampos();
                filtrarTablaPorNombre(vistaVeterinario.tblDatos, "");
            } else {
                JOptionPane.showMessageDialog(vistaVeterinario, "El veterinario no fue actualizado");

            }

        }

    }

    public void eliminarVeterinario() {
        int fila = vistaVeterinario.tblDatos.getSelectedRow();
        if (fila == 1) {
            JOptionPane.showMessageDialog(vistaVeterinario.tblDatos, "Debe seleccionar un registro para eliminar ");
        } else {
            int id = Integer.parseInt(vistaVeterinario.tblDatos.getValueAt(fila, 0).toString());
            dao.eliminarVeterinario(id);
            JOptionPane.showMessageDialog(vistaVeterinario.tblDatos, "Veterinario eliminado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaVeterinario.tblDatos, "");

        }
    }

    /*
    SE ELIMINÓ EL MÉTODO LISTAR YA QUE PARA AHORRAR CÓDIGO SE PUEDE USAR FILTRAR.
     */
    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);

    }

    public void limpiarCampos() {
        vistaVeterinario.txtIdVeterinario.setText("");
        vistaVeterinario.txtNombre.setText("");
        vistaVeterinario.txtApellido1.setText("");
        vistaVeterinario.txtApellido2.setText("");
        vistaVeterinario.txtCedula.setText("");
        vistaVeterinario.txtCodProfesional.setText("");
        vistaVeterinario.txtEmail.setText("");
        vistaVeterinario.txtTelefono.setText("");
        vistaVeterinario.txaDireccion.setText("");
        vistaVeterinario.cbxActivo.setSelected(false);

    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaVeterinario.tblDatos, "");
    }

}
