/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;

/**
 * Clase DTO que tiene la estrucutra de un proveedor de la tienda.
 * @author Alejandro Ramirez
 */
public class ProveedorDTO implements Serializable{
    
    private String codigo, nit, nombre, cuenta, tipoCuenta, 
            sitioWeb, nombreContacto, emailContacto, numCuenta, telContacto;
    
    /**
     * Coonstructor vacio.
     */
    public ProveedorDTO(){
    }

    /**
     * Constructor que pide todos los atributos del proveedor.
     * @param codigo Codigo del proveedor.
     * @param nit Nit del proveedor.
     * @param nombre Nombre del proveedor.
     * @param cuenta Cuenta del proveedor.
     * @param tipoCuenta Tipo de cuenta del proveedor.
     * @param sitioWeb URL del Sito Web del proveedor.
     * @param nombreContacto Nombre de la persona que sirve de intermediario con el proveedor.
     * @param emailContacto Email del contacto.
     * @param numCuenta Numero de cuenta del proveedor.
     * @param telContacto Telefono del contacto.
     */
    public ProveedorDTO(String codigo, String nit, String nombre, String cuenta, 
            String tipoCuenta, String sitioWeb, String nombreContacto, 
            String emailContacto, String numCuenta, String telContacto) {
        this.codigo = codigo;
        this.nit = nit;
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.tipoCuenta = tipoCuenta;
        this.sitioWeb = sitioWeb;
        this.nombreContacto = nombreContacto;
        this.emailContacto = emailContacto;
        this.numCuenta = numCuenta;
        this.telContacto = telContacto;
    }

    /**
     * Metodo que obtiene el codigo del proveedor.
     * @return String con el codigo del proveedor.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo que establece el codigo del proveedor.
     * @param codigo String con el codigo del proveedor.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que obtiene el NIT del proveedor.
     * @return String con el nit del proveedor.
     */
    public String getNit() {
        return nit;
    }

    /**
     * Metodo que establece el nit del proveedor.
     * @param nit String con el nit del proveedor.
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Metodo que obtiene el nombre del proveedor.
     * @return String con el nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre del proveedor.
     * @param nombre String con el nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que obtiene la cuenta del proveedor.
     * @return String con la cuenta del proveedor.
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Metodo que establece la cuenta del proveedor.
     * @param cuenta String con la cuenta del proveedor.
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Metodo que obtiene el tipo de cuenta del proveedor.
     * @return String con el el tipo de cuenta del proveedor.
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Metodo que establece el tipo de cuenta del proveedor.
     * @param tipoCuenta String con el tipo de cuenta del proveedor.
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Metodo que obtiene la URL del sitio web del proveedor.
     * @return String con la URL del sitio web del proveedor.
     */
    public String getSitioWeb() {
        return sitioWeb;
    }

    /**
     * Metodo que establece el URL del sitio web del proveedor.
     * @param sitioWeb String con el URL del sitio web del proveedor.
     */
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    /**
     * Metodo que obtiene el nombre del contacto.
     * @return String con el nombre del contacto.
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * Metodo que establece el nombre del contacto.
     * @param nombreContacto String con el nombre del contacto.
     */
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * Metodo que obtiene el email del contacto.
     * @return String con el email del contacto.
     */
    public String getEmailContacto() {
        return emailContacto;
    }

    /**
     * Metodo que establece el email del contacto.
     * @param emailContacto String con el email del contacto.
     */
    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    /**
     * Metodo que obtiene el numero de cuenta del proveedor.
     * @return String con el numero de cuenta del proveedor.
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Metodo que establece el numero de cuenta del proveedor.
     * @param numCuenta String con el numero de cuenta del proveedor.
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Metodo que obtiene el telefono del contacto.
     * @return String con el telefono del contacto.
     */
    public String getTelContacto() {
        return telContacto;
    }

    /**
     * Metodo que establece el telefono del contacto.
     * @param telContacto String con el telefono del contacto.
     */
    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }
    
}
