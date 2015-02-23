$(function() {
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
});

function insertar() {
    document.getElementById('inv_insertProducto').submit();
}

function cleanForm() {
    document.getElementById('producto_nombre').value = "";
    document.getElementById('producto_descripcion').value = "";
    document.getElementById('producto_referencia').value = "";
    document.getElementById('producto_codigo').value = "";
    document.getElementById('producto_porcIva').value = "";
    document.getElementById('producto_marca').value = "";
    document.getElementById('producto_cantidad').value = "";
    document.getElementById('producto_costo').value = "";
    document.getElementById('producto_categoria').value = "-1";
    document.getElementById('gravamen').value = "-1";
    document.getElementById('sedes').value = "-1";
}

function cambioCategoria(valor) {
    if (valor == '-1') {
        $('.vencimiento').hide('slow');
        $('.regUnico').hide('slow');
    } else {
        var obj = new Object();
        obj.cate_cate = valor;
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxObtieneCategoria.jsp",
            type: 'POST',
            data: obj,
            dataType: 'json',
            success: function(data, textStatus, jqXHR) {
                determinaAccionDeCategoria(data)
            }
        });
    }
}

function muestraVencimiento() {
    $('.vencimiento').show('slow');
}

function muestraRegistroUnico() {
    $('.regUnico').show('slow');
    var cantidad = $('#producto_cantidad');
    cantidad.val('1');
    cantidad.attr('readonly', true);
}

function determinaAccionDeCategoria(objeto) {
    if (objeto.cate_runic == 'S') {
        muestraRegistroUnico();
    }
    if (objeto.cate_feven == 'S') {
        muestraVencimiento();
    }
}

function cambioVlr(valor) {
    if (valor == '') {
        $('#vlrTotal').html('0');
        $('#vlrTotalText').val('0');
    } else {
        $('#vlrTotal').html(valor);
        $('#vlrTotalText').val(valor);
    }

}

