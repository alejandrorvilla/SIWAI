/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao;

import co.edu.ufps.siwai.modelo.dao.fabrica.Conexion;
import co.edu.ufps.siwai.modelo.dto.EmpleadoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author MarlonGuerrero
 */
public class DAOEmpleado {

    private Connection conn;

    /**
     * Metodo que se conecta a la base de datos y registra los datos de un
     * empleado.
     *
     * @param dto EmpleadoDTO con los datos de un empleado.
     * @return true si se registro, false si no lo hizo.
     * @throws java.lang.Exception Exception originada por fallo en la conexion
     * o error al insertar la sucursal.
     */
    public boolean registrarEmpleado(EmpleadoDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_empleado"
                    + "(cod_empleado, dni_empleado, nom_empleado, ape_empleado, tel_empleado,"
                    + "cel_empleado, cont_empleado, email_empleado, dir_empleado, fIngreso_empleado,"
                    + "suc_empleado, cargo_empleado)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, dto.getCodigo());
            stmt.setString(2, dto.getDni());
            stmt.setString(3, dto.getNombre());
            stmt.setString(4, dto.getApellido());
            stmt.setString(5, dto.getTelefono());
            stmt.setString(6, dto.getCelular());
            stmt.setString(7, dto.getContraseña());
            stmt.setString(8, dto.getEmail());
            stmt.setString(9, dto.getDireccion());
            stmt.setString(10, dto.getfIngreso());
            stmt.setString(11, dto.getSucursal().getCodigo());
            stmt.setString(12, dto.getCargo());

            try {
                exito = stmt.executeUpdate() > 0;
            } catch (SQLException ex) {
                return false;
            } finally {
                conn.close();
                stmt.close();
            }
        }
        return exito;
    }

    public ArrayList<EmpleadoDTO> consultarEmpleado(String buscarPor, String informacion) throws Exception {
        conn = Conexion.generarConexion();
        ArrayList<EmpleadoDTO> lista = null;
        PreparedStatement stmt = null;
        if (conn != null) {
          String sql ="SELECT tbl_empleado.cod_empleado, tbl_empleado.dni_empleado,tbl_empleado.nom_empleado,tbl_empleado.ape_empleado,tbl_empleado.tel_empleado,tbl_empleado.cel_empleado,tbl_empleado.cont_empleado, tbl_empleado.email_empleado,tbl_empleado.dir_empleado,tbl_empleado.fIngreso_empleado,tbl_sucursal.nom_sucursal,tbl_empleado.cargo_empleado,tbl_empleado.fSalida_empleado,tbl_empleado.hab_empleado FROM tbl_empleado INNER JOIN tbl_sucursal ON tbl_empleado.suc_empleado = tbl_sucursal.cod_sucursal";
            if (buscarPor.equalsIgnoreCase("Codigo")) {
                stmt = conn.prepareStatement(sql+= " WHERE cod_empleado = ?");
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Sucursal")) {
                stmt = conn.prepareStatement(sql+=" WHERE suc_empleado = ?");
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Todos")) {
                stmt = conn.prepareStatement(sql);
            } else if (buscarPor.equalsIgnoreCase("Nombre")) {
                stmt = conn.prepareStatement(sql+=" WHERE CONCAT(nom_empleado, ' ', ape_empleado) LIKE ?");
                stmt.setString(1, "%"+informacion+"%");
            } else if (buscarPor.equalsIgnoreCase("Dni")) {
                stmt = conn.prepareStatement(sql+=" WHERE dni_empleado = ?");
                stmt.setString(1, informacion);
            } else if (buscarPor.equalsIgnoreCase("Vendedor")) {
                stmt = conn.prepareStatement(sql+=" WHERE tbl_empleado.cargo_empleado = ? and tbl_empleado.suc_empleado = ?");
                stmt.setString(1, buscarPor);
                stmt.setString(2,informacion);
            }

            try {
                ResultSet rs = stmt.executeQuery();
                lista = new ArrayList<>();
                while (rs.next()) {
                    EmpleadoDTO dto = new EmpleadoDTO();
                    dto.setCodigo(rs.getString(1));
                    dto.setDni(rs.getString(2));
                    dto.setNombre(rs.getString(3));
                    dto.setApellido(rs.getString(4));
                    dto.setTelefono(rs.getString(5));
                    dto.setCelular(rs.getString(6));
                    dto.setContraseña(rs.getString(7));
                    dto.setEmail(rs.getString(8));
                    dto.setDireccion(rs.getString(9));
                    dto.setfIngreso(rs.getString(10));
                    dto.getSucursal().setNombre(rs.getString(11));
                    dto.setCargo(rs.getString(12));
                    dto.setfSalida(rs.getString(13));
                    dto.setHabilitado(rs.getShort(14));
                    lista.add(dto);
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
        return lista;
    }

    public String iniciarSesion(String usuario, String contraseña) throws Exception {
        String resultado = "nul";
        try {
            conn = Conexion.generarConexion();
            CallableStatement stmt = conn.prepareCall("{?=call iniciarSesion(?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, usuario);
            stmt.setString(3, contraseña);
            stmt.execute();
            resultado = stmt.getString(1);
            stmt.close();
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
        return resultado;
    }

    public boolean actualizarEmpleado(EmpleadoDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE tbl_empleado set suc_empleado = ?, "
                    + "cargo_empleado = ?, nom_empleado = ?, ape_empleado = ?,"
                    + "cod_empleado = ?, cel_empleado = ?,tel_empleado = ?,email_empleado = ?,"
                    + "dir_empleado = ?, fIngreso_empleado = ?,fSalida_empleado = ?,hab_empleado = ? where dni_empleado = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, dto.getSucursal().getCodigo());
            stmt.setString(2, dto.getCargo());
            stmt.setString(3, dto.getNombre());
            stmt.setString(4, dto.getApellido());
            stmt.setString(5, dto.getCodigo());
            stmt.setString(6, dto.getCelular());
            stmt.setString(7, dto.getTelefono());
            stmt.setString(8, dto.getEmail());
            stmt.setString(9, dto.getDireccion());
            stmt.setString(10, dto.getfIngreso());
            stmt.setString(11, dto.getfSalida());
            stmt.setShort(12, dto.getHabilitado());
            stmt.setString(13, dto.getDni());

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

    public boolean validarContraseña(EmpleadoDTO dto) throws Exception {
        conn = Conexion.generarConexion();
        boolean exito = false;
        PreparedStatement stmt = null;
        if (conn != null) {
            stmt = conn.prepareStatement("SELECT nom_empleado FROM tbl_empleado "
                    + "WHERE cod_empleado = ? and cont_empleado = ?");
            stmt.setString(1, dto.getCodigo());
            stmt.setString(2, dto.getContraseña());
            try {
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    exito = true;
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
        return exito;
    }

    public boolean cambiarContraseña(EmpleadoDTO dto) throws Exception {
        boolean exito = false;
        conn = Conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE tbl_empleado set cont_empleado = ? where cod_empleado = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, dto.getContraseña());
            stmt.setString(2, dto.getCodigo());
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
