$(function () {
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
});

function consultaCierre() {
    var fecha = $('#inv_consCierre_fecha').val();
    var sede = $('#sede').val();
    var url = "inv_ReporteCierre?cierreDiario.cier_fech="+fecha + "&cierreDiario.cier_sede=" + sede;
    window.open(url);
    document.getElementById('inv_ReporteCierre').submit();
}
function insertaCierre() {
    document.getElementById('inv_consCierre').submit();
}

function ejecutaReporte(){
    
}