$(function(){
    
});
function consultarMarcas(){
    document.getElementById('inv_ConMarca').submit();
}

function insertarMarca(){
    document.getElementById('inv_InsMarca').submit();
}

function actulizarEspecifico(id){
    document.getElementById('marca_marca').value = id;
    document.getElementById('inv_conUpdMarca').submit();
}
function actualizacionMarca() {
   
    document.getElementById('inv_UpdMarca').submit();
}


