/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;

/**
 * Clase DTO que tiene la estructura de un cliente de la tienda.
 * @author Alejandro Ramírez
 */
public class ClienteDTO implements Serializable{
    
    private String dni, nombre, apellido, direccion, email, telefono;
    private UbicacionDTO ubicacion;
    
    /**
     * Constructor sin parametros.
     */
    public ClienteDTO() {
        ubicacion = new UbicacionDTO();
    }

    /**
     * Constructor que pide como parametros todos los atributos del cliente.
     * @param dni Documento nacional de identificación del cliente.
     * @param nombre Nombres del cliente.
     * @param apellido Apellidos del cliente.
     * @param direccion Direccion de residencia del cliente.
     * @param email Correo electronico del cliente.
     * @param telefono Telefono o celular del cliente.
     * @param ciudad Id de la ciudad en donde vive el cliente.
     */
    public ClienteDTO(String dni, String nombre, String apellido, 
            String direccion, String email, String telefono, int ciudad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        ubicacion = new UbicacionDTO();
        ubicacion.setIdCiudad(ciudad);
    }
    
    /**
     * Metodo que retorna el documento nacional de identificacion del cliente.
     * @return String con el documento nacional de identificacion. 
     */
    public String getDni() {
        return dni;
    }

    /**
     * Metodo que establece el documento nacional de identificacion del cliente.
     * @param dni Nuevo dni del cliente.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Metodo que retorna el nombre(s) del cliente.
     * @return String con el nombre(s) del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre(s) del cliente.
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el apellido(s) del cliente.
     * @return String con el apellido(s) del cliente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo que establece el apellido(s) del cliente.
     * @param apellido Nuevo apellido del cliente.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo que retorna la direccion de residencia del cliente.
     * @return String con la direccion de residencia del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que establece la direccion de residencia del cliente.
     * @param direccion Nueva direccion del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo que retorna el correo electronico del cliente.
     * @return String con el correo elctronico del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo que establece el correo electronico del cliente.
     * @param email Nuevo correo electronico del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo que retorna el numero telefonico del cliente.
     * @return String con el numero telefonico del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo que establece el telefono del cliente.
     * @param telefono Nuevo telefono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo que obtiene el objeto UbicacionDTO del cliente.
     * @return UbicacionDTO.
     */
    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    /**
     * Metodo que establece el objeto UbicacionDTO del cliente.
     * @param dto UbicacionDTO.
     */
    public void setUbicacion(UbicacionDTO dto) {
        this.ubicacion = dto;
    }

}
