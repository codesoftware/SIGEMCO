$(function () {
    $(document).on('click', '.elimnarFila', function () {
        var suma = sumaValoresSubcuenta();
        var valor = $(this).data('valor');
        var resta = parseInt(suma) - parseInt(valor);
        $('#vlrSumCuentasText').val(resta);
        $('#vlrSumCuentas').html(resta);
        $(this).closest('.filaAdicionada').remove();
    });

    $('#codigo_subcuenta').keyup(function (event) {
        var datos = new Object();
        datos.sbcu_codigo = $(this).val();
        if (datos.sbcu_codigo.length == 1) {
            $.ajax({
                url: RutaSitio + "/consultaSubCuentaXCodigo.action",
                data: datos,
                dataType: 'json',
                success: function (data, textStatus, jqXHR) {
                    $('#codigo_subcuenta').autocomplete({
                        source: data,
                        select: function (event, ui) {
                        }
                    });
                }
            });
        }
    });
});

function buscarProductoIndividual() {
    document.getElementById('inv_consProdForAddEx').submit();
}
function ocultaDatosProd() {
    $('.datosProd').toggle();
}

function contabilizar() {
    var validaDatos = validaDatosProducto();
    if (validaDatos) {
        //Multiplicacion de Productos
        var cantidad = eliminarPuntos($('#numProductos').val());
        cantidad = parseInt(cantidad);
        var precioProducto = eliminarPuntos($('#costo').val()); 
        precioProducto = parseInt(precioProducto);
        var totalProducto = cantidad * precioProducto;
        $('#costo').val(totalProducto);
        //Logica para contabilizar
        var vlr = $('#costo').val();
        cambioVlr(vlr);
        $('.datosProdDiv').hide();
        $('.datosContabiliza').show('fast');
        var vlrProd = $('#costo').val();
        vlrProd = eliminarPuntos(vlrProd);
        var vlrIva = $('#vlrIvaText').val();
        var vlrTotal = parseInt(vlrProd) + parseInt(vlrIva);
        $('#vlrTotalText').val(vlrTotal);
        vlrTotal = mascaraMonedaConValor(vlrTotal.toString());
        $('#vlrTotal').html(vlrTotal);
    }

}

function validaDatosProducto() {
    var numProd = $('#numProductos').val();
    if (numProd == '') {
        $('#textoMsn').html('El numero de productos no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var costo = $('#costo').val();
    if (costo == '') {
        $('#textoMsn').html('El costo de los productos no puede se nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var sede = $('#sedes').val();
    if (sede == '-1') {
        $('#textoMsn').html('Por Favor seleccione la sede a la cual ingresan los productos');
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}

function datosProducto() {
    $('#costo').val('');
    
    $('.datosProdDiv').show('fast');
    $('.datosContabiliza').hide();
}

function cambioVlr(valor) {
    var vlrInt = parseInt(eliminarPuntos(valor));
    if (valor == '') {
        $('#vlrProd').html('0');
        $('#vlrIvaText').val('0');
        $('#vlrIva').html('0');
    } else {
        $('#vlrProd').html(valor);
        var vlrIva = (vlrInt * 16) / 100;
        var vlrIvaMas = mascaraMonedaConValor(vlrIva.toString())
        $('#vlrIvaText').val(vlrIva);
        $('#vlrIva').html(vlrIvaMas);

    }
}

function despuesEnter(valor) {
    if (valor == '1') {
        validaGuionesSubcuenta();
        var datos = new Object();
        datos.sbcu_codigo = $('#codigo_subcuenta').val();
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxValidaSubCuenta.jsp",
            data: datos,
            dataType: 'json',
            async: false,
            success: function (data, textStatus, jqXHR) {
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

//Funcion con la cual agrego cuentas a la lista
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
                success: function (data, textStatus, jqXHR) {
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
            success: function (data, textStatus, jqXHR) {
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
/**
 * Funcion la cual valida si la subcuenta que van ha ingresar ya se encuentra adicionada a la lista
 * @param {type} subcuenta
 * @returns {Boolean}
 */
function validaSubcuentasRepetidas(subcuenta) {
    var subCuentas = $('.sbcu_codigoAdicionadas');
    var rta = true;
    if (subCuentas.length == 0) {
        return true;
    } else {
        $.each(subCuentas, function (key, value) {
            if (value.value == subcuenta && rta == true) {
                $('#textoMsn').html('Subcuenta agregada previamente operacion no permitida');
                $('#mensaje').modal('show');
                rta = false;
            }
        });
    }
    return rta;
}

/**
 * Funcion la cual se encarga de sumar todos los valores de las subcuentas
 * @returns {aux|Number}
 */
function sumaValoresSubcuenta() {
    var valores = $('.vlrSubCuentas');
    if (valores.length == 0) {
        return 0;
    } else {
        var sumatoria = 0;
        $.each(valores, function (key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}

function insertar() {
    var suma = parseInt(sumaValoresSubcuenta());
    var costoAux = eliminarPuntos($('#vlrTotalText').val());
    var costo = parseInt(costoAux);
    if (suma == costo) {
        var costoSinDec = $('#costo').val();
        costoSinDec = eliminarPuntos(costoSinDec);
        $('#costo').val(costoSinDec);
        document.getElementById('inv_ejecutaAddProdEx').submit();
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

function validaGuionesSubcuenta() {
    var valor = $('#codigo_subcuenta').val();
    var vector = valor.split('-');
    $('#codigo_subcuenta').val(vector[0].trim());

}