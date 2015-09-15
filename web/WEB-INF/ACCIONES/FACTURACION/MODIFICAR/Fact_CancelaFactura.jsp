<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/MODIFICAR/Fact_CancelaFactura.js"></script>
    </head>
    <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/NEWTEMPLATE/FrameTop.jsp" > 
                <s:param name="nombre"><s:text name="usuario.apellido"/> <s:text name="usuario.nombre"/></s:param>
                <s:param name="perfil"><s:text name="usuario.NomPerfil"/></s:param>
                <s:param name="ultimoIngreso"><s:text name="usuario.ultimoIngreso"/></s:param>
            </s:include>
        </s:div>
        <s:div cssClass="navigator">
            <s:include value="/WEB-INF/NEWTEMPLATE/menu.jsp">
                <s:param name="title"><s:property value="usuario.usuario" /></s:param>
            </s:include> 
        </s:div>
        <div class="row">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
                <div class="Mensajes" style="display: none;">
                    <s:if test="hasActionErrors()">
                        <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
                        <script>
                            mostrarMsn();
                        </script>
                    </s:if>
                </div>
                <div class="MensajesOk" style="display: none;">
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                        <script>
                            mostrarMsnOk();
                        </script>
                    </s:if>
                </div>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <div class="row">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12" style="border: 1px solid #D6E3E2;padding-left: 0px;padding-right: 0px;">
                <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h4>CANCELACION DE FACTURAS</h4></div>
                <div class="col-md-8 col-xs-12 col-sm-12 ">
                    <div class="form-group">
                        <label for="factCanc">Digite el numero de Factura a cancelar:</label>
                        <s:textfield name="factura.fact_fact" cssClass="form-control" id="fact_fact" onkeypress="soloNumeros();" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-12 ">
                    <div class="form-group">
                        <br/>
                        <a href="#" class="btn btn-primary" id="buscarFactura">BUSCAR</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <br/>
        <div class="row" id="postFactura" style="display: none;">
            <div class="col-md-4 col-xs-0 col-sm-0"></div>
            <div class="col-md-4 col-xs-12 col-sm-12 text-center">
                <div href="#" class="btn btn-default" id="verAsiento" >ASIENTO CONTABLE</div> &nbsp;&nbsp;&nbsp;
                <input type="hidden" id="mvco_trans_asiento"/>
                <a href="#" class="btn btn-warning" id="verFactura" >Info Factura.</a> <br/><br/>
                <a href="#" class="btn btn-danger" id="cancelarFact" >Cancela Factura.</a>
            </div>
            <div class="col-md-4 col-xs-0 col-sm-0"></div>                        
        </div>
        <!--Modal para el asiento contable -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="partidaDoble">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">ASIENTO CONTABLE</h4>
                    </div>
                    <div class="modal-body">
                        <span id="tablaAsientocontable"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-success" data-dismiss="modal" id="parametrizarPrecio">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal para la informacion de la factura-->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="facturaModal">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Factura No. <span id="noFactModal"></span></h4>
                    </div>
                    <div class="modal-body">
                        <span id="tablaBasicosFact"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-success" data-dismiss="modal" id="parametrizarPrecio">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal advertencia cancelacion factura-->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="CancelaFactura">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">CANCELACIÓN FACTURA</h4>
                    </div>
                    <div class="modal-body">
                        <p>
                            ¿Relamente desea cancelar la factura?
                        </p>
                        <p>
                            Recuerde que si cancela la factura se reversara el inventario, contabilidad y la factura ya no sera valida.
                        </p>
                        <p>
                            Porfavor Digite el motivo de la cancelación:
                        </p>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-success" data-dismiss="modal" id="cancelaFactuDef" >
                            CANCELAR DEFINITIVO
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
