function removerProductos() {
    var productos = $('#Fact_UpdtFactura_listDetProd').val();
    if (productos.trim() == '') {
        chooseMinus.dialog('close');
        abrirDialogoInfo('Esta Factura no tiene Servicios Para Eliminar ');
    } else {
        var datos = new Object();
        datos.fact_fact = $('#fact_fact').val();
        $.ajax({
            type: 'GET',
            data: datos,
            dataType: 'json',
            url: "/PROYECTOHOTEL/AJAX/JSP/ajaxBuscaDetallesProductosFactura.jsp",
            success: function(data, textStatus, jqXHR) {
                tabla = $('#tablaRemoveProd');
                var cabecera = '<tr class="ui-widget-header ">' +
                        '<th>&nbsp; Opc &nbsp;</td>' +
                        '<th>&nbsp; Id &nbsp;</td>' +
                        '<th>&nbsp; Fecha &nbsp;</td>' +
                        '<th>&nbsp; Nombre &nbsp;</td>' +
                        '<th>&nbsp; Valor &nbsp;</td>' +
                        '<th>&nbsp; Iva &nbsp;</td>' +
                        '<th>&nbsp; Total &nbsp;</td>' +
                        '<th>&nbsp; Cantidad &nbsp;</td>' +
                        '</tr>';
                $.each(data,function(index, obj){
                    if(index == 0){
                        tabla.children().remove();
                        tabla.append(cabecera);
                    }
                    var linea = '<tr>' +
                            '<td><input type=\"checkbox\" name=\"removeProd\" value=\"' + obj.dtpr_dtpr + '\"/></td>' +
                            '<td>' + obj.dtpr_dtpr + '</td>' +
                            '<td>' + obj.dtpr_fecha + '</td>' +
                            '<td>' + obj.nombProducto + '</td>' +
                            '<td>' + obj.dtpr_valor_pr + '</td>' +
                            '<td>' + obj.dtpr_valor_iva + '</td>' +
                            '<td>' + obj.dtpr_valor_venta + '</td>' +
                            '<td>' + obj.dtpr_cant + '</td>' +
                            '</tr>'; 
                    tabla.append(linea);
                });
            }

        })
        chooseMinus.dialog('close');
        listaRemoveProd.dialog('open');
    }
}

function eliminarProductos(){
    listaRemoveProd.dialog('close');
    var datos = new Object();
    datos.fact_fact = $('#fact_fact').val();
    var eliminar = document.getElementsByName("removeProd");
    var ban = true;
    msn = '';
    for(var i = 0 ; i < eliminar.length; i++){
        if(eliminar[i].checked == true){
            datos.dtpr_dtpr = eliminar[i].value;
            $.ajax({
                type: 'POST',
                dataType: 'json',
                data: datos,
                url: "/PROYECTOHOTEL/AJAX/JSP/ajaxEliminaDetProdFactura.jsp",
                async: false,
                success: function(data, textStatus, jqXHR) {
                    alert('hola: ' + data.respuesta);
                    if(data.respuesta != 'Ok'){
                        msn = msn+ '\n' +data.respuesta;
                        ban = false;
                    }
                }
            });
            if(ban){
                abrirDialogoInfo('Producto(s) eliminados correctamente');
                recargarFormulario();
            }else{
                abrirDialogoInfo('Error al eliminar Productos ' + msn);
            }
        }
    }
}