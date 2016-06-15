<%--
    Document   : registrar
    Created on : 17-mar-2016, 14:37:27
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<%@page import="co.edu.ufps.siwai.modelo.dto.SucursalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.ufps.siwai.modelo.Fachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../../index.jsp");
    }%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar Empleado</title>
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
        <script src="../../Js/blockUI.js"></script>
    </head>
    <body>
        <%
            Fachada fachada = (Fachada) request.getSession().getAttribute("fachada");
            ArrayList<SucursalDTO> lista = fachada.consultarSucursal("Todos", "");
        %>
        <!-- Incluye la barra de navegacion que se encuentra en navegador.jsp -->
        <jsp:include page="../navegador.jsp" />
        <!-- Contenido principal contiene el formulario -->
        <section>
            <div>
                <h1 class="centrar-texto">Registrar Empleado</h1>
            </div>
            <br>
            <!-- Inicio del formulario -->
            <form onSubmit="registrarEmpleado(document.forms[0]);
                    return false" id="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <select name="sel1" class="form-control" id="sel1" required>
                                <option value="">Seleccione</option>
                                <% for (int i = 0; i < lista.size(); i++) {%>
                                <option value="<%=lista.get(i).getCodigo()%>"><%=lista.get(i).getNombre()%></option>
                                <% }
                                %>
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            <select name="sel2" class="form-control" id="sel2" required onchange="habilitar()">
                                <option value="">Seleccione</option>
                                <option value="Jefe de Operaciones">Jefe de operaciones</option>
                                <option value="Cajero">Cajero</option>
                                <option value="Administrador">Administrador</option>
                                <option value="Vendedor">Vendedor</option>

                            </select>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Codigo:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name ="codigo" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>DNI:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="dni" type="number" class="form-control ">
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
                            <input required name="nombre" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Apellido:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="apellido" type="text" class="form-control ">
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
                            <input name="telefono" type="number" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Celular:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="celular" type="number" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Email:</p>
                        </div>
                        <div class="col-md-3">
                            <input required name="email" type="email" class="form-control ">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                            <p>Direccion:</p>
                        </div>
                        <div class="col-md-3">
                            <input  required name="direccion" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-1">
                            <p>Fecha ingreso:</p>
                        </div>
                        <div class="col-md-3">
                            <input required  name="fIngreso" type="date" class="form-control">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-1">
                        </div>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <br>
                    <br>
                    <div class="row centrar-texto">
                        <div class="col-md-4"></div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-lg letra">Registrar
                            </button>
                        </div>
                        <div class="col-md-2">
                            <a href="../Menu/menu.jsp" class="btn btn-danger btn-lg letra">Cancelar
                            </a>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </div>
                <br>
            </form>
            <!-- Fin del contenido principal-->
        </section>
        <!-- Inluye el footer de la pagina a traves de pie.jsp-->
        <jsp:include page="../pie.jsp" />
    </body>
</html>
