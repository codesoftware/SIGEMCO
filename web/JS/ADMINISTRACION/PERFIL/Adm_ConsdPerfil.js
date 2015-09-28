$(function() {  
});

function consultar(){
    document.getElementById("formConsultaPerfil").submit();
}
function permisoPerfil(valor){
    document.getElementById("linkPerfil").value = valor;
    document.getElementById("adm_updPermisos").submit();
}