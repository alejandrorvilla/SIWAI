/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dto.UbicacionDTO;
import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Clase de accesso a los datos de la tabla pais y ciudad.
 * @author Alejandro Ramirez
 */
public class DAOUbicacion {
    
    private Connection conn;
    
    /**
     * Metodo que obtiene el codigo y nombre de los paises.
     * @return ArrayList de UbicacionDTO.
     * @throws Exception Si existe un error en la conexion.
     */
    public ArrayList<UbicacionDTO> obtenerPaises() throws Exception{
        conn = Conexion.generarConexion();
        ArrayList<UbicacionDTO> dtos = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT cod_pais, nom_pais"
                + " FROM tbl_pais ORDER BY nom_pais");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            UbicacionDTO dto = new UbicacionDTO();
            dto.setCodPais(rs.getString(1));
            dto.setNomPais(rs.getString(2));
            dtos.add(dto);
        }
        return dtos;
    }
    
    /**
     * Metodo que obtiene los datos de las ciudades de un pais.
     * @param pais Codigo del pais.
     * @return ArrayList de UbicacionDTO.
     * @throws Exception Si existe un error en la conexion.
     */
    public ArrayList<UbicacionDTO> obtenerCiudades(String pais) throws Exception{
        conn = Conexion.generarConexion();
        ArrayList<UbicacionDTO> dtos = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT id_ciudad, nom_ciudad,"
                + " cod_pais FROM tbl_ciudad WHERE cod_pais = ? ORDER BY nom_ciudad");
        stmt.setString(1, pais);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            UbicacionDTO dto = new UbicacionDTO();
            dto.setIdCiudad(rs.getInt(1));
            dto.setNomCiudad(rs.getString(2));
            dto.setCodPais(rs.getString(3));
            dtos.add(dto);
        }
        return dtos;
    }
}
