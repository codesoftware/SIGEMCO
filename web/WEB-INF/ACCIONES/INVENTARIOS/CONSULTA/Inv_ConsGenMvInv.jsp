<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenMvInv.js"></script>
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
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
            <div class="col-md-8 col-xs-12 col-sm-12">
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
                <s:form name="inv_consMovInventario" action="inv_consMovInventario" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaGeneral"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>Consulta General de Movimientos de Inventario</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Descripci&oacute;n:<br>
                                <s:textfield name="movimiento.mvin_descr" cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Naturaleza:<br>
                                <s:select list="naturalezaMvIn"  name="movimiento.mvin_natu" required="true" headerKey="-1" headerValue="Seleccione una naturaleza.." cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Usuario Implicado:<br>
                                <s:select list="usuarioImplicado" name="movimiento.mvin_usim" required="true" headerKey="-1" headerValue="Seleccione un tipo de usuario.." cssClass="form-control"/> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Implica Venta:<br>
                                <s:select list="yesNo" name="movimiento.mvin_venta" required="true" headerKey="-1" headerValue="Facturacion" cssClass="form-control"/> 
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Reverso de Facturaci&oacute;n:<br>
                                <s:select list="yesNo" name="movimiento.mvin_revfact" required="true" headerKey="-1" headerValue="reverso de facturacion" cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                <br>
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                    <s:param name="function">buscaGeneralMvIn</s:param>
                                    <s:param name="title">Busqueda de Movimientos de Inventario</s:param>
                                </s:include>
                            </div>
                        </div>
                    </div>                    
                </s:form>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <s:if test="%{listMov != null}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Descripci&oacute;n</th>
                                <th>Naturaleza</th>
                                <th>Usuario Implicado</th>
                                <th>Implica Facturaci&oacute;n</th>
                                <th>Implica Reverso Facturaci&oacute;n</th>
                                <th>Implica Inventario Inicial</th>
                                <th>Accion</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <%
                                int i = 0;
                            %>
                            <s:iterator value="listMov">
                                <%
                                    if (i % 2 == 0) {
                                %>
                                <tr class="active">
                                    <%
                                    } else {
                                    %>
                                <tr>
                                    <%
                                        }
                                        i++;
                                    %>
                                    <td><s:property value="mvin_descr"/></td>
                                    <td><s:property value="mvin_natu"/></td>
                                    <td><s:property value="mvin_usim"/></td>
                                    <td><s:property value="mvin_venta"/></td>
                                    <td><s:property value="mvin_revfact"/></td>
                                    <td><s:property value="mvin_inicial"/></td>
                                    <td>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" >
                                            <s:param name="function">actulizarEspecifico</s:param>
                                            <s:param name="title">Actualizar movimiento de inventario   </s:param>
                                            <s:param name="paramFunction">'<s:property value="mvin_mvin"/>'</s:param>
                                            <s:param name="clase">imagenIconoPeq</s:param>
                                        </s:include>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>                        
        </div>
        <s:form action="inv_conUpdInventario" id="inv_conUpdInventario" cssStyle="display:none;" >
            <s:textfield name="accion" cssStyle="display:none" value="consultaUpd"/>
            <s:textfield name="movimiento.mvin_mvin"/>            
        </s:form>
    </body>
</html>
