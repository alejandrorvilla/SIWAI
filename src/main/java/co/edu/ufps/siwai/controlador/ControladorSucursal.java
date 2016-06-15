/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.SucursalDTO;
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
 * @author MarlonGuerrero
 */
@WebServlet(name = "ControladorSucursal", urlPatterns = {"/ControladorSucursal"})
public class ControladorSucursal extends HttpServlet {

    private String validarCampos(String codigo, String nombre, int telefono, String email, String paginaWeb, String direccion, String ciudad, String pais) {
        String mensaje = "Ninguno";
        if (codigo.isEmpty()) {
            mensaje = "Por favor verifique el campo del codigo";
        }
        if (nombre.isEmpty()) {
            mensaje = "Por favor verifique el campo del nombre";
        }
        if (telefono <= 0) {
            mensaje = "Por favor verifique el campo del telefono";
        }
        if (!email.contains("@")) {
            mensaje = "Por favor verifique el campo del email";
        }
        if (paginaWeb.isEmpty() || !paginaWeb.endsWith(".com")) {
            mensaje = "Por favor verifique el campo de la paginaWeb";
        }
        if (direccion.isEmpty()) {
            mensaje = "Por favor verifique el campo de la direccion";
        }
        if (ciudad.isEmpty()) {
            mensaje = "Por favor verifique el campo de la ciudad";
        }
        if (pais.isEmpty()) {
            mensaje = "Por favor verifique el campo del pais";
        }
        return mensaje;
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
    protected void registrarSucursal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        String email = request.getParameter("email");
        String paginaWeb = request.getParameter("paginaWeb");
        String direccion = request.getParameter("direccion");
        String ciudad = request.getParameter("ciudad");
        String pais = request.getParameter("pais");
        String validacion = validarCampos(codigo, nombre, telefono, email, paginaWeb, direccion, ciudad, pais);
        PrintWriter out = response.getWriter();
        if (validacion.equalsIgnoreCase("Ninguno")) {
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            boolean exito;
            try {
                exito = fachada.registrarSucursal(codigo, nombre, telefono, email, paginaWeb, direccion, ciudad, pais);
                out.print(exito);

            } catch (Exception ex) {
                out.print("Error en la conexion a la base de datos");
            }
        } else {
            out.print(validacion);
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
    protected void actualizarSucursal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        String email = request.getParameter("email");
        String paginaWeb = request.getParameter("paginaWeb");
        String direccion = request.getParameter("direccion");
        String ciudad = request.getParameter("ciudad");
        String pais = request.getParameter("pais");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        boolean exito;
        String validacion = validarCampos(codigo, nombre, telefono, email, paginaWeb, direccion, ciudad, pais);
        PrintWriter out = response.getWriter();
        if (validacion.equalsIgnoreCase("Ninguno")) {
            try {
                exito = fachada.actualizarSucursal(codigo, nombre, telefono, email,
                        paginaWeb, direccion, ciudad, pais);
                if (exito) {
                    request.getSession().setAttribute("msjAS", "La sucursal se actualizó satisfactoriamente");
                }
                out.print(exito);
            } catch (Exception e) {
                out.print("Error en la conexion a la base de datos");
            }
        } else {
            out.print(validacion);
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
    protected void consultarSucursal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String buscarPor = request.getParameter("sel");
        String informacion = request.getParameter("informacion");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<SucursalDTO> lista = new ArrayList<>();
        try {
            lista = fachada.consultarSucursal(buscarPor, informacion);
        } catch (Exception e) {
            request.getSession().setAttribute("msjCS", "Error en la conexion a la base de datos");
            response.sendRedirect("/Seccion/Sucursal/consultar.jsp");
        }
        if (lista.isEmpty()) {
            request.getSession().setAttribute("msjCS", "No se encontro ningúna sucursal");
        }
        request.getSession().setAttribute("sucursales", lista);
        response.sendRedirect("/Seccion/Sucursal/consultar.jsp");
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
    protected void cargarSucursales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<SucursalDTO> dtos;
        try {
            dtos = fachada.consultarSucursal("Todos", "");
            Gson gson = new Gson();
            String listado = gson.toJson(dtos);
            response.getWriter().print(listado);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().print("Error");
        }
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
        if (request.getParameter("consultarSucursal") != null) {
            consultarSucursal(request, response);
        }else
        if (request.getParameter("registrarSucursal") != null) {
            registrarSucursal(request, response);
        }else
        if (request.getParameter("actualizarSucursal") != null) {
            actualizarSucursal(request, response);
        } else if (request.getParameter("cargarSucursales") != null) {
            cargarSucursales(request, response);
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
