/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $.blockUI();
    $.ajax({
        url: '/ControladorProveedor?cargarProveedores=true',
        type: 'post',
        datatype: 'json',
        success: function(proveedores) {
            var json = eval('(' + proveedores + ')');
            var combo = document.getElementById("proveedor");
            combo.options[0] = new Option('Seleccione', '');
            for (var i = 0; i < json.length; i++) {
                combo.options[combo.length] = new Option(json[i].nombre, json[i].codigo);
            }
            $.unblockUI();
        }
    });
});
