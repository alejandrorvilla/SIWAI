/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.ComparacionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Clase de acceso a los datos de la tabla comparacion en la base de datos.
 *
 * @author Alejandro Ramirez
 */
public class DAOComparacion {

    private Connection conn;

    /**
     * Metodo que registra los datos de una comparacion.
     *
     * @param dto ComparacionDTO con los datos y los articulos de la
     * comparacion.
     * @return True si se registro todo, false si algo no se registro.
     * @throws Exception Excepcion al conectarse a la base de datos.
     */
    public boolean registrarComparacion(ComparacionDTO dto) throws Exception {
        boolean exito;
        conn = Conexion.generarConexion();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_comparacion "
                + "(cod_pedido, fecha_comparacion, cant_art_comparacion, cant_unidades_comparacion, "
                + "cod_sucursal, notas_comparacion, costo_transporte_comparacion) "
                + "values (?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, dto.getPedido());
        stmt.setDate(2, new Date(dto.getFecha().getTimeInMillis()));
        stmt.setInt(3, dto.getArticulos().size());
        stmt.setInt(4, dto.obtenerCantidadesArticulos());
        stmt.setString(5, dto.getSucursal().getCodigo());
        stmt.setString(6, dto.getNotas());
        stmt.setInt(7, dto.getTransporte());
        exito = stmt.executeUpdate() > 0;
        for (ArticuloDTO articulo : dto.getArticulos()) {
            stmt = conn.prepareStatement("INSERT INTO tbl_articulo_comparacion "
                    + "(cod_comparacion, refe_articulo, cantidad_articulo, costo_unidad, venta_unidad)"
                    + "values (?, ?, ?, ?, ?)");
            stmt.setInt(1, dto.getPedido());
            stmt.setString(2, articulo.getReferencia());
            stmt.setInt(3, articulo.getCantidad());
            stmt.setInt(4, articulo.getCosto());
            stmt.setInt(5, articulo.getPrecio());
            exito = stmt.executeUpdate() > 0;
        }
        stmt.close();
        conn.close();
        return exito;
    }

    public boolean existeComparacion(int codigo) throws Exception {
        conn = Conexion.generarConexion();
        boolean exito = false;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM tbl_comparacion WHERE cod_pedido = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, codigo);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                exito = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {

            }
        }
        return exito;
    }

    public ComparacionDTO consultarComparacion(int codigo) throws Exception {
        conn = Conexion.generarConexion();
        ComparacionDTO dto = new ComparacionDTO();
        PreparedStatement stmt = null;
        String sql = "SELECT s.nom_sucursal, c.fecha_comparacion, c.notas_comparacion, "
                + "c.costo_transporte_comparacion FROM tbl_comparacion as c JOIN tbl_sucursal as s "
                + "ON c.cod_sucursal = s.cod_sucursal WHERE cod_pedido = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, codigo);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dto.setPedido(codigo);
                dto.getSucursal().setNombre(rs.getString(1));
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(rs.getDate(2));
                dto.setFecha(fecha);
                dto.setNotas(rs.getString(3));
                dto.setTransporte(rs.getInt(4));
            }
            sql = "SELECT a.nom_articulo, ac.refe_articulo, ac.cantidad_articulo, "
                    + "ac.costo_unidad, ac.venta_unidad FROM tbl_articulo_comparacion "
                    + "as ac JOIN tbl_articulo as a ON ac.refe_articulo = "
                    + "a.refe_articulo WHERE cod_comparacion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ArticuloDTO articulo = new ArticuloDTO();
                    articulo.setNombre(rs.getString(1));
                    articulo.setReferencia(rs.getString(2));
                    articulo.setCantidad(rs.getInt(3));
                    articulo.setCosto(rs.getInt(4));
                    articulo.setPrecio(rs.getInt(5));
                    dto.aniadirArticulo(articulo);
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
            return dto;
        }

    }
