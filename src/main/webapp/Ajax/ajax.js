/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function iniciarSesion(campo1, campo2) {
    usuario = campo1.value;
    contra = campo2.value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var text = "/ControladorEmpleado?usuario=" + usuario + "&contra=" + contra + "&iniciarSesion=true";
    xhttp.open("POST", text, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if ((sub.indexOf('nulo') >= 0)) {
                campo1.parentNode.className = " form-group has-error has-feedback";
                campo2.parentNode.className = "form-group espaciado has-error has-feedback";
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(campo1);
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(campo2);
            } else if (sub.indexOf("Error") >= 0) {
                if ($('#alert').length == 0) {
                    $("body").css("padding-top", "0%");
                    $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
                }
            } else {
                window.location = 'Seccion/Menu/menu.jsp';
            }
        }
    };
}

function cambiarContraseña(document) {
    contraActual = document.elements[0];
    contraNueva = document.elements[1];
    contraNueva2 = document.elements[2];
    var xhttp = new XMLHttpRequest();
    $.blockUI();
    var url = "/ControladorEmpleado?cambiarContra=true&contraActual=" + contraActual.value + "&contraNueva=" +
            contraNueva.value + "&contraNueva2=" + contraNueva2.value;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("incorrecta") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" + sub + "</div>");
                contraActual.parentNode.className = "col-md-4 has-error has-feedback";
                contraNueva.parentNode.className = "col-md-4";
                contraNueva2.parentNode.className = "col-md-4";
            } else if (sub.indexOf("coincide") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" + sub + "</div>");
                contraActual.parentNode.className = "col-md-4";
                contraNueva.parentNode.className = "col-md-4 has-error has-feedback";
                contraNueva2.parentNode.className = "col-md-4 has-error has-feedback";
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "Se realizaron los cambios satisfactoriamente" + "</div>");
                contraNueva.parentNode.className = "col-md-4";
                contraNueva2.parentNode.className = "col-md-4";
                contraActual.parentNode.className = "col-md-4";
                $("#form")[0].reset();
            } else if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "No se pudo realizar la modificación" + "</div>");
            }
        }
    };
}

function registrarSucursal(document) {
    codigo = document.elements[0];
    nombre = document.elements[1].value;
    telefono = document.elements[2].value;
    email = document.elements[3].value;
    paginaWeb = document.elements[4].value;
    direccion = document.elements[5].value;
    pais = document.elements[6].value;
    ciudad = document.elements[7].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorSucursal?registrarSucursal=true&codigo=" + codigo.value + "&nombre=" +
            nombre + "&telefono=" + telefono + "&email=" + email + "&paginaWeb=" + paginaWeb +
            "&direccion=" + direccion + "&ciudad=" + ciudad + "&pais=" + pais;
    xhttp.open("POST", url, true);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>Existe otra sucursal registrada con el codigo ingresado</div>");
                codigo.parentNode.className = " col-md-3 has-error has-feedback";
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(codigo);
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-success centrarDiv'>Sucursal registrada exitosamente</div>");
                $("#form")[0].reset();
            } else if (sub.indexOf("Por favor") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" + sub + "</div>");
            }
        }
    };
}

function registrarEmpleado(document) {
    sucursal = document.elements[0].value;
    cargo = document.elements[1].value;
    codigo = document.elements[2].value;
    dni = document.elements[3].value;
    nombre = document.elements[4].value;
    apellido = document.elements[5].value;
    telefono = document.elements[6].value;
    celular = document.elements[7].value;
    email = document.elements[8].value;
    direccion = document.elements[9].value;
    fIngreso = document.elements[10].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorEmpleado?registrarEmpleado=true&sucursal=" + sucursal + "&cargo=" + cargo +
            "&codigo=" + codigo + "&dni=" + dni + "&nombre=" + nombre + "&apellido=" + apellido + "&telefono=" + telefono +
            "&celular=" + celular + "&email=" + email + "&direccion=" + direccion +
            "&fIngreso=" + fIngreso;
    xhttp.open("POST", url, true);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>Existe otro empleado registrado con el DNI ingresado</div>");
                codigo.parentNode.className = " col-md-3 has-error has-feedback";
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(codigo);
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-success centrarDiv'>Empleado registrado exitosamente</div>");
                $("#form")[0].reset();
            }
        }
    };
}

