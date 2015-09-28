$(function () {
    $('#sevicioAdd').click(function () {
        filtrosReservaHabitacion();
    });
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
    $('#buscarPosibleReservHab').click(function () {
        buscaPosiblesReservasXFiltros();
    });
    $('#productoAdd').click(function () {
        filtrosProductos();
    });
    $('#buscaPosiblesProd').click(function () {
        buscaPosiblesProductos();
    });
    $('#confirmarReserva').click(function () {
        adicionaReservaFactura()
    });
    $(document).on('click', '.elimnarFilaServ', function () {
        $(this).closest('.filaAddServ').remove();
    });
});

/**
 * Funcion la cual se activa al dar click sobre el boton de agregar.
 * @returns {undefined}
 */
function agregar() {
    $('#dialogoAddServProd').modal('show');
}


function sumaValoresTotales() {
    var iva = $('.vlrIvatotal');
    var sumaIva = 0;
    $.each(iva, function (index, value) {
        var aux = parseFloat(value.value);
        sumaIva += aux;
    });
    $('#sumivaTotalFactura').html(sumaIva);
    var prodTot = $('.vlrProdtotal');
    var sumaTotal = 0;
    $.each(prodTot, function (index, value) {
        var aux = parseFloat(value.value);
        sumaTotal += aux;
    });
    $('#sumprodTotalFactura').html(sumaTotal);
    var total = $('.vlrTotalPagar');
    var sumaTotalAPagar = 0;
    $.each(total, function (index, value) {
        var aux = parseFloat(value.value);
        sumaTotalAPagar += aux;
    });
    $('#sumtotalAPagar').html(sumaTotalAPagar);
    
}