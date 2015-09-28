$(function () {
    $('.adcionaLista').click(function () {
        var valida = validaDatos();
        if (valida) {
            $('#tipoProducto').val(productoOreceta($('#codigo')));
            document.forms[0].submit();
        }
    });
});

function validaDatos() {
    var codigo = $('#codigo');
    var cant = $('#posicion');
    var archivo = $('#imagen');
    if (parseInt(cant.val()) >= 10 || parseInt(cant.val()) <= 0) {
        $('#textoMsn').html('La cantida ingresada no esta permitida el randgo es de 1 a 10');
        $('#mensaje').modal('show');
        return false;
    }
    if (cant.val().trim() == '') {
        $('#textoMsn').html('La cantidad no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    if (codigo.val().trim() == '') {
        $('#textoMsn').html('El codigo no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    if (archivo.val() == '') {
        $('#textoMsn').html('El archivo no puede estar vacio');
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}

function despuesEnter(valor) {
    if (valor == 1) {
        var codigo = $('#codigo');
        if (codigo.val().trim() == '') {
            $('#textoMsn').html('Para poder buscar el producto o la receta el campo codigo no puede ser nulo');
            $('#mensaje').modal('show');
        } else {
            buscaXCodigo(codigo);
        }
        $('#posicion').focus();
    }
}

function buscaXCodigo(codigo) {
    var tipoProd = productoOreceta(codigo);
    if (tipoProd == 'producto') {
        buscoProducto(codigo);
    } else if (tipoProd == 'receta') {
        buscaReceta(codigo);
    }
}

function productoOreceta(codigo) {
    var tipoProd = codigo.val().substring(0, 1);
    if (tipoProd == '1') {
        return 'producto';
    } else if (tipoProd == '3') {
        return 'receta';
    } else {
        $('#textoMsn').html('El tipo que desea ingresar en esta consulta no es valido por favor intente de nuevo con otro diferente');
        $('#mensaje').modal('show');
        return 'ninguno';
    }
}

function buscoProducto(codigo) {
    var datos = new Object();
    datos.dska_codigo = codigo.val();
    $.ajax({
        url: RutaSitio + "/consultaProdIXcod.action",
        data: datos,
        cache: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            $('#consultas').html('');
            if (data.respuesta == 'Ok') {
                linea = '<br/>';
                linea += '<div class="col-md-5 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="">DESCRIPCIÓN:</label>  ';
                linea += data.objeto.dska_desc;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-5 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="promPnd">PROMEDIO PONDERADO:</label>  ';
                linea += data.objeto.promPonderado;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-2 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="codigo">CODIGO:</label>  ';
                linea += data.objeto.dska_cod;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                $('#consultas').html(linea);
            } else {
                $('#textoMsn').html('El producto que esta buscando no existe');
                $('#mensaje').modal('show');
            }
        }
    });
}

function buscaReceta(codigo) {
    var datos = new Object();
    datos.rece_codigo = codigo.val();
    $.ajax({
        url: RutaSitio + "/buscaRecetaXCodigo.action",
        data: datos,
        cache: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            $('#consultas').html('');
            if (data.respuesta == 'Ok') {
                linea = '<br/>';
                linea += '<div class="col-md-5 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="">DESCRIPCIÓN:</label>  ';
                linea += data.objeto.rece_desc;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-5 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="promPnd">PROMEDIO PONDERADO:</label>  ';
                linea += data.objeto.rece_promedio;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                linea += '<div class="col-md-2 col-xs-12 col-sm-6 ">          ';
                linea += '    <div class="form-group">                        ';
                linea += '        <label for="codigo">CODIGO:</label>  ';
                linea += data.objeto.rece_codigo;
                linea += '    </div>                                          ';
                linea += '</div>                                              ';
                $('#consultas').html(linea);
            } else {
                $('#textoMsn').html(data.respuesta);
                $('#mensaje').modal('show');
            }
        }
    });

}

function eliminarFila(objeto) {
    var datos = new Object();
    datos.ppfa = $(objeto).data('ppfa');
    $.ajax({
        url: RutaSitio + "/eliminaItemPantallaPrincipal.action",
        data: datos,
        dataType: 'json',
        cache: false,
        success: function (data, textStatus, jqXHR) {
            if (data.respuesta == 'Ok') {
                $(objeto).closest('tr').remove();
            } else {
                $('#textoMsn').html(data.respuesta);
                $('#mensaje').modal('show');

            }
        }
    });
}