<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdMovInventario.js"></script>
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
            <s:form action="inv_conUpdInventario" theme="simple" id="inv_conUpdInventario" method="post">
                <s:textfield name="accion" cssStyle="display:none" value="consultaUpd"/>
                <s:textfield name="movimiento.mvin_mvin" cssStyle="display:none" />
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>ACTUALIZACI&Oacute;N DE MOVIMIENTOS DE INVENTARIO</h3></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 40%">Descripci&oacute;n:</td>
                            <td style="width: 60%"><s:textfield name="movimiento.mvin_descr" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td>Naturaleza</td>
                            <td>
                                <s:select list="naturalezaMvIn"  name="movimiento.mvin_natu" required="true" headerKey="-1" headerValue="Seleccione una naturaleza.." cssClass="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Usuario Implicado:</td>
                            <td>
                                <s:select list="usuarioImplicado" name="movimiento.mvin_usim" required="true" headerKey="-1" headerValue="Seleccione un tipo de usuario.." cssClass="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Implica Venta Facturas:</td>
                            <td>
                                <s:select list="yesNo" name="movimiento.mvin_venta" required="true" headerKey="-1" headerValue="Seleccione si implica facturacion" cssClass="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Implica Inventario Inicial:</td>
                            <td>
                                <s:select list="yesNo" name="movimiento.mvin_inicial" required="true" headerKey="-1" headerValue="Seleccione si es para ingreso de productos nuevos" cssClass="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Implica Reverso de Facturaci&oacute;n:</td>
                            <td>
                                <s:select list="yesNo" name="movimiento.mvin_revfact" required="true" headerKey="-1" headerValue="Seleccione si implica reverso de Facturacion" cssClass="form-control"/>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2" style="text-align: right;">
                                <s:set name="variable" value="modifica" />
                                <s:if test="%{#variable.equalsIgnoreCase('S')}">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" > 
                                        <s:param name="function">actualizarMovInv</s:param>
                                        <s:param name="title">Modificar Movientos de inventario</s:param>
                                        <s:param name="paramFunction">'<s:text name="movimiento.mvin_mvin" />'</s:param>
                                        <s:param name="clase">imagenIcono</s:param>
                                    </s:include>
                                </s:if>
                                <s:else>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                        <s:param name="function">consultarMovInv</s:param>
                                        <s:param name="title">Consultar Movimiento de inventario</s:param>
                                    </s:include>
                                </s:else>                                        
                                <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                &nbsp;&nbsp;
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </s:form>
        </div>
        <div class="col-md-3 col-xs-0 col-sm-0"></div>
    </body>
</html>
