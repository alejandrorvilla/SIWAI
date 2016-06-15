<%--
    Document   : mas
    Created on : 17-mar-2016, 14:49:24
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Informacion del Proveedor</title>
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
                <h1 class="centrar-texto">Información del Proveedor</h1>
            </div>
            <br>
            <br>
            <!-- Div que contiene el formulario de solo lectura -->
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Codigo:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly name="codigo" value="<%=request.getParameter("codigo")%>" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>NIT:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly name="nit" value="<%=request.getParameter("nit")%>" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Nombre:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly value="<%=request.getParameter("nombre")%>" name="nombre" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Pagina web:</p>
                    </div>
                    <div class="col-md-3">
                        <input name="pagina" readOnly value="<%=request.getParameter("web")%>" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Telefono:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly value="<%=request.getParameter("telefono")%>" name="telefono" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>E-mail:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly value="<%=request.getParameter("email")%>" name="email" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Tipo cuenta bancaria:</p>
                    </div>
                    <div class="col-md-3">
                        <br>
                        <input required readOnly value="<%=request.getParameter("tipo")%>" name="tipoCuentaBancaria" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>N&ordm; cuenta bancaria:</p>
                    </div>
                    <div class="col-md-3">
                        <br>
                        <input required readOnly value="<%=request.getParameter("numero")%>" name="nCuentaBancaria" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-1">
                        <p>Cuenta Bancaria:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly value="<%=request.getParameter("cuenta")%>" name="cuentaBancaria" type="text" class="form-control ">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-1">
                        <p>Nombre contacto:</p>
                    </div>
                    <div class="col-md-3">
                        <input required readOnly value="<%=request.getParameter("contacto")%>" name="nombreContacto" type="text" class="form-control ">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-10"></div>

                    <div class="col-md-2">
                        <a href="consultar.jsp" class="btn btn-success btn-lg">Volver</a>
                    </div>
                </div>
                <!-- Fin del Div que contiene el formulario de solo lectura -->
            </div>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
