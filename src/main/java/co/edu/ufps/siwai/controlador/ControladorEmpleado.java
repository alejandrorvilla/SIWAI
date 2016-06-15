    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.controlador;

import co.edu.ufps.siwai.modelo.Fachada;
import co.edu.ufps.siwai.modelo.dto.EmpleadoDTO;
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
@WebServlet(name = "ControladorEmpleado", urlPatterns = {"/ControladorEmpleado"})
public class ControladorEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String sucursal = request.getParameter("sucursal");
        String cargo = request.getParameter("cargo");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String codigo = request.getParameter("codigo");
        String celular = request.getParameter("celular");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String direccion = request.getParameter("direccion");
        String fIngreso = request.getParameter("fIngreso");
        short habilitado = Short.parseShort(request.getParameter("habilitado"));
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        boolean exito;
        PrintWriter out = response.getWriter();
        try {
            exito = fachada.actualizarEmpleado(sucursal, cargo, dni, nombre, apellido, telefono, celular, email, direccion, fIngreso, habilitado, codigo);
            if (exito) {
                request.getSession().setAttribute("msjAE", "El empleado se actualizó satisfactoriamente");
            }
            out.print(exito);
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("Error en la conexion a la base de datos");
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
    protected void registrarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String codigo = request.getParameter("codigo");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String direccion = request.getParameter("direccion");
        String fIngreso = request.getParameter("fIngreso");
        String cargo = request.getParameter("cargo");
        String sucursal = request.getParameter("sucursal");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        boolean exito;
        PrintWriter out = response.getWriter();
        try {
            exito = fachada.registrarEmpleado(codigo, dni, nombre, apellido, telefono, celular, email, direccion, fIngreso, cargo, sucursal);
            out.print(exito);
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("Error en la conexion a la base de datos");
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
    protected void cambiarContraseña(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String codigo = "" + request.getSession().getAttribute("cod");
        String contraActual = request.getParameter("contraActual");
        String contraNueva = request.getParameter("contraNueva");
        String contraNueva2 = request.getParameter("contraNueva2");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        boolean exito;
        PrintWriter out = response.getWriter();
        try {
            boolean validarContraseñaActual = fachada.validarContraseña(codigo, contraActual);
            if (validarContraseñaActual) {
                if (contraNueva.equals(contraNueva2)) {
                    exito = fachada.cambiarContraseña(codigo, contraNueva);
                    out.print(exito);
                } else {
                    out.print("Las contraseñas no coinciden");
                }
            } else {
                out.print("Tu contraseña está incorrecta");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("Error en la conexion a la base de datos");
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
    protected void consultarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String buscarPor = request.getParameter("sel");
        String informacion;
        if (buscarPor.equalsIgnoreCase("Sucursal")) {
            informacion = request.getParameter("sucursales");
        } else {
            informacion = request.getParameter("informacion");
        }
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<EmpleadoDTO> lista = null;
        try {
            lista = fachada.consultarEmpleado(buscarPor, informacion);
        } catch (Exception e) {
            request.getSession().setAttribute("msjCE", "Error en la conexion a la base de datos");
            response.sendRedirect("/Seccion/Empleado/consultar.jsp");
        }
        if (lista.isEmpty()) {
            request.getSession().setAttribute("msjCE", "No se encontro ningún Empleado");
        }
        request.getSession().setAttribute("empleados", lista);
        response.sendRedirect("/Seccion/Empleado/consultar.jsp");

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
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contra");
        Fachada fachada = new Fachada();
        request.getSession().setAttribute("fachada", fachada);
        String ingreso;
        PrintWriter out = response.getWriter();
        try {
            ingreso = fachada.iniciarSesion(usuario, contraseña);
            if (!ingreso.equals("nulo")) {
                String[] ingreso2 = ingreso.split("-");
                if (ingreso2[2].equals("1")) {
                    request.getSession().setAttribute("usuario", ingreso2[0]);
                    request.getSession().setAttribute("cargo", ingreso2[1]);
                    request.getSession().setAttribute("cod", ingreso2[3]);
                    request.getSession().setAttribute("suc", ingreso2[4]);
                } else {
                    ingreso = "nulo";
                }
            }
            out.print(ingreso);
        } catch (Exception e) {
            out.print("Error en la base de datos");
        }
    }

    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
    protected void cargarVendedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<EmpleadoDTO> dtos;
        String codigo = request.getParameter("codigo");
        try {
            dtos = fachada.consultarEmpleado("Vendedor", codigo);
            Gson gson = new Gson();
            String listado = gson.toJson(dtos);
            response.getWriter().print(listado);
        } catch (Exception ex) {
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
    protected void buscarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
        ArrayList<EmpleadoDTO> dtos;
        String codigo = request.getParameter("codigo");
        try {
            dtos = fachada.consultarEmpleado("Codigo", codigo);
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
        if (request.getParameter("cerrarSesion") != null) {
            cerrarSesion(request, response);
        }
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
        if (request.getParameter("consultarEmpleado") != null) {
            consultarEmpleado(request, response);
        } else if (request.getParameter("registrarEmpleado") != null) {
            registrarEmpleado(request, response);
        } else if (request.getParameter("iniciarSesion") != null) {
            iniciarSesion(request, response);
        } else if (request.getParameter("actualizarEmpleado") != null) {
            actualizarEmpleado(request, response);
        } else if (request.getParameter("cambiarContra") != null) {
            cambiarContraseña(request, response);
        } else if (request.getParameter("cargarVendedores") != null) {
            cargarVendedores(request, response);
        } else if (request.getParameter("buscarEmpleado") != null) {
            buscarEmpleado(request, response);
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
