/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.norsoft.prueba.modelo;

import co.com.norsoft.prueba.modelo.objeto.Persona;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alejandro Ramirez
 */
public class Fachada {
    
    private final ArrayList<Persona> objs;
    private final String usuario, contraseña;
    
    public Fachada(){
        objs = new ArrayList<>();
        usuario = "user";
        contraseña = "12345";
    }

    public boolean inciarSesion(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }
 
    public boolean insertarPersona(String nombre, String apellido, String estadoCivil, 
            String correo, String departamento, String ciudad, Date nacimiento, int sueldo) {
        return objs.add(new Persona(nombre, apellido, estadoCivil, correo, 
                departamento, ciudad, nacimiento, sueldo));
    }
    
    public ArrayList<Persona> obtenerPersonas(){
        return objs;
    }
    
}
