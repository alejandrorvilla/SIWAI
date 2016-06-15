/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

/**
 * Clase que contiene el pais y la ciudad en la que se encuentra ubicada la entidad que instancia esta clase.
 * @author Alejandro Ramirez.
 */
public class UbicacionDTO {
    
    private String codPais, nomPais, nomCiudad;
    private int idCiudad;

    /**
     * Constructor.
     * @param codPais Código del País.
     * @param nomPais Nombre del País.
     * @param nomCiudad Nombre de la ciudad.
     * @param idCiudad Id de la ciudad.
     */
    public UbicacionDTO(String codPais, String nomPais, String nomCiudad, int idCiudad) {
        this.codPais = codPais;
        this.nomPais = nomPais;
        this.nomCiudad = nomCiudad;
        this.idCiudad = idCiudad;
    }

    /**
     * Constructor sin parametros.
     */
    public UbicacionDTO() {
    }

    /**
     * Metodo que obtiene el código del país.
     * @return String con el código del país.
     */
    public String getCodPais() {
        return codPais;
    }

    /**
     * Metodo que establece el código del país.
     * @param codPais String con el código del país.
     */
    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    /**
     * Metodo que obtiene el nombre del país.
     * @return String con el nombre del país.
     */
    public String getNomPais() {
        return nomPais;
    }

    /**
     * Metodo que establece el nombre del país.
     * @param nomPais String con el nombre del país.
     */
    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    /**
     * Metodo que obtiene el nombre de la ciudad.
     * @return String con el nombre de la ciudad.
     */
    public String getNomCiudad() {
        return nomCiudad;
    }

    /**
     * Metodo que establece el nombre de la ciudad.
     * @param nomCiudad String con el nombre de la ciudad.
     */
    public void setNomCiudad(String nomCiudad) {
        this.nomCiudad = nomCiudad;
    }

    /**
     * Metodo que obtiene la id de la ciudad.
     * @return String con la id de la ciudad.
     */
    public int getIdCiudad() {
        return idCiudad;
    }

    /**
     * Metodo que establece el id de la ciudad.
     * @param idCiudad String con el id de la ciudad.
     */
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
 
}