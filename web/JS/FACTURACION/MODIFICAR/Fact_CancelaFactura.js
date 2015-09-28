$(function () {
    $('#buscarFactura').click(function () {
        var datos = new Object();
        datos.fact_fact = $('#fact_fact').val();
        $.ajax({
            url: RutaSitio + "/buscaFacturaXId.action",
            cache: false,
            dataType: 'json',
            data: datos,
            success: function (data, textStatus, jqXHR) {
                if (data.respuesta == 'Ok') {
                    $('#fact_fact').attr('readonly', 'readonly');
                    creaFactura(data.objeto);
                }
            }
        });
    });
    $('#verAsiento').click(function () {
        var idMvco_trans = $('#mvco_trans_asiento').val();
        obtenerAsientocontable(idMvco_trans);
        $('#partidaDoble').modal('show');
    });
    $('#verFactura').click(function(){
        $('#facturaModal').modal('show');
    }); 
    $('#cancelarFact').click(function(){
        $('#CancelaFactura').modal('show');
    });
    $('#cancelaFactuDef').click(function(){
        var idFact = $('#fact_fact').val();
        alert("Este es el id de la factura" + idFact);
    });
});

function creaFactura(factura) {
    $('#postFactura').show();
    var mvco_trans = factura.mvco_trans;
    $('#mvco_trans_asiento').val(mvco_trans);
    $('#noFactModal').html(factura.fact_fact);
    var tableDatosBasicos = '';
    tableDatosBasicos = '<table class=\"table table-bordered\">';
    tableDatosBasicos += '  <tr>';
    tableDatosBasicos += '      <td colspan=\"4\" class=\"alert alert-success text-center\">';
    tableDatosBasicos += '      DATOS BASICOS FACTURA';
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '  </tr>';
    tableDatosBasicos += '  <tr>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += 'FECHA:';
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += factura.factFecini;
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += 'TIPO DE PAGO';
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    var tipoPago = '';
    if(factura.fact_tipo_pago == 'E'){
        tipoPago = 'Efectivo';
    }else{
        tipoPago = 'Tarjeta';
    }
    tableDatosBasicos += tipoPago;
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '  </tr>';
    tableDatosBasicos += '  <tr>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += '      VALOR TOTAL:';
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += factura.factVlrtotal;
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += '      DESCUENTOS:';
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '      <td>';
    tableDatosBasicos += factura.factVlrdcto;
    tableDatosBasicos += '      </td>';
    tableDatosBasicos += '  </tr>';
    tableDatosBasicos += '</table>';
    $('#tablaBasicosFact').html(tableDatosBasicos);
}