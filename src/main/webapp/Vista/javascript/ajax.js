/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function iniciarSesion(documento){
    usuario = documento.elements[0].value;
    contraseña = documento.elements[1].value;
    var xhttp = new XMLHttpRequest();
    var text = "/Controlador?iniciarSesion=true&usuario=" + 
              usuario + "&contraseña=" + contraseña;
    xhttp.open("POST", text, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            if ((sub.indexOf('exito') >= 0)) {
                window.location = 'registro.jsp';
            } else if (sub.indexOf("fallo") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrar-texto'>" +
                        "Usuario y contraseña no coinciden</div>");
            }
        }
    }
}

function registrar(documento) {
    nombre = documento.elements[0].value;
    apellido = documento.elements[1].value;
    estado = documento.elements[2].value;
    nacimiento = documento.elements[3].value;
    sueldo = documento.elements[4].value;
    email = documento.elements[5].value;
    dpto = documento.elements[6].value;
    ciudad = documento.elements[7].value;
    var xhttp = new XMLHttpRequest();
    var url = "/Controlador?registrar=true&nombre=" + nombre + "&apellido=" +
            apellido + "&estado=" + estado + "&nacimiento=" + nacimiento +
            "&sueldo=" + sueldo + "&email=" + email + "&dpto=" + dpto + "&ciudad=" + ciudad;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var sub = xhttp.responseText;
            if (sub.indexOf("false") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-warning centrar-texto'>" +
                        "Error al registrar la persona</div>");
            } else if (sub.indexOf("Error") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-danger centrar-texto'>" +
                        "Error inesperado</div>");
            } else if (sub.indexOf("true") >= 0) {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrar-texto'>" +
                        "Persona registrado exitosamente</div>");
                $("#form")[0].reset();
            } else {
                $("div").remove("#alert");
                $("section").prepend("<div id='alert' class='alert alert-success centrar-texto'>" +
                         sub + "</div>");
                $("#form")[0].reset();
            }
        }
    }
}