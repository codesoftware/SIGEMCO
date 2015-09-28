$(function(){
    
});

function consultarReferencias(){
    document.getElementById('inv_ConReferencia').submit();
}

function insertarReferencia(){
    document.getElementById('inv_InsReferencia').submit();
}

function actulizarEspecifico(id){
    document.getElementById('refe_refe').value = id;
    document.getElementById('inv_conUpdReferencia').submit();
}
function actualizacionReferencia() {
   
    document.getElementById('inv_UpdReferencia').submit();
}


