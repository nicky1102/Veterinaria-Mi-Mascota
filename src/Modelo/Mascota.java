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
public class Mascota {
    private int idMascota;
    private Dueno dueno;
    private String nombreMascota;
    private String genero;
    private String tipo;
    private String raza;
    private boolean activo;

    public Mascota() {
    }

    public Mascota(int idMascota, Dueno dueno, String nombreMascota, String genero, String tipo, String raza, boolean activo) {
        this.idMascota = idMascota;
        this.dueno = dueno;
        this.nombreMascota = nombreMascota;
        this.genero = genero;
        this.tipo = tipo;
        this.raza = raza;
        this.activo = activo;
    }
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    
    
     
    
}
