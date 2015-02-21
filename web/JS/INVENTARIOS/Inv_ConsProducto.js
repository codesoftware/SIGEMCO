var codigoProd;
var idProd;
$(function() {
    $('#parametrizar').on('click', function() {
        parametirzarPrecio(codigoProd, idProd);
    });
    $('#actualizar').on('click', function() {
        actulizarEspecifico(idProd);
    });
    $('#sticker').on('click', function() {
        generarSticker(idProd);
    });
});

function buscaGeneral() {
    document.getElementById("inv_consProdPorFiltrosGen").submit();
}
function actulizarEspecifico(valor) {
    document.getElementById('idProductoUpdate').value = valor;
    document.getElementById("inv_UpdProducto").submit();
}

function parametirzarPrecio(codigo, id) {
    document.getElementById('codigoParametriza').value = codigo;
    document.getElementById('idParametriza').value = id;
    document.getElementById('inv_BuscaProducto').submit();
}

function ejecutaAcciones(actualiza, parametriza, cod, id) {
    if (actualiza == 'N') {
        $('#actualizar').hide('fast');
    }
    if (parametriza == 'N') {
        $('#parametrizar').hide('fast');
    }
    if (actualiza == 'S' || parametriza == 'S') {
        $('#dialogoAcciones').modal('show');
    }
    codigoProd = cod;
    idProd = id;
}
/**
 * Funcion encargada de generar los stikers de los productos
 * @param {type} id
 * @returns {undefined}
 */
function generarSticker(id) {
    var url = "inv_stickeProd?producto.dska_dska=" + id ;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
    $('#dialogoAcciones').modal('hide');
}