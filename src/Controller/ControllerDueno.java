/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Dueno;
import Modelo.DuenoDAO;
import Vista.frmDueno;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author nicol
 */
public class ControllerDueno implements ActionListener {

    DuenoDAO dao = new DuenoDAO();
    Dueno d = new Dueno();
    frmDueno vistaDueno = new frmDueno();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerDueno(frmDueno frm) {
        this.vistaDueno = frm;
        this.vistaDueno.btnBuscar.addActionListener(this);
        this.vistaDueno.btnCancelar.addActionListener(this);
        this.vistaDueno.btnEliminar.addActionListener(this);
        this.vistaDueno.btnGuardar.addActionListener(this);
        this.vistaDueno.btnModificar.addActionListener(this);
        this.vistaDueno.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaDueno.btnGuardar) {

            if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un nombre para proceder con el registro");
            } else if (vistaDueno.txtApellidoI.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe digitar su primer apellido");
            } else if (vistaDueno.txtApellidoII.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe digitar un segundo apellido");
            } else if (vistaDueno.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número de cédula");
            } else if (vistaDueno.cmbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe indicar su género");
            } else if (vistaDueno.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe digitar un correo");
            } else if (vistaDueno.txtTel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número de télefono");
            } else if (vistaDueno.txaDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar una dirección de domicilio");
            } else {
                agregarDueno();
            }
        }
        if (e.getSource() == vistaDueno.btnModificar) {

            if (vistaDueno.txtIdDueno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe selecionar un registro en la tabla");
            } else if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un nombre para proceder");
            } else if (vistaDueno.txtApellidoI.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe digitar su primer apellido");
            } else if (vistaDueno.txtApellidoII.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe digitar un segundo apellido");
            } else if (vistaDueno.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número de cédula");
            } else if (vistaDueno.cmbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe indicar su género");
            } else if (vistaDueno.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un correo para notificarle sobre nuevas ofertas");
            } else if (vistaDueno.txtTel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número de télefono para completar la modificación");
            } else if (vistaDueno.txaDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar una dirección de domicilio");
            } else {
                actualizarDueno();
            }
        }
        if (e.getSource() == vistaDueno.btnCancelar) {
            limpiarCampos();
        }

        if (e.getSource() == vistaDueno.btnRefrescar) {
            listarDueno(vistaDueno.tblDatos);
        }

        if (e.getSource() == vistaDueno.btnEliminar) {
            eliminarDueno();
            listarDueno(vistaDueno.tblDatos);
        }
        if (e.getSource() == vistaDueno.btnBuscar) {
            filtrarTablaPorNombreDueno(vistaDueno.tblDatos, vistaDueno.txtBuscar.getText());
        }

    }

    public void agregarDueno() {

        String nombre = vistaDueno.txtNombre.getText();
        String apellidoI = vistaDueno.txtApellidoI.getText();
        String apellidoII = vistaDueno.txtApellidoII.getText();
        String cedula = vistaDueno.txtCedula.getText();
        String genero = vistaDueno.cmbGenero.getSelectedItem().toString();
        String email = vistaDueno.txtEmail.getText();
        String telefono = vistaDueno.txtTel.getText();
        String direccion = vistaDueno.txaDireccion.getText();
        d.setNombre(nombre);
        d.setApellidoI(apellidoI);
        d.setApellidoII(apellidoII);
        d.setCedula(cedula);
        d.setGenero(genero);
        d.setEmail(email);
        d.setTel(telefono);
        d.setDireccion(direccion);
        int r = dao.agregarDueno(d);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño registrado con éxito");
            limpiarCampos();
            listarDueno(vistaDueno.tblDatos);
        } else {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño no registrado con éxito");
        }
    }

    public void actualizarDueno() {
        if (vistaDueno.txtIdDueno.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaDueno, "Porfavor seleccione un registro en la tabla");
        } else {
            int idDueno = Integer.parseInt(vistaDueno.txtIdDueno.getText());
            String nombre = vistaDueno.txtNombre.getText();
            String apellidoI = vistaDueno.txtApellidoI.getText();
            String apellidoII = vistaDueno.txtApellidoII.getText();
            String cedula = vistaDueno.txtCedula.getText();
            String genero = vistaDueno.cmbGenero.getSelectedItem().toString();
            String email = vistaDueno.txtEmail.getText();
            String telefono = vistaDueno.txtTel.getText();
            String direccion = vistaDueno.txaDireccion.getText();
            d.setIdDueno(idDueno);
            d.setNombre(nombre);
            d.setApellidoI(apellidoI);
            d.setApellidoII(apellidoII);
            d.setCedula(cedula);
            d.setGenero(genero);
            d.setEmail(email);
            d.setTel(telefono);
            d.setDireccion(direccion);
            int r = dao.actualizarDueno(d);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaDueno, "Dueño actualizado con éxito");
                limpiarCampos();
                listarDueno(vistaDueno.tblDatos);

            } else {
                JOptionPane.showMessageDialog(vistaDueno, "Dueño no actualizado con éxito");
            }
        }
    }

    public void eliminarDueno() {
        int fila = vistaDueno.tblDatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDueno, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaDueno.tblDatos.getValueAt(fila, 0).toString());
            dao.eliminarDueno(id);
            JOptionPane.showMessageDialog(vistaDueno, "Dueño eliminado con éxito");
            limpiarCampos();
        }
    }

    public void listarDueno(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        limpiarTabla(tabla, modelo);
        List<Dueno> lista = dao.listarDueno();
        Object[] objeto = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdDueno();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellidoI();
            objeto[3] = lista.get(i).getApellidoII();
            objeto[4] = lista.get(i).getCedula();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getEmail();
            objeto[7] = lista.get(i).getTel();
            objeto[8] = lista.get(i).getDireccion();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiarCampos() {
        vistaDueno.txtIdDueno.setText("");
        vistaDueno.txtNombre.setText("");
        vistaDueno.txtApellidoI.setText("");
        vistaDueno.txtApellidoII.setText("");
        vistaDueno.txtCedula.setText("");
        vistaDueno.cmbGenero.setSelectedIndex(0);
        vistaDueno.txtEmail.setText("");
        vistaDueno.txtTel.setText("");
        vistaDueno.txaDireccion.setText("");
    }

    public void filtrarTablaPorNombreDueno(JTable table, String filtro) {

        dao.filtrarTablaPorNombreDueno(table, filtro);

    }

    public void iniciar() {
        limpiarCampos();
        listarDueno(vistaDueno.tblDatos);

    }
}
