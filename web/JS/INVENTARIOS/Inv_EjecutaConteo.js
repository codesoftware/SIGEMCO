$(function () {
    $('#cierraConteo').on('click', function () {
        $('#closeInv').modal('hide');
        var datos = new Object();
        datos.copr_copr = $('#copr_coprId').val();
        $('#cargando').modal('show');
        $.ajax({
            data: datos,
            dataType: 'json',
            url: RutaSitio + "/cierraConteo.action",
            success: function (data, textStatus, jqXHR) {
                if (data.respuesta == 'Ok') {
                    $('#cargando').modal('hide');
                    $('#textoMsn').html("El conteo fue Realizado correctamente por favor consulte de nuevo el conteo para ver los reportes del conteo");
                    $('#mensaje').modal('show');
                    setInterval(function(){ location.href = "reenvioGeneral.action?accion=233"; }, 5000);
                } else {
                    $('#textoMsn').html(data.respuesta);
                    $('#mensaje').modal('show');
                }
            }
        });

    });
});
var producto = new Object();

function agregaProductoConteo() {
    var datosOk = validaDatos();
    if (datosOk) {
        var datos = new Object();
        datos.dska_codigo = $('#codigo').val();
        $.ajax({
            url: RutaSitio + "/consultaProdIXcod.action",
            data: datos,
            async: false,
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                if (data.respuesta == 'Ok') {
                    producto = data.objeto;
                    actualizaInventario(data.objeto.dska_dska);
                } else {
                    $('#textoMsn').html('Producto inexistente por favor intente de nuevo');
                    $('#mensaje').modal('show');
                }
            }
        });
    }
}

function despuesEnter(valor) {
    if (valor == '1') {
        if ($('#codigo').val() != '') {
            $('#cantidad').val('1');
            agregaProductoConteo();
        } else {
            $('#codigo').focus();
        }
    }
}

function validaDatos() {
    var codigo = $('#codigo').val();
    if ($.trim(codigo) == '') {
        $('#textoMsn').html('El codigo no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    var cantidad = $('#cantidad').val();
    if ($.trim(cantidad) == '') {
        $('#textoMsn').html('La cantidad no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    if (cantidad < 0) {
        $('#textoMsn').html('La cantidad no puede ser menor a cero');
        $('#mensaje').modal('show');
        return false;
    }
    if (isNaN(cantidad)) {
        $('#textoMsn').html('La cantidad debe ser un dato numerico');
        $('#mensaje').modal('show');
        return false;

    }
    return true;
}

function actualizaInventario(dska_dska) {
    var datos = new Object();
    datos.dska_dska = dska_dska;
    datos.dska_codigo = $('#codigo').val();
    datos.cantidad = $('#cantidad').val();
    datos.copr_copr = $('#copr_coprId').val();
    $.ajax({
        url: RutaSitio + "/actualizaConteoProd.action",
        dataType: 'json',
        data: datos,
        success: function (data, textStatus, jqXHR) {
            if (data.respuesta == 'Ok') {
                var tabla = $('#cuerpoProductos');
                var id = 'fila' + producto.dska_dska;
                $('#' + id).remove();
                var fila = '<tr id=\"fila' + producto.dska_dska + '\">' +
                        '<td>' + producto.dska_cod + '</td>' +
                        '<td>' + producto.dska_nom_prod + '</td>' +
                        '<td>' + producto.dska_desc + ' ' + producto.dska_marca + '</td>' +
                        '<td>' + data.Objeto.ecop_valor + '</td>';
                tabla.append(fila);
            } else {
                $('#textoMsn').html(data.respuesta);
                $('#mensaje').modal('show');
            }
        }
    });
}

function cerrarInventario() {
    $('#closeInv').modal('show');
}