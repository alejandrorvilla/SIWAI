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
public class SucursalDTO implements Serializable {

    private String codigo, nombre, email, paginaWeb, direccion;
    private int telefono;
    private UbicacionDTO ubicacion;

    public SucursalDTO() {
        this.ubicacion = new UbicacionDTO();
    }

    /**
     * Constructor que pide como parametros todos los atributos de la sucursal.
     *
     * @param codigo Codigo de la sucursal
     * @param nombre Nombre de la sucursal
     * @param email Email de la sucursal
     * @param paginaWeb Pagina web de la sucursal
     * @param direccion Direccion de la sucursal
     * @param ciudad Ciudad donde se encuentra la sucursal
     * @param pais Pais donde se encuentra la sucursal
     * @param telefono Telefono de la sucursal
     */
    public SucursalDTO(String codigo, String nombre, String email, String paginaWeb, String direccion, String ciudad, String pais, int telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.paginaWeb = paginaWeb;
        this.direccion = direccion;
        this.ubicacion = new UbicacionDTO();
        this.ubicacion.setCodPais(pais);
        this.ubicacion.setIdCiudad(Integer.parseInt(ciudad));
        this.telefono = telefono;
    }

    /**
     * Metodo que retorna el codigo de la sucursal
     *
     * @return String con el codigo de la surcursal
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo que establece el codigo de la sucursal
     *
     * @param codigo Codigo de la sucursal
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que retorna el nombre de la sucursal
     *
     * @return String con el nombre de la surcursal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre de la sucursal
     *
     * @param nombre Nombre de la sucursal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el email de la sucursal
     *
     * @return String con el email de la surcursal
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo que establece el email de la sucursal
     *
     * @param email Email de la sucursal
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo que retorna la pagina web de la sucursal
     *
     * @return String con la pagina web de la surcursal
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * Metodo que establece la pagina web de la sucursal
     *
     * @param paginaWeb Pagina web de la sucursal
     */
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Metodo que retorna la direccion de la sucursal
     *
     * @return String con la direccion de la surcursal
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que establece la direccion de la sucursal
     *
     * @param direccion Direccion de la sucursal
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo que obtiene la ubicacion donde se encuentra la sucursal
     *
     * @return
     */
    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    /**
     * Metodo que establece la ubicacion donde se encuentra la sucursal
     *
     * @param ubicacion
     */
    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Metodo que retorna el telefono de la sucursal
     *
     * @return String con el telefono de la surcursal
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Metodo que establece el telefono de la sucursal
     *
     * @param telefono Telefono de la sucursal
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
