/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udb.modelo;

/**
 *
 * @author josed
 */
public class Cargo {

    public Cargo() {
    }
    
    private int idCargo;
    
    private String cargo;
    
    private String descripcionCargo;
    
    private boolean jefatura;

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public boolean isJefatura() {
        return jefatura;
    }

    public void setJefatura(boolean jefatura) {
        this.jefatura = jefatura;
    }

    public Cargo(int idCargo, String cargo, String descripcionCargo, boolean jefatura) {
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.descripcionCargo = descripcionCargo;
        this.jefatura = jefatura;
    }
    
    
    
}
