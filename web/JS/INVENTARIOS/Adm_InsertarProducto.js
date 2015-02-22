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
    } else {
        $('#vlrTotal').html(valor);
    }

}

function contabilizar() {
    $('.datosProd').hide('slow');
    $('.contabilidad').show('slow');
    $('#codigo_subcuenta').focus();
}

function datosProducto() {
    $('.datosProd').show('slow');
    $('.contabilidad').hide('slow');
    $('#producto_nombre').focus();
}

function despuesEnter(valor){
    if(valor=='1'){
        var datos = new Object();
        datos.sbcu_codigo = $('#codigo_subcuenta').val();
        $.ajax({
            url: RutaSitio + "/AJAX/JSP/ajaxValidaSubCuenta.jsp",
            data: datos,
            dataType: 'json',
            async: false,
            success: function(data, textStatus, jqXHR) {
                if(data.respuesta == 'inexistente'){
                    $('#textoMsn').html('La subcuenta ingresada es inexistente porfavor parametricela o verifique el codigo');
                    $('#mensaje').modal('show');  
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                }else if(data.respuesta == 'error'){
                    $('#textoMsn').html('Error al realizar la busqueda de la subcuenta por favor verifique e intente de nuevo');
                    $('#mensaje').modal('show');    
                    $('#tableFacturaCompra').html('');
                    $('#codigo_subcuenta').focus();
                }else{
                    var linea = '<td>Nombre:</td>'+
                                '<td>'+data.objeto.sbcu_nombre + '<input type=\"hidden\" id=\"sbcuElegida\" value=\"'+data.objeto.sbcu_sbcu+'\" /></td>';
                    var natu = '';
                    if(data.objeto.sbcu_naturaleza == 'C'){
                        natu = 'CREDITO';
                    }else{
                        natu = 'DEBITO';
                    }
                    linea += '<td>Naturaleza:</td>'+
                             '<td>'+natu+'</td>';
                    $('#tableFacturaCompra').html(linea);
                    $('#valorSubCuenta').focus();
                }
            }            
        });        
    }
}