function objAjax(){
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// Obtiene el objeto para los exploradores IE7+, Firefox, Chrome, Opera y Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// Obtiene el objeto para los exploradores IE6 y IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

function buscaUsuariosJson(cadena, opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/AjaxBuscaUsuarios.jsp?cadena='+cadena+'&opcion='+opcion, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }    
}

function inactivaUsuario(usuario, opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/AjaxInactivaUsuarios.jsp?usuario='+usuario+'&opcion='+opcion, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }
}

function ajaxBuscaCodigoProducto(opcion,cadena){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/AjaxBuscaCodigoPr.jsp?codigo='+cadena, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(opcion);
        }        
    }
}


function ajaxbuscaPerfilesJson(filtro, opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/ajaxbuscaPerfilesJson.jsp?filtro='+filtro+'&opcion='+opcion, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }
}

function ajaxbuscaPerfileJson(nombre,desc,estado,opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/ajaxbuscaPerfileJson.jsp?nombre='+nombre+'&desc='+desc+'&estado='+estado+'&opcion='+opcion, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }    
}

function ajaxBuscaCodMovInventario(codigo, opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/ajaxBuscaCodMovInventario.jsp?codigo='+codigo, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }
}

function ajaxBuscaProductosXFiltros(datos, opcion){
    var ajax = objAjax();
    ajax.open('GET',RutaSitio+'/AJAX/JSP/ajaxBuscaProductosXFiltros.jsp?codigo='+datos.codigo+'&referencia='+datos.referencia+'&nombre='+datos.nombre, true);
    ajax.send(null);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            postAjax(ajax.responseText, opcion);
        }        
    }
}

/**
 * Funcion la cual va y busca el asiento contable de una transaccion
 * @param {type} idTransac
 * @returns {undefined}
 */
function obtenerAsientocontable(idTransac){
    var datos = new Object();
    datos.mvco_trans = idTransac;
    $.ajax({
        url: RutaSitio + "/AJAX/JSP/ajaxObtieneAsientoContable.jsp",
        async: false,
        data: datos,
        dataType: 'json',
        success: function(data, textStatus, jqXHR){
            var tabla = '';
            var cabezoteTabla = '<table class=\"table table-bordered table-hover\">'+
                    '<thead>' + 
                    '<tr>' + 
                        '<th class=\"alert alert-info\"> Descripcion Cuenta </th>' + 
                        '<th class=\"alert alert-info\"> Subcuenta </th>' +
                        '<th class=\"alert alert-info\"> Debitos </th>' +
                        '<th class=\"alert alert-info\"> Creditos </th>' +
                    '</tr>'+
                    '</thead>'
                    '<tbody>';
            var body = '';
             if(data.respuesta == 'Ok'){
                 if(data.list.length != 0){
                     for(var i = 0; i< data.list.length ; i++){
                        var objeto = data.list[i];
                        var linea = '<tr>'+
                             '<td>' + objeto.sbcu_nombre + '</td>'+
                             '<td>' + objeto.sbcu_codigo + '</td>';
                        if(objeto.debitos == 'N/A'){
                            linea +=    '<td> </td>'+
                                        '<td style=\"text-align:right\">' + objeto.creditos + '</td>';
                        }else{
                            linea +=    '<td style=\"text-align:right\">' + objeto.debitos + '</td>'+
                                        '<td> </td>';
                        }
                             linea += '</tr>';
                        body +=linea;
                     }
                     body += '</tbody></table>';
                 }
             }
             tabla = cabezoteTabla + body;
             $('#tablaAsientocontable').html(tabla);
        } 
    });
}