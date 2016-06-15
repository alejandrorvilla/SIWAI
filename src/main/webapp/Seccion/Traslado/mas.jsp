<%--
    Document   : mas
    Created on : 17-mar-2016, 14:45:27
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }else if(request.getParameter("codigo")==null){
        response.sendRedirect("consultar.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Informacion Traslado</title>
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
        <script src="../../Js/javascript.js"></script>

    </head>
    <body onload="cargarArticulosTraslado(<%=request.getParameter("codigo")%>)">
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Informacion del Traslado</h1>
            </div>
            <br>
            <br>
            <!-- Inicio Div que contiene el formulario de solo lectura -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Sucursal Origen:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("sOrigen")%>" required  type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Sucursal Destino:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="<%=request.getParameter("sDestino")%>" required type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                  <div class="row">
                      <div class="col-md-1"></div>
                      <div class="col-md-10">
                          <div class="table-responsive">
                              <table id="tabla" class="table table-hover">
                                  <thead>
                                      <tr>
                                          <th>Referencia Articulo</th>
                                          <th>Cantidad</th>
                                      </tr>
                                  </thead>
                              </table>
                          </div>
                      </div>
                      <div class="col-md-1"></div>
                  </div>
                <div class="row">
                    <div class="col-md-10"></div>
                    <div class="col-md-2">
                        <a href="consultar.jsp" class="btn btn-success ">Volver
                        </a>
                    </div>
                    <div class="col-md-4"></div>

                </div>
            </div>
            <!-- Fin Div que contiene el formulario de solo lectura -->

            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
