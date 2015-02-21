$(function() {
    $("#accordion").accordion({
        heightStyle: "content"
    });
    $("#tabs").tabs();
    $(".calendar").datepicker({ 
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        defaultDate: +1
    });
});

function generaReporte(){
    var estado = document.getElementById("usuarioRep_estado").value;
    var perfil = document.getElementById("usuarioRep_idPerfil").value;            
    var url = "adm_usuariosRep?usuarioRep.estado="+ estado + "&usuarioRep.idPerfil="+ perfil ;
    window.open(url,"_blank","directories=no, status=no,width=1400, height=870,top=0,left=0");
}