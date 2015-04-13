function insertConteo(){
    var datosOk = validaDatos();
    if(datosOk){
        document.getElementById('Inv_Creaconteo').submit();
    }
}

function validaDatos(){
    var sedes = $('#sedes').val();
    if(sedes == '-1'){
        $('#textoMsn').html('Por Favor Seleccione una sede para continuar');
        $('#mensaje').modal('show');
        return false;        
    }
    var comentario = $('#comentario').val();
    if(comentario == ''){
        $('#textoMsn').html('El campo comentario no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}