/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dto.ClienteDTO;
import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase de control de acceso a los datos de los clientes.
 *
 * @author Alejandro Ramírez
 */
public class DAOCliente {

    private Connection conn;

    /**
     * Metodo que se conecta a la base de datos y registra los datos de un
     * cliente.
     *
     * @param dto ClienteDTO con los datos de un cliente.
     * @return true si se registro, false si no lo hizo.
     * @throws java.lang.Exception Exception originada por fallo en la conexion
     * o error al insertar el cliente.
     */
    public boolean registrarCliente(ClienteDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_cliente "
                    + "(dni_cliente, nom_cliente, ape_cliente, dir_cliente, "
                    + "tel_cliente, email_cliente, id_ciudad) values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, dto.getDni());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getApellido());
            stmt.setString(4, dto.getDireccion());
            stmt.setString(5, dto.getTelefono());
            stmt.setString(6, dto.getEmail());
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

    /**
     * Metodo que se conecta a la base de datos y registra los datos de un
     * cliente.
     *
     * @param informacion Contenido que debe cumplir la columna por la que se
     * busca a los clientes.
     * @param columna Nombre de la columna por la que se desea buscar a los
     * clientes (nom, dni o Todos).
     * @return ArrayList de ClienteDTO o null si ocurre una SQLException
     * @throws java.lang.Exception Exception originada por fallo en la conexion
     * o error al consultar los clientes.
     */
    public ArrayList<ClienteDTO> consultarClientes(String columna, String informacion) throws Exception {
        ArrayList<ClienteDTO> dtos = new ArrayList<>();
        conn = Conexion.generarConexion();
        String sql = "";
        if (columna.equals("nom")) 
            sql = " WHERE CONCAT(nom_cliente, ' ', ape_cliente) LIKE ? OR "
                    + "CONCAT(nom_cliente, ' ', ape_cliente) LIKE ? OR "
                    + "CONCAT(nom_cliente, ' ', ape_cliente) LIKE ? OR "
                    + "CONCAT(nom_cliente, ' ', ape_cliente) LIKE ?";
        else if (columna.equals("dni"))
            sql = " WHERE dni_cliente = ? ";
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("SELECT `tbl_cliente`.*, "
                    + "`tbl_ciudad`.`nom_ciudad`, `tbl_pais`.`nom_pais`, `tbl_pais`.`cod_pais` "
                    + "FROM `tbl_cliente` JOIN `tbl_ciudad` ON `tbl_cliente`.`id_ciudad` "
                    + "= `tbl_ciudad`.`id_ciudad` JOIN `tbl_pais` ON `tbl_ciudad`.`cod_pais` "
                    + "= `tbl_pais`.`cod_pais`" + sql);
            if (columna.equals("dni")) {
                stmt.setString(1, informacion);
            } else if (columna.equals("nom")) {
                stmt.setString(1, informacion);
                stmt.setString(2, "%" + informacion);
                stmt.setString(3, informacion + "%");
                stmt.setString(4, "%" + informacion + "%");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteDTO dto = new ClienteDTO();
                dto.setDni(rs.getString(1));
                dto.setNombre(rs.getString(2));
                dto.setApellido(rs.getString(3));
                dto.setDireccion(rs.getString(4));
                dto.setTelefono(rs.getString(5));
                dto.setEmail(rs.getString(6));
                dto.getUbicacion().setIdCiudad(rs.getInt(7));
                dto.getUbicacion().setNomCiudad(rs.getString(8));
                dto.getUbicacion().setNomPais(rs.getString(9));
                dto.getUbicacion().setCodPais(rs.getString(10));
                dtos.add(dto);
            }
            stmt.close();
            rs.close();
            conn.close();
        }
        return dtos;
    }
    
    /**
     * Metodo que se conecta a la base de datos y actualizar los datos de un
     * cliente.
     * @param dto ClienteDTO con los datos del cliente.
     * @return true si se registro, false si no lo hizo.
     * @throws java.lang.Exception Exception originada por fallo en la conexion
     * o error al actualizar el cliente.
     */
    public boolean actualizarCliente(ClienteDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE tbl_cliente "
                    + "SET nom_cliente = ?, ape_cliente = ?, dir_cliente = ?, "
                    + "tel_cliente = ?, email_cliente = ?, id_ciudad = ? WHERE dni_cliente = ?");
            stmt.setString(1, dto.getNombre());
            stmt.setString(2, dto.getApellido());
            stmt.setString(3, dto.getDireccion());
            stmt.setString(4, dto.getTelefono());
            stmt.setString(5, dto.getEmail());
            stmt.setInt(6, dto.getUbicacion().getIdCiudad());
            stmt.setString(7, dto.getDni());
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
    
}
