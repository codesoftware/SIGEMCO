function removerServicios() {
    chooseMinus.dialog("close");
    var datos = new Object();
    datos.fact_fact = $('#fact_fact').val();
    $.ajax({
        type: 'POST',
        url: "/PROYECTOHOTEL/AJAX/JSP/ajaxBuscaDetallesServicioFactura.jsp",
        data: datos,
        dataType: 'json',
        success: function(data, textStatus, jqXHR) {
            var valida = true;
            if (data == null) {
                abrirDialogoInfo('Esta Factura no tiene Servicios Para Eliminar ');
                valida = false;
            }
            if (textStatus == 'success' || valida) {
                cabecera = '<tr class="filaTablaTemp ui-widget-header ">' +
                        '<th>&nbsp; Opc &nbsp;</td>' +
                        '<th>&nbsp; Serial &nbsp;</td>' +
                        '<th>&nbsp; No. Resrv &nbsp;</td>' +
                        '<th>&nbsp; Días Reserv &nbsp;</td>' +
                        '<th>&nbsp; Valor Iva &nbsp;</td>' +
                        '<th>&nbsp; Valor Servicio &nbsp;</td>' +
                        '<th>&nbsp; Valor Total &nbsp;</td>' +
                        '</tr>';

                $.each(data, function(index, obj) {
                    if (index == 0) {
                        $('.filaTablaTemp').remove();
                        $('#tablaServRemove').append(cabecera);
                    }
                    var linea = '<tr class="filaTablaTemp">' +
                            '<td><input type=\"checkbox\" name=\"removeServ\" value=\"' + obj.dtsv_dtsv + '\"/></td>' +
                            '<td>' + obj.dtsv_dtsv + '</td>' +
                            '<td>' + obj.diasReserv + '</td>' +
                            '<td>' + obj.dtsv_fecha + '</td>' +
                            '<td>' + obj.dtsv_valor_iva + '</td>' +
                            '<td>' + obj.dtsv_valor_sv + '</td>' +
                            '<td>' + obj.dtsv_valor_venta + '</td>' +
                            '</tr>';
                    $('#tablaServRemove').append(linea);
                });
                listaRemoveServ.dialog("open");
            }
        }
    });
}

function eliminarServicios() {
    listaRemoveServ.dialog("close");
    confirmaElimServ.dialog("open");
}

function ejecurtaEliminarServicios() {
    confirmaElimServ.dialog("close");
    var eliminar = document.getElementsByName("removeServ");
    var datos = new Object();
    datos.fact_fact = $("#fact_fact").val();
    for (var i = 0; i < eliminar.length; i++) {
        if (eliminar[i].checked == true) {
            datos.dtsv_fact = eliminar[i].value;
            $.ajax({
                type: 'POST',
                url: "/PROYECTOHOTEL/AJAX/JSP/ajaxEliminaDetServFactura.jsp",
                data: datos,
                dataType: 'json',
                success: function(data, textStatus, jqXHR) {
                    if (textStatus == 'success') {
                        abrirDialogoInfo('Productos Eliminados correctamente');
                    }
                }
            });
        }
    }
    recargarFormulario();
}