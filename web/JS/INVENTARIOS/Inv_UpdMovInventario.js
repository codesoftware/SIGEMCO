$(function(){
    
});

function consultarMovInv(){
    document.getElementById('inv_conUpdInventario').submit();
}

function actualizarMovInv(id){
    document.getElementById('inv_conUpdInventario').action = 'inv_UpdMvInventario';
    document.getElementById('inv_conUpdInventario').submit();
}