/**
 * Metodo que recibe la peticion de registro de un cliente y la envia a ControladorCliente.
 * @param {type} document Formulario con los datosdel cliente.
 * @returns {undefined}
 */
function registrarCliente(document) {
    dni = document.elements[0].value;
    nombres = document.elements[1].value;
    apellidos = document.elements[2].value;
    ciudad = document.elements[4].value;
    direccion = document.elements[5].value;
    telefono = document.elements[6].value;
    email = document.elements[7].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorCliente?registrarCliente=true&dni=" + dni + "&nombre=" +
            nombres + "&apellido=" + apellidos + "&telefono=" + telefono +
            "&email=" + email + "&direccion=" + direccion + "&ciudad=" + ciudad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Fallo") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Existe otro cliente registrado con el DNI: " + dni + "</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("Exito") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Cliente registrado exitosamente</div>");
                $("#form")[0].reset();
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + sub + "</div>");
            }
        }
    };
}

/**
 * Metodo que se encarga de registrar articulo
 * @param {type} document
 * @returns {undefined}
 */
function registrarArticulo(document) {
    ref = document.elements[0].value;
    nombre = document.elements[1].value;
    tipo = document.elements[2].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticulo?registrarArticulo=true&referencia=" + ref + "&nombre=" +
            nombre + "&tipo=" + tipo;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Fallo") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Existe otro artículo registrado con la misma referencia: " + ref + "</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("Exito") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Articulo registrado exitosamente</div>");
                $("#form")[0].reset();
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + sub + "</div>");
            }
        }
    };
}
/**
 * Metodo que recibe la peticion de registro de un proveedor y la envia a ControladorPrveedor.
 * @param {type} document Formulario con los datos del proveedor.
 * @returns {undefined}
 */
function registrarProveedor(document) {
    codigo = document.elements[0].value;
    nit = document.elements[1].value;
    nombre = document.elements[2].value;
    web = document.elements[3].value;
    tipoCuentaBancaria = document.elements[4].value;
    nCuentaBancaria = document.elements[5].value;
    cuentaBancaria = document.elements[6].value;
    nombreContacto = document.elements[7].value;
    telefono = document.elements[8].value;
    email = document.elements[9].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorProveedor?registrarProveedor=true&codigo=" + codigo + "&nit=" +
            nit + "&nombre=" + nombre + "&web=" + web + "&telefono=" + telefono +
            "&email=" + email + "&tipoCuentaBancaria=" + tipoCuentaBancaria + "&nCuentaBancaria=" + nCuentaBancaria
            + "&cuentaBancaria=" + cuentaBancaria + "&nombreContacto=" + nombreContacto;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Fallo") >= 0) {
                mensaje = "Existe otro proveedor con el ";
                if (sub.indexOf("codigo") >= 0)
                    mensaje += " codigo: " + codigo;
                else if (sub.indexOf("nit") >= 0)
                    mensaje += "NIT: " + nit;
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + mensaje + "</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("Exito") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Proveedor registrado exitosamente</div>");
                $("#form")[0].reset();
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + sub + "</div>");
            }
        }
    };
}

function actualizarSucursal(documento) {
    codigo = documento.elements[0].value;
    nombre = documento.elements[1].value;
    telefono = documento.elements[2].value;
    email = documento.elements[3].value;
    paginaWeb = documento.elements[4].value;
    direccion = documento.elements[5].value;
    pais = documento.elements[6].value;
    ciudad = documento.elements[7].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorSucursal?actualizarSucursal=true&codigo=" + codigo + "&nombre=" + nombre +
            "&telefono=" + telefono + "&email=" + email + "&paginaWeb=" + paginaWeb + "&direccion=" + direccion + "&ciudad=" + ciudad +
            "&pais=" + pais;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("Por favor") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("true") >= 0) {
                window.location = 'consultar.jsp';
            }
        }
    };
}

/**
 * Metodo que recibe la peticion de actualizacion de un cliente y la envia a ControladorCliente.
 * @param {type} document Formulario con los datos del cliente.
 * @returns {undefined}
 */
