/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.norsoft.prueba.controlador;

import co.com.norsoft.prueba.modelo.Fachada;
import co.com.norsoft.prueba.modelo.objeto.Persona;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro Ramirez
 */

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = new Fachada();
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");
        if (fachada.inciarSesion(usuario, contraseña)) {
            request.getSession().setAttribute("sesion", "creada");
            request.getSession().setAttribute("fachada", fachada);
            response.getWriter().print("exito");
        } else {
            response.getWriter().print("fallo");
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().invalidate();
        response.sendRedirect("/index.jsp");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<Persona> objs = fachada.obtenerPersonas();
        Gson gson = new Gson();
        String listado = gson.toJson(objs);
        response.getWriter().print(listado);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String estadoCivil = request.getParameter("estado");
            String fechaSt = request.getParameter("nacimiento");
            String sueldoSt = request.getParameter("sueldo");
            String correo = request.getParameter("email");
            String dpto = request.getParameter("dpto");
            String ciudad = request.getParameter("ciudad");
            String msj = validarCampos(nombre, apellido, estadoCivil, correo, dpto, ciudad, fechaSt, sueldoSt);
            if(!msj.isEmpty()){
                response.getWriter().print(msj);
            }
            int sueldo = Integer.parseInt(sueldoSt);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date nacimiento = format.parse(fechaSt);
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            response.getWriter().print(fachada.insertarPersona(nombre, apellido, estadoCivil, correo, dpto, 
                    ciudad, nacimiento, sueldo));
        } catch (Exception ex) {
            response.getWriter().print("Error");
        }
    }

    private String validarCampos(String nombre, String apellido, String estadoCivil, 
            String correo, String departamento, String ciudad, String nacimiento, String sueldo) {
        String msj = "";
        if(nombre.trim().isEmpty() || apellido.trim().isEmpty() || 
                estadoCivil.trim().isEmpty() || correo.trim().isEmpty() || departamento.trim().isEmpty()
                || ciudad.trim().isEmpty() || nacimiento.trim().isEmpty() || sueldo.trim().isEmpty()) {
            msj = "Diligencie todos los campos por favor";
        } else if (!estadoCivil.equals("Soltero")) {
            if(!estadoCivil.equals("Casado"))
                msj = "El campo \"Estado Civil\" no es aceptado";
        } else if (!correo.contains("@") || !correo.contains(".")) {
            msj = "El formato del correo no es aceptado";
        }
        try {
            Integer.parseInt(sueldo);
        } catch (NumberFormatException ex) {
            msj = "El campo sueldo solo recibe numeros enteros";
        }
        return msj;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("cerrarSesion") != null) 
            cerrarSesion(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("iniciarSesion") != null) {
            iniciarSesion(request, response);
        } else if (request.getParameter("registrar") != null) {
            registrar(request, response);
        } else if (request.getParameter("consultar") != null) {
            consultar(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
