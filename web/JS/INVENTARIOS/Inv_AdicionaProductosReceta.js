$(function () {
    $('#consultaProducto').click(function () {
        buscaProductos();
    });
    $('#adicionaProdReceta').click(function(){
        
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
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
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