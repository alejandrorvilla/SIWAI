<%--
    Document   : registrar
    Created on : 17-mar-2016, 14:44:37
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar Pedido</title>
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
        <!-- Script que carga los proveedores en el select -->
        <script src="../../Js/proveedores.js"></script>
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Registrar Pedido</h1>
            </div>
            <br>
            <!-- Inicio del formulario para crear el pedido -->
            <form id="formPedido" onSubmit="crearPedido(document.forms[0]);
                    return false">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Proveedor:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="proveedor" class="form-control" id="proveedor" required>
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Fecha:</p>
                        </div>
                        <div class="col-md-3">
                            <input required  name="fecha" type="date" class="form-control">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <div class="row">
                        <br>
                        <div class="text-center">
                            <button id="crear" class="btn btn-success btn-lg letra">Crear Pedido</button>
                        </div>
                        <br>
                    </div>
                </div>
            </form>
            <div id="nuevo-formulario"></div>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
