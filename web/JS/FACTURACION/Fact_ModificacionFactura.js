$(function(){
    $( ".acordionFact" ).accordion({
      collapsible: false
    });
});

function buscaGeneral(){
    document.getElementById("Fact_buscaFactura").submit();
}

function actualizarFactura(idFact){
    document.getElementById('Fact_BuscaFacEspecificaGestion_factura_fact_fact').value = idFact;
    document.getElementById('Fact_BuscaFacEspecificaGestion').submit();
}
