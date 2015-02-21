<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdReferencia.js"></script>

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
            <div class="col-md-4 col-xs-0 col-sm-0"></div>
            <div class="col-md-4 col-xs-12 col-sm-12">
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
                <s:form action="inv_UpdReferencia" method="post" id="inv_UpdReferencia" theme="simple"> 
                    <s:textfield name="referencia.refe_refe" cssStyle="display:none;" cssClass="idReferenciaUpdate"/>
                    <s:textfield name="accion" cssStyle="display:none" value="updReferenciaIndv"/>
                    <s:textfield name="subAccion" cssStyle="display:none" value="" cssClass="subAccionForm" />
                    <table style="width: 100%" class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>MODIFICAR REFERENCIA</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><h4>Descripción:</h4></td>
                                <td><s:textfield name="referencia.refe_desc" required="true" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td><h4>Camara (mpx):</h4></td>
                                <td><s:textfield label="Camara (mpx)" name="referencia.refe_came" required="true" cssClass="form-control" onkeypress="return validaNumeros(event)"/></td>
                            </tr>
                            <tr>
                                <td><h4>Memoria (GB):</h4></td>
                                <td><s:textfield label="Memoria (GB):" name="referencia.refe_memori" required="true" maxLength="10" cssClass="form-control" onkeypress="return validaNumeros(event)"/></td>
                            </tr>
                            <tr>
                                <td><h4>Pantalla (Pulgadas):</h4></td>
                                <td><s:textfield label="Pantalla (Pulgadas):" name="referencia.refe_pantalla" required="true" maxLength="10" cssClass="form-control" onkeypress="return validaNumeros(event)"/></td>
                            </tr>

                            <tr>
                                <td><h4>Estado:</h4></td>
                                <td><s:select  list="estadoMap"  name="referencia.refe_estado" required="true" headerKey="-1" headerValue="Estado" cssClass="form-control"/></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr style="text-align: right">
                                <td colspan="2">
                                    <span class="spanModificar">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp" > 
                                            <s:param name="function">actualizacionReferencia</s:param>
                                            <s:param name="title">Modificación atributos de la Referencia</s:param>

                                        </s:include>
                                    </span>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>

            <div class="col-md-4 col-xs-0 col-sm-0"></div>
        </div>
    </body>
</html>
