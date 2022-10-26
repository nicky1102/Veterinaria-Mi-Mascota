/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.CitaDAO;
import Modelo.DuenoDAO;
import Vista.frmReportes;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nicol
 */
public class ControllerReportes implements ActionListener {
    
    frmReportes vistaReportes = new frmReportes();
    CitaDAO citadao = new CitaDAO();
    DuenoDAO duenoDAO = new DuenoDAO();
    
    public ControllerReportes(frmReportes frm) {
        this.vistaReportes.btnBuscarDueno.addActionListener(this);
        this.vistaReportes.btnImprimir.addActionListener(this);
        this.vistaReportes.btnBuscarFecha.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaReportes.btnBuscarDueno) {
            filtrarPorFecha(vistaReportes.tblDatos, vistaReportes.txtBuscarDueno.getText());
            
        }
        if (e.getSource() == vistaReportes.btnBuscarFecha) {
            filtrarTablaPorReportesDueno(vistaReportes.tblDatos, vistaReportes.jDateChooser1.getDate().toString());
        }
    }
    
    public void filtrarPorFecha(JTable table, String filtro) {
        citadao.filtrarTablaPorFecha(table, filtro);
    }
    
    public void filtrarTablaPorReportesDueno(JTable table, String filtro) {
        citadao.filtrarTablaPorReportesDueno(table, filtro);
    }
    
    public void iniciar() {
        filtrarPorFecha(vistaReportes.tblDatos, "");
    }
    
}
