function insertarSubCuenta(){
    var valida = validaDatos();
    if(valida){
        document.getElementById("Con_InsertSubCuentas").submit();
    }
}

function validaDatos(){
    var nombre = $('#sbcu_nombre');    
    var descripcion = $('#sbcu_descripcion');
    var codigo = $('#sbcu_codigo');
    nombre.val(nombre.val().trim());
    descripcion.val(descripcion.val().trim());
    codigo.val(codigo.val().trim());
    if(nombre.val().trim()== ''){
        $('#textoMsn').html('El nombre de la subcuenta no pude ser nulo ');
        $('#mensaje').modal('show');
        return false;        
    }
    if(descripcion.val().trim()== ''){
        $('#textoMsn').html('La descripcion de la subcuenta no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    if(codigo.val().trim()== ''){
        $('#textoMsn').html('El codigo de la subcuenta no puede ser nula');
        $('#mensaje').modal('show');
        return false;
    }
    var validaCarac = validaCaracteres(codigo.val(),2);
    if(!validaCarac){
        $('#textoMsn').html('La cantidad de caracteres del codigo supera la permitida ya que solo se puden dos cifras');
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}


