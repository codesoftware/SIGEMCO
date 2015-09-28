function buscaGeneralMvIn(){
    document.getElementById('inv_consMovInventario').submit();
}

function actulizarEspecifico(id){
    document.getElementById('inv_conUpdInventario_movimiento_mvin_mvin').value = id;
    document.getElementById('inv_conUpdInventario').submit();
}