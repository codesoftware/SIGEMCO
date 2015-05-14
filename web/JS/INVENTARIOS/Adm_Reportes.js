function ejecutaReporte(){
    var fechaIni = $('#fechaIni').val();
    var fechaFin = $('#fechaFin').val();
    var url = "con_Reportes?fechaIni=" + fechaIni + "&fechaFin=" + fechaFin+ "&reportes=" + reportes ;
    window.open(url, "_parent");
}
