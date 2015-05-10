$(document).ready(function () {
    $('#listClase').change(function () {
        var datos = new Object();
        datos.grup_clas = $('#listClase').val();
        $.ajax({
            type: 'POST',
            data: datos,
            url: RutaSitio + '/consultaComboGrupo.action',
            dataType: 'json',
            success: function (response) {
                $('#grup_grup option').remove();
                $('#grup_grup').append('<option value=\"-1\" selected >Seleccione Grupo...</option>')
                if (response.respuesta == 'OK') {
                    var lista = response.objeto;
                    for (var i = 0; i < lista.length; i++) {
                        $('#grup_grup').append('<option value=\"' + lista[i].grup_grup + '\" >' + lista[i].grup_nombre + '</option>')
                    }
                }
            }
        });
    });
    $('#grup_grup').change(function () {
        var datos = new Object();
        datos.grup_grup = $(this).val();
        $.ajax({
            url: RutaSitio + "/consultaComboCuenta.action",
            data: datos,
            dataType: 'json',
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
                $('#cuen_cuen option').remove();
                $('#cuen_cuen').append('<option value=\"-1\" selected >Seleccione Cuenta...</option>')
                if (data.respuesta == 'OK') {
                    var lista = data.objeto;
                    for (var i = 0; i < lista.length; i++) {
                        $('#cuen_cuen').append('<option value=\"' + lista[i].cuen_cuen + '\" >' + lista[i].cuen_nombre + '</option>')
                    }
                }
            }
        });
    });
    $('.ocultar').click(function () {
        $('.ocultar').toggle();
    });
    $('.mostrar').click(function () {
        $('.ocultar').toggle();
    });
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
});

function buscaGeneralMvCon() {
    document.getElementById('inv_consMovContable').submit();
}

function traeGrupoXClase(clase) {
    var datos = new Object();
    datos.grup_clas = clase;
    $.ajax({
        type: 'POST',
        data: datos,
        url: RutaSitio + '/consultaComboGrupo.action',
        dataType: 'json',
        async: false,
        success: function (response) {
            $('#grup_grup option').remove();
            $('#grup_grup').append('<option value=\"-1\" selected >Seleccione Grupo...</option>')
            if (response.respuesta == 'OK') {
                var lista = response.objeto;
                for (var i = 0; i < lista.length; i++) {
                    $('#grup_grup').append('<option value=\"' + lista[i].grup_grup + '\" >' + lista[i].grup_nombre + '</option>')
                }
            }
        }
    });
}

function traeCuentaXGrupo(grupo) {
    var datos = new Object();
    datos.grup_grup = grupo;
    $.ajax({
        url: RutaSitio + "/consultaComboCuenta.action",
        data: datos,
        dataType: 'json',
        type: 'POST',
        async: false,
        success: function (data, textStatus, jqXHR) {
            $('#cuen_cuen option').remove();
            $('#cuen_cuen').append('<option value=\"-1\" selected >Seleccione Cuenta...</option>')
            if (data.respuesta == 'OK') {
                var lista = data.objeto;
                for (var i = 0; i < lista.length; i++) {
                    $('#cuen_cuen').append('<option value=\"' + lista[i].cuen_cuen + '\" >' + lista[i].cuen_nombre + '</option>')
                }
            }
        }
    });
}
