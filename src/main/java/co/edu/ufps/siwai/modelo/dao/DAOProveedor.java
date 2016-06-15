/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dto.ProveedorDTO;
import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase DAO que sirve de conexion con la tabla tbl_proveedor de la base de
 * datos.
 *
 * @author Alejandro Ramirez
 */
public class DAOProveedor {

    private Connection conn;

    /**
     * Metodo que registra los datos de un proveedor en la base de datos.
     *
     * @param dto ProveedorDTO con los datos a registrar.
     * @return Cadena de texto, Exito si registro o la excepcion generada.
     * @throws Exception si existe un error en la conexion a la base de datos.
     */
    public String registrarProveedor(ProveedorDTO dto) throws Exception {
        String mensaje = "";
        conn = Conexion.generarConexion();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_proveedor "
                + "(cod_proveedor, nit_proveedor, nom_proveedor, "
                + "num_cuenta_proveedor, cuenta_proveedor, tipo_cuenta_proveedor, "
                + "sitio_web_proveedor, nom_contacto_proveedor, tel_contacto_proveedor, "
                + "email_contacto_proveedor) values (?, ?, ? ,? ,? , ?, ?, ?, ?, ?)");
        stmt.setString(1, dto.getCodigo());
        stmt.setString(2, dto.getNit());
        stmt.setString(3, dto.getNombre());
        stmt.setString(4, dto.getNumCuenta());
        stmt.setString(5, dto.getCuenta());
        stmt.setString(6, dto.getTipoCuenta());
        stmt.setString(7, dto.getSitioWeb());
        stmt.setString(8, dto.getNombreContacto());
        stmt.setString(9, dto.getTelContacto());
        stmt.setString(10, dto.getEmailContacto());
        try{
            if(stmt.executeUpdate() > 0)
                mensaje = "Exito";
        } catch (SQLException ex) {
            mensaje = ex.toString();
        }
        stmt.close();
        conn.close();
        return mensaje;
    }

    /**
     * Metodo que consulta los proveedores de la base de datos.
     * @param columna Columna por la que se va a filtrar (Todos, nom o cod).
     * @param informacion Informacion que debe cumplir la columna.
     * @return ArrayList de ProveedorDTO.
     * @throws Exception Si hay un error en la conexion a la base de datos.
     */
    public ArrayList<ProveedorDTO> consultarProveedores(String columna,
            String informacion) throws Exception {
        ArrayList<ProveedorDTO> dtos = new ArrayList<>();
        String sql = "";
        if (columna.equals("nom")) {
            sql = " WHERE nom_proveedor = ? OR nom_proveedor = ? "
                    + "OR nom_proveedor = ? OR nom_proveedor = ?";
        } else if (columna.equals("cod")) {
            sql = " WHERE cod_proveedor = ?";
        }
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("SELECT cod_proveedor, "
                    + "nit_proveedor, nom_proveedor, num_cuenta_proveedor, "
                    + "cuenta_proveedor, tipo_cuenta_proveedor, sitio_web_proveedor, "
                    + "nom_contacto_proveedor, tel_contacto_proveedor, "
                    + "email_contacto_proveedor FROM tbl_proveedor" + sql);
            if (columna.equals("nom")) {
                stmt.setString(1, informacion);
                stmt.setString(2, informacion + "%");
                stmt.setString(3, "%" + informacion);
                stmt.setString(4, "%" + informacion + "%");
            } else if (columna.equals("cod"))
                stmt.setString(1, informacion);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProveedorDTO dto = new ProveedorDTO();
                dto.setCodigo(rs.getString(1));
                dto.setNit(rs.getString(2));
                dto.setNombre(rs.getString(3));
                dto.setNumCuenta(rs.getString(4));
                dto.setCuenta(rs.getString(5));
                dto.setTipoCuenta(rs.getString(6));
                dto.setSitioWeb(rs.getString(7));
                dto.setNombreContacto(rs.getString(8));
                dto.setTelContacto(rs.getString(9));
                dto.setEmailContacto(rs.getString(10));
                dtos.add(dto);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        return dtos;
    }
    
    /**
     * Metodo que actualiza los datos de un proveedor en la base de datos.
     *
     * @param dto ProveedorDTO con los datos a actualizar.
     * @return Cadena de texto, Exito si actualizo o la excepcion generada.
     * @throws Exception si existe un error en la conexion a la base de datos.
     */
    public String actualizarProveedor(ProveedorDTO dto) throws Exception {
        String mensaje = "";
        conn = Conexion.generarConexion();
        PreparedStatement stmt = conn.prepareStatement("UPDATE tbl_proveedor "
                + "SET nom_proveedor = ?, num_cuenta_proveedor = ?, cuenta_proveedor = ?,"
                + " tipo_cuenta_proveedor  = ?, sitio_web_proveedor = ?,"
                + " nom_contacto_proveedor = ?, tel_contacto_proveedor = ?,"
                + " email_contacto_proveedor = ? WHERE cod_proveedor = ?");
        stmt.setString(1, dto.getNombre());
        stmt.setString(2, dto.getNumCuenta());
        stmt.setString(3, dto.getCuenta());
        stmt.setString(4, dto.getTipoCuenta());
        stmt.setString(5, dto.getSitioWeb());
        stmt.setString(6, dto.getNombreContacto());
        stmt.setString(7, dto.getTelContacto());
        stmt.setString(8, dto.getEmailContacto());
        stmt.setString(9, dto.getCodigo());
        try{
            if(stmt.executeUpdate() > 0)
                mensaje = "Exito";
        } catch (SQLException ex) {
            mensaje = ex.toString();
        }
        stmt.close();
        conn.close();
        return mensaje;
    }
    
}
