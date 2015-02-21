$(function() {
    $("#cabecera").click(function(){
        $('.ocultas').toggle("slow");
    });

    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: RutaSitio + "/AJAX/JSP/ajaxBuscaAlertas.jsp",
        success: function(data, textStatus, jqXHR) {
            dibujaAlertasEquipos(data);
        }
    });
});

function dibujaAlertasEquipos(objeto) {
    tabla = $('#alertas');
    $.each(objeto, function(index, obj) {
        var linea = '<tr>' +
                '<td><span data-idRemision=\"' + obj.rmce_rmce + '\" >' + obj.rmce_fcve + '</span></td>' +
                '<td>' + obj.rmce_refe + '</td>' +
                '<td>' + obj.rmce_sede + '</td>' +
                '<td class=\"ocultas\">' + obj.rmce_imei + '</td>' +
                '<td class=\"ocultas\">' + obj.rmce_iccid + '</td>' +
                '</tr>';
        tabla.append(linea);
    });
}