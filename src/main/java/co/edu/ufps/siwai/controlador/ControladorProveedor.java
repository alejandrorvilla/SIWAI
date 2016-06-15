/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ProveedorDTO;
import com.google.gson.Gson;
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
@WebServlet(name = "ControladorProveedor", urlPatterns = {"/ControladorProveedor"})
public class ControladorProveedor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void registrarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String codigo = request.getParameter("codigo");
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String web = request.getParameter("web");
        String telefono = request.getParameter("telefono");
        String tipoCuenta = request.getParameter("tipoCuentaBancaria");
        String email = request.getParameter("email");
        String numeroCuenta = request.getParameter("nCuentaBancaria");
        String cuenta = request.getParameter("cuentaBancaria");
        String nombreContacto = request.getParameter("nombreContacto");
        String validacion = validarCampos(codigo, nit, nombre, telefono, email,
                nombreContacto, numeroCuenta);
        PrintWriter out = response.getWriter();
        if(!validacion.contains("Exito")) {
            out.print(validacion);
            return;
        }
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        try {
            String mensaje = fachada.registrarProveedor(codigo, nit, nombre, cuenta, 
                    tipoCuenta, web, nombreContacto, email, numeroCuenta, telefono);
            if(mensaje.contains("PRIMARY")) {
                out.print("Fallo codigo");
            } else if (mensaje.contains("nit_proveedor")) {
                out.print("Fallo nit");
            } else {
                out.print(mensaje);
            }
        } catch (Exception ex) {
            out.print("Error");
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
    protected void actualizarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String codigo = request.getParameter("codigo");
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String web = request.getParameter("web");
        String telefono = request.getParameter("telefono");
        String tipoCuenta = request.getParameter("tipoCuentaBancaria");
        String email = request.getParameter("email");
        String numeroCuenta = request.getParameter("nCuentaBancaria");
        String cuenta = request.getParameter("cuentaBancaria");
        String nombreContacto = request.getParameter("nombreContacto");
        String validacion = validarCampos(codigo, nit, nombre, telefono, email, 
                nombreContacto, numeroCuenta);
        PrintWriter out = response.getWriter();
        if(!validacion.contains("Exito")) {
            out.print(validacion);
            return;
        }
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        try {
            String mensaje = fachada.actualizarProveedor(codigo, nit, nombre, cuenta, 
                    tipoCuenta, web, nombreContacto, email, numeroCuenta, telefono);
            if(mensaje.contains("PRIMARY")) {
                out.print("Fallo codigo");
            } else {
                request.getSession().setAttribute("msjCP", "Exito");
                out.print("");
            }
        } catch (Exception ex) {
            out.print("Error");
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
    protected void consultarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String columna = request.getParameter("sel");
        String informacion = request.getParameter("informacion");
        Fachada fachada = new Fachada();
        ArrayList<ProveedorDTO> dtos;
        try {
            dtos = fachada.consultarProveedores(columna, informacion);
            if(dtos == null)
                request.getSession().setAttribute("msjCP", "Error en la conexion a la base de datos");
            else if (dtos.isEmpty())
                request.getSession().setAttribute("msjCP", "No se encontro ningún proveedor");
            else
                request.getSession().setAttribute("proveedores", dtos);
        } catch (Exception ex) {
            request.getSession().setAttribute("msjCP", "Error en la conexion a la base de datos");
        } finally {
            response.sendRedirect("/Seccion/Proveedor/consultar.jsp");
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
    protected void cargarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<ProveedorDTO> dtos;
        try {
            dtos = fachada.consultarProveedores("Todos", "");
            Gson gson = new Gson();
            String listado = gson.toJson(dtos);
            response.getWriter().print(listado);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().print("Error");
        }
    }
    
    private String validarCampos(String codigo, String nit, String nombre,
            String telefono, String email, String nomContacto, String numCuenta){
        String msj = "Exito";
        if(nombre.trim().isEmpty() || codigo.trim().isEmpty() || nit.trim().isEmpty() ||
                nomContacto.trim().isEmpty() || telefono.trim().isEmpty())
            msj = "Por favor diligencie todos los campos obligatorios.";                
        else if(telefono.length() < 7) {
            msj = "El teléfono debe tener al menos 7 digitos.";
            try {
                if(Long.parseLong(telefono) < 0)
                msj = "El teléfono no puede ser negativo.";
            } catch (NumberFormatException ex) {
                msj = "El campo teléfono solo admite números.";
            }
        } else if (!email.isEmpty() && !email.contains("@") && !email.contains(".")) {
            msj = "El email no es correcto.";
        } else if (!numCuenta.isEmpty()) {
            try {
                if(Long.parseLong(telefono) < 0)
                msj = "El numero de cuenta no puede ser negativo.";
            } catch (NumberFormatException ex) {
                msj = "El campo numero de cuenta solo admite números.";
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
        if (request.getParameter("registrarProveedor") != null) {
            registrarProveedor(request, response);
        } else if (request.getParameter("consultarProveedor") != null) {
            consultarProveedor(request, response);
        } else if (request.getParameter("actualizarProveedor") != null) {
            actualizarProveedor(request, response);
        } else if (request.getParameter("cargarProveedores") != null) {
            cargarProveedores(request, response);
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
