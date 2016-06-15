/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro Ramirez
 */
public class ControladorComparacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void registrarComparacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String[] referencia = request.getParameterValues("referencia[]");
            String[] cantidad = request.getParameterValues("cantidad[]");
            String[] costo = request.getParameterValues("costo[]");
            String[] precio = request.getParameterValues("precio[]");
            String fechaSt = request.getParameter("fecha");
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String sucursal = request.getParameter("sucursales");
            int transporte = Integer.parseInt(request.getParameter("transporte"));
            String notas = request.getParameter("notas");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(df.parse(fechaSt));
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            ArrayList<ArticuloDTO> dtos = new ArrayList<>();
            for(int i = 0; i < referencia.length - 1; i++){
                ArticuloDTO dto = new ArticuloDTO();
                dto.setReferencia(referencia[i]);
                dto.setCantidad(Integer.parseInt(cantidad[i]));
                dto.setCosto(Integer.parseInt(costo[i]));
                dto.setPrecio(Integer.parseInt(precio[i]));
                dtos.add(dto);
            }
            if(fachada.registrarComparacion(codigo, dtos, fecha, notas, sucursal, transporte))
                request.getSession().setAttribute("msjCP", "Comparación Registrada Exitosamente");
            else
                request.getSession().setAttribute("msjCP", "No se pudo registrar la comparación, verifique los articulos o el codigo del pedido por favor.");
            response.sendRedirect("/Seccion/Pedido/consultar.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("msjCP", "Error en la conexion con la base de datos");
            response.sendRedirect("/Seccion/Pedido/consultar.jsp");
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
    protected void existeComparacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            response.getWriter().print(fachada.existeComparacion(codigo));
        } catch (Exception ex) {
            response.getWriter().print("Error");
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
    protected void consultarComparacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            Gson gson = new Gson();
            response.getWriter().print(gson.toJson(fachada.consultarComparacion(codigo)));
        } catch (Exception ex) {
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
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("registrarComparacion") != null)
            registrarComparacion(request, response);
        else if(request.getParameter("verificarComparacion") != null)
            existeComparacion(request, response);
        else if(request.getParameter("consultarComparacion") != null)
            consultarComparacion(request, response);
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
