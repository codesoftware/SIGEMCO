$(function () {
    $('#adicionaProd').click(function () {
        adicionaProductosListaCompras();
    });
});

function filtrosProductos() {
    $('#dialogoAddServProd').modal('hide');
    $('#dialogoFiltroAdicionProd').modal('show');
}

function buscaPosiblesProductos() {
    var validaDatos = validaConsultaProductos();
    if (validaDatos) {
        ejecutaBusquedaProductos();
    }
}


function validaConsultaProductos() {
    var codigo = $('#codigoProd').val();
    var referencia = $('#referenciaProd').val();
    var nomProd = $('#nombreProducto').val();
    $('#divErrorProd').hide('fast');
    if (codigo == '' && referencia == '' && nomProd == '') {
        $('#divErrorProd').show('slow');
        $('#msgErrorProd').html('LOS TRES CAMPOS NO PUEDEN SER NULOS');
        return false;
    }
    return true;
}

function ejecutaBusquedaProductos() {
    var datos = new Object();
    datos.codigo = $('#codigoProd').val();
    datos.referencia = $('#referenciaProd').val();
    datos.nombre = $('#nombreProducto').val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: datos,
        url: RutaSitio + "/AJAX/JSP/ajaxBuscaProductosXFiltros.jsp",
        success: function (data, textStatus, jqXHR) {
            if (data == null) {
                $('#divErrorProd').show('slow');
                $('#msgErrorProd').html('LA CONSULTA NO ARROJO NINGUN RESULTADO');

            } else {
                var tabla = $('#tablaAddProd');
                tabla.children().remove();
                if (data.length > 0) {
                    cabecera = '<thead>' +
                            '<tr>' +
                            '<th>&nbsp; Codigo &nbsp;</th>' +
                            '<th>&nbsp; Nombre &nbsp;</th>' +
                            '<th>&nbsp; Precio &nbsp;</th>' +
                            '<th>&nbsp; Cant.&nbsp;</th>' +
                            '<th>&nbsp; Cantidad &nbsp;</th>' +
                            '</tr>' +
                            '</thead>';
                    tabla.append(cabecera);
                }
                $.each(data, function (index, obj) {
                    var linea = '<tr>' +
                            '<td>' + obj.codigo + '</td>' +
                            '<td>' + obj.nombre + '</td>' +
                            '<td>' + obj.costo + '</td>' +
                            '<td>' + obj.cantidad + '</td>';
                    if (obj.cantidad > 0) {
                        linea += '<td> <input type=\"text\" onkeypress=\"return validaNumeros(event)\" class=\"addProducto\" value=\"\" data-key=\"' + obj.id + '\" data-cantidades=\"' + obj.cantidad + '\" /></td>';
                    } else {
                        linea += '<td style="font-size:12px;">Este Producto no se puede comprar ya que no existen cantidades disponibles</td>';
                    }
                    linea += '</tr>';
                    tabla.append(linea);
                });
                $('#dialogoFiltroAdicionProd').modal('hide');
                $('#consultaBusquedaProd').modal('show');

            }
        }

    });
}
/**
 * Funcion la cual se encargara de realizar la logica para determinar los productos que el usuario escojio para listar
 * @returns {undefined}
 */
function adicionaProductosListaCompras() {
    var productos = $('.addProducto');
    var productosLst = [];
    var iterator = 0;
    $.each(productos, function (index, value) {
        $(this).css('border-color', '');
        if (value.value.trim()) {
            /* Variable en la cual se almacenara el valor que el cliente desea comprar */
            var cantidad = parseInt(value.value.trim());
            /* Variable en la cual se almacenaran la cantidad disponible de productos*/
            var disponibles = parseInt(value.dataset.cantidades);
            /*Identificador unico del producto*/
            var dska_dska = value.dataset.key;
            /*Variable en el cual se almacenara si paso todas las validaciones necesarias para adicionar el producto*/
            var valida = true;
            if (cantidad > disponibles) {
                $('#msnDangerAddProd').show('fast');
                $('#msnDangerAddProd').html('El numero de cantidades disponibles de este producto es menor al solicitado por el cliente');
                $(this).css('border-color', 'red');
                valida = false;
            }
            if (valida) {
                var producto = new Object();
                producto.dska_dska = dska_dska;
                producto.cant = cantidad;
                productosLst[iterator] = producto;
                iterator++;
            }
        }

    });
    dibujaTablaProductos(productosLst);
}

function dibujaTablaProductos(lstProductos){
    for(var i=0; i<lstProductos.length ; i++ ){
        var datos = new Object();
        datos.dska_dska = lstProductos[i].dska_dska;
        datos.cantidad = lstProductos[i].cant;
        $.ajax({
            url: RutaSitio + "/adicionaProductoFactura.action",
            data: datos,
            dataType: 'json',
            async: false,
            success: function (data, textStatus, jqXHR) {
                alert('Acabo');
                alert(data);
            }
        });
    }
}
