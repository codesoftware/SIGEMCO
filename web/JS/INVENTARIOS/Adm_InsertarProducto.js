$(function(){
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

function cambioCategoria(valor){
    if(valor == '-1'){
        $('.vencimiento').hide('slow');
        $('.regUnico').hide('slow');
    }else{
        var obj = new Object();
        obj.cate_cate = valor;
        $.ajax({
            url: RutaSitio+"/AJAX/JSP/ajaxObtieneCategoria.jsp",
            type: 'POST',
            data: obj,
            dataType: 'json',
            success: function(data, textStatus, jqXHR) {
                determinaAccionDeCategoria(data)
            }
        });
    }
}

function muestraVencimiento(){
    $('.vencimiento').show('slow');
}

function muestraRegistroUnico(){
    $('.regUnico').show('slow');
    var cantidad = $('#producto_cantidad');
    cantidad.val('1');
    cantidad.attr('readonly', true);    
}

function determinaAccionDeCategoria(objeto){
    if(objeto.cate_runic == 'S'){
        muestraRegistroUnico();
    }
    if(objeto.cate_feven == 'S'){
        muestraVencimiento();        
    }
}