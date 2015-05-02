$(function () {
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
});

function consultaCierre() {
    document.getElementById('inv_ReporteCierre').submit();
}
function insertaCierre() {
    document.getElementById('inv_consCierre').submit();
}