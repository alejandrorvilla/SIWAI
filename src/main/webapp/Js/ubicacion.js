/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $.blockUI();
    $.ajax({
        url: '/ControladorUbicacion?cargarPaises=true',
        type: 'post',
        datatype: 'json',
        success: function(paises) {
            var json = eval('(' + paises + ')');
            var combo = document.getElementById("selPais");
            combo.options[0] = new Option('Seleccione', '');
            for (var i = 0; i < json.length; i++) {
                combo.options[combo.length] = new Option(json[i].nomPais, json[i].codPais);
            }
            $.unblockUI();
        }
    });
});

/**
 * Metodo que cargar en el combo selCiudad las ciudades del pais que se selecciona
 * @returns {undefined}
 */
function cargarCiudades(){
    $.blockUI();
    var posicion = document.getElementById("selPais").options.selectedIndex;
    var valor = document.getElementById("selPais").options[posicion].value;
    $.ajax({
        url: '/ControladorUbicacion?cargarCiudades=true&pais=' + valor,
        type: 'post',
        datatype: 'json',
        success: function(ciudades) {
            var json = eval('(' + ciudades + ')');
            var combo = document.getElementById("selCiudad");
            while(combo.length > 0){
                combo.remove(combo.length-1);
            }
            combo.options[0] = new Option('Seleccione', '');
            for (var i = 0; i < json.length; i++) {
                combo.options[combo.length] = new Option(json[i].nomCiudad, json[i].idCiudad);
            }
            $.unblockUI();
        }
    });
}