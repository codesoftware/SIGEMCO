$(function () {
});

function ejecutaReporte() {
    var reporte = $('#reportes').val();
    if (reporte != '-1') {
        var url = "reportBasicosInv?tipoReporte=" + reporte;
        window.open(url, "_parent");
    }else{
        $('#textoMsn').html('Por favor seleccione un tipo de reporte');
        $('#mensaje').modal('show');
    }
}