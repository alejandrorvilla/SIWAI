/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.PedidoDTO;
import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase de acceso a los datos de la tabla pedido y articulo_pedido
 * @author Alejandro Ramírez
 */
public class DAOPedido {
    
    private Connection conn;
    
   /**
     * Metodo que registra un pedido junto con los articulos del pedido.
     * @param dto PedidoDTO con los datos del pedido y los articulos del pedido.
     * @return True si se registro todo, false si algo no se registro.
     * @throws Exception Excepcion al conectarse a la base de datos.
     */
    public boolean registrarPedido(PedidoDTO dto) throws Exception {
        boolean exito;
        conn = Conexion.generarConexion();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_pedido "
                + "(cod_proveedor, fecha_pedido, cant_art_pedido, cant_unidades_pedido) "
                + "values (?, ?, ?, ?)");
        stmt.setString(1, dto.getProveedor().getCodigo());
        stmt.setDate(2, new Date(dto.getFecha().getTimeInMillis()));
        stmt.setInt(3, dto.getArticulos().size());
        stmt.setInt(4, dto.obtenerCantidadesArticulos());
        exito = stmt.executeUpdate() > 0;
        stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet rs = stmt.executeQuery();
        int codigo = 0;
        while(rs.next())
            codigo = rs.getInt(1);
        for (ArticuloDTO articulo : dto.getArticulos()) {
            stmt = conn.prepareStatement("INSERT INTO tbl_articulo_pedido "
                + "(cod_pedido, refe_articulo, cantidad_articulo_pedido)"
                + "values (?, ?, ?)");
            stmt.setInt(1, codigo);
            stmt.setString(2, articulo.getReferencia());
            stmt.setInt(3, articulo.getCantidad());
            exito = stmt.executeUpdate() > 0;
        }
        stmt.close();
        conn.close();
        return exito;
    }
    
    public ArrayList<PedidoDTO> consultarPedido(String buscarPor, String informacion) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<PedidoDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT pe.cod_pedido,pro.nom_proveedor,pe.fecha_pedido FROM tbl_pedido as pe"+
            " INNER JOIN tbl_proveedor as pro ON pe.cod_proveedor = pro.cod_proveedor ";
            if (buscarPor.equalsIgnoreCase("codProveedor")) {
                sql += " WHERE pe.cod_proveedor = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            }else if (buscarPor.equalsIgnoreCase("fecha")) {
                sql += " WHERE pe.fecha_pedido = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Todos")) {
                stmt = conn.prepareStatement(sql);
            }

            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setCodigo(rs.getInt(1));
                    dto.getProveedor().setNombre(rs.getString(2));
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(rs.getDate(3));
                    dto.setFecha(cal);
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

    public PedidoDTO cargarArticuloPedidos(PedidoDTO dto) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<ArticuloDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT a.nom_articulo,ap.cantidad_articulo_pedido,ap.refe_articulo FROM tbl_articulo_pedido as ap"+
            " INNER JOIN tbl_articulo as a ON a.refe_articulo = ap.refe_articulo WHERE ap.cod_pedido = ? ";
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
