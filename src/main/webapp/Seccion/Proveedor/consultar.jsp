<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:48:26
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.dto.ProveedorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Proveedor</title>
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
            <%
                String mensaje = session.getAttribute("msjCP") + "";
                if (!mensaje.equals("null")) {
                    if(mensaje.contains("Error")){
            %>
            <div class="alert alert-danger centrar-texto" role="alert" arial >
                <%=mensaje%>
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <%
                    } else if (mensaje.contains("Exito")) { %>
            <div class="alert alert-success centrar-texto" role="alert" arial >
                Cliente actualizado exitosamente
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <% } else { %>
            <div class="alert alert-warning centrar-texto" role="alert" arial >
                <%=mensaje%>
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <%      }
                    session.removeAttribute("msjCP");
                }
            %>
            <div>
                <h1 class="centrar-texto">Consultar Proveedor</h1>
            </div>
            <br>
            <form name="form" action="/ControladorProveedor" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="sel" >Buscar por:</label>
                            <select name="sel" class="tamañoConsultar" id="sel" required onchange="capturar()" >
                                <option value="" >Seleccione</option>
                                <option value="Todos" >Todos</option>
                                <option value="nom" >Nombre</option>
                                <option value="cod">Código</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="informacion" >Información:</label>
                            <input required name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button name="consultarProveedor" type="submit" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-search"></span>
                            </button>
                        </div>
                        <div class="col-md-1">
                        </div>
                    </div>
                </div>
            </form>
            <br>
            <br>
            <% if (session.getAttribute("proveedores") != null) { %>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table  class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="centrar-texto">Codigo</th>
                                        <th class="centrar-texto">NIT</th>
                                        <th class="centrar-texto">Nombre</th>
                                        <th class="centrar-texto">Contacto</th>
                                        <th class="centrar-texto">Telefono</th>
                                        <th class="centrar-texto">E-mail</th>
                                        <th  class="centrar-texto"></th>
                                    </tr>
                                </thead>
                                <%
                                    ArrayList<ProveedorDTO> dtos = (ArrayList) session.getAttribute("proveedores");
                                    for (int i = 0; i < dtos.size(); i++) {
                                %>
                                <tr>
                                    <td class="centrar-texto"><%=dtos.get(i).getCodigo()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getNit() %></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getNombre()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getNombreContacto()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getTelContacto()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getEmailContacto()%></td>
                                    <td class="centrar-texto">
                                        <a onclick="enviarFormOcultoProveedorActualizar('<%=dtos.get(i).getCodigo()%>',
                                                    '<%=dtos.get(i).getNit()%>', '<%=dtos.get(i).getNombre()%>',
                                                    '<%=dtos.get(i).getNombreContacto()%>', '<%=dtos.get(i).getTelContacto()%>',
                                                    '<%=dtos.get(i).getEmailContacto()%>', '<%=dtos.get(i).getSitioWeb()%>',
                                                    '<%=dtos.get(i).getCuenta()%>', '<%=dtos.get(i).getTipoCuenta()%>',
                                                    '<%=dtos.get(i).getNumCuenta()%>')" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </a>
                                        <a onclick="enviarFormOcultoProveedorMas('<%=dtos.get(i).getCodigo()%>',
                                                    '<%=dtos.get(i).getNit()%>', '<%=dtos.get(i).getNombre()%>',
                                                    '<%=dtos.get(i).getNombreContacto()%>', '<%=dtos.get(i).getTelContacto()%>',
                                                    '<%=dtos.get(i).getEmailContacto()%>', '<%=dtos.get(i).getSitioWeb()%>',
                                                    '<%=dtos.get(i).getCuenta()%>', '<%=dtos.get(i).getTipoCuenta()%>',
                                                    '<%=dtos.get(i).getNumCuenta()%>')" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-info-sign"></span>
                                        </a>
                                    </td>
                                </tr>
                                <%  } %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <%  session.removeAttribute("proveedores");
                } %>
            <form id="form-oculto" method="post">
                <input type="hidden" name="codigo" id="codigo">
                <input type="hidden" name="nit" id="nit">
                <input type="hidden" name="nombre" id="nombre">
                <input type="hidden" name="cuenta" id="cuenta">
                <input type="hidden" name="tipo" id="tipo">
                <input type="hidden" name="numero" id="numero">
                <input type="hidden" name="contacto" id="contacto">
                <input type="hidden" name="telefono" id="telefono">
                <input type="hidden" name="email" id="email">
                <input type="hidden" name="web" id="web">
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
