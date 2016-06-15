<%-- 
    Document   : registro
    Created on : 28-abr-2016, 12:35:03
    Author     : Alejandro Ramirez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("sesion") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
        <link rel="stylesheet" href="Vista/css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="Vista/css/bootstrap.min.css">
        <script src="Vista/javascript/ajax.js"></script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp"/>
        <section>
            <div class="centrar-texto">
                <h1>Registrar</h1>
                <br>
            </div>
            <form onSubmit="registrar(document.forms[0]);
                    return false" id="form">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1 col-lg-1"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Nombre:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input required name="nombre" type="text" class="form-control ">
                        </div>
                        <div class="col-md-2 col-lg-2"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Apellido</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input required name="apellido" type="text" class="form-control ">
                        </div>
                        <div class="col-md-1 col-lg-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1 col-lg-1"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Estado Civil:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <select name="sel1" class="form-control" id="selEstado" required>
                                <option value="">Seleccione</option>
                                <option value="Casado">Casado</option>
                                <option value="Soltero">Soltero</option>
                            </select>
                        </div>
                        <div class="col-md-2 col-lg-2"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Fecha de Nacimiento</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input value="" required  name="nacimiento" type="date" class="form-control">
                        </div>
                        <div class="col-md-1 col-lg-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1 col-lg-1"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Sueldo:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input required name="sueldo" type="number" class="form-control ">
                        </div>
                        <div class="col-md-2 col-lg-2"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Correo:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input name="email" type="email" class="form-control" required>
                        </div>
                        <div class="col-md-1 col-lg-1"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1 col-lg-1"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Departamento:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input name="dpto" type="text" class="form-control " required>
                        </div>
                        <div class="col-md-2 col-lg-2"></div>
                        <div class="col-md-1 col-lg-1">
                            <p>Ciudad:</p>
                        </div>
                        <div class="col-md-3 col-lg-3">
                            <input name="ciudad" type="text" class="form-control " required>
                        </div>
                        <div class="col-md-1 col-lg-1"></div>
                    </div>
                    <br>
                    <br>
                    <div class="row centrar-texto">
                        <div class="col-md-5 col-lg-5"></div>
                        <div class="col-md-2 col-lg-2">
                            <button class="btn btn-success btn-lg letra">
                                Registrar
                            </button>
                        </div>
                        <div class="col-md-4 col-lg-4"></div>
                    </div>
                </div>
                <br>
                <br>
            </form>
        </section>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
