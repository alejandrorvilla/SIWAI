/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import co.edu.ufps.siwai.modelo.dto.SucursalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MarlonGuerrero
 */
public class DAOSucursal {

    private Connection conn;

    /**
     * Metodo que se conecta a la base de datos y registra los datos de una
     * sucursal.
     *
     * @param dto SucursalDTO con los datos de una sucursal.
     * @return true si se registro, false si no lo hizo.
     * @throws java.lang.Exception Exception originada por fallo en la conexion
     * o error al insertar la sucursal.
     */
    public boolean registrarSucursal(SucursalDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_sucursal "
                    + "(cod_sucursal, nom_sucursal, tel_sucursal, email_sucursal, "
                    + "pag_sucursal, dir_sucursal, ciudad_sucursal)"
                    + " values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, dto.getCodigo());
            stmt.setString(2, dto.getNombre());
            stmt.setInt(3, dto.getTelefono());
            stmt.setString(4, dto.getEmail());
            stmt.setString(5, dto.getPaginaWeb());
            stmt.setString(6, dto.getDireccion());
            stmt.setInt(7, dto.getUbicacion().getIdCiudad());

            try {
                exito = stmt.executeUpdate() > 0;
            } catch (SQLException ex) {

            } finally {
                conn.close();
                stmt.close();
            }
        }
        return exito;
    }

    public ArrayList<SucursalDTO> consultarSucursal(String buscarPor, String informacion) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<SucursalDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT tbl_sucursal.cod_sucursal,tbl_sucursal.nom_sucursal,"
                    + "tbl_sucursal.tel_sucursal,tbl_sucursal.email_sucursal,"
                    + "tbl_sucursal.pag_sucursal,tbl_sucursal.dir_sucursal,tbl_ciudad.nom_ciudad,"
                    + "tbl_pais.nom_pais from tbl_sucursal inner join tbl_ciudad on "
                    + "tbl_sucursal.ciudad_sucursal = tbl_ciudad.id_ciudad inner join tbl_pais on "
                    + "tbl_pais.cod_pais = tbl_ciudad.cod_pais";
            if (buscarPor.equalsIgnoreCase("Codigo")) {
                sql += " WHERE cod_sucursal = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Nombre")) {
                sql += " WHERE nom_sucursal = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Todos")) {
                stmt = conn.prepareStatement(sql);
            }

            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                    SucursalDTO dto = new SucursalDTO();
                    dto.setCodigo(rs.getString(1));
                    dto.setNombre(rs.getString(2));
                    dto.setTelefono(rs.getInt(3));
                    dto.setEmail(rs.getString(4));
                    dto.setPaginaWeb(rs.getString(5));
                    dto.setDireccion(rs.getString(6));
                    dto.getUbicacion().setNomCiudad(rs.getString(7));
                    dto.getUbicacion().setNomPais(rs.getString(8));
                    lista.add(dto);

                }
                stmt.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {

                }
            }
        }
        return lista;
    }

    public boolean actualizarSucursal(SucursalDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE tbl_sucursal set nom_sucursal = ?, "
                    + "tel_sucursal = ?, email_sucursal = ?, pag_sucursal = ?, dir_sucursal = ?,"
                    + "ciudad_sucursal = ? where cod_sucursal = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, dto.getNombre());
            stmt.setInt(2, dto.getTelefono());
            stmt.setString(3, dto.getEmail());
            stmt.setString(4, dto.getPaginaWeb());
            stmt.setString(5, dto.getDireccion());
            stmt.setInt(6, dto.getUbicacion().getIdCiudad());
            stmt.setString(7, dto.getCodigo());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
        return exito;
    }
}
