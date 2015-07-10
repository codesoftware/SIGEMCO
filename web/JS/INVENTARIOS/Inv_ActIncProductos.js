$(function () {
    $('#buscaProducto').click(function () {
        var codigo = $('#dska_codigo');
        if (codigo.val().trim() == '') {
            $('#textoMsn').html('El codigo no puede esta vacio');
            $('#mensaje').modal('show');
        } else {
            var datos = new Object();
            datos.dska_codigo = codigo.val();
            $.ajax({
                url: RutaSitio + "/buscaProductoXCodigo.action",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: datos,
                success: function (data, textStatus, jqXHR) {
                    $('#datosBasicos').html('');
                    if (data.respuesta != 'existente') {
                        $('#textoMsn').html('Producto inexistente en el sistema por favor verifiquelo e intente de nuevo');
                        $('#mensaje').modal('show');
                    } else {
                        var table = '';
                        table += '<table class="table table-bordered">                       ';
                        table += '    <thead>                                                            ';
                        table += '        <tr>                                                           ';
                        table += '            <td class="alert alert-success text-center" colspan="4">Datos Basicos Producto</td>';
                        table += '        </tr>                                                          ';
                        table += '    </thead>                                                           ';
                        table += '    <tbody>                                                            ';
                        table += '        <tr>           ';
                        table += '            <td>Codigo</td>  ';
                        table += '            <td>' + data.objeto.dska_cod + '</td>  ';
                        table += '            <td>Referencia</td>  ';
                        table += '            <td>' + data.objeto.dska_desc + '</td>  ';
                        table += '        </tr>          ';
                        table += '        <tr>           ';
                        table += '            <td>Promedio Ponderado</td>  ';
                        table += '            <td>' + data.objeto.promPonderado + '</td>  ';
                        if (data.objeto.dska_estado == 'A') {
                            table += '            <td colspan="2"><a class="btn btn-primary" onclick="cambiaEstado(\'' + data.objeto.dska_dska + '\',\'I\')" >Inactivar</a></td>  ';
                        } else {
                            table += '            <td colspan="2"><a class="btn btn-danger" onclick="cambiaEstado(\'' + data.objeto.dska_dska + '\',\'A\')">Activar</a></td>  ';
                        }
                        table += '        </tr>          ';
                        table += '    </tbody>                ';
                        table += '</table>                    ';
                        $('#datosBasicos').html(table);
                    }
                }
            });
        }
    });
});

function cambiaEstado(id, estado) {
    var datos = new Object();
    datos.dska_dska = id;
    datos.dska_estado = estado;
    $.ajax({
        url: RutaSitio + "/cambiarEstadoProducto.action",
        data: datos,
        cache: false,
        type: 'POST',
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data.respuesta == "Ok") {
                $('#textoMsn').html('Producto actualizado correctamente');
                $('#mensaje').modal('show');
            }else{
                $('#textoMsn').html('Error al cambiar de estado el producto: ' + data.respuesta );
                $('#mensaje').modal('show');
            }
            $('#datosBasicos').html('');
        }
    });
}