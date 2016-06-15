<%--
    Document   : actualizar
    Created on : 17-mar-2016, 14:36:35
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Actualizar Cliente</title>
        <meta charset="UTF-8">
        <!-- Procurar llamar los archivos .min porque pesan menos -->
        <link rel="stylesheet" href="../../Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../Css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="../../Imagenes/icono.ico">
        <!-- Script de Jquery 1.12-->
        <script src="../../Bootstrap/js/jquery.js"></script>
        <!-- Script de Bootstrap, agrega funcionalidad a la barra de navegacion -->
        <script src="../../Bootstrap/js/bootstrap.min.js"></script>
        <script src="../../Js/ubicacion.js"></script>
        <script src="../../Ajax/ajax.js"></script>
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Actualizar Cliente</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form onSubmit="actualizarCliente(document.forms[0]);
                    return false" id="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>DNI:</p>
                        </div>
                        <div class="col-md-3">
                            <input required readonly name="dni" value="<%=request.getParameter("dni")%>" type="number" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Nombres:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="nombre" value="<%=request.getParameter("nombre")%>" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Apellidos:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="apellido" value="<%=request.getParameter("apellido")%>" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>País:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="sel1" class="form-control" id="selPais" required onchange="cargarCiudades()">
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Ciudad:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="sel2" class="form-control" id="selCiudad" required>
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Dirección:</p>
                        </div>
                        <div class="col-md-3">
                            <input name="direccion" value="<%=request.getParameter("direccion")%>" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Teléfono:</p>
                        </div>
                        <div class="col-md-3">
                            <input name="telefono" type="number" value="<%=request.getParameter("telefono")%>" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>E-mail:</p>
                        </div>
                        <div class="col-md-3">
                            <input name="email" type="email" value="<%=request.getParameter("email")%>" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <br>
                    <div class="row centrar-texto">
                        <div class="col-md-4"></div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-lg letra">
                                Actualizar
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
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
