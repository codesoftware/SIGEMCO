$(function() {
    $(".calendar").datepicker({
        changeMonth: true,
        changeYear: true,
        showWeek: true,
        yearRange: "2014:2016",
        altFormat: "dd/mm/yy"
    });

    $("#tabs").tabs();
});

function buscar() {
    document.getElementById("Fac_BuscaHabitaciones").submit();
}

function verReservas(valor) {
    //document.getElementsByClassName("form2IdHabitacion")[0].value = valor;
    var url = "Fac_DetalleReservasHabitacion?habitacion.idHabitacion=" + valor + "&accion=detalleReservaHabitacion";
    window.open(url, "_blank", "directories=no, status=no,width=1400, height=870,top=0,left=0");
}

function agregaQuitaReserva(valor, obj) {
    var checked = obj.checked
    if (checked) {
        var aux = document.getElementsByClassName("habitacionesReservadas")[0].value;
        var repeat = aux.indexOf('|' + valor + '|');
        if (repeat < 0) {
            document.getElementsByClassName("habitacionesReservadas")[0].value += '|' + valor + '|';
        } else {
            abrirDialogoInfo('Habitación ya reservada en otra pestaña');
            obj.checked = false;
        }
    } else {
        var aux = document.getElementsByClassName("habitacionesReservadas")[0].value;
        aux = aux.replace('|' + valor + '|', '');
        document.getElementsByClassName("habitacionesReservadas")[0].value = aux;
    }
}

function postDialogAc(valor, dialog) {
    $(dialog).dialog("close");
}

function verDisponibilidad(valor, checked, obj) {
    if (checked) {
        var fecha = $('#fechaInicial').val();
        var numdias = $('#numDias').val();
        var datos = {habitacion: valor, numDias: numdias, fecha: fecha};
        $.ajax({
            type: 'POST',
            url: "/PROYECTOHOTEL/AJAX/JSP/ajaxVerfDisponibilidadHabitacion.jsp",
            data: datos,
            success: function(response, textStatus, jqXHR) {
                response = eliminarEnter(response);
                var number = Number(response);
                var boolean = Boolean(number);
                if (boolean === false) {
                    abrirDialogoInfo('Esta habitacion no tiene la disponibilidad en el rango de dias deseado');
                    obj.checked = false;
                    var aux = document.getElementsByClassName("habitacionesReservadas")[0].value;
                    aux = aux.replace('|' + valor + '|', '');
                    document.getElementsByClassName("habitacionesReservadas")[0].value = aux;
                }
            }
        });

    }

}

function reservar() {
    var aux = document.getElementsByClassName("habitacionesReservadas")[0].value;
    aux = eliminarEnter(aux);
    aux =  replaceAll(aux, ' ', '');
    if(aux == ''){
        abrirDialogoInfo('Por favor reserve alguna habitación');        
    }else{
        document.getElementById("Fac_ReservarHabitaciones").submit();        
    }
}