function actualizarCliente(document) {
    dni = document.elements[0].value;
    nombres = document.elements[1].value;
    apellidos = document.elements[2].value;
    ciudad = document.elements[4].value;
    direccion = document.elements[5].value;
    telefono = document.elements[6].value;
    email = document.elements[7].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorCliente?actualizarCliente=true&dni=" + dni + "&nombre=" +
            nombres + "&apellido=" + apellidos + "&telefono=" + telefono +
            "&email=" + email + "&direccion=" + direccion + "&ciudad=" + ciudad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Fallo") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se encontro el cliente registrado con el DNI: " + dni + "</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.length == 0) {
                window.location = 'consultar.jsp';
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + sub + "</div>");
            }
        }
    };
}

/**
 * Metodo que recibe la peticion de actualizacion de un proveedor y la envia a ControladorPrveedor.
 * @param {type} document Formulario con los datos del proveedor.
 * @returns {undefined}
 */
function actualizarProveedor(document) {
    codigo = document.elements[0].value;
    nit = document.elements[1].value;
    nombre = document.elements[2].value;
    web = document.elements[3].value;
    telefono = document.elements[4].value;
    email = document.elements[5].value;
    tipoCuentaBancaria = document.elements[6].value;
    nCuentaBancaria = document.elements[7].value;
    cuentaBancaria = document.elements[8].value;
    nombreContacto = document.elements[9].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorProveedor?actualizarProveedor=true&codigo=" + codigo + "&nit=" +
            nit + "&nombre=" + nombre + "&web=" + web + "&telefono=" + telefono +
            "&email=" + email + "&tipoCuentaBancaria=" + tipoCuentaBancaria + "&nCuentaBancaria=" + nCuentaBancaria
            + "&cuentaBancaria=" + cuentaBancaria + "&nombreContacto=" + nombreContacto;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Fallo") >= 0) {
                mensaje = "Existe otro proveedor con el ";
                if (sub.indexOf("codigo") >= 0)
                    mensaje += " codigo: " + codigo;
                else if (sub.indexOf("nit") >= 0)
                    mensaje += "NIT: " + nit;
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + mensaje + "</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.length == 0) {
                window.location = 'consultar.jsp';
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                        + sub + "</div>");
            }
        }
    };
}
function actualizarEmpleado(document) {
    sucursal = document.getElementById("sel1").value;
    cargo = document.getElementById("sel2").value;
    dni = document.getElementById("dni").value;
    nombre = document.getElementById("nombre").value;
    apellido = document.getElementById("apellido").value;
    codigo = document.getElementById("codigo").value;
    celular = document.getElementById("celular").value;
    telefono = document.getElementById("telefono").value;
    email = document.getElementById("email").value;
    direccion = document.getElementById("direccion").value;
    fIngreso = document.getElementById("fIngreso").value;
    if (document.getElementById('r1').checked == true) {
        habilitado = document.getElementById("r1").value;
    } else {
        habilitado = document.getElementById("r2").value;
    }
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorEmpleado?actualizarEmpleado=true&sucursal=" + sucursal + "&cargo=" + cargo +
            "&dni=" + dni + "&nombre=" + nombre + "&apellido=" + apellido + "&codigo=" + codigo + "&celular=" + celular +
            "&telefono=" + telefono + "&email=" + email + "&direccion=" + direccion + "&fIngreso=" + fIngreso +
            "&habilitado=" + habilitado;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("true") >= 0) {
                window.location = 'consultar.jsp';
            }
        }
    };
}

function registrarArticuloExtra(document) {
    codigo = document.elements[0];
    sucursal = document.elements[1].value;
    nombre = document.elements[2].value;
    cantidad = document.elements[3].value;
    fEntrada = document.elements[4].value;
    costo = document.elements[5].value;
    valor = document.elements[6].value;
    notas = document.elements[7].value;
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticuloExtra?registrarArticuloExtra=true&codigo=" + codigo.value + "&sucursal=" +
            sucursal + "&nombre=" + nombre + "&cantidad=" + cantidad + "&fEntrada=" + fEntrada +
            "&costo=" + costo + "&valor=" + valor + "&notas=" + notas;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>Existe otro articulo extra registrado con el codigo ingresado</div>");
                codigo.parentNode.className = " col-md-3 has-error has-feedback";
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" + sub + "</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-success centrarDiv'>Articulo extra registrado exitosamente</div>");
                $("#form")[0].reset();
            } else if (sub.indexOf("Por favor") >= 0) {
                $("div").remove("#alert");
                $("body").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" + sub + "</div>");
            }
        }
    };
}

