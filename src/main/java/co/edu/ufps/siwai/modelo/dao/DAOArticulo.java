/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
*
* @author Lenovo
*/
public class DAOArticulo {

  private Connection conn;

  /**
  * Este metodo se conecta con la base de datos para registrar los articulos
  *
  * @param dto DTO del articulo
  * @return true si pudo registrar el articulo ó false sino se pudo registrar
  * @throws Exception Conexión con la base de datos no pudo realizarse.
  */
  public boolean registrarArticulo(ArticuloDTO dto) throws Exception {
    boolean exito = false;
    conn = Conexion.generarConexion();
    if (conn != null) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_articulo"
      + " (refe_articulo, nom_articulo, tipo_articulo) values (?,?,?)");
      stmt.setString(1, dto.getReferencia());
      stmt.setString(2, dto.getNombre());
      stmt.setString(3, dto.getTipoArticulo());
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

  /**
  * Metodo que obtiene el nombre de un articulo.
  *
  * @param referencia String con la referencia del articulo.
  * @return String con el nombre del articulo.
  * @throws Exception Si existe un error en la conexion.
  */
  public String obtenerNombreArticulo(String referencia) throws Exception {
    conn = Conexion.generarConexion();
    String nombre = "";
    PreparedStatement stmt = conn.prepareStatement("SELECT nom_articulo"
    + " FROM tbl_articulo WHERE refe_articulo COLLATE utf8_bin=?");
    stmt.setString(1, referencia);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      nombre = rs.getString(1);
    }
    return nombre;
  }

  /**
  * Se encarga de buscar todos los articulos de la sucursal elejida y
  * referencia
  *
  * @param sucursal
  * @param buscarPor
  * @param informacion
  * @return
  */
  public ArrayList<ArticuloDTO> consultarArticulo(String sucursal, String buscarPor, String informacion) throws Exception {
    conn = Conexion.generarConexion();
    ArrayList<ArticuloDTO> lista = null;
    PreparedStatement stmt = null;
    if (conn != null) {
      String sentencia = "WHERE";
      int numero = 1;
      String sql = "SELECT artS.refe_artic_artsuc,art.nom_articulo,art.tipo_articulo,suc.nom_sucursal,artS.cant_artsuc,artS.valor_artsuc, artS.trans_artsuc FROM tbl_ArticuloSucursal as artS INNER JOIN tbl_articulo as art on artS.refe_artic_artsuc = art.refe_articulo INNER JOIN tbl_sucursal as suc ON artS.cod_sucur_artsuc = suc.cod_sucursal ";
      if(!sucursal.equalsIgnoreCase("Todas")){
        sql += " WHERE artS.cod_sucur_artsuc = ? ";
        sentencia = "AND";
        numero++;
      }
      if (buscarPor.equalsIgnoreCase("Tipo")) {
          sql += sentencia+" art.tipo_articulo = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(numero, informacion);
      } else if (buscarPor.equalsIgnoreCase("Referencia")) {
          sql += sentencia+" artS.refe_artic_artsuc = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(numero, informacion);
      }else if (buscarPor.equalsIgnoreCase("Nombre")) {
          sql += sentencia+" art.nom_articulo like ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(numero, "%"+informacion+"%");
      } else if (buscarPor.equalsIgnoreCase("Todos")) {
          stmt = conn.prepareStatement(sql);
      }
      if(!sucursal.equalsIgnoreCase("Todas")){
        numero--;
        stmt.setString(numero, sucursal);
      }
      try {
        ResultSet rs = stmt.executeQuery();
        lista = new ArrayList<>();
        while (rs.next()) {
          ArticuloDTO art = new ArticuloDTO();
          art.setReferencia(rs.getString(1));
          art.setNombre(rs.getString(2));
          art.setTipoArticulo(rs.getString(3));
          art.getSucursal().setNombre(rs.getString(4));
          art.setCantidad(rs.getInt(5));
          art.setValor(rs.getInt(6));
          lista.add(art);
        }
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

  public ArticuloDTO obtenerNombreArticuloTraslado(String referencia, String sucursal) throws Exception {
    conn = Conexion.generarConexion();
    ArticuloDTO dto = null;
    PreparedStatement stmt = conn.prepareStatement("SELECT a.nom_articulo ,arts.cant_artsuc, arts.valor_artsuc from tbl_ArticuloSucursal as arts INNER JOIN tbl_articulo as a ON arts.refe_artic_artsuc = a.refe_articulo where refe_artic_artsuc COLLATE utf8_bin= ? and arts.cod_sucur_artsuc =? ");
    stmt.setString(1, referencia);
    stmt.setString(2, sucursal);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      dto = new ArticuloDTO();
      dto.setNombre(rs.getString(1));
      dto.setCantidad(rs.getInt(2));
      dto.setValor(rs.getInt(3));
    }
    return dto;
  }

}
