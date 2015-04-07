
var valorSelect = '';
$(document).ready(function() {
    $('#listClase').change(function() {
            valorSelect = $('#listClase').val();
            var datos = new Object();
            datos.listClase = $('#listClase').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/consultaComboGrupo.html',
                async: false,
                success: function(response) {
                    var datos = JSON.parse(response);
                    if (datos.respuesta == 'OK') {
                        var tbody = '';
                        var codigo = valorSelect;

                            tbody = '<tr id=\"filaConsultaProd\" >' +
                                    '<td>Descripcion:</td>' +
                                    '<td>' + datos.objeto.dska_desc + '</td>' +
                                    '<td>Nombre:</td>' +
                                    '<td>' + datos.objeto.dska_nom_prod + '</td>' +
                                    '<td>Precio:</td>' +
                                    '<td>' + datos.objeto.precio + '</td>' +
                                    '<td>Existencias:</td>' +
                                    '<td>' + datos.objeto.cantExis + '</td>' +
                                    '</tr>';


                        $('#bodyConsulta').append(tbody);
                        $('#cantidad').focus();
                    } else {
                        $('#filaConsultaProd').remove();
                        $('#filaConsultaRem').remove();
                        $('#msnInfo').html('Producto Inexistente o ya no se encuentra en esta sede por favor intente de nuevo');
                        $('#informacionPopUp').modal('show');
                    }
                }
            });
        
    });

});