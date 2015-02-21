<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp">
        </s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/EMPRESA/Adm_ParamGenerales.js"></script>
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
                <s:form theme="simple" name="adm_updtParaGenEmp" id="adm_updtParaGenEmp" action="adm_updtParaGenEmp" autocomplete="off">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" id="info" role="alert"><h3>PARAMETROS GENERALES EMPRESARIALES</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>IVA:</td>
                                <td>
                                    <div class="input-group">
                                        <span class="input-group-addon">%</span>
                                        <s:textfield id="iva" name="empresa.iva" cssClass="form-control" onkeypress="soloNumeros();"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Dias Vencimiento:</td>
                                <td><s:textfield id="diasVen" name="empresa.diasVen" cssClass="form-control" data-toggle="tooltip" data-placement="bottom" title="Número de días Previos a vencimiento de entrega del equipo"  onkeypress="soloNumeros();"/></td>
                            </tr>
                            <tr>
                                <td>Comisi&oacute;n:</td>
                                <td>
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <s:textfield id="comision" name="empresa.comision" cssClass="form-control" data-toggle="tooltip" data-placement="bottom" title="Valor de comision que se cobrara por cada equipo celular al venderlo"  onkeypress="soloNumeros();" onkeyup="mascaraMoneda(this)"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" >
                                    <div style="float: right">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp">
                                            <s:param name="function">ingresarParametros</s:param>
                                            <s:param name="title">Actualizar parametros Generales de la compañia</s:param>
                                        </s:include>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp">
                                        </s:include>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <s:if test="%{empresa.comision!=null}">
            <script>
                var valor = mascaraMonedaConValor('<s:text name="empresa.comision" />');
                document.getElementById('comision').value = valor;
            </script>
        </s:if>
    </body>
</html>