function validaDatosProducto() {
    var nombre = $('#producto_nombre').val();
    if (nombre == '') {
        $('#textoMsn').html('El nombre del producto no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var desc = $('#producto_descripcion').val();
    if (desc == '') {
        $('#textoMsn').html('La descripcion del producto no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var ref = $('#producto_referencia').val();
    if (ref == '') {
        $('#textoMsn').html('La referencia del producto no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    var marca = $('#producto_marca').val();
    if (marca == '') {
        $('#textoMsn').html('La marca del producto no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    var cant = $('#producto_cantidad').val();
    if (cant == '') {
        $('#textoMsn').html('La cantidad del producto no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var cost = $('#producto_costo').val();
    if (cost == '') {
        $('#textoMsn').html('El costo del producto no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var cat = $('#producto_categoria').val();
    if (cat == '-1') {
        $('#textoMsn').html('Por Favor seleccione una categoria para el producto');
        $('#mensaje').modal('show');
        return false;
    }
    var sedes = $('#sedes').val();
    if (sedes == '-1') {
        $('#textoMsn').html('Por Favor seleccione una sede para el producto');
        $('#mensaje').modal('show');
        return false;
    }

    return true;
}

function contabilizar() {
    var datosOk = validaDatosProducto();
    if (datosOk) {
        $('#subcuentasAdicionadas').children('tr').remove();
        buscaSubCuentasFijas();
        $('.datosProd').hide('slow');
        $('.contabilidad').show('slow');
        $('#codigo_subcuenta').focus();
    }
}

function datosProducto() {
    $('.datosProd').show('slow');
    $('.contabilidad').hide('slow');
    $('#producto_nombre').focus();
}

function despuesEnter(valor) {
    if (valor == '1') {
        var datos = new Object();
        datos.sbcu_codigo = $('#codigo_subcuenta').val();
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxValidaSubCuenta.jsp",
            data: datos,
            dataType: 'json',
            async: false,
            success: function(data, textStatus, jqXHR) {
                if (data.respuesta == 'inexistente') {
                    $('#textoMsn').html('La subcuenta ingresada es inexistente porfavor parametricela o verifique el codigo');
                    $('#mensaje').modal('show');
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                } else if (data.respuesta == 'error') {
                    $('#textoMsn').html('Error al realizar la busqueda de la subcuenta por favor verifique e intente de nuevo');
                    $('#mensaje').modal('show');
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                } else {
                    var linea = '<td>Nombre:</td>' +
                            '<td>' + data.objeto.sbcu_nombre + '<input type=\"hidden\" id=\"sbcuElegida\" value=\"' + data.objeto.sbcu_sbcu + '\" /></td>';
                    var natu = '';
                    if (data.objeto.sbcu_naturaleza == 'C') {
                        natu = 'CREDITO';
                    } else {
                        natu = 'DEBITO';
                    }
                    linea += '<td>Naturaleza:</td>' +
                            '<td>' + natu + '</td>';
                    $('#tableFacturaCompra').html(linea);
                    $('#valorSubCuenta').focus();
                }
            }
        });
    }
}

function buscaSubCuentasFijas() {
    var datos = new Object();
    datos.tido_nombre = 'FACTCOMPRA';
    $.ajax({
        url: RutaSitio + "/AJAX/JSP/ajaxBuscaSubcuentasFijasTipoDocumento.jsp",
        data: datos,
        dataType: 'json',
        async: false,
        success: function(data, textStatus, jqXHR) {
            if (data.respuesta == 'Ok') {
                $.each(data.obj, function(key, value) {
                    if (value.sbft_porcentaje == null) {
                        value.sbft_porcentaje = '0';
                    }
                    var porcentaje = parseInt(value.sbft_porcentaje);
                    var vlrTotal = parseInt($('#vlrTotalText').val());
                    var vlrCuenta = parseInt((vlrTotal * porcentaje) / 100);
                    adicionaDetalleSubucentasAgregadas(value.sbcu_codigo, vlrCuenta, value.sbcu_naturaleza, 'N', value.sbft_comentario);
                });
            } else {
                //Aqui se hace algo por si no hay nada parametrizado en el sistema
            }

        }
    })
}

/**
 * 
 * Funcion encargada de agregar una subcuenta a la lista de creditos
 * 
 * @param {type} codigo: Codigo con el cual identifica la subcuenta
 * @param {type} valor:  Valor al cuan le agregara a la cuenta
 * @param {type} naturaleza: naturaleza por la cual se adicionara al movimiento contable
 * @param {type} elimina: Indica si se puede eliminar esta fila por el usuario 
 * @param {type} comentario: Comentario especial esta adicion
 * @returns {undefined}
 */
function adicionaDetalleSubucentasAgregadas(codigo, valor, naturaleza, elimina, comentario) {
    var valida = validaSubcuentasRepetidas(codigo);
    if (valida) {
        var datos = new Object();
        datos.sbcu_codigo = codigo;
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxValidaSubCuenta.jsp",
            data: datos,
            dataType: 'json',
            async: false,
            success: function(data, textStatus, jqXHR) {
                if (data.respuesta == 'inexistente') {
                    $('#textoMsn').html('La subcuenta ' + comentario + ' no existe o no esta parametrizada en el sistema por favor revise e intente de nuevo');
                    $('#mensaje').modal('show');
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                } else if (data.respuesta == 'error') {
                    $('#textoMsn').html('Error al realizar la busqueda de la subcuenta por favor verifique e intente de nuevo');
                    $('#mensaje').modal('show');
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                } else {
                    var natu = '';
                    if (naturaleza == 'C') {
                        natu = 'CREDITO';
                    } else {
                        natu = 'DEBITO';
                    }
                    var vlrTotalSuma = parseInt(sumaValoresSubcuenta());
                    vlrTotalSuma = vlrTotalSuma + parseInt(valor);
                    $('#vlrSumCuentasText').val(vlrTotalSuma);
                    $('#vlrSumCuentas').html(vlrTotalSuma);
                    var linea = '<tr>' +
                            '<td>' + data.objeto.sbcu_codigo + '<input type=\"text\" value=\"' + data.objeto.sbcu_codigo + '\" class=\"sbcu_codigoAdicionadas\" /> </td>' +
                            '<td>' + data.objeto.sbcu_nombre + '</td>' +
                            '<td>' + natu + '</td>' +
                            '<td> $ ' + valor + '<input type=\"text\" value=\"' + valor + '\" class=\"vlrSubCuentas\" /></td>' +
                            '<td>' + elimina + '</td>' +
                            '</tr>';
                    $('#subcuentasAdicionadas').append(linea);
                }
            }
        });
    }
}


function agrearCuenta() {
    //var vlrSubCuenta = $('#valorSubCuenta').val();
    //var valor =  eliminarPuntos(vlrSubCuenta);
    var datos = new Object();
    datos.sbcu_codigo = $('#codigo_subcuenta').val();
    $.ajax({
        data: datos,
        dataType: 'json',
        async: false,
        url: RutaSitio + "/AJAX/JSP/ajaxValidaSubCuenta.jsp",
        success: function(data, textStatus, jqXHR) {
            if (data.respuesta == 'inexistente') {
                $('#textoMsn').html('La subcuenta ingresada es inexistente porfavor parametricela o verifique el codigo');
                $('#mensaje').modal('show');
                $('#tableFacturaCompra').html('');
                $('#codigo_subcuenta').focus();
            } else if (data.respuesta == 'error') {
                $('#textoMsn').html('Error al realizar la busqueda de la subcuenta por favor verifique e intente de nuevo');
                $('#mensaje').modal('show');
                $('#tableFacturaCompra').html('');
                $('#codigo_subcuenta').focus();
            } else {
                var vlrSubCuenta = $('#valorSubCuenta').val();
                var valor = eliminarPuntos(vlrSubCuenta);
                adicionaDetalleSubucentasAgregadas(data.objeto.sbcu_codigo, valor, 'C', 'S', '');
            }
        }
    });
}


function sumaValoresSubcuenta() {
    var valores = $('.vlrSubCuentas');
    if (valores.length == 0) {
        return 0;
    } else {
        var sumatoria = 0;
        $.each(valores, function(key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}

function validaSubcuentasRepetidas(subcuenta) {
    var subCuentas = $('.sbcu_codigoAdicionadas');
    var rta = true;
    if (subCuentas.length == 0) {
        return true;
    } else {
        $.each(subCuentas, function(key, value) {
            if (value.value == subcuenta && rta == true) {
                $('#textoMsn').html('Subcuenta agregada previamente operacion no permitida');
                $('#mensaje').modal('show');
                rta = false;
            }
        });
    }
    return rta;
}