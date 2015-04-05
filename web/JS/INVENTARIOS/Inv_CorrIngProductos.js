function consultarProd() {
    document.getElementById('inv_ConsProdCorrIngresos').submit();
}

function ocultaDatosProd() {
    $('.datosProd').toggle();
}

function cambiaSede(valor) {
    if (valor == '-1') {
        $('.existencia').html('0');
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
                    $('.existencia').html(data.cantidad);
                } else {
                    $('#mensaje').modal('show');
                    $('#textoMsn').html('Error al recuperar el numero de existencias por producto');
                }
            }
        });
    }
}

function corregir() {
    var datosOk = validaDatos();
    if (datosOk) {
        document.getElementById('Inv_CorrIngresoProd').submit();
    }
}

function validaDatos() {
    var sede = $('#sede').val();
    if (sede == '-1') {
        $('#mensaje').modal('show');
        $('#textoMsn').html('Por Favor seleccione la sede implicada');
        return false;
    }
    var cantidad = $('#cantidadCorreccion').val();
    if (cantidad == 0 || cantidad == '') {
        $('#mensaje').modal('show');
        $('#textoMsn').html('La cantidad no puede ser nula o igual a cero');
        return false;
    }
    var existencias = $('.existencia').html();
    if(parseInt(cantidad) > parseInt(existencias)){
        $('#mensaje').modal('show');
        $('#textoMsn').html('La cantidad de productos a corregir no puede ser mayor a las existencias en la sede');
        return false;        
    }
    return true;
}