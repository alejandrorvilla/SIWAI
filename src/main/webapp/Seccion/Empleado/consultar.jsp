<%--
    Document   : consultar
    Created on : 17-mar-2016, 14:37:57
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.dto.EmpleadoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Consultar Empleado</title>
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
                if (session.getAttribute("msjAE") != null) {
                    String mensaje = "" + session.getAttribute("msjAE");
            %>
            <div id="alert" class="alert alert-success centrarDiv">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <%=mensaje%>
            </div>
            <%
                    session.setAttribute("msjAE", null);
                }
            %>
            <!-- Fin del Alert -->
            <!-- Inicio del alert -->
            <%
                String mensaje = session.getAttribute("msjCE") + "";
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
                    session.removeAttribute("msjCE");
                }
            %>
            <!-- Fin del alert-->
            <div>
                <h1 class="centrar-texto">Consultar Empleado</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form action="/ControladorEmpleado" method="post" name="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="sel" >Buscar por: </label>
                            <select name="sel" class="tamañoConsultar" id="sel" required onchange="capturar()" >
                                <option value="">Seleccione</option>
                                <option value="Todos">Todos</option>
                                <option value="Sucursal">Sucursal</option>
                                <option value="Dni">Dni</option>
                                <option value="Nombre">Nombre</option>
                                <option value="Codigo">Codigo</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <label for="informacion">Informacion: </label>
                            <input required name="informacion" type="text" class="tamañoConsultar">
                        </div>
                        <div class="col-md-1">
                            <button name="consultarEmpleado" type="submit" class="btn btn-success  letra">
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
            <%
                if (session.getAttribute("empleados") != null) {
                    ArrayList<EmpleadoDTO> lista = (ArrayList) session.getAttribute("empleados");
                    if (!lista.isEmpty()) {
            %>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table class="table" id="tablaE">
                                <thead>
                                    <tr>
                                        <th>Dni</th>
                                        <th>Nombre</th>
                                        <th>Sucursal</th>
                                        <th>Cargo</th>
                                        <th>Fecha Ingreso</th>
                                        <th>Fecha Salida</th>
                                        <th>Celular</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <%
                                    for (int i = 0; i < lista.size(); i++) {
                                %>
                                <tr>
                                    <td><%=lista.get(i).getDni()%></td>
                                    <td><%=lista.get(i).getNombre()%></td>
                                    <td><%=lista.get(i).getSucursal().getNombre() %></td>
                                    <td><%=lista.get(i).getCargo()%></td>
                                    <td><%=lista.get(i).getfIngreso()%></td>
                                    <td><%=lista.get(i).getfSalida()%></td>
                                    <td><%=lista.get(i).getCelular()%></td>
                                    <td>
                                        <a onclick="enviarFormOcultoEmpleadoActualizar(document,<%=i %>,'<%=lista.get(i).getCodigo()%>','<%=lista.get(i).getApellido() %>','<%=lista.get(i).getTelefono()%>',
                                                    '<%=lista.get(i).getEmail() %>','<%=lista.get(i).getDireccion() %>',
                                                    '<%=lista.get(i).getHabilitado() %>','<%=lista.get(i).getContraseña() %>')" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-edit asd "></span>
                                        </a>
                                        <a onclick="enviarFormOcultoEmpleadoMas(document,<%=i %>,'<%=lista.get(i).getApellido() %>','<%=lista.get(i).getTelefono()%>',
                                                    '<%=lista.get(i).getEmail() %>','<%=lista.get(i).getDireccion() %>',
                                                    '<%=lista.get(i).getHabilitado() %>')" style="cursor:pointer;">
                                            <span class="glyphicon glyphicon-info-sign asd "></span>
                                        </a>
                                    </td>
                                </tr>
                                <% }
                                    session.setAttribute("empleados", null);
                                %>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <%}
                }%>
            <!-- Fin del contenido principal-->
            <!-- Inicio del form oculto -->
            <form id="formOculto" method="post">
                <input type="hidden" id="sucursal" name="sucursal">
                <input type="hidden" id="codigo" name="codigo">
                <input type="hidden" id="cargo" name="cargo">
                <input type="hidden" id="dni" name="dni">
                <input type="hidden" id="nombre" name="nombre">
                <input type="hidden" id="apellido" name="apellido">
                <input type="hidden" id="telefono" name="telefono">
                <input type="hidden" id="celular" name="celular">
                <input type="hidden" id="email" name="email">
                <input type="hidden" id="direccion" name="direccion">
                <input type="hidden" id="fIngreso" name="fIngreso">
                <input type="hidden" id="fSalida" name="fSalida">
                <input type="hidden" id="habilitado" name="habilitado">
                <input type="hidden" id="contraseña" name="contraseña">
            </form>
            <!--- Fin del form oculto -->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
