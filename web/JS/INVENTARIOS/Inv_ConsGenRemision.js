var idRemision;
$(function () {
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
    $('.muestraImei').hover(
            function () {
                $(this).next('span').show('slow');
            }
    );
    $('.muestraIccid').hover(
            function () {
                $(this).next('span').show('slow');
            }
    );

    $('#sticker').on('click', function () {
        generarSticker(idRemision);
    });
    $('#actualizar').on('click', function () {
        actualizarCelular(idRemision);
    });
    $('#devolucion').on('click', function () {
        devolverCelular(idRemision);
    });
    $('#devolver').on('click', function () {
        generarDevolucion(idRemision);
    });
});

function buscaGeneral() {
    document.getElementById('inv_consRemPorFiltrosGen').submit();
}

function muestraIccid() {
    $('.iccid').show('slow');
}
function muestraImei() {
    $('.imei').show('slow');
}

function accionesEquipo(id) {
    //Aqui deben llegar los permisos para ocultar los botones de las acciones
    idRemision = id;
    $('#dialogoAcciones').modal('show');
}

function generarSticker(id) {
    var url = "inv_stickerRemision?remision.rmce_rmce=" + id;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
    $('#dialogoAcciones').modal('hide');
}
function actualizarCelular(id) {
    document.getElementById('rmce_rmce').value = id;
    document.getElementById('inv_ConCelular').submit();

}
function devolverCelular(id) {
    idRemision = id;
    $('#dialogoAcciones').modal('hide');
    $('#dialogoAccionDevolver').modal('show');

}

function generarDevolucion(id) {
    var comentarioDevolucion = $('#comentario').val();
    var dato = new Object();
    dato.rmce_rmce = id;
    dato.rmce_comdev = comentarioDevolucion;

    $.ajax({
        url: RutaSitio + '/AJAX/JSP/ajaxDevuelveCelular.jsp',
        type: 'POST',
        data: dato,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            alert('rta:' + data.rta);
            $('#dialogoAccionDevolver').modal('hide');

        }
    });
}

