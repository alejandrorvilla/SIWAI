<%--
    Document   : mas
    Created on : 17-mar-2016, 14:32:59
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Informacion del Articulo</title>
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
                <h1 class="centrar-texto">Informacion del Articulo</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Referencia:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="" name="referencia" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Nombre:</p>
                    </div>
                    <div class="col-md-3">
                        <input  readOnly value="" name="nombre" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Tipo:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="" name="tipo" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Sucursal:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="" name="sucursal" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Unidades disponibles:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value="" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Valor:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value='' type="text" class="form-control ">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Costo:</p>
                    </div>
                    <div class="col-md-3">
                        <input readOnly value='' type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Costo transporte:</p>
                    </div>
                    <div class="col-md-3">

                        <input readOnly value='' type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-md-10"></div>

                    <div class="col-md-2">
                        <a href="consultar.jsp" class="btn btn-success btn-lg">Volver</a>
                    </div>
                </div>
            </div>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
