<%-- 
    Document   : index
    Created on : 29-abr-2016, 15:47:06
    Author     : Alejandro Ramirez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  if(session.getAttribute("sesion") != null){
        response.sendRedirect("registro.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" href="Vista/css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="Vista/css/bootstrap.min.css">
        <script src="Vista/javascript/ajax.js"></script>
    </head>
    <body>
        <section>
            <div class="centrar-texto">
                <img src="Vista/imagenes/user.png" class="imagen-inicio">
            </div>
            <div class="centrar-texto well">
                <div class="form-inline">
                    <form onSubmit="iniciarSesion(document.forms[0]); return false">
                        <input required type="text" class="form-control" id="usuario" placeholder="Usuario">
                        <br>
                        <input required type="password" class="form-control espacio" id="contra" placeholder="Contraseña">
                        <br>
                        <button class="btn btn-danger espacio" onclick="" >Ingresar</button>
                    </form>
                </div>
            </div>
        </section>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
