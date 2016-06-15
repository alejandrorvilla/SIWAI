/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.VentaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author MarlonGuerrero
 */
public class DAOVenta {

    private Connection conn;

    public boolean registrarVenta(VentaDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_factura "
                    + "(cod_sucu_factura, DNI_cliente_factura, cod_cajero_factura,cod_vendedor_factura,	fecha_factura,sub_total_factura,	IVA_factura,valor_total_factura) "
                    + " values (?, ?, ?, ? , ?, ?, ?, ?)");
            stmt.setString(1, dto.getSucursal().getCodigo());
            stmt.setString(2, dto.getCliente().getDni());
            stmt.setString(3, dto.getCajero().getCodigo());
            stmt.setString(4, dto.getVendedor().getCodigo());
            stmt.setDate(5, new java.sql.Date(dto.getFecha().getTimeInMillis()));
            stmt.setInt(6, dto.getSubTotal());
            stmt.setDouble(7, dto.getIva());
            stmt.setDouble(8, dto.getTotal());
            exito = stmt.executeUpdate() > 0;
            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = stmt.executeQuery();
            int codigo = 0;
            while (rs.next()) {
                codigo = rs.getInt(1);
            }
            for (ArticuloDTO articulo : dto.getArticulos()) {
                stmt = conn.prepareStatement("INSERT INTO tbl_articulo_factura "
                        + "(cod_fac_artfac, refe_art_artfac, 	cant_artfac,valor_unitario_artfac)"
                        + "values (?, ?, ?, ?)");
                stmt.setInt(1, codigo);
                stmt.setString(2, articulo.getReferencia());
                stmt.setInt(3, articulo.getCantidad());
                stmt.setInt(4, articulo.getValor());
                exito = stmt.executeUpdate() > 0;
                stmt = conn.prepareStatement("UPDATE tbl_ArticuloSucursal SET cant_artsuc=cant_artsuc- ? "+
                "WHERE refe_artic_artsuc = ? AND cod_sucur_artsuc = ?");
                stmt.setInt(1,articulo.getCantidad());
                stmt.setString(2, articulo.getReferencia());
                stmt.setString(3,dto.getSucursal().getCodigo());
                stmt.executeUpdate();
            }

            stmt.close();
            conn.close();
            return exito;
        }
        return exito;
    }

    public ArrayList<VentaDTO> consultarVenta(String buscarPor, String informacion) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<VentaDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT v.cod_factura, suc.nom_sucursal,cli.nom_cliente,emp.nom_empleado,empl.nom_empleado, v.fecha_factura FROM tbl_factura as v INNER JOIN tbl_sucursal as suc ON v.cod_sucu_factura = suc.cod_sucursal INNER JOIN tbl_cliente as cli ON v.DNI_cliente_factura = cli.dni_cliente INNER JOIN tbl_empleado as emp ON v.cod_cajero_factura = emp.cod_empleado INNER JOIN tbl_empleado as empl ON v.cod_vendedor_factura = empl.cod_empleado ";
            if (buscarPor.equalsIgnoreCase("sucursal")) {
                sql += " WHERE suc.nom_sucursal = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            }else if (buscarPor.equalsIgnoreCase("fecha")) {
                sql += " WHERE v.fecha_factura = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, informacion);
            }else if (buscarPor.equalsIgnoreCase("cliente")) {
              sql += "WHERE cli.nom_cliente like ?";
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, "%"+informacion+"%");
            } else if (buscarPor.equalsIgnoreCase("Todos")) {
                stmt = conn.prepareStatement(sql);
            }

            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                    VentaDTO dto = new VentaDTO();
                    dto.setCodigo(rs.getInt(1));
                    dto.getSucursal().setNombre(rs.getString(2));
                    dto.getCliente().setNombre(rs.getString(3));
                    dto.getCajero().setNombre(rs.getString(4));
                    dto.getVendedor().setNombre(rs.getString(5));
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(rs.getDate(6));
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
    public VentaDTO cargarArticuloVenta(VentaDTO dto) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<ArticuloDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
            String sql = "SELECT av.refe_art_artfac, a.nom_articulo,av.cant_artfac ,av.valor_unitario_artfac FROM tbl_articulo_factura as av INNER JOIN tbl_articulo as a ON a.refe_articulo = av.refe_art_artfac WHERE av.cod_fac_artfac = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dto.getCodigo());
            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                  ArticuloDTO articuloDTO = new ArticuloDTO();
                  articuloDTO.setReferencia(rs.getString(1));
                  articuloDTO.setNombre(rs.getString(2));
                  articuloDTO.setCantidad(rs.getInt(3));
                  articuloDTO.setValor(rs.getInt(4));
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
