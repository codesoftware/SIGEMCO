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