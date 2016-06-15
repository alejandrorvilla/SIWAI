<%--
    Document   : actualizar
    Created on : 17-mar-2016, 14:39:01
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

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
        <title>Actualizar Empleado</title>
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
        <script src="../../Ajax/ajax.js"></script>
        <script src="../../Js/blockUI.js"></script>
        <script>
            window.onload = function () {
                document.getElementById("sel2").options.namedItem("<%=request.getParameter("cargo")%>").selected = true;
            }
        </script>
    </head>
    <body>
        <%
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            ArrayList<SucursalDTO> lista = fachada.consultarSucursal("Todos", "");
        %>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Actualizar Empleado</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form  onSubmit="actualizarEmpleado(document);
                    return false" name="form2">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <select name="sel1" class="form-control" id="sel1" required>
                                <option value="">Seleccione</option>
                                <% for (int i = 0; i < lista.size(); i++) {%>
                                <option <% if (request.getParameter("sucursal").equals(lista.get(i).getNombre())) { %> selected<%}%>
                                 value="<%=lista.get(i).getCodigo()%>" > <%=lista.get(i).getNombre()%></option>
                                    <% }
                                    %>
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            <select name="sel2" class="form-control" id="sel2" required onchange="habilitar()">
                                <option value="">Seleccione</option>
                                <option id="Jefe de Operaciones" value="Jefe de Operaciones">Jefe de Operaciones</option>
                                <option id="Cajero" value="Cajero">Cajero</option>
                                <option id="Administrador" value="Administrador">Administrador</option>
                                <option id="Vendedor" value="Vendedor">Vendedor</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>DNI:</p>
                        </div>
                        <div class="col-md-3">
                            <input readOnly value="<%=request.getParameter("dni")%>" required name="dni" id="dni" type="number" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Nombre:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("nombre")%>" required name="nombre" id="nombre" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>

                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Apellido:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("apellido")%>" required name="apellido" id="apellido" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Codigo:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("codigo")%>" name="codigo" id="codigo" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Celular:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("celular")%>" required name="celular" id="celular" type="number" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Telefono:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("telefono")%>" name="telefono" id="telefono"  type="number" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>E-mail:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("email")%>" name="email" id="email" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Direccion:</p>
                        </div>
                        <div class="col-md-3">
                            <input  value="<%=request.getParameter("direccion")%>" name="direccion" id="direccion" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Fecha ingreso:</p>
                        </div>
                        <div class="col-md-3">
                            <input value="<%=request.getParameter("fIngreso")%>" required  name="fIngreso" id="fIngreso" type="date" class="form-control">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-2">
                            <div class="radio">
                                <label><input value ="1"  <% if (request.getParameter("habilitado").equals("1")) {%>checked <%} %> type="radio" name="habilitado" id="r1">Habilitado</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="radio">
                                <label><input value="0"  <% if (request.getParameter("habilitado").equals("0")) {%>checked <%}%> type="radio" name="habilitado" id="r2">Deshabilitado</label>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </div>

                    <br>
                    <br>
                    <div class="row centrar-texto">
                        <div class="col-md-4"></div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-lg letra">Actualizar
                            </button>
                        </div>
                        <div class="col-md-2">
                            <a href="consultar.jsp" class="btn btn-danger btn-lg letra">Cancelar
                            </a>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </div>
                <br>
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
