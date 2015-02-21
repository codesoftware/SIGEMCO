<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_InsertCelulares.js"></script>
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
                <br/>
                <s:form action="inv_insCelular" id="inv_insCelular" method="post" theme="simple" autocomplete="off">
                    <s:textfield name="accion" value="insertar" cssStyle="display:none" />
                    <table class="table table-bordered" >
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>INSERCION DE CELULARES</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 35%;">Referencia:</td>
                                <td style="width: 65%;">
                                    <s:select cssClass="form-control" list="referencias" name="remision.rmce_refe"  headerKey="-1" headerValue="Referencia del Equipo" />
                                </td>
                            </tr>
                            <tr>
                                <td>Imei:</td>
                                <td>
                                    <s:textfield name="remision.rmce_imei" cssClass="form-control" maxLength="15" />
                                </td>
                            </tr>
                            <tr>
                                <td>Iccid:</td>
                                <td>
                                    <s:textfield name="remision.rmce_iccid" cssClass="form-control" />
                                </td>
                            </tr>
                            <tr>
                                <td>Valor de Venta:</td>
                                <td>
                                    <s:textfield name="remision.rmce_valor" cssClass="form-control" onkeyup="mascaraMoneda(this)" onkeypress="return validaNumeros(event)"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Comision:</td>
                                <td>
                                    <s:textfield name="remision.rmce_comision" cssClass="form-control" id="comision" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>Tipo Plan:</td>
                                <td><s:select cssClass="form-control" list="tipoPlan" name="remision.rmce_tppl"  headerKey="-1" headerValue="Plan del equipo" /></td>
                            </tr>
                            <tr>
                                <td>Fecha de Vencimiento:</td>
                                <td>
                                    <div class="input-group date" >
                                        <s:textfield name="remision.rmce_fcve" cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Sede:</td>
                                <td><s:select cssClass="form-control" list="sedes" name="remision.rmce_sede"  headerKey="-1" headerValue="Sede de ingreso del equipo" /></td>
                            </tr>
                        </tbody>                        
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertarCelular</s:param>
                                        <s:param name="title">Adicion un equipo Celular</s:param>
                                    </s:include>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />

                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <s:if test="%{remision.rmce_comision != null }">
            <script type="text/javascript">
                var valor = mascaraMonedaConValor('<s:text name="remision.rmce_comision" />');
                document.getElementById('comision').value = valor;
            </script>
        </s:if>
        <s:else>
            <script>
                alert('Debe parametrizar la comision de venta para equipos celulares');
            </script>
        </s:else>
    </body>
</html>
