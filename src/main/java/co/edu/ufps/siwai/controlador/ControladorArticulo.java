/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Lenovo
*/
public class ControladorArticulo extends HttpServlet {

  /**
  * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
  * methods.
  *
  * @param request servlet request
  * @param response servlet response
  * @throws ServletException if a servlet-specific error occurs
  * @throws IOException if an I/O error occurs
  */
  protected void registrarArticulo(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    /* TODO output your page here. You may use following sample code. */
    String referencia = request.getParameter("referencia");
    String nombre = request.getParameter("nombre");
    String tipo = request.getParameter("tipo");
    String validacion = validarCampos(referencia, nombre, tipo);
    PrintWriter out = response.getWriter();
    if(!validacion.contains("Exito")) {
      out.print(validacion);
      return;
    }
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    String mensaje = "";
    try {
      boolean exito=fachada.registrarAriculo(referencia, nombre, tipo);
      if (exito)
      mensaje = "Exito";
      else
      mensaje = "Fallo";
    } catch (Exception ex) {
      out.print("Error");
    }
    out.print(mensaje);
  }
  protected void consultarArticulo(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    String sucursal = request.getParameter("sucursal");
    String buscarPor = request.getParameter("sel");
    String info = request.getParameter("informacion");
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    ArrayList<ArticuloDTO> lis=null;
    PrintWriter out = response.getWriter();
    try{

      lis=fachada.consultarArticulo(sucursal, buscarPor, info);
      if (lis.isEmpty()) {
        request.getSession().setAttribute("msjCA", "No se encontro ningún articulo ");
      }
      request.getSession().setAttribute("articulos", lis);
      response.sendRedirect("/Seccion/Articulo/consultar.jsp");
    } catch (Exception ex) {
      request.getSession().setAttribute("msjCA", "Error en la conexion a la base de datos");
      response.sendRedirect("/Seccion/Articulo/consultar.jsp");
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
  protected void cargarNombreArticuloPedido(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    /* TODO output your page here. You may use following sample code. */
    String referencia = request.getParameter("referencia");
    PrintWriter out = response.getWriter();
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    try {
      String nombre = fachada.cargarNombreArticuloPedido(referencia);
      if(!nombre.isEmpty())
      out.print(nombre);
      else
      out.print("ArticuloReferencia");
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
  protected void cargarNombreArticuloComparacion(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    /* TODO output your page here. You may use following sample code. */
    String referencia = request.getParameter("referencia");
    PrintWriter out = response.getWriter();
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    try {
      String nombre = fachada.cargarNombreArticuloComparacion(referencia);
      if(!nombre.isEmpty())
      out.print(nombre);
      else
      out.print("ArticuloReferencia");
    } catch (Exception ex) {
      out.print("Error");
    }
  }

  private String validarCampos(String referencia, String nombre, String tipoArticulo){
    String msj="Exito";
    if(referencia.trim().isEmpty()|| nombre.trim().isEmpty()||tipoArticulo.trim().isEmpty())
    msj = "referencia, nombre y tipo son obligatorios.";
    return msj;
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
  protected void cargarNombreArticuloTraslado(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    /* TODO output your page here. You may use following sample code. */
    String referencia = request.getParameter("referencia");
    PrintWriter out = response.getWriter();
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    try {
      ArticuloDTO dto = fachada.cargarNombreArticuloTraslado(referencia);
      if(dto!=null){
        out.print(dto.getNombre());
        request.getSession().setAttribute("cant",dto.getCantidad());
      }else{
        out.print("ArticuloReferencia");
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
  protected void cargarNombreArticuloVenta(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    /* TODO output your page here. You may use following sample code. */
    String referencia = request.getParameter("referencia");
    String sucursal = request.getSession().getAttribute("suc").toString();
    PrintWriter out = response.getWriter();
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    try {
      ArticuloDTO dto = fachada.cargarNombreArticuloVenta(referencia,sucursal);
      if(dto!=null){
        out.print(dto.getNombre()+"-"+dto.getValor());
        request.getSession().setAttribute("valor",dto.getValor());
        request.getSession().setAttribute("cant",dto.getCantidad());
      }else{
        out.print("ArticuloReferencia");
      }
    } catch (Exception ex) {
      out.print("Error");
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
    if(request.getParameter("registrarArticulo")!=null){
      this.registrarArticulo(request, response);
    } else if(request.getParameter("cargarNombreArticuloPedido")!=null){
      this.cargarNombreArticuloPedido(request, response);
    }else if(request.getParameter("consultarArticulo")!=null){
      this.consultarArticulo(request, response);
    } else if(request.getParameter("cargarNombreArticuloComparacion")!=null){
      this.cargarNombreArticuloComparacion(request, response);
    }else if(request.getParameter("cargarNombreArticuloTraslado")!=null){
      this.cargarNombreArticuloTraslado(request, response);
    }else if(request.getParameter("cargarNombreArticuloVenta")!=null){
      this.cargarNombreArticuloVenta(request, response);
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
