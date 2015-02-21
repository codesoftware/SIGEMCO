$(function(){
    $('#continuar').on('click',function(){
        document.getElementById('inv_ParamPrecioPr').submit();                
    });
});
function consultarProd() {
    document.getElementById('inv_BuscaProducto').submit();
}

function cleanForm() {
    document.getElementById("producto_codigo").value = '';
}

function insertarParametro() {
    var dska = document.getElementById('dska_dska').value;
    var obj = new Object();
    obj.dska_dska = dska;
    $.ajax({
        url: RutaSitio + "/AJAX/JSP/ajaxObtieneCostoPromedioProd.jsp",
        type: 'POST',
        data: obj,
        dataType: 'json',
        success: function(data, textStatus, jqXHR) {
            var valorPromedio = parseInt(data.rta);
            var futuroValor = document.getElementById('futuroPrecio').value;
            futuroValor = replaceAll(futuroValor, '.', '');
            // futuroValor = futuroValor.split('.').join('')
            if (futuroValor <= valorPromedio) {
                $('#confirmacion').modal('show');
            } else {
                document.getElementById('inv_ParamPrecioPr').submit();
            }
        }
    });
}

function despuesEnter() {
    consultarProd();
}