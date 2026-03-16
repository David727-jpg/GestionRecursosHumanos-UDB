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
public class Empleado {

    public Empleado() {
    }

    private int idEmpleado;
    private String duiEmpleado;
    private String nombreEmpleado;
    private String usuarioEmpleado;
    private String telefonoEmpleado;
    private String correoEmpleado;
    private Date fechadenacimientoEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDuiEmpleado() {
        return duiEmpleado;
    }

    public void setDuiEmpleado(String duiEmpleado) {
        this.duiEmpleado = duiEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getUsuarioEmpleado() {
        return usuarioEmpleado;
    }

    public void setUsuarioEmpleado(String usuarioEmpleado) {
        this.usuarioEmpleado = usuarioEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public Date getFechadenacimientoEmpleado() {
        return fechadenacimientoEmpleado;
    }

    public void setFechadenacimientoEmpleado(Date fechadenacimientoEmpleado) {
        this.fechadenacimientoEmpleado = fechadenacimientoEmpleado;
    }

    public Empleado(int idEmpleado, String duiEmpleado, String nombreEmpleado, String usuarioEmpleado,
            String telefonoEmpleado, String correoEmpleado, Date fechadenacimientoEmpleado, Departamento departamento) {
        this.idEmpleado = idEmpleado;
        this.duiEmpleado = duiEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.usuarioEmpleado = usuarioEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.fechadenacimientoEmpleado = fechadenacimientoEmpleado;
    }
}
