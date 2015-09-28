$(function() {
    /*$('#perfil_nombre').autocomplete({
        source: nombrePerfil,
        select: function(event, ui) {
            var perfilSelect = ui.item.value;
            consultarPerfil();
            //var valida = buscaNombre(usuarioSelect);
            /*if (valida) {
             $("#info").show("fold", 1000);
             document.getElementById('find').focus();
             }*/
       /* }
    });*/
       

});

var buscaAnt = '';
var nombrePerfil = [];

function buscaUsuario(cadena) {
    cadena.replace(/\s/g, '');
    if (cadena != buscaAnt) {
        if (cadena == '') {
            return false;
        } else {
            buscaAnt = cadena;
            ajaxbuscaPerfilesJson(cadena, 1);
        }
    }
}

function postAjax(rta, opcion) {
    if (opcion == '1') {
        // Accion para realizar el autocomplete
        var objeto = JSON.parse(rta);
        armarObjetoPerfil(objeto);
    }
    if (opcion == '2') {
        var objeto = JSON.parse(rta);
        llenaPerfil(objeto);
    }
}

function armarObjetoPerfil(objeto) {
    var tam = objeto.length;
    for (var i = 0; i < tam; i++) {
        var perfil = objeto[i].perfil;
        nombrePerfil[i] = perfil;
    }
}

function cleanForm() {
    document.getElementsByName("perfil.nombre")[0].value = "";
    document.getElementsByName("perfil.descripcion")[0].value = "";
    document.getElementsByName("perfil.estado")[0].value = '-1';
    $(".divBtnActualizar").hide();
    $(".divBtnConsulta").show();
}

function consultarPerfil() {
    var nombre = document.getElementsByName("perfil.nombre")[0].value;
    var desc = document.getElementsByName("perfil.descripcion")[0].value;
    var estado = document.getElementsByName("perfil.estado")[0].value;
    if (estado == '-1') {
        estado = '';
    }
    ajaxbuscaPerfileJson(nombre, desc, estado, 2);
}

function llenaPerfil(obj) {
    document.getElementsByName("perfil.nombre")[0].value = obj[0].nombre;
    document.getElementsByName("perfil.descripcion")[0].value = obj[0].descripcion;
    document.getElementsByName("perfil.estado")[0].value = obj[0].estado;
    document.getElementsByName("perfil.id")[0].value = obj[0].id;
    $(".divBtnConsulta").hide();
    $(".divBtnActualizar").show();
}

function actualizar() {
    document.getElementById("adm_insertPerfil").submit();
}