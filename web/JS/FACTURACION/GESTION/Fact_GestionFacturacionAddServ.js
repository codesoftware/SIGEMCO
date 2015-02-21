function filtrosReservaHabitacion() {
    $('#dialogoAddServProd').modal('hide');
    $('#dialogoFiltroReservServ').modal('show');
}

function buscaPosiblesReservasXFiltros() {
    var valida = validaConsultaServicios();
    if (valida) {
        $('#dialogoFiltroReservServ').modal('hide');
        ejecutarBusquedaReservaHab();
    }

}

function validaConsultaServicios() {
    var fecha = $('#fechaReserv').val();
    var pers = $('#numPersonasReserv').val();
    var numD = $('#numDiasReserv').val();
    $('#divError').hide('fast');
    if (fecha == '') {
        $('#divError').show('slow');
        $('#msgError').html('EL CAMPO FECHA NO PUEDE SER NULO');
        return false;
    } else if (pers == '') {
        $('#divError').show('slow');
        $('#msgError').html('EL CAMPO NUMERO DE PERSONAS NO PUEDE SER NULO');
        return false;
    } else if (numD == '') {
        $('#divError').show('slow');
        $('#msgError').html('EL CAMPO NUMERO DE DÍAS NO PUEDE SER NULO');
        return false;
    } else if (pers == '0') {
        $('#divError').show('slow');
        $('#msgError').html('EL CAMPO NUMERO DE PERSONAS NO PUEDE SER CERO');
        return false;
    } else if (numD == '0') {
        $('#divError').show('slow');
        $('#msgError').html('EL CAMPO NUMERO DE DÍAS NO PUEDE SER CERO');
        return false;
    }
    return true;
}

function ejecutarBusquedaReservaHab() {
    var datos = new Object();
    datos.fecha = $('#fechaReserv').val();
    datos.numPers = $('#numPersonasReserv').val();
    datos.numDias = $('#numDiasReserv').val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: datos,
        url: RutaSitio + "/AJAX/JSP/ajaxBuscaServiciosXFiltros.jsp",
        success: function(data, textStatus, jqXHR) {
            tabla = $('#tablaAddServ');
            if (data == null) {
                $('#informacionPopUp').modal('show');
                $('#msnInfo').html('NO EXISTEN HABITACIONES DISPONIBLES CON ESTOS CRITERIOS DE BUSQUEDA');
            } else {
                var cabecera = '<thead>'+
                         '<tr>' +
                           '<th>&nbsp; Opc &nbsp;</td>' +
                           '<th>&nbsp; Num Habit. &nbsp;</td>' +
                           '<th>&nbsp; Max Personas &nbsp;</td>' +
                           '<th>&nbsp; Min Personas &nbsp;</td>' +
                         '</tr>'+
                        '</thead>';
                tabla.children().remove();
                tabla.append(cabecera);
                $.each(data, function(index, obj) {
                    var numeroDias = $('#numDiasReserv').val();
                    var fechaRev = $('#fechaReserv').val();
                    var linea = '<tr>' +
                            '<td><input type=\"checkbox\" name=\"addServ\" class=\"servAdd\" value=\"' + obj.idHabitacion + '\" data-numdias=\"'+numeroDias+'\" data-fecha=\"'+fechaRev+'\" /></td>' +
                            '<td>' + obj.numHabi + '</td>' +
                            '<td>' + obj.numMaxPers + '</td>' +
                            '<td>' + obj.numMinPers + '</td>' +
                            '</tr>';
                    tabla.append(linea);
                });
                $('#consultaBusquedaServ').modal('show');
            }
        }
    });
}

function adicionaReservaFactura(){
    var servicios = document.getElementsByClassName('servAdd');
    var arrayServSelec = [];
    var j = 0;
    for(var i = 0 ;  i < servicios.length ; i++){
        if(servicios[i].checked == true ){
            var datos = new Object();
            datos.dska_dska = servicios[i].value;
            datos.fecha = servicios[i].dataset.fecha;
            datos.numdias = servicios[i].dataset.numdias;
            arrayServSelec[j] = datos;
            j++;
        }
    }
    consultaDatosFacturacionServ(arrayServSelec);
}

function consultaDatosFacturacionServ(servicios){
        
    //alert('Hola');
}



