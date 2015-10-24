$(function(){
    $('.input-group.date').datepicker({
        format: 'mm/dd/yyyy'
    });
});
function cleanForm(){
    document.getElementById("usuaNuevo_nombre").value = "";
    document.getElementById("usuaNuevo_apellido").value = "";
    document.getElementById("usuaNuevo_cedula").value = "";
    document.getElementById("usuaNuevo_correo").value = "";
    document.getElementById("aliasUsuarioNuevo").value = "";
    document.getElementById("usuaNuevo_tipoUsuario").value = "-1";
}

function addNewUser(){
   $('.formInsertUsuario').submit();
}