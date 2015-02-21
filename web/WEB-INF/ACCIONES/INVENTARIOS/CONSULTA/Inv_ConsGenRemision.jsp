<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenRemision.js"></script>
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
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
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
                <s:form action="inv_consRemPorFiltrosGen" id="inv_consRemPorFiltrosGen" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaGen" />
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>CONSULTA GENERAL DE EQUIPOS CELULARES EN PRESTAMO</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-2 col-sm-4 col-xs-4">
                                Referencia:<br>
                                <s:select cssClass="form-control" list="referencias" name="remision.rmce_refe"  headerKey="-1" headerValue="Referencia del Equipo" />
                            </div>
                            <div class="form-group col-md-2 col-sm-4 col-xs-4">
                                Tipo de Plan:<br>
                                <s:select cssClass="form-control" list="tipoPlan" name="remision.rmce_tppl"  headerKey="-1" headerValue="Plan del equipo" />
                            </div>
                            <div class="form-group col-md-2 col-sm-4 col-xs-4">
                                Sede:<br>
                                <s:select cssClass="form-control" list="sedes" name="remision.rmce_sede"  headerKey="-1" headerValue="Sede de ingreso del equipo" />
                            </div>                                                        
                            <div class="form-group col-md-3 col-sm-6 col-xs-6">
                                Imei:<br>
                                <s:textfield name="remision.rmce_imei" cssClass="form-control" />
                            </div> 
                            <div class="form-group col-md-3 col-sm-6 col-xs-6">
                                Iccid:<br>
                                <s:textfield name="remision.rmce_iccid" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-6 col-xs-6">
                                Margen de Fechas<br>
                                <table>
                                    <tr>
                                        <td>
                                            <div class="input-group date" >
                                                <s:textfield name="remision.rmce_fcve" cssClass="form-control" readonly="true"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </td>
                                        <td>
                                            A
                                        </td>
                                        <td>
                                            <div class="input-group date" >
                                                <s:textfield name="remision.fechaBeteween" cssClass="form-control" readonly="true"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="form-group col-md-4 col-sm-6 col-xs-6">
                                Valor:<br>
                                <table>
                                    <tr>
                                        <td><s:textfield name="remision.rmce_valor" cssClass="form-control" onkeyup="mascaraMoneda(this)" onkeypress="return validaNumeros(event)"/></td>
                                        <td>A</td>
                                        <td><s:textfield name="remision.valorBeteween" cssClass="form-control" onkeyup="mascaraMoneda(this)" onkeypress="return validaNumeros(event)"/></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="form-group col-md-2 col-sm-6 col-xs-6">
                                Estado:<br>
                                <s:select cssClass="form-control" list="estadoEqCeluar" name="remision.rmce_estado"  headerKey="-1" headerValue="Ubicacion del Equipo Celular" />                                
                            </div>
                            <div class="form-group col-md-2 col-sm-6 col-xs-6">
                                Pagado en Central:<br>
                                <s:select cssClass="form-control" list="yesNo" name="remision.rmce_pagado"  headerKey="-1" headerValue="Cancelado en Claro" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-10 col-sm-8 col-xs-8"></div>
                            <div class="col-md-2 col-sm-4 col-xs-4" >
                                <div style="float: right">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                        <s:param name="function">buscaGeneral</s:param>
                                        <s:param name="title">Busca Productos</s:param>
                                    </s:include>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <s:if test="listRemisiones != null" >
                    <table class="table table-hover" style="width: 100%">
                        <thead>
                            <tr>
                                <th>REFERENCIA/IMEI</th>
                                <th>TIPO PLAN/ICCID</th>
                                <th>SEDE</th>
                                <th>FECHA VENCIMIENTO</th>
                                <th>VALOR DE VENTA</th>
                                <th>ESTADO</th>
                                <th>PAG. CEN.</th>
                            </tr>                    
                        </thead>
                        <tbody>
                            <%int i = 0;%>
                            <s:iterator value="listRemisiones">
                                <%if (i % 2 == 0) {%>
                                <tr class="active">
                                    <%} else {%>
                                <tr>
                                    <%}
                                        i++;%>
                                    <td><span class="muestraImei" onclick="accionesEquipo('<s:property value="rmce_rmce"/>')"><s:property value="rmce_refe"/><br/></span>
                                        <span class="imei" style="display: none"><b><s:property value="rmce_imei"/></b></span>
                                    </td>
                                    <td><span class="muestraIccid"><s:property value="rmce_tppl"/><br/></span>
                                        <span class="iccid" style="display: none"><b><s:property value="rmce_iccid"/></b></span>
                                    </td>
                                    <td><s:property value="rmce_sede"/></td>
                                    <td><s:property value="rmce_fcve"/></td>
                                    <td><s:property value="rmce_valor"/></td>
                                    <td><s:property value="rmce_estado"/></td>
                                    <td><s:property value="rmce_pagado"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
        </div>
        <script>
            try {
                var vmuestraIccid = '<s:text name="muestraIccid" />';
                if (vmuestraIccid == 'S') {
                    muestraIccid();
                }
            } catch (e) {

            }

            var vMuestraImei = '<s:text name="muestraImei" />';
            if (vMuestraImei == 'S') {
                muestraImei();
            }
        </script>
        <s:set name="valor" value="remision.rmce_valor"/>
        <s:if test="%{#valor!=null}">
            <script>
                var numero = mascaraMonedaConValor('<s:text name="valor" />');
                document.getElementById('inv_consRemPorFiltrosGen_remision_rmce_valor').value = numero;
            </script>

        </s:if>

        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoAcciones">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Acci&oacute;n Equipos Celulares</h4>
                    </div>
                    <div class="modal-body">
                        ¿Que accion desea Realizale al Equipo Celular?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CERRAR
                        </button>
                        <button type="button" class="btn btn-primary" id="sticker">
                            STICKER
                        </button>
                        <button type="button" class="btn btn-primary" id="devolucion">
                            DEVOLUCION
                        </button>
                        <button type="button" class="btn btn-primary" id="actualizar">
                            ACTUALIZAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoAccionDevolver">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Devolución de equipos</h4>
                    </div>
                    <div class="modal-body">
                        ¿Esta seguro de devolver el celular?
                    </div>
                    <div class="modal-footer">
                        <div class="form-group">
                            <label for="exampleInputEmail1">COMENTARIO DEVOLUCIÓN</label>
                            <input type="text" id="comentario" class="form-control"  />
                        </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>
                        <button type="button" class="btn btn-primary" id="devolver">
                            DEVOLVER
                        </button>

                    </div>
                </div>
            </div>
        </div>
        <s:form action="inv_ConCelular"  id="inv_ConCelular" theme="simple" cssStyle="display:none">
            <s:textfield id="accion" name="accion" value="consultaEspecifica"/>  
            <s:textfield id="rmce_rmce" name="remision.rmce_rmce"/>   
        </s:form>
    </body>
</html>
