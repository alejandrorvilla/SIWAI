/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ArticuloExtraDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MarlonGuerrero
 */
public class ControladorArticuloExtra extends HttpServlet {

    private String validarCampos(String codigo, String sucursal, String nombre, int cantidad, String fEntrada,
            int costo, int valor, String notas) {
        String mensaje = "Ninguno";
        if (codigo.isEmpty()) {
            mensaje = "Por favor verifique el campo del codigo";
        }
        if (sucursal.isEmpty()) {
            mensaje = "Por favor verifique el campo de la sucursal";
        }
        if (nombre.isEmpty()) {
            mensaje = "Por favor verifique el campo del nombre";
        }
        if (cantidad < 0) {
            mensaje = "Por favor verifique el campo de la cantidad";
        }
        if (fEntrada.isEmpty()) {
            mensaje = "Por favor verifique la fecha";
        }
        if (costo < 0) {
            mensaje = "Por favor verifique el campo del costo";
        }
        if (valor < 0) {
            mensaje = "Por favor verifique el campo del valor";
        }
        if (notas.isEmpty()) {
            mensaje = "Por favor verifique el campo de las notas";
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
    protected void registrarArticuloExtra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String codigo = request.getParameter("codigo");
        String sucursal = request.getParameter("sucursal");
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String fEntrada = request.getParameter("fEntrada");
        int costo = Integer.parseInt(request.getParameter("costo"));
        int valor = Integer.parseInt(request.getParameter("valor"));
        String notas = request.getParameter("notas");
        String validacion = validarCampos(codigo, sucursal, nombre, cantidad, fEntrada, costo, valor, notas);
        PrintWriter out = response.getWriter();
        if (validacion.equalsIgnoreCase("Ninguno")) {
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            boolean exito;
            try {
                exito = fachada.registrarArticuloExtra(codigo, sucursal, nombre, cantidad, fEntrada, costo, valor, notas);
                out.print(exito);

            } catch (Exception ex) {
                ex.printStackTrace();
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
    protected void consultarArticuloExtra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String sucursal = request.getParameter("sucursal");
        String buscarPor = request.getParameter("sel");
        String info = request.getParameter("informacion");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<ArticuloExtraDTO> lista = null;
        try {
            lista = fachada.consultarArticuloExtra(sucursal, buscarPor, info);
            if (lista.isEmpty()) {
                request.getSession().setAttribute("msjCAE", "No se encontro ningún articulo extra");
            }
            request.getSession().setAttribute("articulosExtra", lista);
            response.sendRedirect("/Seccion/ArticuloExtra/consultar.jsp");
        } catch (Exception e) {
            request.getSession().setAttribute("msjCAE", "Error en la conexion a la base de datos");
            response.sendRedirect("/Seccion/ArticuloExtra/consultar.jsp");
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
        if (request.getParameter("registrarArticuloExtra") != null) {
            registrarArticuloExtra(request, response);
        }
        if (request.getParameter("consultarArticuloExtra") != null) {
            consultarArticuloExtra(request, response);
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
