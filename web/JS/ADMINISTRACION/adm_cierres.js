$(function () {
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
});

function consultaCierre() {
    var valida = validaDatos();
    if (valida) {
        var fecha = $('#inv_consCierre_fecha').val();
        var sede = $('#sede').val();
        var url = "inv_ReporteCierreExcel?cierreDiario.cier_fech=" + fecha + "&cierreDiario.cier_sede=" + sede;
        //var url = "inv_ReporteCierre?cierreDiario.cier_fech=" + fecha + "&cierreDiario.cier_sede=" + sede;
        window.open(url);
        document.getElementById('inv_ReporteCierre').submit();
    }
}
function insertaCierre() {
    var valida = validaDatos();
    if (valida) {
        document.getElementById('inv_consCierre').submit();
    }
}

function ejecutaReporte() {

}

function validaDatos() {
    var sede = $('#sede').val();
    var fecha = $('#inv_consCierre_fecha').val();
    if (sede.trim() == '') {
        $('#mensaje').modal('show');
        return false;
    }
    if (fecha.trim() == '') {
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}