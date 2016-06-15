<%--
    Document   : actualizar
    Created on : 17-mar-2016, 14:57:40
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Actualizar Sucursal</title>
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
        <script src="../../Js/ubicacion.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Actualizar Sucursal</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form onSubmit="actualizarSucursal(document.forms[0]);
                    return false" id="formulario">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Codigo:</p>
                        </div>
                        <div class="col-md-3">
                            <input required readOnly value="<%=request.getParameter("codigo")%>" name="codigo" type="text" class="form-control">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Nombre:</p>
                        </div>
                        <div class="col-md-3">
                            <input  required value="<%=request.getParameter("nombre")%>" name="nombre" type="text" class="form-control " >
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
                            <input name="telefono" value="<%=Integer.parseInt(request.getParameter("telefono")) %>" type="number" class="form-control " >
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>E-mail:</p>
                        </div>
                        <div class="col-md-3">
                            <input name="email" value="<%=request.getParameter("email")%>" type="email" class="form-control "  >
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Pagina web:</p>
                        </div>
                        <div class="col-md-3">
                            <input name="pagina" value="<%=request.getParameter("paginaWeb")%>" type="text" class="form-control "  >
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Direccion:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="direccion" value="<%=request.getParameter("direccion")%>" type="text" class="form-control " >
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Pais:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="sel1" class="form-control" id="selPais" required onchange="cargarCiudades()">
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Ciudad:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="sel2" class="form-control" id="selCiudad" required>
                            </select>                        </div>
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
                <br>
                <!-- Fin del formulario -->
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
