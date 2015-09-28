$(function () {
    $('#consultaProducto').click(function () {
        buscaProductos();
    });
    $('#adicionaProdReceta').click(function () {
        buscaProductos();
        adicionaProductoReceta();
    });
});

function despuesEnter(valor) {
    buscaProductos();
}

function buscaProductos() {
    var datos = new Object();
    datos.dska_codigo = $('#codigoProducto').val();
    $.ajax({
        url: RutaSitio + "/consultaProdIXcod.action",
        data: datos,
        cache: false,
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            $('#productoConsulta').html('');
            if (data.respuesta == 'Ok') {
                linea = '<br/>';
                linea += '<div class="col-md-4 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="">DESCRIPCIÓN:</label>  ';
                linea += data.objeto.dska_desc;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-4 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="promPnd">PROMEDIO PONDERADO:</label>  ';
                linea += data.objeto.promPonderado;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-4 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="codigo">CODIGO:</label>  ';
                linea += data.objeto.dska_cod;
                linea += ' <input type="hidden" id="productoAdicionarReceta" value="' + data.objeto.dska_dska + '" />';
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                $('#productoConsulta').html(linea);
            } else {
                $('#textoMsn').html('El producto que esta buscando no existe');
                $('#mensaje').modal('show');
            }
        }
    });
}

function adicionaProductoReceta() {
    var datos = new Object();
    datos.rece_rece = $('#idRecetaPrinc').val();
    datos.rece_dska = $('#productoAdicionarReceta').val();
    $.ajax({
        url: RutaSitio + "/addProdReceta.action",
        data: datos,
        dataType: 'json',
        cache: false,
        success: function (data, textStatus, jqXHR) {
            if (data.respuesta != 'Ok') {
                $('#textoMsn').html(data.respuesta);
                $('#mensaje').modal('show');
            } else {
                buscaProductosReceta();
            }
        }
    });
}

function buscaProductosReceta() {
    var datos = new Object();
    datos.rece_rece = $('#idRecetaPrinc').val();
    $.ajax({
        url: RutaSitio + "/consultaProductosReceta.action",
        data: datos,
        dataType: 'json',
        cache: false,
        success: function (data, textStatus, jqXHR) {
            $('#filasProdAdicionados').html('');
            if (data.respuesta != 'Ok') {
                $('#textoMsn').html('Error al buscar los productos de la receta o la receta no tiene productos asociados');
                $('#mensaje').modal('show');
            } else {
                var linea = '';
                $.each(data.objeto, function (index, value) {
                    linea += '<tr class="filaAdicionada"> ';
                    linea += '  <td>';
                    linea += value.dska_cod;
                    linea += '  </td>';
                    linea += '  <td>';
                    linea += value.dska_nombre;
                    linea += '  </td>';
                    linea += '  <td>$ ';
                    linea += value.repr_promedio;
                    linea += '  </td>';
                    linea += '  <td>';
                    linea += value.repr_cantidad;
                    linea += '  </td>';
                    linea += '  <td>';
                    linea += '<button type="button" class="btn btn-danger" onclick="eliminarFila(this)" data-dska="'+value.repr_dska+'" data-cantidad="'+value.repr_cantidad+'">';
                    linea += '<span class="glyphicon glyphicon-remove"></span> ';
                    linea += '</button>';                    
                    linea += '  </td>';
                    linea += '</tr> ';
                });
                $('#filasProdAdicionados').html(linea);
                var promedio = data.objReceta.rece_promedio;
                $('#costoRecetaLabel').html(promedio);
            }
        }
    });
}

function eliminarFila(obj){
    var datos = new Object();
    datos.rece_rece = $('#idRecetaPrinc').val();
    datos.rece_dska = $(obj).data('dska');
    $.ajax({
        url: RutaSitio + "/eliminaProductoReceta.action",
        data: datos,
        dataType: 'json',
        cache: false,
        success: function (data, textStatus, jqXHR) {
                buscaProductosReceta();
        }
    });
}