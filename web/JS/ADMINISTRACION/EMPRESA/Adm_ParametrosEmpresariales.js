function cleanForm(){
    document.getElementById("adm_updtParaEmp_empresa_nombre").value = '';
    document.getElementById("adm_updtParaEmp_empresa_nit").value = '';
    document.getElementById("adm_updtParaEmp_empresa_direccion").value = '';
    document.getElementById("adm_updtParaEmp_empresa_telefono").value = '';
    document.getElementById("adm_updtParaEmp_empresa_ciudad").value = '';
    document.getElementById("adm_updtParaEmp_empresa_resolucion").value = '';
}

function ingresarParametros(){
    document.getElementById("adm_updtParaEmp").submit();
}