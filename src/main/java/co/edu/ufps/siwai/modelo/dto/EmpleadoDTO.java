/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;


/**
 *
 * @author MarlonGuerrero
 */
public class EmpleadoDTO implements Serializable {

    private String codigo, dni, nombre, apellido, contraseña, email, direccion, cargo;
    private String telefono;
    private String celular;
    private String fIngreso,fSalida;
    private short habilitado;
    private SucursalDTO sucursal;

    public EmpleadoDTO() {
        this.sucursal = new SucursalDTO();
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public String getfSalida() {
        return fSalida;
    }

    public void setfSalida(String fSalida) {
        this.fSalida = fSalida;
    }

    public short getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(short habilitado) {
        this.habilitado = habilitado;
    }

    public EmpleadoDTO(String codigo, String dni, String nombre, String apellido, String contraseña, String email, String direccion, String cargo, String sucursal, String telefono, String celular, String fIngreso) {
        this.codigo = codigo;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.email = email;
        this.direccion = direccion;
        this.cargo = cargo;
        this.sucursal = new SucursalDTO();
        this.sucursal.setCodigo(sucursal);
        this.telefono = telefono;
        this.celular = celular;
        this.fIngreso = fIngreso;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getfIngreso() {
        return fIngreso;
    }

    public void setfIngreso(String fIngreso) {
        this.fIngreso = fIngreso;
    }

}
