$(function () {
});

function consultar() {
    document.getElementById("formConsultaSede").submit();
}
function permisoSede(valor) {
    document.getElementById("linkSede").value = valor;
    document.getElementById("adm_updSede").submit();
}

function actualizarSede(id) {
    document.getElementById('linkSede').value = id;
    document.getElementById('adm_conEspSede').submit();
}

function actualizacionSede() {
   
    document.getElementById('adm_UpdSede').submit();
}