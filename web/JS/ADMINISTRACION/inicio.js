$(function() {
    $("#cabecera").click(function() {
        $('.ocultas').toggle("slow");
    });

    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: RutaSitio + "/buscaParametroInicio.action",
        cache: false,
        success: function(data, textStatus, jqXHR) {
            if (data.respuesta == 'Ok') {
                if (data.para == "I") {
                    buscaAlertaCelular();
                } else if (data.para == "A") {
                    buscaAlertaProductos();
                }
            }
        }
    });
});

function dibujaAlertasEquipos(objeto) {
    tabla = $('#alertas');
    $.each(objeto, function(index, obj) {
        var linea = '<tr>' +
                '<td><span data-idRemision=\"' + obj.rmce_rmce + '\" >' + obj.rmce_fcve + '</span></td>' +
                '<td>' + obj.rmce_refe + '</td>' +
                '<td>' + obj.rmce_sede + '</td>' +
                '<td class=\"ocultas\">' + obj.rmce_imei + '</td>' +
                '<td class=\"ocultas\">' + obj.rmce_iccid + '</td>' +
                '</tr>';
        tabla.append(linea);
    });
}

function buscaAlertaCelular() {
    var cabecera = "";
    cabecera += '    <tr id="cabecera">                                                           ';
    cabecera += '        <th>FECHA</th>                                                           ';
    cabecera += '        <th>';
    cabecera += columna;
    cabecera += '        </th>                                                                    ';
    cabecera += '        <th>SEDE</th>                                                            ';
    cabecera += '        <th class="ocultas">IMEI</th>                                            ';
    cabecera += '        <th class="ocultas">ICCID</th>                                           ';
    cabecera += '    </tr>                                                                        ';
    $('#cabeceraTabla').html(cabecera);
    $('#tituloAlerta').html('Vencimiento de Equipos');
    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: RutaSitio + "/AJAX/JSP/ajaxBuscaAlertas.jsp",
        success: function(data, textStatus, jqXHR) {
            dibujaAlertasEquipos(data);
        }
    });
}

function buscaAlertaProductos() {
    $('#tituloAlerta').html('Productos Proximos a terminar');
    var cabecera = "";
    cabecera += '    <tr id="cabecera">                 ';
    cabecera += '        <th>Existencias</th>           ';
    cabecera += '        <th>Nombre Producto </th>      ';
    cabecera += '        <th>Codigo</th>                ';
    cabecera += '    </tr>                              ';
    $('#cabeceraTabla').html(cabecera);
    $.ajax({
        cache: false,
        type: 'POST',
        url: RutaSitio + "/buscaProductosInicio.action",
        dataType: 'json',
        success: function(data, textStatus, jqXHR) {
            tabla = $('#alertas');
            if (data.respuesta == 'Ok') {
                $.each(data.para, function(index, obj) {
                    var linea = '<tr>' +
                            '<td>' + obj.cantidad + '</td>' +
                            '<td>' + obj.dska_nom_prod + '</td>' +
                            '<td>' + obj.dska_cod + '</td>' +
                            '</tr>';
                    tabla.append(linea);
                });
            }
        }
    });
}