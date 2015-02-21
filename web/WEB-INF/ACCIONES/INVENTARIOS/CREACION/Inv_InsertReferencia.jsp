<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
         <script type="text/javascript" src="JS/INVENTARIOS/Inv_UpdReferencia.js"></script>
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
                <s:form action="inv_InsReferencia" method="post" theme="simple">
                    <table class="table table-bordered" >
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>INSERCION DE REFERENCIAS DE CELULAR</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Descripción:</td>
                                <td><s:textfield name="referencia.refe_desc" cssClass="form-control"  /></td>
                            </tr>
                         
                            <tr>
                                <td>Camara (mpx):</td>
                                <td><s:textfield name="referencia.refe_came" cssClass="form-control" onkeypress="return validaNumeros(event)"/></td>
                            </tr>
                            <tr>
                                <td>Memoria interna (GB):</td>
                                <td><s:textfield name="referencia.refe_memori" cssClass="form-control" onkeypress="return validaNumeros(event)" /></td>
                            </tr>
                            <tr>
                                <td>Pantalla (pulgadas):</td>
                                <td><s:textfield name="referencia.refe_pantalla" cssClass="form-control"   onkeypress="return validaNumeros(event)"  /></td>
                            </tr>

                        </tbody>                        
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertarReferencia</s:param>
                                        <s:param name="title">Adicion Referencia</s:param>
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
    </body>
</html>
