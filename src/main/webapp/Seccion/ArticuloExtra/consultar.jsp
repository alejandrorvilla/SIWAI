<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:34:19
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>
<%@page import="co.edu.ufps.siwai.modelo.dto.ArticuloExtraDTO"%>
<%@page import="co.edu.ufps.siwai.modelo.dto.SucursalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.Fachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Articulo Extra</title>
        <meta charset="UTF-8">
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="../../Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../Css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="../../Imagenes/icono.ico">
        <!-- Script de Jquery 1.12-->
        <script src="../../Bootstrap/js/jquery.js"></script>
        <!-- Script de Bootstrap, agrega funcionalidad a la barra de navegacion -->
        <script src="../../Bootstrap/js/bootstrap.min.js"></script>
        <script src="../../Js/blockUI.js"></script>
        <script src="../../Js/javascript.js"></script>
    </head>
    <body>
      <%
          Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
          ArrayList<SucursalDTO> lista = fachada.consultarSucursal("Todos", "");
      %>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Inicio del alert -->
        <%
            String mensaje = session.getAttribute("msjCAE") + "";
            if (!mensaje.equals("null")) {
                if (mensaje.contains("Error")) {
        %>
        <div class="alert alert-danger centrar-texto" role="alert" arial >
            <%=mensaje%>
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </div>
        <%
        } else {%>
        <div class="alert alert-warning centrar-texto" role="alert" arial >
            <%=mensaje%>
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </div>
        <%      }
                session.removeAttribute("msjCAE");
            }
        %>
        <!-- Fin del alert-->
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Consultar Articulo Extra</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form action="/ControladorArticuloExtra" method="post" name="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                          <label for="sel1">Sucursal:</label>
                          <select name="sucursal" class="tamañoConsultar" id="sel1" required>
                              <option value="">Seleccione</option>
                              <option value="Todas">Todas</option>
                              <% for (int i = 0; i < lista.size(); i++) {%>
                              <option value="<%=lista.get(i).getCodigo()%>"><%=lista.get(i).getNombre()%></option>
                              <% }
                              %>
                          </select>
                        </div>
                        <div class="col-md-4">
                          <label for="sel">Buscar por:</label>
                            <select required name="sel" class="tamañoConsultar" id="sel" onchange="capturar()">
                                <option value="" >Seleccione</option>
                                <option value="Todos" >Todos</option>
                                <option value="Codigo" >Codigo</option>
                                <option value="Nombre">Nombre</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="informacion">Información:</label>
                            <input required id="informacion" name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button name="consultarArticuloExtra" type="submit" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-search"></span>
                            </button>
                        </div>

                    </div>
                </div>
            </form>
            <br>
            <br>
              <%
                  if (session.getAttribute("articulosExtra") != null) {
                      ArrayList<ArticuloExtraDTO> lista2 = (ArrayList) session.getAttribute("articulosExtra");
                      if (!lista2.isEmpty()) {
              %>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Sucursal</th>
                                        <th>Nombre</th>
                                        <th>Valor</th>
                                        <th>Cantidad</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <%
                                    for (int i = 0; i < lista2.size(); i++) {
                                %>
                                <tr>
                                  <td><%=lista2.get(i).getCodigo()%></td>
                                  <td><%=lista2.get(i).getSucursal().getNombre()%></td>
                                  <td><%=lista2.get(i).getNombre()%></td>
                                  <td><%=lista2.get(i).getValor()%></td>
                                  <td><%=lista2.get(i).getCantidad()%></td>
                                    <td>

                                        <a onclick="enviarFormOcultoArticuloExMas(document,<%=i %>,'<%=lista2.get(i).getCodigo() %>',
                                        '<%=lista2.get(i).getSucursal().getNombre() %>','<%=lista2.get(i).getNombre() %>',
                                        '<%=lista2.get(i).getCantidad() %>','<%=lista2.get(i).getfEntrada() %>',
                                        '<%=lista2.get(i).getCosto() %>','<%=lista2.get(i).getValor() %>',
                                        '<%=lista2.get(i).getNotas() %>')" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-info-sign asd "></span>
                                        </a>
                                    </td>
                                </tr>
                                <% }
                                    session.setAttribute("articulosExtra", null);
                                %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <% }
                }%>
            <!-- Fin del contenido principal-->

            <form id="formOculto" method="post">
                <input type="hidden" id="codigo" name="codigo">
                <input type="hidden" id="sucursal" name="sucursal">
                <input type="hidden" id="nombre" name="nombre">
                <input type="hidden" id="cantidad" name="cantidad">
                <input type="hidden" id="fEntrada" name="fEntrada">
                <input type="hidden" id="costo" name="costo">
                <input type="hidden" id="valor" name="valor">
                <input type="hidden" id="notas" name="notas">
            </form>
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
