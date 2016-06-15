/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.norsoft.prueba.modelo.objeto;

import java.util.Date;

/**
 *
 * @author Alejandro Ramirez
 */
public class Persona {
    
    private String nombre, apellido, estadoCivil, correo, departamento, ciudad, nacimientoSt;
    private Date nacimiento;
    private int sueldo;

    public Persona(String nombre, String apellido, String estadoCivil, 
            String correo, String departamento, String ciudad, Date nacimiento, int sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoCivil = estadoCivil;
        this.correo = correo;
        this.departamento = departamento;
        this.nacimientoSt = String.format("%td/%tm/%tY", nacimiento, nacimiento, nacimiento);
        this.ciudad = ciudad;
        this.nacimiento = nacimiento;
        this.sueldo = sueldo;
    }

    public String getNacimientoSt() {
        return nacimientoSt;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
    
}
