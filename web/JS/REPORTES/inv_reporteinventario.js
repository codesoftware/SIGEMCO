/*$(function() {
    $("#accordion").accordion({
        heightStyle: "content"
    });
    $("#tabs").tabs();
    $(".calendar").datepicker({ 
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        defaultDate: +1
    });
});*/
$(function(){
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
});
function buscaProducto(){
    document.getElementById('inv_consProdPorFiltro').submit();
}

function generaReporte(id){
    var fechaIni= document.getElementById("fechaIni").value;
    var fechaFin= document.getElementById("fechaFin").value;            
    var url = "rep_PromPonderado?producto.id="+ id + "&fechaFin="+ fechaFin + "&fechaIni="+fechaIni;    
    window.open(url,"_blank","directories=no, status=no,width=1400, height=870,top=0,left=0");
    
}