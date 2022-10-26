/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Helpers.Helpers;
import Modelo.Cita;
import Modelo.CitaDAO;
import Modelo.Dueno;
import Modelo.DuenoDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.Veterinario;
import Modelo.VeterinarioDAO;
import Vista.frmRegistroCitas;
import Vista.frmVentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author nicol
 */
public class ControllerCita implements ActionListener{

    Helpers help = new Helpers();
    
    Cita cita = new Cita();
    Dueno dueno = new Dueno();
    Veterinario veterinario = new Veterinario();
    Usuario usuario = new Usuario();
    
    CitaDAO citaDAO = new CitaDAO();
    DuenoDAO duenoDAO = new DuenoDAO();
    VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    frmRegistroCitas vistaCitas = new frmRegistroCitas();
    
    public ControllerCita(frmRegistroCitas registro){
    
        this.vistaCitas = registro;
        this.vistaCitas.btnGuardar.addActionListener(this);
        this.vistaCitas.btnCancelar.addActionListener(this);
  
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaCitas.btnGuardar) {
            if (vistaCitas.cmbNomVeterinario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe seleccionar una mascota");
            } else if (vistaCitas.cmbNomDueno.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe seleccionar un dueño");
            }else if (vistaCitas.jdcFechaCita.getDate() == null) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe indicar la fecha de la cita");
            }else if (vistaCitas.txaAsunto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe indicar la causa de la cita");
            }else{
                try {                   
                    agregarCita();
                    limpiarCampos();
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerCita.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
        }
        if (e.getSource() == vistaCitas.btnCancelar ) {
            limpiarCampos();
        }
     
    }
    
    
    public void agregarCita() throws ParseException{
    
        Integer idDueno = Integer.valueOf(vistaCitas.txtIdDueno.getText());
        Integer idVeterinario = Integer.valueOf(vistaCitas.txtIdVeterinario.getText());
        Integer idUsuario = Integer.valueOf(frmVentanaPrincipal.lblIdUsuario.getText());
        String asunto = vistaCitas.txaAsunto.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(vistaCitas.jdcFechaCita.getDate());
        String fechaCita = date;
        String horaCita = vistaCitas.txtHora.getText();
        dueno.setIdDueno(idDueno);
        veterinario.setIdVeterinario(idVeterinario);
        usuario.setIdUsuario(idUsuario);
        cita.setDueno(dueno);
        cita.setVeterinario(veterinario);
        cita.setUsuario(usuario);
        cita.setAsunto(asunto);
        cita.setFechaCita(fechaCita);
        cita.setHoraCita(horaCita);
        cita.setFechaCreacion(help.fechaActual());
        cita = new Cita(0, dueno, veterinario, usuario, asunto, fechaCita, horaCita, fechaCreacion);
        int r = citaDAO.agregarCita(cita);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaCitas, "Cita registrada con éxito");    
            limpiarCampos();
        }
        
    }

    public void cargarComboVeterinario() {
        veterinarioDAO.cargarComboVeterinario(vistaCitas.cmbNomVeterinario);
    }
        
    public void cargarComboDueno() {
        duenoDAO.cargarComboDueno(vistaCitas.cmbNomDueno);
    }

    public void iniciar() {
        limpiarCampos();
    }

    public void limpiarCampos(){
        vistaCitas.txtIdVeterinario.setText("");
        vistaCitas.txtIdDueno.setText("");
        vistaCitas.jdcFechaCita.setCalendar(null);
        vistaCitas.txtHora.setText("");
        vistaCitas.txaAsunto.setText("");
        cargarComboVeterinario();
        cargarComboDueno();

    }
        
    
}
