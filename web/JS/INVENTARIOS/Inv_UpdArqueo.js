$(function(){
    
});
function consultarArqueos(){
    document.getElementById('inv_ConArqueo').submit();
}

function insertarArqueo(){
    document.getElementById('inv_InsArqueo').submit();
}

function actualizarEspecifico(id){
    document.getElementById('arque_arque').value = id;
    document.getElementById('inv_conUpdArqueo').submit();
}
function actualizacionArqueo() {
   
    document.getElementById('inv_UpdArqueo').submit();
}
