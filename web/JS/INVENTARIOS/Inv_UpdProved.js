$(function(){
    
});
function consultarProveedores(){
    document.getElementById('inv_ConProved').submit();
}

function insertarProveedor(){
    document.getElementById('inv_InsProved').submit();
}

function actualizarEspecifico(id){
    document.getElementById('prov_prov').value = id;
    document.getElementById('inv_conUpdProved').submit();
}
function actualizacionProveedor() {
   
    document.getElementById('inv_UpdProv').submit();
}
