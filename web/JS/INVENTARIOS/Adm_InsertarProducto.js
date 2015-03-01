$(function() {
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });

    $(document).on('click', '.elimnarFila', function() {
        var suma = sumaValoresSubcuenta();
        var valor = $(this).data('valor');
        var resta = parseInt(suma) - parseInt(valor);
        $('#vlrSumCuentasText').val(resta);
        $('#vlrSumCuentas').html(resta);
        $(this).closest('.filaAdicionada').remove();
    });

    $('#parametrizarPrecio').click(function() {
        document.getElementById('inv_BuscaProducto').submit();
    });
});

function insertar() {
    var suma = parseInt(sumaValoresSubcuenta());
    var costoAux = eliminarPuntos($('#vlrTotalText').val());
    var costo = parseInt(costoAux);
    if (suma == costo) {
        var costoSinDec = $('#producto_costo').val();
        costoSinDec = eliminarPuntos(costoSinDec);
        $('#producto_costo').val(costoSinDec);
        document.getElementById('inv_insertProducto').submit();
    } else {
        var diferencia = costo - suma;
        if (diferencia < 0) {
            $('#textoMsn').html('La suma de las subcuentas supera el costo del producto operacion no permitida');
            $('#mensaje').modal('show');
        } else if (diferencia < 3) {
            document.getElementById('inv_insertProducto').submit();
        } else {
            $('#textoMsn').html('Las sumas no coinciden por favor verifique he intente de nuevo');
            $('#mensaje').modal('show');
        }
    }

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
    var vlrInt = parseInt(eliminarPuntos(valor));
    if (valor == '') {
        $('#vlrProd').html('0');
        $('#vlrIvaText').val('0');
        $('#vlrIva').html('0');
    } else {        
        $('#vlrProd').html(valor);        
        var vlrIva = (vlrInt *16)/100;
        var vlrIvaMas = mascaraMonedaConValor(vlrIva.toString())
        $('#vlrIvaText').val(vlrIva);
        $('#vlrIva').html(vlrIvaMas);
        
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
        var vlrProd = $('#producto_costo').val();
        vlrProd = eliminarPuntos(vlrProd);
        var vlrIva = $('#vlrIvaText').val();
        var vlrTotal = parseInt(vlrProd) + parseInt(vlrIva);
        $('#vlrTotalText').val(vlrTotal);
        vlrTotal = mascaraMonedaConValor(vlrTotal.toString());
        $('#vlrTotal').html(vlrTotal);
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
                    var porcentaje = parseFloat(value.sbft_porcentaje);
                    var vlrTotal = parseFloat(eliminarPuntos($('#vlrTotalText').val()));
                    var vlrCuenta = parseFloat((vlrTotal * porcentaje) / 100);
                    adicionaDetalleSubucentasAgregadas(value.sbcu_codigo, vlrCuenta, value.sbcu_naturaleza, 'N', value.sbft_comentario, 'S');
                });
            } else {
                //Aqui se hace algo por si no hay nada parametrizado en el sistema
            }

        }
    });
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
 * @param {type} calculada: Indica si la adicion de la subcuenta fue calculada por el sistema(S) o por el usuario(U)
 * @returns {undefined}
 */
function adicionaDetalleSubucentasAgregadas(codigo, valor, naturaleza, elimina, comentario, calculada) {
    var valida = validaSubcuentasRepetidas(codigo);
    var valorMascara = mascaraMonedaConValor(valor);
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
                    $('#vlrSumCuentas').html(mascaraMonedaConValor(vlrTotalSuma.toString()));
                    var linea = '<tr class=\"filaAdicionada\">' +
                            '<td>' + data.objeto.sbcu_codigo + '<input type=\"hidden\" value=\"' + data.objeto.sbcu_codigo + '\" class=\"sbcu_codigoAdicionadas\" /> </td>' +
                            '<td>' + data.objeto.sbcu_nombre + '<input type=\"hidden\" value=\"' + data.objeto.sbcu_codigo + '&' + valor + '&' + calculada + '&C\" name=\"ArrayAddSubCuentas\" /></td>' +
                            '<td>' + natu + '</td>' +
                            '<td> $ ' + valorMascara + '<input type=\"hidden\" value=\"' + valor + '\" class=\"vlrSubCuentas\" /></td>';
                    if (elimina == 'S') {
                        linea += '<td><button type=\"button\" class=\"btn btn-danger elimnarFila\" data-valor=\"' + valor + '\" >';
                        linea += '<span class=\"glyphicon glyphicon-remove\" ></span> </button></td>';
                    } else {
                        linea += '<td>' + elimina + '</td>';
                    }
                    linea += '</tr>';
                    $('#subcuentasAdicionadas').append(linea);
                }
            }
        });
    }
}


function agrearCuenta() {
    var vlrAdd = $('#valorSubCuenta').val();
    vlrAdd = eliminarPuntos(vlrAdd);
    var vlrTotal = $('#vlrTotalText').val();
    vlrTotal = eliminarPuntos(vlrTotal);
    if (vlrAdd != '' && vlrAdd != '0') {
        if (parseInt(vlrAdd) <= parseInt(vlrTotal)) {
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
                        adicionaDetalleSubucentasAgregadas(data.objeto.sbcu_codigo, valor, 'C', 'S', '', 'U');
                        $('#valorSubCuenta').val('');
                    }
                }
            });
        } else {
            $('#textoMsn').html('El valor a adicionar debe ser menor o igual al valor total pagado por el producto');
            $('#mensaje').modal('show');
        }
    } else {
        $('#textoMsn').html('El valor que desea agregar a la lista de subcuentas debe ser diferente a nulo o a cero');
        $('#mensaje').modal('show');
    }
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

function obtenerAsientocontable(idTransac){
    var datos = new Object();
    datos.mvco_trans = idTransac;
    $.ajax({
        url: RutaSitio + "/AJAX/JSP/ajaxObtieneAsientoContable.jsp",
        async: false,
        data: datos,
        dataType: 'json',
        success: function(data, textStatus, jqXHR){
            var tabla = '';
            var cabezoteTabla = '<table class=\"table table-bordered table-hover\">'+
                    '<thead>' + 
                    '<tr>' + 
                        '<th class=\"alert alert-info\"> Descripcion Cuenta </th>' + 
                        '<th class=\"alert alert-info\"> Subcuenta </th>' +
                        '<th class=\"alert alert-info\"> Debitos </th>' +
                        '<th class=\"alert alert-info\"> Creditos </th>' +
                    '</tr>'+
                    '</thead>'
                    '<tbody>';
            var body = '';
             if(data.respuesta == 'Ok'){
                 if(data.list.length != 0){
                     for(var i = 0; i< data.list.length ; i++){
                        var objeto = data.list[i];
                        var linea = '<tr>'+
                             '<td>' + objeto.sbcu_nombre + '</td>'+
                             '<td>' + objeto.sbcu_codigo + '</td>';
                        if(objeto.debitos == 'N/A'){
                            linea +=    '<td> </td>'+
                                        '<td style=\"text-align:right\">' + objeto.creditos + '</td>';
                        }else{
                            linea +=    '<td style=\"text-align:right\">' + objeto.debitos + '</td>'+
                                        '<td> </td>';
                        }
                             linea += '</tr>';
                        body +=linea;
                     }
                     body += '</tbody></table>';
                 }
             }
             tabla = cabezoteTabla + body;
             $('#tablaAsientocontable').html(tabla);
        } 
    });
}

function calculaIva(){
        
}
