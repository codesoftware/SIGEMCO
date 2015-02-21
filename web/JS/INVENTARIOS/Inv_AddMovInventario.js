$(function(){
     
});

function insertarMvInventario(){
    document.getElementById("inv_addMovInventario").submit();
}

function cleanForm(){
    document.getElementById("inv_addMovInventario_movimiento_mvin_descr").value = "";
    document.getElementById("inv_addMovInventario_movimiento_mvin_natu").value = "-1";    
    document.getElementById("inv_addMovInventario_movimiento_mvin_usim").value = "-1";    
    document.getElementById("inv_addMovInventario_movimiento_mvin_venta").value = "-1";
}