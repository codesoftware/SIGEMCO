function consultarProd() {
    document.getElementById('inv_ConsProdCambioSede').submit();
}

function ocultaDatosProd() {
    $('.datosProd').toggle();
}

function trasladar() {
    var datosOk = validaDatos();
    if(datosOk){
        document.getElementById('inv_cambioSedeProd').submit();
    }
}

function validaDatos() {
    var sedeOrigen = $('#sedeOrigen').val();
    if (sedeOrigen == '-1') {
        $('#mensaje').modal('show');
        $('#textoMsn').html('Por Favor seleccione una sede de origen');
        return false;
    }
    var sedeDestino = $('#sedeDestino').val();
    if (sedeDestino == '-1') {
        $('#mensaje').modal('show');
        $('#textoMsn').html('Por Favor seleccione una sede de destino');
        return false;
    }
    if (sedeDestino == sedeOrigen) {
        $('#mensaje').modal('show');
        $('#textoMsn').html('La sede destino y origen deben ser diferentes');
        return false;
    }
    var cantidad = $('#cantidadTraslado').val();
    if (cantidad == '' || cantidad == '0') {
        $('#mensaje').modal('show');
        $('#textoMsn').html('La cantidad de productos no puede ser cero ni nula');
        return false;
    }
    return true;
}

function cambiaSede(valor, opcion) {
    if (valor == '-1') {
        if (opcion == '1') {
            $('.existenciaOrigen').html('0');
        } else {
            $('.existenciaDest').html('0');
        }
    } else {
        var datos = new Object();
        var dska = $('#dska_dska').val();
        datos.dska_dska = dska;
        datos.sede_sede = valor;
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxBuscaExistenciasSede.jsp",
            data: datos,
            async: false,
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                if (data.respuesta == 'Ok') {
                    if (opcion == '1') {
                        $('.existenciaOrigen').html(data.cantidad);
                    } else {
                        $('.existenciaDest').html(data.cantidad);
                    }
                } else {
                    $('#mensaje').modal('show');
                    $('#textoMsn').html('Error al recuperar el numero de existencias por producto');
                }
            }
        });
    }
}
