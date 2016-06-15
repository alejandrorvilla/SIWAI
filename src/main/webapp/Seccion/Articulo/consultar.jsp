<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:29:27
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="co.edu.ufps.siwai.modelo.dto.ArticuloDTO"%>
<%@page import="co.edu.ufps.siwai.modelo.dto.SucursalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.Fachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<%
    Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
    ArrayList<SucursalDTO> lista = fachada.consultarSucursal("Todos", "");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Articulo</title>
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
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Inicio del alert -->
        <%
            String mensaje = session.getAttribute("msjCA") + "";
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
                session.removeAttribute("msjCA");
            }
        %>
        <!-- Fin del alert-->
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Consultar Articulo</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form name="form" action="/ControladorArticulo" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                          <label for="sucursal">Sucursal:</label>
                            <select required name="sucursal" class="tamañoConsultar" id="sel1" >
                                <option value="" >Seleccione</option>
                                <option value="Todas" >Todas</option>
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
                                <option value="Todos">Todos</option>
                                <option value="Tipo" >Tipo</option>
                                <option value="Referencia" >Referencia</option>
                                <option value="Nombre">Nombre</option>
                            </select>
                        </div>

                        <div class="col-md-4">
                          <label for="informacion">Informacion:</label>
                            <input required id="informacion" name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button name="consultarArticulo" type="submit" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-search"></span>
                            </button>
                        </div>

                    </div>
                </div>
            </form>
            <br>
            <br>
            <%
                if (session.getAttribute("articulos") != null) {
                    ArrayList<ArticuloDTO> lis = (ArrayList) session.getAttribute("articulos");
                    if (!lis.isEmpty()) {
            %>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Sucursal</th>
                                        <th>Referencia</th>
                                        <th>Nombre</th>
                                        <th>Tipo</th>
                                        <th>Cantidad</th>
                                        <th>Valor</th>
                                    </tr>
                                </thead>
                                <%
                                    for (int i = 0; i < lis.size(); i++) {
                                %>
                                <tr>

                                    <td><%=lis.get(i).getSucursal().getNombre()%></td>
                                    <td><%=lis.get(i).getReferencia()%></td>
                                    <td><%=lis.get(i).getNombre()%></td>
                                    <td><%=lis.get(i).getTipoArticulo()%></td>
                                    <td><%=lis.get(i).getCantidad()%></td>
                                    <td><%=lis.get(i).getValor()%></td>
                                </tr>
                                <% }
                                    session.setAttribute("articulos", null);
                                %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <%
                    }
                }
            %>


            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
