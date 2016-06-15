<%--
    Document   : registrar
    Created on : 17-mar-2016, 14:46:37
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar Comparacion</title>
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
        <script src="../../Ajax/ajax.js"></script>
    </head>
    <body onload="cargarSucursales(); cargarArticulosComparacion(<%=request.getParameter("codigoComparacion")%>)">
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Registrar Comparación</h1>
            </div>
            <br>
            <br>
            <!-- Inicio del formulario para registrar una comparacion -->
            <form action="/ControladorComparacion" method="post" onchange="return anular(event)">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Código:</p>
                        </div>
                        <div class="col-md-3">
                            <input readOnly required name ="codigo" type="text" class="form-control" value="<%=request.getParameter("codigoComparacion")%>">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Sucursal:</p>
                        </div>
                        <div class="col-md-3">
                            <select name="sucursales" class="form-control" id="sucursales" required>
                                <option value="">Seleccione</option>
                            </select>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Fecha:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="fecha" type="date" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Costo Transporte:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="transporte" type="number" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Notas:</p>
                        </div>
                        <div class="col-md-3">
                            <textarea name="notas" class="form-control" ></textarea>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <div class="table-responsive">
                                <table class="table" id="table">
                                    <thead id="cabecera">
                                        <tr >
                                            <th>Referencia</th>
                                            <th>Nombre</th>
                                            <th>Cantidad</th>
                                            <th>Precio Compra Unidad</th>
                                            <th>Precio Venta Unidad</th>
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
                            <button type="submit" name="registrarComparacion" class="btn btn-success btn-lg letra">Registrar
                            </button>
                        </div>
                        <div class="col-md-2">
                            <a  href="consultar.jsp" class="btn btn-danger btn-lg letra">Cancelar
                            </a>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <br>
                </div>
            </form>
            <!-- Fin del formulario para registrar una comparacion-->
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
