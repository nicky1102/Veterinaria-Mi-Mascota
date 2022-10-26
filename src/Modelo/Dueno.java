/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author nicol
 */
public class Dueno {
    
    private int idDueno;
    private String nombre;
    private String apellidoI;
    private String apellidoII;
    private String cedula;
    private String genero;
    private String email;
    private String tel;
    private String direccion;

    public Dueno() {
    }

    public Dueno(int idDueno, String nombre, String apellidoI, String apellidoII, String cedula, String genero, String email, String tel, String direccion) {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.apellidoI = apellidoI;
        this.apellidoII = apellidoII;
        this.cedula = cedula;
        this.genero = genero;
        this.email = email;
        this.tel = tel;
        this.direccion = direccion;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoI() {
        return apellidoI;
    }

    public void setApellidoI(String apellidoI) {
        this.apellidoI = apellidoI;
    }

    public String getApellidoII() {
        return apellidoII;
    }

    public void setApellidoII(String apellidoII) {
        this.apellidoII = apellidoII;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
            
            
}
