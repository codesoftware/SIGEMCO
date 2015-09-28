$(function(){
    
});

function consultarCategorias(){
    document.getElementById('inv_ConCategoria').submit();
}

function insertarCategoria(){
    document.getElementById('inv_InsCategoria').submit();
}

function actulizarEspecifico(id){
    document.getElementById('cate_cate').value = id;
    document.getElementById('inv_conUpdCategoria').submit();
}
function actualizacionCategoria() {
       document.getElementById('inv_UpdCategoria').submit();
}


