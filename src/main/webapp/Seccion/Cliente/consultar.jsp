<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:36:13
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="co.edu.ufps.siwai.modelo.dto.ClienteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                String mensaje = session.getAttribute("msjCC") + "";
                if (!mensaje.equals("null")) {
                    if (mensaje.contains("Error")) {
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
            <% } else {%>
            <div class="alert alert-warning centrar-texto" role="alert" arial >
                <%=mensaje%>
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <%      }
                    session.removeAttribute("msjCC");
                }
            %>
            <div>
                <h1 class="centrar-texto">Consultar Cliente</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form action="/ControladorCliente" method="post" name="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="sel" >Buscar por:</label>
                            <select name="sel" class="tamañoConsultar" id="sel" required onchange="capturar()" >
                                <option value="" >Seleccione</option>
                                <option value="Todos" >Todos</option>
                                <option value="nom" >Nombre</option>
                                <option value="dni">Dni</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="informacion" >Información:</label>
                            <input required name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button name="consultarCliente" type="submit" class="btn btn-success  letra">
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
            <% if (session.getAttribute("clientes") != null) { %>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table class="table" id="tabla-cliente">
                                <thead>
                                    <tr>
                                        <th class="centrar-texto">Dni</th>
                                        <th class="centrar-texto">Nombre</th>
                                        <th class="centrar-texto">Telefono</th>
                                        <th class="centrar-texto">E-mail</th>
                                        <th class="centrar-texto">País</th>
                                        <th class="centrar-texto">Ciudad</th>
                                        <th class="centrar-texto">Direccion</th>
                                        <th class="centrar-texto"></th>
                                    </tr>
                                </thead>
                                <%
                                    ArrayList<ClienteDTO> dtos = (ArrayList) session.getAttribute("clientes");
                                    for (int i = 0; i < dtos.size(); i++) {
                                %>
                                <tr>
                                    <td class="centrar-texto"><%=dtos.get(i).getDni()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getNombre() + " " + dtos.get(i).getApellido()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getTelefono()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getEmail()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getUbicacion().getNomPais()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getUbicacion().getNomCiudad()%></td>
                                    <td class="centrar-texto"><%=dtos.get(i).getDireccion()%></td>
                                    <td class="centrar-texto">
                                        <a onclick="enviarFormOcultoCliente('<%=dtos.get(i).getDni()%>',
                                                        '<%=dtos.get(i).getNombre()%>', '<%=dtos.get(i).getApellido()%>',
                                                        '<%=dtos.get(i).getTelefono()%>', '<%=dtos.get(i).getEmail()%>',
                                                        '<%=dtos.get(i).getDireccion()%>', '<%=dtos.get(i).getUbicacion().getCodPais()%>',
                                           <%=dtos.get(i).getUbicacion().getIdCiudad()%>)" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-edit asd"></span>
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
            <%  session.removeAttribute("clientes");
                }%>
            <form id="form-oculto" method="post" action="actualizar.jsp">
                <input type="hidden" name="dni" id="dni">
                <input type="hidden" name="nombre" id="nombre">
                <input type="hidden" name="apellido" id="apellido">
                <input type="hidden" name="telefono" id="telefono">
                <input type="hidden" name="email" id="email">
                <input type="hidden" name="direccion" id="direccion">
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