/**
 
 function adicionaServicios() {
 chooseAdd.dialog("close");
 var fecha = $('#fechaReserva');
 var numPers = $('#numPersonas');
 var numDias = $('#numDias');
 var campos = $([]).add(fecha).add(numPers).add(numDias);
 campos.removeClass('ui-state-error');
 campos.val('');
 $(".validateTipsAddServ").text('Seleccione la fecha y digite el numero de personas');
 addServicioFiltro.dialog('open');
 }
 
 function ejecurtaBusquedaServicios() {
 var fecha = $('#fechaReserva');
 var numPers = $('#numPersonas');
 var numDias = $('#numDias');
 $(".validateTipsAddServ").text('Seleccione la fecha y digite el numero de personas');
 var valida = validaAddServ(fecha, numPers, numDias);
 if (valida) {
 obtieneServiciosDisponibles(fecha, numPers, numDias);
 }
 }
 
 function validaAddServ(fecha, numPers, numDias) {
 var validaFecha = validaFechaDDMMAAAA(fecha.val());
 if (validaFecha == false) {
 fecha.addClass('ui-state-error');
 updateTipsAddServ('El Campo Fecha no Tiene el formato deseado o es Nulo');
 return false;
 }
 if (isNaN(numPers.val())) {
 numPers.addClass('ui-state-error');
 updateTipsAddServ('El campo numero de personas debe ser Numerico');
 return false;
 }
 if (isNaN(numDias.val())) {
 numDias.addClass('ui-state-error');
 updateTipsAddServ('El campo numero de Dias debe ser Numerico');
 return false;
 }
 return true;
 }
 
 function updateTipsAddServ(t) {
 tips = $(".validateTipsAddServ");
 tips.text(t)
 .addClass("ui-state-highlight");
 setTimeout(function() {
 tips.removeClass("ui-state-highlight", 1500);
 }, 1000);
 }
 
 //Funcion en la cual envio los datos para consultar las posibles habitaciones a reservar
 function obtieneServiciosDisponibles(fecha, numPers, numDias) {
 var datos = new Object();
 datos.fecha = escape(fecha.val());
 datos.numPers = numPers.val();
 datos.numDias = numDias.val();
 $.ajax({
 type: 'GET',
 dataType: 'json',
 data: datos,
 url: "/PROYECTOHOTEL/AJAX/JSP/ajaxBuscaServiciosXFiltros.jsp",
 success: function(data, textStatus, jqXHR) {
 tabla = $('#tablaAddServ');
 if (data == null) {
 abrirDialogoInfo('No existen Habitaciones disponibles con estos filtros de Busqueda Disponibles');
 } else {
 var cabecera = '<tr class="ui-widget-header ">' +
 '<th>&nbsp; Opc &nbsp;</td>' +
 '<th>&nbsp; Num Habit. &nbsp;</td>' +
 '<th>&nbsp; Max Personas &nbsp;</td>' +
 '<th>&nbsp; Min Personas &nbsp;</td>' +
 '</tr>';
 tabla.children().remove();
 tabla.append(cabecera);
 $.each(data, function(index, obj) {
 var linea = '<tr>' +
 '<td><input type=\"checkbox\" name=\"addServ\" value=\"' + obj.idHabitacion + '\"/></td>' +
 '<td>' + obj.numHabi + '</td>' +
 '<td>' + obj.numMaxPers + '</td>' +
 '<td>' + obj.numMinPers + '</td>' +
 '</tr>';
 tabla.append(linea);
 });
 listaAddServ.dialog('open');
 }
 addServicioFiltro.dialog('close');
 
 
 }
 });
 }
 
 function adicionarServicios() {
 listaAddServ.dialog('close');
 var factura = $('#fact_fact').val();
 var fecha = $('#fechaReserva').val();
 var numPers = $('#numPersonas').val();
 var numDias = $('#numDias').val();
 var addServ = document.getElementsByName("addServ");
 msn = "";
 var ban = false;
 for (var i = 0; i < addServ.length; i++) {
 if (addServ[i].checked == true) {
 ban = true;
 var datos = new Object();
 datos.fact_fact = factura;
 datos.fecha_resv = fecha;
 datos.num_pers = numPers;
 datos.num_dias = numDias;
 datos.idHabi = addServ[i].value;
 $.ajax({
 type: 'POST',
 data: datos,
 dataType: 'json',
 url: "/PROYECTOHOTEL/AJAX/JSP/ajaxAdicionaServiciosFact.jsp",
 async: false,
 success: function(data, textStatus, jqXHR) {
 if(data.respuesta != 'OK'){
 msn += "\n"+ data.respuesta;
 }
 }                
 });
 }
 }
 if(ban == true){
 if(msn==''){
 abrirDialogoInfo('Habitaciones reservadas correctamente');
 recargarFormulario();
 }else{
 abrirDialogoInfo('Error al adcionar Servicios: ' + msn);
 }
 }
 
 }*/