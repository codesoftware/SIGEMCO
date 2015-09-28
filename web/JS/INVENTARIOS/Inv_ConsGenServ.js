function consultar(){
    document.getElementById("inv_consGenServ").submit();
}

function actulizarEspecifico(idHabitacion){
    document.getElementById('numHabiPorUpd').value = idHabitacion;
    document.getElementById('adm_updServicio').submit();
}