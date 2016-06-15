<%--
    Document   : index
    Created on : 17-mar-2016, 14:24:44
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") != null) {
        response.sendRedirect("Seccion/Menu/menu.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>SIWAI</title>
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="Css/estilos.css">
        <script src="Bootstrap/js/jquery.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
        <link rel="shortcut icon" type="image/x-icon" href="Imagenes/icono.ico">
        <script src="Ajax/ajax.js"></script>
        <script src="Js/blockUI.js"></script>
    </head>
    <body>
        <!--Inicio - Div que contiene la imagen corporativa-->
        <div class="centrarDiv">
            <img src="Imagenes/Logo.png" alt="" class="imagen">
        </div>
        <!--Fin - Div que contiene la imagen corporativa-->
        <!--Inicio - Div que contiene el formulario para iniciar sesion-->
        <div class="centrarLogin well">
            <div  class="form-inline">
                <form onSubmit="iniciarSesion(document.getElementById('usuario'), document.getElementById('contra'));return false">
                <div id="dv1" class="form-group">
                    <label class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-user"></span></label>
                    <input required type="text" class="form-control" id="usuario" placeholder="Usuario">
                </div><br>
                <div id="dv2" class=" form-group espaciado ">
                    <label class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-lock"></span></label>
                    <input required type="password" class="form-control" id="contra" placeholder="Contraseña">
                </div><br>
                <button class="btn btn-danger espaciado" onclick="" >Ingresar</button>
                </form>
                <a href="#">¿Olvidaste tu contraseña?</a>
            </div>
        </div>
        <!-- Pie, no se incluye pie.jsp por diferencia de rutas en la imagen.-->
        <footer class="centrar-texto">
            <img src="Imagenes/logoufps.png" alt="" class="footerImagen"><br>
            <p>&copy Copyright 2016</p>
        </footer>
    </body>
</html>
