$(function () {
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
});

function ejecutaReporte() {
    var url = "con_Reportes";
    window.open(url, "_parent");
}