/**
 * Metodo que recibe la peticion para crear un pedido.
 * @param {type} document Datos del pedido.
 * @returns {undefined}
 */
function crearPedido(documento) {
    proveedor = documento.elements[0].value;
    fecha = documento.elements[1].value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorPedido?crearPedido=true&codProveedor=" +
            proveedor + "&fecha=" + fecha;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else {
                $("#nuevo-formulario").prepend("<div id='formulario-borrar'><h2 class='text-center'>Articulos</h2>" +
                        "<div class='container'>" +
                        "<div class='row'>" +
                        "<div class='col-md-1'></div>" +
                        "<div class='col-md-10'>" +
                        "<br>" +
                        "<div class='table-responsive'>" +
                        "<table class='table' id='table'>" +
                        "<thead>" +
                        "<tr>" +
                        "<th class='text-center'>Referencia</th>" +
                        "<th class='text-center'>Nombre</th>" +
                        "<th class='text-center'>Cantidad</th>" +
                        "<th></th>" +
                        "</tr>" +
                        "</thead>" +
                        "</table>" +
                        "</div>" +
                        "</div>" +
                        "<div class='col-md-1'></div>" +
                        "</div>" +
                        "<br>" +
                        "<br>" +
                        "<div class='row centrar-texto'>" +
                        "<div class='col-md-4'></div>" +
                        "<div class='col-md-2'>" +
                        "<button  name='enviarPedido' type='submit' onclick='registrarPedido()'" +
                        "class='btn btn-success btn-lg letra'>Registrar" +
                        "</button>" +
                        "</div>" +
                        "<div class='col-md-2'>" +
                        "<a href='../Menu/menu.jsp' class='btn btn-danger btn-lg letra'>Cancelar" +
                        "</a>" +
                        "</div>" +
                        "<div class='col-md-4'></div>" +
                        "</div>" +
                        "</div>" +
                        "<br>" +
                        "<br></div>");
                documento.elements[0].disabled = true;
                documento.elements[1].readOnly = true;
                $("#crear").remove();
                a = añadirFilaPedidos();
                document.getElementById("codigo" + a).focus();
            }
        }
    };
}

/**
 * Metodo que carga el nombre de un articulo en el pedido.
 * @param {type} referencia Referencia del articulo.
 * @returns {undefined}
 */
function cargarNombreArticuloPedido(campo, nombre, cantidad) {
    $.blockUI();
    referencia = campo.value;
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticulo?cargarNombreArticuloPedido=true&referencia=" + referencia;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div></div></div></div>");
            } else if (sub.indexOf("ArticuloDuplicado") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El artículo con referencia " + referencia + " ya esta en el pedido</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("ArticuloReferencia") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se encontro un artículo con la referencia: " + referencia + "</div></div></div></div>");
                nombre.value = "";
            } else {
                $("div").remove("#alert");
                nombre.value = sub;
                campo.readOnly = true;
                cantidad.readOnly = false;
                cantidad.focus();
            }
        }
    };
}

/**
 * Metodo que carga el nombre de un articulo en el pedido.
 * @param {type} referencia Referencia del articulo.
 * @returns {undefined}
 */
function aniadirArticuloPedido(campo1, campo2) {
    referencia = campo2.value;
    cantidad = campo1.value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorPedido?aniadirArticulo=true&referencia=" + referencia +
            "&cantidad=" + cantidad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error, intente de nuevo por favor</div></div></div></div>");
            } else if (sub.indexOf("Numero") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El campo cantidad solo recibe un numero entero mayor que 0</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Existe otro articulo con esa referencia en el pedido</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                campo1.readOnly = true;
                a = añadirFilaPedidos();
                document.getElementById("codigo" + a).focus();
            }
        }
    };
}

/**
 * Metodo que elimina un articulo del pedido
 * @param {type} referencia Referencia del articulo.
 * @returns {undefined}
 */
function eliminarArticuloPedido(referencia, fila) {
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorPedido?eliminarArticulo=true&referencia=" + referencia;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error, intente de nuevo por favor</div></div></div></div>");
            } else if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se pudo eliminar el artículo</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("#" + fila).remove();
            }
        }
    };
}

/**
 * Metodo que recibe la peticion de registro de un pedido y la envia a ControladorPedido.
 * @returns {undefined}
 */
