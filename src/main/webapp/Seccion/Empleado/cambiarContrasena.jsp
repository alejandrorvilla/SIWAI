<%--
    Document   : registrar
    Created on : 17-mar-2016, 14:37:27
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="co.edu.ufps.siwai.modelo.Fachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Cambiar Contraseña</title>
        <meta charset="UTF-8">
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="../../Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../Css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="../../Imagenes/icono.ico">
        <!-- Script de Jquery 1.12-->
        <script src="../../Bootstrap/js/jquery.js"></script>
        <!-- Script de Bootstrap, agrega funcionalidad a la barra de navegacion -->
        <script src="../../Bootstrap/js/bootstrap.min.js"></script>
        <script src="../../Ajax/ajax.js"></script>
        <script src="../../Js/javascript.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Cambiar Contraseña</h1>
            </div>
            <br>
            <form onSubmit="cambiarContraseña(document.forms[0]);return false" id="form">
              <div class="container">
                <div class="row">
                  <div class="col-md-4"></div>
                  <div class="col-md-4">
                    <label for="contraActual">Contraseña actual:</label>
                    <input required name ="contraActual" type="password" placeholder="Contraseña" class="form-control">
                  </div>
                  <div class="col-md-4"></div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-4"></div>
                  <div class="col-md-4">
                    <label for="contraNueva">Contraseña nueva:</label>
                    <input required name ="contraNueva" type="password" placeholder="Contraseña" class="form-control">
                  </div>
                  <div class="col-md-4"></div>
                </div>
                <br>
                <div class="row">
                  <div class="col-md-4"></div>
                  <div class="col-md-4">
                    <label for="contraNueva2">Repetir contraseña nueva:</label>
                    <input required name ="contraNueva2" type="password" placeholder="Contraseña" class="form-control">
                  </div>
                  <div class="col-md-4"></div>
                </div>
                <div class="row">
                  <div class="col-md-4"></div>
                  <div class="col-md-4 text-center">
                    <button class="btn btn-success btn-sm espaciado">Guardar cambios</button>
                    <a href="../Menu/menu.jsp" class="btn btn-danger btn-sm espaciado espacioEntreBoton">Cancelar</a>
                  </div>
                  <div class="col-md-4"></div>
                </div>
              </div>
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
