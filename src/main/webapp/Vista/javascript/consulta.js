/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $.ajax({
        url: '/Controlador?consultar=true',
        type: 'post',
        datatype: 'json',
        success: function(objs) {
            var json = eval('(' + objs + ')');
            for (var i = 0; i < json.length; i++) {
                $("#tabla tbody").after("<tr><td class='centrar-texto'>" + json[i].nombre+ " " +
                        json[i].apellido + "</td><td class='centrar-texto'>" + json[i].estadoCivil +
                        "</td><td class='centrar-texto'>" + json[i].nacimientoSt + 
                        "</td><td class='centrar-texto'>" + json[i].sueldo + "</td><td class='centrar-texto'>" 
                        + json[i].correo + "</td><td class='centrar-texto'>" + json[i].departamento + 
                        "</td><td class='centrar-texto'>" + json[i].ciudad + "</td></tr>");
            }
        }
    });
});