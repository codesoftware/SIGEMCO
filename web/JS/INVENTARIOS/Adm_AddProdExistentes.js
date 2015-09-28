$(function() {
    $('[data-toggle="tooltip"]').tooltip()
});

function buscarProductoIndividual() {
    document.getElementById("inv_consPrIndAddExistente").submit();
}

function cambioMovimento(valor) {
    if (valor == '-1') {
        $('#promedio').hide('slow');
        $('#natuMov').val('');
    } else {
        var noProd = $('#numProd').val();
        if (noProd.trim() == '') {
            document.getElementById('movInv').value = '-1';
            $('#textoMsn').html('Primero debe Ingresar el numero de productos implicados en el movimiento de inventario para poder calcular el costo del movimiento');
            $('#mensaje').modal('show');
            return false;
        }
        var datos = new Object();
        datos.dska_dska = $('#idProducto').val();
        datos.mvin_mvin = valor;
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxDatosIngresarProdExistente.jsp",
            data: datos,
            dataType: 'json',
            success: function(data, textStatus, jqXHR) {
                var num = data.promedio.split(".");
                var moneda = mascaraMonedaConValor(num[0]);
                $('#promedio').show('slow');
                $('#promPonderado').val(moneda);
                $('#natuMov').val(data.naturaleza);
                if (data.naturaleza == 'Ingreso') {
                    $('#costo').prop('readonly', false);
                } else if (data.naturaleza == 'Egreso') {
                    var costo = num[0] * noProd;
                    $('#costo').prop('readonly', true);
                    $('#costo').val(costo);
                }
            }
        });
    }
}

function ingresoProducto() {
    var valida = validaDatos();
    if (valida) {
        document.getElementById("inv_addProdExstInv").submit();
    }
}

function validaDatos() {
    var prod = $('#numProd');
    prod.removeClass('has-error');
    var bool = $.isNumeric(prod.val());
    if (!bool) {
        $('#textoMsn').html('El campo numero de productos no puede ser nulo y debe ser numerico');
        $('#mensaje').modal('show');
        prod.addClass('has-error');
        return false;
    }
    var costo = $('#costo');
    costo.removeClass('has-error');
    bool = $.isNumeric(costo.val());
    if (!bool) {
        $('#textoMsn').html('El campo costo no puede ser nulo y debe ser numerico');
        $('#mensaje').modal('show');
        costo.addClass('has-error');
        return  false;
    }
    var mvIn = $('#movInv');
    mvIn.removeClass('has-error');
    if (mvIn.val() == '-1') {
        $('#textoMsn').html('Por Favor seleccione un movimiento de inventario');
        $('#mensaje').modal('show');
        mvIn.addClass('has-error');
        return  false;
    }

    var sede = $('#sedes');
    sede.removeClass('has-error');
    if (sede.val() == '-1') {
        $('#textoMsn').html('Por Favor seleccione la sede en la cual esta implicada el moviemiento de inventario');
        $('#mensaje').modal('show');
        sede.addClass('has-error');
        return  false;
    }
    if ($('#natuMov').val() == 'Egreso') {
        var cantidadesSede = validaCantidadesSede();
        if (!cantidadesSede) {
            return false;
        }
    }
    return true;
}

function validaCantidadesSede() {
    var datos = new Object();
    datos.dska_dska = $('#idProducto').val();
    datos.sede_sede = $('#sedes').val();
    var valida = true;
    $.ajax({
        data: datos,
        dataType: 'json',
        url: RutaSitio + "/AJAX/JSP/ajaxObtieneExistenciasProdXSede.jsp",
        async: false,
        success: function(data, textStatus, jqXHR) {
            if (data.respuesta == 'Ok') {
                var existencias = Number(data.cantidades);
                var productos = Number($('#numProd').val());
                if (productos > existencias) {
                    $('#textoMsn').html('El numero de existencias en la sede es:' + existencias + ' y no existen las cantidad de productos que desea retirar de la sede por favor revise la informacion');
                    $('#mensaje').modal('show');
                    valida = false;
                }
            }
        }
    });
    return valida;
}