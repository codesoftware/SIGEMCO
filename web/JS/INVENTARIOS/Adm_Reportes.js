$(function () {
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
});

function ejecutaReporte() {
    var fechaIni = $('#fechaIni').val();
    var fechaFin = $('#fechaFin').val();
    var url = "con_Reportes?fechaIni=" + fechaIni + "&fechaFin=" + fechaFin ;
    window.open(url, "_parent");
}
