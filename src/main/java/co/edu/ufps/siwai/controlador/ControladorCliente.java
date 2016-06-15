/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ClienteDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro Ramirez
 */
@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void registrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ciudadSt = request.getParameter("ciudad");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String validacion = validarCampos(nombre, apellido, dni, telefono, email, ciudadSt);
        PrintWriter out = response.getWriter();
        if(!validacion.contains("Exito")) {
            out.print(validacion);
            return;
        }
        int ciudad = Integer.parseInt(ciudadSt);
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        String mensaje = "";
        try {
            if (fachada.registrarCliente(dni, nombre, apellido, direccion, email, telefono, ciudad))
               mensaje = "Exito";
            else
                mensaje = "Fallo";
        } catch (Exception ex) {
            out.print("Error");
        }
        out.print(mensaje);
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
    protected void consultarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String columna = request.getParameter("sel");
        String informacion = request.getParameter("informacion");
        Fachada fachada = new Fachada();
        ArrayList<ClienteDTO> dtos;
        try {
            dtos = fachada.consultarClientes(columna, informacion);
            if(dtos == null)
                request.getSession().setAttribute("msjCC", "Error en la conexion a la base de datos");
            else if (dtos.isEmpty())
                request.getSession().setAttribute("msjCC", "No se encontro ningún cliente");
            else
                request.getSession().setAttribute("clientes", dtos);
        } catch (Exception ex) {
            request.getSession().setAttribute("msjCC", "Error en la conexion a la base de datos");
        } finally {
            response.sendRedirect("/Seccion/Cliente/consultar.jsp");
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
    protected void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ciudadSt = request.getParameter("ciudad");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String validacion = validarCampos(nombre, apellido, dni, telefono, email, ciudadSt);
        PrintWriter out = response.getWriter();
        if(!validacion.contains("Exito")) {
            out.print(validacion);
            return;
        }
        int ciudad = Integer.parseInt(ciudadSt);
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        String mensaje = "";
        try {
            if (fachada.actualizarCliente(dni, nombre, apellido, direccion, email, telefono, ciudad))
               request.getSession().setAttribute("msjCC", "Exito");
            else
                mensaje = "Fallo";
        } catch (Exception ex) {
            out.print("Error");
        }
        out.print(mensaje);
    }
    
    private String validarCampos(String nombre, String apellidos, String dni,
            String telefono, String email, String ciudad){
        String msj = "Exito";
        if(nombre.trim().isEmpty() || apellidos.trim().isEmpty() || dni.trim().isEmpty() || ciudad == null)
            msj = "El nombre, apellido, DNI y ciudad de residencia son obligatorios.";                
        else if(!telefono.isEmpty()) {
            if(telefono.length() < 7)
                msj = "El teléfono debe tener al menos 7 digitos.";
            else {
                try {
                    if(Long.parseLong(telefono) < 0)
                        msj = "El teléfono no puede ser negativo.";
                } catch (NumberFormatException ex) {
                    msj = "El campo teléfono solo admite números.";
                }
            }
        } else if (!email.isEmpty() && !email.contains("@") && !email.contains(".")) {
            msj = "El email no es correcto.";
        } else if (ciudad.isEmpty()) {
            msj = "La ciudad es un campo obligatorio.";
        }else {
            try {
                if(Long.parseLong(dni) < 0)
                    msj = "El DNI no puede ser negativo.";
            } catch (NumberFormatException ex){
                msj = "El DNI solo admite números.";
            }
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
        if (request.getParameter("registrarCliente") != null) {
            registrarCliente(request, response);
        } else if (request.getParameter("consultarCliente") != null) {
            consultarCliente(request, response);
        } else if (request.getParameter("actualizarCliente") != null) {
            actualizarCliente(request, response);
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