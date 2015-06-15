$(function () {
    $('#ejecutaActulizacionSbcu').click(function () {
        var datos = new Object();
        datos.sbcu_sbcu = $('#nombreSbcuUpdate').data('sbcu');
        datos.sbcu_nombre = $('#nombreSbcuUpdate').val();
        datos.sbcu_descripcion = $('#descSbcuUpdate').val();
        datos.sbcu_naturaleza = $('#natuSbcuUpdate').val();
        $.ajax({
            url: RutaSitio + "/actualizaSbcu.action",
            type: 'POST',
            cache: false,
            data: datos,
            success: function (data, textStatus, jqXHR) {
                $('#updateSbcu').modal('hide');
                if (data.respuesta = 'Ok') {
                    $('#textoMsn').html('Subcuenta actualizada Correctamente');
                    setTimeout(function(){ location.reload(); }, 3000);
                } else {
                    $('#textoMsn').html('Error al actualizar la subcuenta');
                }
                $('#mensaje').modal('show');
            }
        });
    });
});

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
                        '<td>Nombre: </td>' +
                        '<td><input type=\"text\" value=\"' + data.objeto.sbcu_nombre + '\" class=\"form-control\" id=\"nombreSbcuUpdate\" data-sbcu=\"' + data.objeto.sbcu_sbcu + '\"/></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td>Descripcion: </td>' +
                        '<td><input type=\"text\" value=\"' + data.objeto.sbcu_descripcion + '\" class=\"form-control\" id=\"descSbcuUpdate\" /></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td>Naturaleza:</td>' +
                        '<td>' +
                        '<select class=\"form-control\" id=\"natuSbcuUpdate\" >' +
                        '<option value=\"D\" >DEBITO</option>' +
                        '<option value=\"C\" >CREDITO</option>' +
                        '</select> ' +
                        '</td>' +
                        '</tr>' +
                        '</table>';
                $('#tcontenidoUpdate').html(table);
                $('#updateSbcu').modal('show');
                var naturaleza = data.objeto.sbcu_naturaleza;
                document.getElementById('natuSbcuUpdate').value = naturaleza;
            }
        }
    });
}

