<%--
    Document   : navegador
    Created on : 17-mar-2016, 15:49:01
    Author     : Alejandro Ramirez; Marlon Guerrero.
--%>

<!-- Encabezado de todas las paginas a excepcion del index.jsp -->
<header class="container">
    <!-- Barra de navegacion -->
    <nav class = "navbar navbar-default navbar-fixed-top bg-col" role="navigation">
        <div class="container">
            <!-- Cabecera de la barra de navegacion -->
            <div class="navbar-header">
                <!-- Boton que aparece en pantallas peque�as, llama al id navigacion -->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navegacion">
                    <!-- Span para dispositivos de lectura -->
                    <span class="sr-only">Desplegar/Ocultar Menu</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <!-- Cierre del Boton que aparece en pantallas peque�as -->
                </button>
                <!-- Imagen que direcciona al menu.jsp-->
                <a class="navbar-brand" href="../Menu/menu.jsp">
                    <img class = "logoBarraNav" src="../../Imagenes/Logo.png">
                </a>
                <!-- Cierre de la cabecera de la barra de navegacion -->
            </div>
            <!-- Div que contiene las opciones de la barra de navegacion, el boton responsive accede a este menu-->
            <div class="navbar-collapse collapse" id="navegacion">
                <ul class="nav navbar-nav">
                    <!-- Dropdown para Sucursal -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Sucursal<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Sucursal/registrar.jsp">Registrar Sucursal</a></li>
                            <li><a href="../Sucursal/consultar.jsp">Consultar Sucursal</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Sucursal -->
                    </li>
                    <!-- Dropdown para Proovedor -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Proveedor<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Proveedor/registrar.jsp">Registrar Proveedor</a></li>
                            <li><a href="../Proveedor/consultar.jsp">Consultar Proveedor</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Proveedor -->
                    </li>
                    <!-- Dropdown para Empleado -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Empleado<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Empleado/registrar.jsp">Registrar Empleado</a></li>
                            <li><a href="../Empleado/consultar.jsp">Consultar Empleado</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Empleado -->
                    </li>
                    <!-- Dropdown para Cliente -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cliente<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Cliente/registrar.jsp" class=>Registrar Cliente</a></li>
                            <li><a href="../Cliente/consultar.jsp" class=>Consultar Cliente</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Cliente -->
                    </li>
                    <!-- Dropdown para Articulo -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Articulo<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Articulo/registrar.jsp">Registrar Articulo</a></li>
                            <li><a href="../Articulo/consultar.jsp">Consultar Articulo</a></li>
                            <li><a href="../ArticuloExtra/registrar.jsp">Registrar Articulo Extra</a></li>
                            <li><a href="../ArticuloExtra/consultar.jsp">Consultar Articulo Extra</a></li>
                            <li><a href="../Pedido/registrar.jsp">Registrar Pedido</a></li>
                            <li><a href="../Pedido/consultar.jsp">Consultar Pedido</a></li>
                            <li><a href="../Traslado/registrar.jsp">Registrar Traslado</a></li>
                            <li><a href="../Traslado/consultar.jsp">Consultar Traslado</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Articulo -->
                    </li>
                    <!-- Dropdown para Venta -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ventas<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="../Venta/registrar.jsp">Registrar Venta</a></li>
                            <li><a href="../Venta/consultar.jsp">Consultar Ventas</a></li>
                        </ul>
                        <!-- Cierre del Dropdown para Venta -->
                    </li>
                </ul>
                <!-- Mensaje de Bienvenida y Boton para cerrar Sesion -->
                <ul class="nav navbar-nav navbar-right">
                    <li><p class="navbar-text">Bienvenido <%=request.getSession().getAttribute("usuario") %></p></li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class=" glyphicon glyphicone glyphicon-cog"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="../Empleado/cambiarContrasena.jsp">Cambiar Contrase&ntildea</a></li>
                          <li><a href="/ControladorEmpleado?cerrarSesion=true">Cerrar Sesion</a></li>
                      </ul>

                    </li>
                    <!-- Cierre del Mensaje de Bienvenida y Boton para cerrar Sesion -->
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>
    <!-- Fin del encabezado del cuerpo del proyecto -->
</header>
