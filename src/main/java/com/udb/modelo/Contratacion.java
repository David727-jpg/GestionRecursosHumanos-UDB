/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udb.modelo;

import java.util.Date;

/**
 *
 * @author josed
 */
public class Contratacion {

    public Contratacion() {
    }
    
    
    private int idContratacion;
            
    private int idDepartamento; 
            
    private int idEmpleado;
             
    private int idCargo;
            
    private int idTipoContratacion;
            
    private Date fechaDeContratacion;
    
    private double salario;
    
    private boolean estado;
    
    private Departamento departamento;
    
    private Empleado empleado;
    
    private Cargo cargo;
    
    private TipoContratacion tipoContratacion;

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Date getFechaDeContratacion() {
        return fechaDeContratacion;
    }

    public void setFechaDeContratacion(Date fechaDeContratacion) {
        this.fechaDeContratacion = fechaDeContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public TipoContratacion getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacion tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Contratacion(int idContratacion, int idDepartamento, int idEmpleado, int idCargo, int idTipoContratacion, Date fechaDeContratacion, double salario, boolean estado, Departamento departamento, Empleado empleado, Cargo cargo, TipoContratacion tipoContratacion) {
        this.idContratacion = idContratacion;
        this.idDepartamento = idDepartamento;
        this.idEmpleado = idEmpleado;
        this.idCargo = idCargo;
        this.idTipoContratacion = idTipoContratacion;
        this.fechaDeContratacion = fechaDeContratacion;
        this.salario = salario;
        this.estado = estado;
        this.departamento = departamento;
        this.empleado = empleado;
        this.cargo = cargo;
        this.tipoContratacion = tipoContratacion;
    }
    
    
    
}
