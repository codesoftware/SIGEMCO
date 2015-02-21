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
        success: function(data, textStatus, jqXHR) {
            if (data == null) {
                $('#divErrorProd').show('slow');
                $('#msgErrorProd').html('LA CONSULTA NO ARROJO NINGUN RESULTADO');

            } else {
                var tabla = $('#tablaAddProd');
                tabla.children().remove();
                if (data.length > 0) {
                    cabecera = '<thead>'+
                            '<tr>' +
                            '<th>&nbsp; Codigo &nbsp;</th>' +
                            '<th>&nbsp; Nombre &nbsp;</th>' +
                            '<th>&nbsp; Precio &nbsp;</th>' +
                            '<th>&nbsp; Cant.&nbsp;</th>' +
                            '<th>&nbsp; Cantidad &nbsp;</th>' +
                            '</tr>'+
                            '</thead>';
                    tabla.append(cabecera);
                }
                $.each(data, function(index, obj) {
                    var linea = '<tr>' +
                            '<td>' + obj.codigo + '</td>' +
                            '<td>' + obj.nombre + '</td>' +
                            '<td>' + obj.costo + '</td>' +
                            '<td>' + obj.cantidad + '</td>';
                    if (obj.cantidad > 0) {
                        linea += '<td> <input type=\"text\" class=\"addProducto\" value=\"\" data-key=\"' + obj.id + '\" data-cantidades=\"' + obj.cantidad + '\" /></td>';
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

/*
 //Funcion a la cual se envia cuando han escogido la adicion de productos
 function adicionaProductos() {
 chooseAdd.dialog('close');
 //Envio a en el cual se encuentran los filtros para la busqueda de productos
 var codigo = $('#addCodigoProducto');
 var refe = $('#addReferenciaProducto');
 var nomb = $('#addNombreProducto');
 var allFields = $([]).add(codigo).add(refe).add(nomb);
 allFields.removeClass("ui-state-error");
 allFields.val('');
 formBusqProductos.dialog('open');
 }
 //Desde Aqui hago la logica de adicion de productos
 
 //Funcion con la logica del formulario de 
 function formBuscarProductos() {
 var codigo = $('#addCodigoProducto');
 var refe = $('#addReferenciaProducto');
 var nomb = $('#addNombreProducto');
 var valid = true;
 var allFields = $([]).add(codigo).add(refe).add(nomb);
 valid = valid && validNullAddProductos(codigo, refe, nomb);
 if (valid) {
 formBusqProductos.dialog('close');
 var datos = new Object();
 datos.codigo = codigo.val();
 datos.referencia = refe.val();
 datos.nombre = nomb.val();
 buscaListaProductos(datos);
 }
 }
 
 function buscaListaProductos(datos) {
 ajaxBuscaProductosXFiltros(datos, '1');
 }
 //Funcion de validacion de nulos
 function validNullAddProductos(dato1, dato2, dato3) {
 if (dato1.val() == null || dato1.val() == '') {
 if (dato2.val() == null || dato2.val() == '') {
 if (dato3.val() == null || dato3.val() == '') {
 dato1.addClass("ui-state-error");
 dato2.addClass("ui-state-error");
 dato3.addClass("ui-state-error");
 updateTips("Los Tres Campos no pueden ser nulos");
 return false;
 }
 }
 
 }
 return true;
 }
 //Resalto el error que cometio el usuario al completar el formulario
 function updateTips(t) {
 tips = $(".validateTips");
 tips.text(t)
 .addClass("ui-state-highlight");
 setTimeout(function() {
 tips.removeClass("ui-state-highlight", 1500);
 }, 1000);
 }
 //Funcion a la cual se envia despues de los ajax
 function postAjax(data, valor) {
 data = eliminarEnter(data);
 if (valor == 1) {
 var obj = jQuery.parseJSON(data);
 var tabla = $('#tablaAddProd');
 tabla.children().remove();
 if (obj.length > 0) {
 cabecera = '<tr class="ui-widget-header ">' +
 '<th>&nbsp; Codigo &nbsp;</td>' +
 '<th>&nbsp; Nombre &nbsp;</td>' +
 '<th>&nbsp; Precio &nbsp;</td>' +
 '<th>&nbsp; Cant. Existentes &nbsp;</td>' +
 '<th>&nbsp; Cantidad &nbsp;</td>' +
 '</tr>';
 tabla.append(cabecera);
 }
 $.each(obj, function(index, obj) {
 var linea = '<tr>' +
 '<td>' + obj.codigo + '</td>' +
 '<td>' + obj.nombre + '</td>' +
 '<td>' + obj.costo + '</td>' +
 '<td>' + obj.cantidad + '</td>';
 if (obj.cantidad > 0) {
 linea += '<td> <input type=\"text\" class=\"addProducto\" value=\"\" data-key=\"' + obj.id + '\" data-cantidades=\"' + obj.cantidad + '\" /></td>';
 } else {
 linea += '<td style="font-size:12px;">Este Producto no se puede comprar ya que no existen cantidades disponibles</td>';
 }
 linea += '</tr>';
 tabla.append(linea);
 });
 listaAddProd.dialog('open');
 }
 }
 //Aqui veo que productos escojio
 function adicionProductosFactura() {
 var productos = $('.addProducto');
 productos.removeClass('ui-state-error');
 var acceso = validaAddProductos(productos);
 if (acceso) {
 agregarBaseDatosProductos();
 }
 
 }
 //function que valida los datos ingresados por el usuario son numeros al pedir los productos
 function validaAddProductos(array) {
 var tam = array.length;
 for (var i = 0; i < tam; i++) {
 var tem = array[i].value;
 var cant = array[i].dataset.cantidades;
 if (tem.trim() != '') {
 if (isNaN(tem)) {
 updateTipsAddProductos('Los valores deben ser numericos');
 array[i].className += ' ui-state-error';
 return false;
 } else {
 //alert('Voy a comprar: ' + tem + 'Cantidades: ' + cant);
 tem = Number(tem);
 cant = Number(cant);
 if (tem > cant) {
 updateTipsAddProductos('La cantidad de productos que desea comprar superan a los que estan disponibles');
 array[i].className += ' ui-state-error';
 return false;
 }
 }
 }
 }
 return true;
 }
 
 function updateTipsAddProductos(t) {
 tips = $(".validateTipsAddProd");
 tips.text(t)
 .addClass("ui-state-highlight");
 setTimeout(function() {
 tips.removeClass("ui-state-highlight", 1500);
 }, 1000);
 }
 //Funcion con la que agregamos los datos en la base de datos
 function agregarBaseDatosProductos() {
 var productos = $('.addProducto');
 listaAddProd.dialog('close');
 var factura = $('#fact_fact').val();
 
 $.each(productos, function(index, valor) {
 if (valor.value != "") {
 var obj = new Object();
 obj.identificador = valor.dataset.key;
 obj.cantidad = valor.value;
 obj.factura = factura;
 var banAddProd = true;
 var msn = '';
 $.ajax({
 url: "/PROYECTOHOTEL/AJAX/JSP/ajaxAdicionaProductosFact.jsp",
 type: 'GET',
 data: obj,
 dataType: 'json',
 async: false,
 success: function(data, textStatus, jqXHR) {
 if (data.respuesta != 'Ok') {
 msn = msn + ' \n' + data.respuesta;
 banAddProd = false;
 }
 }
 });
 if (banAddProd) {
 abrirDialogoInfo('Productos Adicionados Correctamente');
 recargarFormulario();
 } else {
 abrirDialogoInfo('Error al adicionar productos ' + msn);
 }
 }
 });
 }
 */
