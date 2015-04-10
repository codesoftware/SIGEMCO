
var valorSelect = '';
$(document).ready(function() {
    $('#listClase').change(function() {
            valorSelect = $('#listClase').val();
            var datos = new Object();
            datos.grup_clas = $('#listClase').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/consultaComboGrupo.action',
                async: false,
                dataType: 'json',
                success: function(response) {
                    if(response.respuesta == 'OK'){
                        var lista = response.objeto;
                        for(var i = 0 ; i<lista.length; i++){
                            $('#grup_grup').append('<option value=\"'+lista[i].grup_grup+'\" >'+lista[i].grup_nombre+'</option>')
                        }
                    }
                }
            });
        
    });

});