/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.TrasladoDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author MarlonGuerrero
 */
public class DAOTraslado {

    private Connection conn;

    public boolean registrarTraslado(TrasladoDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_traslado "
                    + "(cod_sucu_origen_traslado, cod_sucu_destino_traslado, fecha_traslado) "
                    + " values (?, ?, ?)");
            stmt.setString(1, dto.getOrigen().getCodigo());
            stmt.setString(2, dto.getDestino().getCodigo());
            stmt.setDate(3, new Date(dto.getFecha().getTimeInMillis()));
            exito = stmt.executeUpdate() > 0;
            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = stmt.executeQuery();
            int codigo = 0;
            while (rs.next()) {
                codigo = rs.getInt(1);
            }
            for (ArticuloDTO articulo : dto.getArticulos()) {
                stmt = conn.prepareStatement("INSERT INTO tbl_articulo_traslado "
                        + "(cod_traslado, refe_articulo, cantidad_articulo_traslado)"
                        + "values (?, ?, ?)");
                stmt.setInt(1, codigo);
                stmt.setString(2, articulo.getReferencia());
                stmt.setInt(3, articulo.getCantidad());
                exito = stmt.executeUpdate() > 0;
                stmt = conn.prepareStatement("UPDATE tbl_ArticuloSucursal SET cant_artsuc=cant_artsuc- ? "+
                "WHERE refe_artic_artsuc = ? AND cod_sucur_artsuc = ?");
                stmt.setInt(1,articulo.getCantidad());
                stmt.setString(2, articulo.getReferencia());
                stmt.setString(3,dto.getOrigen().getCodigo());
                stmt.executeUpdate();
                try{
                stmt = conn.prepareStatement("INSERT INTO tbl_ArticuloSucursal "+
                "(refe_artic_artsuc,cod_sucur_artsuc,cant_artsuc,cant_apart_artsuc,valor_artsuc,trans_artsuc)"+
                " values(?, ?, ?, ?, ?, ?)");
                stmt.setString(1,articulo.getReferencia());
                stmt.setString(2, dto.getDestino().getCodigo());
                stmt.setInt(3,articulo.getCantidad());
                stmt.setInt(4, 0);
                stmt.setInt(5, 0);
                stmt.setInt(6, 0);
                stmt.executeUpdate();
                }catch(Exception ex){
                  stmt = conn.prepareStatement("UPDATE tbl_ArticuloSucursal SET cant_artsuc=cant_artsuc+ ? "+
                  "WHERE refe_artic_artsuc = ? AND cod_sucur_artsuc = ?");
                  stmt.setInt(1,articulo.getCantidad());
                  stmt.setString(2, articulo.getReferencia());
                  stmt.setString(3,dto.getDestino().getCodigo());
                  stmt.executeUpdate();
                }
            }
            stmt.close();
            conn.close();
            return exito;
        }
        return exito;
    }

    public ArrayList<TrasladoDTO> consultarTraslado(String buscarPor, String informacion) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<TrasladoDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT s1.nom_sucursal,s2.nom_sucursal,t.fecha_traslado, t.cod_traslado"+
             " FROM tbl_traslado as t INNER JOIN tbl_sucursal as s1  ON s1.cod_sucursal = "+
             "t.cod_sucu_origen_traslado INNER JOIN tbl_sucursal as s2 on s2.cod_sucursal = "+
             "t.cod_sucu_destino_traslado";
            if (buscarPor.equalsIgnoreCase("sOrigen")) {
                sql += " WHERE s1.cod_sucursal = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            }else if (buscarPor.equalsIgnoreCase("sDestino")) {
                sql += " WHERE s2.cod_sucursal = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            }else if (buscarPor.equalsIgnoreCase("fecha")) {
                sql += " WHERE t.fecha_traslado = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Todos")) {
                stmt = conn.prepareStatement(sql);
            }

            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                    TrasladoDTO dto = new TrasladoDTO();
                    dto.getOrigen().setNombre(rs.getString(1));
                    dto.getDestino().setNombre(rs.getString(2));
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(rs.getDate(3));
                    dto.setFecha(cal);
                    dto.setCodigo(rs.getInt(4));
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

    public TrasladoDTO cargarArticuloTraslado(TrasladoDTO dto) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<ArticuloDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT a.nom_articulo,at.cantidad_articulo_traslado	,at.refe_articulo FROM tbl_articulo_traslado as at"+
            " INNER JOIN tbl_articulo as a ON a.refe_articulo = at.refe_articulo WHERE at.cod_traslado = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dto.getCodigo());
            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                  ArticuloDTO articuloDTO = new ArticuloDTO();
                  articuloDTO.setNombre(rs.getString(1));
                  articuloDTO.setCantidad(rs.getInt(2));
                  articuloDTO.setReferencia(rs.getString(3));
                  dto.aniadirArticulo(articuloDTO);
                }
                stmt.close();

            } catch (SQLException ex) {
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {

                }
            }
        }
        return dto;
    }
}
