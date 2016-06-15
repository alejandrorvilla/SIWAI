/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.PedidoDTO;
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
 * @author Alejandro Ramírez
 */
public class ControladorPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void crearPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fechaSt = request.getParameter("fecha");
        String codProveedor = request.getParameter("codProveedor");
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(df.parse(fechaSt));
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            fachada.crearPedido(fecha, codProveedor);
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
    protected void aniadirArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String referencia = request.getParameter("referencia");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            if(cantidad > 0) {
                Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
                response.getWriter().print(fachada.aniadirArticuloPedido(referencia, cantidad));
            } else {
                response.getWriter().print("Numero");
                return;
            }
        } catch (NumberFormatException ex) {
            response.getWriter().print("Numero");
        }   catch (Exception ex) {
            ex.printStackTrace();
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
    protected void eliminarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String referencia = request.getParameter("referencia");
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            response.getWriter().print(fachada.eliminarArticuloPedido(referencia));
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
    protected void registrarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        PrintWriter out = response.getWriter();
        try {
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            out.print(fachada.registrarPedido());
        } catch (Exception ex) {
            ex.printStackTrace();
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
    protected void consultarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String buscarPor = request.getParameter("sel");
        String informacion = request.getParameter("informacion");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<PedidoDTO> lista = null;
        try {
            lista = fachada.consultarPedido(buscarPor, informacion);
            if (lista.isEmpty()) {
                request.getSession().setAttribute("msjCP", "No se encontro ningún pedido");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("msjCP", "Error en la conexion a la base de datos");
            response.sendRedirect("/Seccion/Pedido/consultar.jsp");
        }

        request.getSession().setAttribute("pedidos", lista);
        response.sendRedirect("/Seccion/Pedido/consultar.jsp");
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
    protected void cargarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        int codigoPedido = Integer.parseInt(request.getParameter("codigo"));
        TreeSet<ArticuloDTO> dtos;
        try {
            dtos = fachada.cargarArticuloPedidos(codigoPedido);
            Gson gson = new Gson();
            String listado = gson.toJson(dtos);
            response.getWriter().print(listado);
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
        if (request.getParameter("crearPedido") != null) {
            crearPedido(request, response);
        } else if (request.getParameter("aniadirArticulo") != null) {
            aniadirArticulo(request, response);
        } else if (request.getParameter("eliminarArticulo") != null) {
            eliminarArticulo(request, response);
        } else if (request.getParameter("consultarPedido") != null) {
            consultarPedido(request, response);
        }else if (request.getParameter("cargarPedidos") != null) {
            cargarPedidos(request, response);
        } else if (request.getParameter("registrarPedido") != null) {
            registrarPedido(request, response);
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
