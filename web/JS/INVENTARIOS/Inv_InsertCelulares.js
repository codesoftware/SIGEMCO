$(function () {
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
});

function insertarCelular() {
    document.getElementById('inv_insCelular').submit();
}

function cambioPlan(valor) {
    if (valor != '-1') {
        var datos = new Object();
        datos.tppl = valor;
        $.ajax({
            data: datos,
            type: 'POST',
            dataType: 'json',
            url: RutaSitio + "/AJAX/JSP/ajaxBuscaValorComisionXTppl.jsp",
            success: function (data, textStatus, jqXHR) {
                if (data.respuesta == 'Ok') {
                    var datoValor = mascaraMonedaConValor(data.valor);
                    $('#comision').val(datoValor);
                } else {
                    //Aqui muestro el mensaje de error
                }
            }
        });

    }else{
        $('#comision').val('');
        
    }

}
