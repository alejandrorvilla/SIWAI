<%--
    Document   : consultar
    Created on : 28-abr-2016, 14:46:55
    Author     : Alejandro Ramirez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  if(session.getAttribute("sesion") == null){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar</title>
        <link rel="stylesheet" href="Vista/css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="Vista/css/bootstrap.min.css">
        <script src="Vista/javascript/consulta.js"></script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp"/>
        <section>
            <div class="centrar-texto">
                <h1>Consultar</h1>
                <br>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="table-responsive">
                            <table class="table" id="tabla">
                                <thead>
                                    <tr>
                                        <th class="centrar-texto">Nombre</th>
                                        <th class="centrar-texto">Estado Civil</th>
                                        <th class="centrar-texto">Nacimiento</th>
                                        <th class="centrar-texto">Sueldo</th>
                                        <th class="centrar-texto">Correo</th>
                                        <th class="centrar-texto">Departamento</th>
                                        <th class="centrar-texto">Ciudad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </section>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
