<%--
    Document   : registrar
    Created on : 17-mar-2016, 14:44:37
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar Traslado</title>
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
        <!-- Script del Ajax-->
        <script src="../../Ajax/ajax.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body onload="cargarSucursales()">
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Registrar Traslado</h1>
            </div>
            <br>
            <!-- Inicio del formulario para crear el pedido -->
            <form onSubmit="crearTraslado(document.forms[0]);
                  return false" id="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-5">
                            <label for="sel" >Sucursal origen: </label>
                            <select name='sucursalOrigen' onchange="añadirSucursalesDestino()" id='sucursalOrigen' class='tamañoConsultar' required></select>
                        </div>
                        <div class="col-md-5">
                            <label for="informacion">Sucursal destino: </label>
                            <select name='sucursalDestino' id='sucursalDestino' class='tamañoConsultar' required></select>
                        </div>
                        <div class="col-md-1">
                            <button id="ok" class="btn btn-success  letra">
                                <span class="glyphicon glyphicons glyphicon-ok"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
            <br>
              <div id="nuevo-formulario"></div>

            <!--Tabla-->
                <div class="container" id="tabla">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <div class="table-responsive">
                                <table class="table" id="tablaT">
                                    <thead>
                                        <tr id="uno">
                                            <th>Referencia</th>
                                            <th>Nombre</th>
                                            <th>Cantidad</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row centrar-texto">
                        <div class="col-md-4"></div>
                        <div class="col-md-2">
                            <button onclick='registrarTraslado()' class="btn btn-success btn-lg letra">Registrar
                            </button>
                        </div>
                        <div class="col-md-2">
                            <a href="../Menu/menu.jsp" class="btn btn-danger btn-lg letra">Cancelar
                            </a>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </div>

            <!--Fin Tabla-->

            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
