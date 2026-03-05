/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udb.modelo;

/**
 *
 * @author josed
 */
public class Departamento {

    public Departamento() {
    }
    
    
    
    private String nombreDepartamento;
    
    private int idDepartamento;
    
    private String descripcionDepartamento;
    

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public Departamento(String nombreDepartamento, int idDepartamento, String descripcionDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.idDepartamento = idDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }
   
 
    
}
