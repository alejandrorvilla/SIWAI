<%--
    Document   : mas
    Created on : 17-mar-2016, 14:40:16
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Informacion del Empleado</title>
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
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Información del Empleado</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-4">
                        <select name="sel1" class="form-control" id="sel1" required disabled>
                            <option value=""><%=request.getParameter("sucursal")%></option>
                        </select>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <select name="sel2" class="form-control" id="sel2" required onchange="habilitar()" disabled>
                            <option value=""><%=request.getParameter("cargo")%></option>
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
                        <input readOnly value="<%=request.getParameter("dni")%>" required  type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Nombre:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("nombre")%>" required type="text" class="form-control ">
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
                        <input  readOnly value="<%=request.getParameter("apellido")%>" required  type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Telefono:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("telefono")%>" required  type="text" class="form-control ">
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
                        <input readOnly value="<%=request.getParameter("celular")%>"  type="number" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>E-mail:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("email")%>" required  type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Direccion:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("direccion")%>" required  type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Fecha Ingreso:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("fIngreso")%>"  type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Fecha salida:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly  value="fSalida"  type="date" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-2">
                        <div class="radio">

                            <label><input readOnly <% if (request.getParameter("habilitado").equals("1")) {%>checked <%} %> disabled value ="0" type="radio" name="radio">Habilitado</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="radio">
                            <label><input readOnly <% if (request.getParameter("habilitado").equals("0")) {%>checked <%} %> disabled value="1" type="radio" name="radio">Deshabilitado</label>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-10"></div>
                    <div class="col-md-2">
                        <a href="consultar.jsp" class="btn btn-success btn-lg ">Volver
                        </a>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
