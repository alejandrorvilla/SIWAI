<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:58:14
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="co.edu.ufps.siwai.modelo.dto.SucursalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Sucursal</title>
        <meta charset="UTF-8">
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="../../Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../Css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="../../Imagenes/icono.ico">
        <!-- Script de Jquery 1.12-->
        <script src="../../Bootstrap/js/jquery.js"></script>
        <!-- Script de Bootstrap, agrega funcionalidad a la barra de navegacion -->
        <script src="../../Bootstrap/js/bootstrap.min.js"></script>
        <script src="../../Js/javascript.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <!-- Inicio del Alert (Notificacion de actualizar) -->
            <%
                if (session.getAttribute("msjAS") != null) {
                    String mensaje = "" + session.getAttribute("msjAS");
            %>
            <div id="alert" class="alert alert-success centrarDiv">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <%=mensaje%>
            </div>
            <%
                    session.setAttribute("msjAS", null);
                }
            %>
            <!-- Fin del Alert -->
            <!-- Inicio del alert -->
            <%
                String mensaje = session.getAttribute("msjCS") + "";
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
                    session.removeAttribute("msjCS");
                }
            %>
            <!-- Fin del alert-->
            <div>
                <h1 class="centrar-texto">Consultar Sucursal</h1>
            </div>
            <br>
            <!-- Formulario para consultar la sucursal -->
            <form name="form" action="/ControladorSucursal" method="post">
                <div class="container">
                    <div class="row ">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="sel" >Buscar por: </label>
                            <select name="sel" id="sel" class="tamañoConsultar"  onchange="capturar()" required>
                                <option value="" >Seleccione</option>
                                <option value="Todos" >Todos</option>
                                <option value="Codigo" >Codigo</option>
                                <option value="Nombre" >Nombre</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="informacion">Informacion: </label>
                            <input required id="informacion" name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button id="consultar" name="consultarSucursal" type="submit" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-search"></span>
                            </button>
                        </div>
                        <div class="col-md-1">
                        </div>
                    </div>
                </div>
                <br>
                <br>
            </form>
            <!-- Finalizacion del formulario para consultar -->
            <%
                if (session.getAttribute("sucursales") != null) {
                    ArrayList<SucursalDTO> lista = (ArrayList) session.getAttribute("sucursales");
                    if (!lista.isEmpty()) {
            %>
            <!-- Div que contiene la tabla de sucursales -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table id="tablaS" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Telefono</th>
                                        <th>Email</th>
                                        <th>Pagina Web</th>
                                        <th>Direccion</th>
                                        <th>Ciudad</th>
                                        <th>Pais</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <%
                                    for (int i = 0; i < lista.size(); i++) {
                                %>
                                <tr>
                                    <td><%= lista.get(i).getCodigo()%></td>
                                    <td><%= lista.get(i).getNombre()%></td>
                                    <td><%= lista.get(i).getTelefono()%></td>
                                    <td><%= lista.get(i).getEmail()%></td>
                                    <td><%= lista.get(i).getPaginaWeb()%></td>
                                    <td><%= lista.get(i).getDireccion()%></td>
                                    <td><%= lista.get(i).getUbicacion().getNomCiudad() %></td>
                                    <td><%= lista.get(i).getUbicacion().getNomPais() %></td>
                                    <td>
                                        <a onclick="enviarFormOcultoSucursal(document,<%=i%>)" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-edit asd "></span>
                                        </a>
                                    </td>
                                </tr>
                                <% }
                                    session.setAttribute("sucursales", null);
                                %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <!-- Fin del div que contiene la tabla de sucursales -->
                <% }
                    }%>
            </div>
            <!-- Fin del contenido principal-->
            <form id="formOculto" method="post" action="actualizar.jsp">
                <input type="hidden" name="codigo" id="codigo">
                <input type="hidden" name="nombre" id="nombre">
                <input type="hidden" name="telefono" id="telefono">
                <input type="hidden" name="email" id="email">
                <input type="hidden" name="paginaWeb" id="paginaWeb">
                <input type="hidden" name="direccion" id="direccion">
                <input type="hidden" name="ciudad" id="ciudad">
                <input type="hidden" name="pais" id="pais">

            </form>
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
