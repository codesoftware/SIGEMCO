function buscaGeneral(){
    document.getElementById('Inv_ConsGenConteos').submit();
}

function iniciarConteo(copr_copr){
    document.getElementById('copr_coprInicio').value = copr_copr;
    document.getElementById('Inv_IniciaConteo').submit();
}

function generarReporte(copr_copr) {
    var url = "inv_reporteConteo?conteo.copr_copr=" + copr_copr ;
    window.open(url, "_blank");
}

function generarReporteExcel(copr_copr) {
    var url = "inv_prodGeneralXls?conteo.copr_copr=" + copr_copr ;
    window.open(url, "_parent");
}

function actualizaConteo(copr_copr){
    var datos = new Object();
    datos.copr_copr = copr_copr;
    $.ajax({
        url: RutaSitio + "/actualizaInicioConteo.action",
        data: datos,
        dataType: 'json',
        async: false,
        success: function (data, textStatus, jqXHR) {
            if(data.respuesta == 'Ok'){
                iniciarConteo(copr_copr);                
            }
        }
    });    
}