function registrarPedido() {
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorPedido?registrarPedido=true";
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Ocurrio un error al intentar registrar el pedido</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Pedido registrado exitosamente</div>");
                $("div").remove("#formulario-borrar");
                document.getElementById("proveedor").value = "";
                document.getElementById("proveedor").disabled = false;
                document.getElementById("fecha").readOnly = false;
                document.getElementById("fecha").value = "";
                $("#boton").prepend("<button id='crear' class='btn btn-success btn-lg letra'>Crear Pedido</button>");
                reiniciarTablaPedido();
            }
        }
    };
}

/**
 * Metodo que carga el nombre de un articulo en la comparacion.
 * @param {type} referencia Referencia del articulo.
 * @returns {undefined}
 */
function cargarNombreArticuloComparacion(campo, nombre, cantidad) {
    for (var i = 1; i < ($('#table >thead >tr').length - 1); i++) {
        if (campo.value == document.getElementById("referencia" + (i)).value) {
            $("div").remove("#alert");
            $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                    "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                    "El artículo ya esta en la comparacion</div>");
            return;
        }
    }
    $.blockUI();
    referencia = campo.value;
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticulo?cargarNombreArticuloComparacion=true&referencia=" + referencia;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("ArticuloReferencia") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se encontro un artículo con la referencia " + referencia + "</div>");
                nombre.value = "";
            } else {
                $("div").remove("#alert");
                var fila = $('#table >thead >tr').length - 1;
                document.getElementById("precio" + (fila)).required = true;
                document.getElementById("costo" + (fila)).required = true;
                nombre.value = sub;
                campo.readOnly = true;
                campo.required = true;
                cantidad.required = true;
                cantidad.focus();
            }
        }
    };
}

/**
 * Metodo que recibe la peticion de registro de una comparacion  y la envia a ControladorComparacion.
 * @param {type} document Formulario con los datos de la comparacion.
 * @returns {undefined}
 */
function verificarComparacion(codigo) {
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorComparacion?verificarComparacion=true&codigo=" + codigo;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                enviarFormOcultoComparacion(document, codigo);
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El pedido ya tiene una comparación registrada</div>");
            }
        }
    };
}

function crearTraslado(document){
  sucursalOrigen = document.elements[0];
  sucursalDestino = document.elements[1];
  $.blockUI();
  var xhttp = new XMLHttpRequest();
  var url = "/ControladorTraslado?crearTraslado=true&sOrigen=" + sucursalOrigen.value + "&sDestino=" + sucursalDestino.value;
  xhttp.open("POST", url, true);
  xhttp.send();

  xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
          var sub = xhttp.responseText;
          $.unblockUI();
          if(sub.indexOf("Error")>=0){
            $("div").remove("#alert");
            $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                    "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                    "Error en la conexion a la base de datos</div>");
          }else{
            sucursalOrigen.disabled = true;
            sucursalDestino.disabled = true;
            $('#ok').hide();
            $('#tabla').show();
            c = añadirFilaTraslado();
            document.getElementById("referencia"+c).focus();
          }
      }
    };
}

function cargarNombreArticuloTraslado(campo, nombre, cantidad) {
    $.blockUI();
    referencia = campo.value;
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticulo?cargarNombreArticuloTraslado=true&referencia=" + referencia;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div></div></div></div>");
            } else if (sub.indexOf("ArticuloDuplicado") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El artículo con referencia " + referencia + " ya esta en el traslado</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("ArticuloReferencia") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se encontro un artículo con la referencia: " + referencia + " en la sucursal de origen</div></div></div></div>");
                nombre.value = "";
            } else {
                $("div").remove("#alert");
                nombre.value = sub;
                campo.readOnly = true;
                cantidad.readOnly = false;
                cantidad.focus();
            }
        }
    };
}

function aniadirArticuloTraslado(campo1, campo2) {
    referencia = campo2.value;
    cantidad = campo1.value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorTraslado?aniadirArticulo=true&referencia=" + referencia +
            "&cantidad=" + cantidad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error, intente de nuevo por favor</div></div></div></div>");
            } else if (sub.indexOf("Numero") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El campo cantidad solo recibe un numero entero mayor que 0</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Existe otro articulo con esa referencia en el traslado</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                campo1.readOnly = true;
                c = añadirFilaTraslado();
                document.getElementById("referencia"+c).focus();
            }else if (sub.indexOf("cantidad") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No dispone de la cantidad del articulo ingresada</div></div></div></div>");
                nombre.value = "";
            }
        }
    };
}

