$(function() {
    $(".fechaNacimiento").datepicker( );
    $(".nombreUsuario").autocomplete({
            source: nomUsua,
            select: function(event, ui){    
                var nomUsuarioSelect = ui.item.value;
                consultaUsuarioXNombre(nomUsuarioSelect);
            }
    });
});

function findUser() {
    var formulario = document.getElementById("consDatosXUsua");
    formulario.action = 'consDatosXUsua';
    formulario.submit();
}

var buscaAnt = '';
var nomUsua = [];
var apellidosUsua = [];
var usuarios = [];

function cleanForm() {
    document.getElementById("usuaNuevo_nombre").value = "";
    document.getElementById("usuaNuevo_apellido").value = "";
    document.getElementById("usuaNuevo_cedula").value = "";
    document.getElementById("usuaNuevo_correo").value = "";
    document.getElementById("aliasUsuarioNuevo").value = "";
    document.getElementById("usuaNuevo_tipoUsuario").value = "-1";
}

function buscaUsuario(cadena) {
    cadena.replace(/\s/g, '');
    if (cadena != buscaAnt) {
        if (cadena == '') {
            return false;
        } else {
            buscaAnt = cadena;
            buscaUsuariosJson(cadena, 1);
        }
    }
}

function postAjax(valor, opcion) {
    if (opcion == '1') {
        // Accion para realizar el autocomplete
        var objeto = JSON.parse(valor);
        armaObjetoUsuario(objeto);
    }
}

function armaObjetoUsuario(objeto) {
    var tam = objeto.length;
    for (var i = 0; i < tam; i++) {
        var nombre = objeto[i].nombre;
        var usuario = objeto[i].usuario;
        var apellido = objeto[i].apellido;
        nomUsua[i] = nombre;
        usuarios[i] = usuario;
        apellidosUsua[i] = apellido;
    }
}

function consultaUsuarioXNombre(valor){
    var usuario = buscaUsuaVector(valor);
    var apellidos = buscaApellidoVector(valor);
    document.getElementById("usuaNuevo_apellido").value = apellidos;
    document.getElementById("aliasUsuarioNuevo").value = usuario;
}

function buscaUsuaVector(valor){
    var tam = nomUsua.length;
    for(var i = 0 ; i<tam ; i++){
        if(nomUsua[i] == valor){
            return usuarios[i];
        }        
    }
}

function buscaApellidoVector(valor){
    var tam = nomUsua.length;
    for(var i = 0 ; i<tam ; i++){
        if(nomUsua[i] == valor){
            return apellidosUsua[i];
        }        
    }
}

function editUser(){
    var formulario = document.getElementById("consDatosXUsua");
    formulario.action = 'adm_updateUsua';
    formulario.submit();
}