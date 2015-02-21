$(function(){
    $('#sevicioAdd').click(function (){
        filtrosReservaHabitacion();
    });
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });
    $('#buscarPosibleReservHab').click(function(){
        buscaPosiblesReservasXFiltros();
    });
    $('#productoAdd').click(function(){
        filtrosProductos();        
    });
    $('#buscaPosiblesProd').click(function (){
        buscaPosiblesProductos();                
    });
    $('#confirmarReserva').click(function(){
        adicionaReservaFactura()     
    });
});

/**
 * Funcion la cual se activa al dar click sobre el boton de agregar.
 * @returns {undefined}
 */
function agregar() {
    $('#dialogoAddServProd').modal('show');
}


/**
 * 
 * 
 
 $(function() {
 confirmaElimServ = $('#confirmaEliminaServ').dialog({
 autoOpen: false,
 height: 230,
 width: 450,
 modal: true,
 buttons: {
 "Eliminar": function() {
 ejecurtaEliminarServicios();
 },
 Cancel: function() {
 confirmaElimServ.dialog("close");
 }
 }
 });
 //Dialogo en el cual se le pregunta al usuario si desea adicionar productos o servicios
 chooseAdd = $('#choseAdd').dialog({
 autoOpen: false,
 height: 230,
 width: 450,
 modal: true,
 buttons: {
 "Productos": function() {
 adicionaProductos();
 },
 "Servicios": function() {
 adicionaServicios();
 },
 Cancel: function() {
 chooseAdd.dialog("close");
 }
 }
 });
 //Dialogo en el cual se le pregunta si desea eliminar productos o servicios
 chooseMinus = $('#choseMinus').dialog({
 autoOpen: false,
 height: 230,
 width: 450,
 modal: true,
 buttons: {
 "Productos": function() {
 removerProductos();
 },
 "Servicios": function() {
 removerServicios()
 },
 Cancel: function() {
 chooseMinus.dialog("close");
 }
 }
 });
 
 listaRemoveServ = $('#listaRemoveServ').dialog({
 autoOpen: false,
 width: 800,
 modal: true,
 buttons: {
 "Eliminar Servicios": function() {
 eliminarServicios();
 },
 Cancel: function() {
 listaRemoveServ.dialog("close");
 }
 }
 });
 //Formulario en el cual se le dan los parametros de busqueda de los productos 
 //a ingresar
 formBusqProductos = $('#adicionProductos').dialog({
 autoOpen: false,
 width: 630,
 modal: true,
 buttons: {
 "Buscar": function() {
 formBuscarProductos();
 },
 Cancel: function() {
 formBusqProductos.dialog("close");
 }
 }
 });
 //dialogo en el cual sale la lista de productos que cumplen con los criterios de busqueda
 listaAddProd = $('#listaAddProd').dialog({
 autoOpen: false,
 width: 680,
 height: 400,
 modal: true,
 buttons: {
 "Adicionar": function() {
 adicionProductosFactura();
 },
 Cancel: function() {
 listaAddProd.dialog("close");
 }
 }
 });    
 //Aqui aparecera la lista de productos de la factura
 listaRemoveProd = $('#listaRemooveProd').dialog({
 autoOpen: false,
 width: 800,
 modal: true,
 buttons: {
 "Eliminar Productos": function() {
 eliminarProductos();
 },
 Cancel: function() {
 $(this).dialog("close");
 }
 }
 });
 
 addServicioFiltro = $('#addServicioFiltro').dialog({
 autoOpen: false,
 height: 390,
 width: 500,
 modal: true,
 buttons: {
 "Buscar": function() {
 ejecurtaBusquedaServicios();
 },
 Cancel: function() {
 addServicioFiltro.dialog("close");
 }
 }
 });
 //Dialog en el cual se  visualizara la lista en la cual se encontraran las posibles habitaciones
 listaAddServ = $('#listaAddServ').dialog({
 autoOpen: false,
 height: 390,
 width: 580,
 modal: true,
 buttons: {
 "Adicionar": function() {
 adicionarServicios();
 },
 Cancel: function() {
 listaAddServ.dialog("close");
 }
 }
 });
 
 $( "#fechaReserva" ).datepicker({
 defaultDate: +0,
 dateFormat: "dd/mm/yy"        
 });
 });
 //Abre el dialogo en el cual se le pregunta al usuario si desea adicionar servicios o productos
 function adicionarItems() {
 chooseAdd.dialog("open");
 }
 //Abre el dialogo en el cual se le pregunta al usuario si desea elminar servicios o productos
 function eliminarItems() {
 chooseMinus.dialog("open");
 }
 //Se encarga de recargar el formulario
 function recargarFormulario() {
 setTimeout(function() {
 $('#recargarFactura').submit();
 }, 2000);
 }
 
 function postDialogAc(valor, dialogo) {
 $('#informacion').dialog('close');
 }
 
 function ejecutarReporteFactura(idFactura){   
 var fact_fact= idFactura;
 var url = "Rep_GenerarFactura?factura.fact_fact="+ fact_fact;    
 window.open(url,"_blank","directories=no, status=no,width=1400, height=870,top=0,left=0");
 }
 */