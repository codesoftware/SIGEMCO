$(function(){
    
});

function manipulaBoton(valor){
    if(valor == 'Actualiza'){
        $('.spanConsultar').hide();
        $('.spanModificar').show();
        document.getElementById("producto_codigo").readOnly = "true";
    }else if(valor == 'Consulta'){
        $('.spanModificar').hide();
        $('.spanConsultar').show();
        document.getElementById("producto_codigo").readOnly = false;
    }
}

function consultaProducto(){
    document.getElementsByClassName("subAccionForm")[0].value = "consultaFiltro";
    document.getElementById("inv_UpdProducto").submit();
}

function actualizacionProducto(){
    document.getElementsByClassName("subAccionForm")[0].value = "update";
    document.getElementById("inv_UpdProducto").submit();    
}


function cleanForm(){
    document.getElementById("producto_nombre").value = "";
    document.getElementById("producto_descripcion").value = "";
    document.getElementById("producto_referencia").value = "";
    document.getElementById("producto_codigo").value = "";
    document.getElementById("producto_porcIva").value = "";
    document.getElementById("producto_marca").value = "";
    document.getElementById("producto_iva").value = "-1";    
}
