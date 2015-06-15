function busGrupo(clas_clas) {
    document.getElementById('claseBusca').value = clas_clas;
    document.getElementById('buscaCuentasXGrup').submit();
}
function guardarScuenta() {
    document.getElementById("insertSubCuenta").submit();
}

function edicionSubcuentas(id) {
    var datos = new Object();
    datos.sbcu_sbcu = id;
    $.ajax({
        url: RutaSitio + "/buscaDatosEditarSubcuenta.action",
        data: datos,
        dataType: 'json',
        cache: false,
        success: function (data, textStatus, jqXHR) {
            if (data.respuesta != 'Ok') {
                $('#textoMsn').html(data.respuesta)
                $('#mensaje').modal('show');
            } else {
                $('#codigoSbcuTitulo').html(data.objeto.sbcu_codigo);
                var table = '<table class=\"table table-border\"> ' +
                        '<tr>' +
                            '<td>Nombre: </td>'+
                            '<td><input type=\"text\" value=\"' + data.objeto.sbcu_nombre + '\" class=\"form-control\"/></td>'+
                        '</tr>'+
                        '<tr>' +
                            '<td>Descripcion: </td>' +
                            '<td><input type=\"text\" value=\"' + data.objeto.sbcu_descripcion + '\" class=\"form-control\" /></td>' +
                        '</tr>'+
                        '<tr>' +
                            '<td>Naturaleza:</td>'+
                            '<td>'+
                                '<select class=\"form-control\" id=\"natuSbcuUpdate\" >' +
                                    '<option value=\"D\" >DEBITO</option>' +
                                    '<option value=\"C\" >CREDITO</option>' +
                                '</select> ' +
                            '</td>'+
                        '</tr>'+
                        '</table>';
                $('#tcontenidoUpdate').html(table);
                $('#updateSbcu').modal('show');
                var naturaleza = data.objeto.sbcu_naturaleza;
                document.getElementById('natuSbcuUpdate').value = naturaleza;
                alert('natu:' + naturaleza);
            }
        }
    });
}

