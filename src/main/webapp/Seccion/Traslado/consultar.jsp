<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:45:05
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.dto.TrasladoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Traslado</title>
        <meta charset="UTF-8">
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="../../Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../Css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="../../Imagenes/icono.ico">
        <script src="../../Js/javascript.js"></script>
        <!-- Script de Jquery 1.12-->
        <script src="../../Bootstrap/js/jquery.js"></script>
        <!-- Script de Bootstrap, agrega funcionalidad a la barra de navegacion -->
        <script src="../../Bootstrap/js/bootstrap.min.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
      <!-- Inicio del alert -->
      <%
          String mensaje = session.getAttribute("msjCT") + "";
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
              session.removeAttribute("msjCT");
          }
      %>
      <!-- Fin del alert-->
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Consultar Traslado</h1>
            </div>
            <br>
            <!-- Inicio del formulario para consultar el pedido-->
            <form action="/ControladorTraslado" method="post" name="form">
                <div class="container">
                    <div class="row">
                      <div class="col-md-1"></div>
                        <div class="col-md-4">
                          <label for="sel">Buscar por:</label>
                            <select name="sel" class="tamañoConsultar" id="sel" required onchange="capturar()">
                                <option value="">Seleccione</option>
                                <option value="Todos">Todos</option>
                                <option value="sOrigen">Sucursal Origen</option>
                                <option value="sDestino">Sucursal Destino</option>
                                <option value="fecha">Fecha del traslado</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                          <label for="informacion">Información:</label>
                          <input required name="informacion" id="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-2">
                            <button name="consultarTraslado" type="submit" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                </div>
                <br>
                <br>
            </form>
            <!-- Fin del formulario para consultar el pedido-->
            <%
                if (session.getAttribute("traslados") != null) {
                    ArrayList<TrasladoDTO> lista = (ArrayList) session.getAttribute("traslados");
                    if (!lista.isEmpty()) {
            %>
            <!-- Inicio del div que contiene la tabla de pedidos -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table id="tabla" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Sucursal Origen</th>
                                        <th>Sucursal Destino</th>
                                        <th>Fecha</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <%
                                    for (int i = 0; i < lista.size(); i++) {
                                %>
                                <tr>
                                    <td><%=lista.get(i).getOrigen().getNombre() %></td>
                                    <td><%=lista.get(i).getDestino().getNombre()%></td>
                                    <td><%=lista.get(i).getFechaFormateada() %></td>
                                    <td>
                                        <a onclick="enviarFormOcultoMasTraslado(document,<%=i %>,<%=lista.get(i).getCodigo() %>)" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-info-sign"></span>
                                        </a>
                                    </td>
                                </tr>
                                <% }
                                    session.setAttribute("traslados", null);
                                %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <%}
                }%>
            <!-- Fin del div que contiene la tabla de pedidos -->
            <!-- Fin del contenido principal-->
            <!-- Inicio del form oculto -->
            <form id="formOculto" action="mas.jsp" method="post">
                <input type="hidden" name="codigo" id="codigo">
                <input type="hidden" id="sOrigen" name="sOrigen">
                <input type="hidden" id="sDestino" name="sDestino">
                <input type="hidden" id="fecha" name="fecha">
            </form>
            <!--- Fin del form oculto -->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
