/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que sirve para administrar las conexiones a la base de datos.
 * @author Alejandro Ramírez
 */
public class Conexion {
    
    private static final String DRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://sandbox2.ufps.edu.co/ufps_45?noAccessToProcedureBodies=true";
    private static final String USER = "ufps_45";
    private static final String PASSWORD = "ufps_11";
    
    /**
     * Metodo que genera la conexion a la base de datos.
     * @return Connection a traves del cual se puede acceder a la base de datos.
     * @throws java.lang.Exception Exception originada por fallo en la conexion.
     */
    public static Connection generarConexion() throws Exception{
        Connection conexion;
        Class.forName(DRIVER).newInstance();
        conexion = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return conexion;
    }
}