function registrarTraslado() {
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorTraslado?registrarTraslado=true";
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Ocurrio un error al intentar registrar el traslado</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Traslado registrado exitosamente</div>");
                document.getElementById("sucursalOrigen").disabled = false;
                document.getElementById("sucursalDestino").disabled= false;
                $('#sucursalOrigen').prop('selectedIndex',0);
                $('#sucursalDestino').prop('selectedIndex',0);
                $("#ok").show();
                eliminarTablaTraslado();
                $("#tabla").hide();
            }
        }
    };
}

function crearVenta(document){
  dniCliente = document.elements[0];
  vendedor = document.elements[1];
  $.blockUI();
  var xhttp = new XMLHttpRequest();
  var url = "/ControladorVenta?crearVenta=true&cliente=" + dniCliente.value + "&vendedor=" + vendedor.value;
  xhttp.open("POST", url, true);
  xhttp.send();

  xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
          var sub = xhttp.responseText;
          $.unblockUI();
          if(sub.indexOf("Error")>=0){
            $("div").remove("#alert");
            $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                    "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                    "Error en la conexion a la base de datos</div>");
          }else if(sub.indexOf("false")>=0){
            $("div").remove("#alert");
            $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                    "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                    "No se encontro el cliente con el dni ingresado</div>");
          }else{
            $("div").remove("#alert");
            dniCliente.disabled = true;
            vendedor.disabled = true;
            $('#ok').hide();
            $('#tabla').show();
            c = añadirFilaVentas();
            $("#referencia"+c).focus();
          }
      }
    };
}

function cargarNombreArticuloVenta(campo, nombre, cantidad,total) {
    $.blockUI();
    referencia = campo.value;
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorArticulo?cargarNombreArticuloVenta=true&referencia=" + referencia;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div></div></div></div>");
            } else if (sub.indexOf("ArticuloDuplicado") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El artículo con referencia " + referencia + " ya esta en la venta</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("ArticuloReferencia") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No se encontro un artículo con la referencia: " + referencia + "</div></div></div></div>");
                nombre.value = "";
            } else {
                $("div").remove("#alert");
                var res = sub.split("-");
                nombre.value = res[0];
                campo.readOnly = true;
                total.value =res[1];
                cantidad.readOnly = false;
                cantidad.focus();
            }
        }
    };
}

function aniadirArticuloVenta(campo1, campo2,total,totalI) {
    referencia = campo2.value;
    cantidad = campo1.value;
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorVenta?aniadirArticulo=true&referencia=" + referencia +
            "&cantidad=" + cantidad+"&valor="+(total.value)*cantidad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error, intente de nuevo por favor</div></div></div></div>");
            } else if (sub.indexOf("Numero") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "El campo cantidad solo recibe un numero entero mayor que 0</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Existe otro articulo con esa referencia en el traslado</div></div></div></div>");
                nombre.value = "";
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                campo1.readOnly = true;
                total.value = (total.value)*cantidad;
                a1 = parseInt(totalI.value);
                b1 = parseInt(total.value);
                totalI.value = a1+b1;
                c = añadirFilaVentas();
                document.getElementById("referencia"+c).focus();
            }else if (sub.indexOf("cantidad") >= 0) {
                $("div").remove("#alert");
                $("#nuevo-formulario").prepend("<div class='container'><div class='row'><div class='col-md-10 col-md-offset-1'><div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "No dispone de la cantidad del articulo ingresada</div></div></div></div>");
                nombre.value = "";
            }
        }
    };
}

function registrarVenta() {
    $.blockUI();
    var xhttp = new XMLHttpRequest();
    var url = "/ControladorVenta?registrarVenta=true";
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            $.unblockUI();
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Ocurrio un error al intentar registrar el traslado</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Error en la conexion a la base de datos</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrarDiv'>" +
                        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
                        "Se realizo la venta exitosamente</div>");
                document.getElementById("dniCliente").disabled = false;
                document.getElementById("vendedores").disabled= false;
                $('#vendedores').prop('selectedIndex',0);
                $("#ok").show();
                eliminarTablaVenta();
                $("#tabla").hide();
            }
        }
    };
}
