$(function (){
    $('#btnAddProdReceta').click(function(){
        $('#Inv_buscaRecetaXCodAddProd').submit();
    });
    $('#btnParaPrecio').click(function(){
        $('#Inv_buscaRecetaXCod').submit();
    });
    
});

function actualizar(id){
    $('#rece_receActu').val(id);
    $('#consultaActualizacionReceta').submit();
}

function ejecutaAcciones(id, codigo){
    $("#rece_codigoPrecio").val(codigo);
    $('#rece_receAddProd').val(id);
    $('#ejecutaAcciones').modal('show');     
}