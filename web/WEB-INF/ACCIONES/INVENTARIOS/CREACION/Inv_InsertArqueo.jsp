<%-- 
    Document   : Inv_InsertArqueo
    Created on : 6/07/2015, 10:30:17 PM
    Author     : john
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript" src="JS/INVENTARIOS/Inv_UpdArqueo.js"></script>
        </head>
        <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/NEWTEMPLATE/FrameTop.jsp">
                <s:param name="nombre"><s:text name="usuario.apellido"/><s:text name="usuario.nombre"/></s:param>
                <s:param name="perfil"><s:text name="usuario.NomPerfil"/></s:param>
                <s:param name="ultimoIngreso"><s:text name="usuario.UltimoIngreso"/></s:param>
            </s:include>
        </s:div> 
        <s:div cssClass="navigator">
            <s:include value="/WEB-INF/NEWTEMPLATE/menu.jsp">
                <s:param name="title"><s:property value="usuario.usuario"></s:property></s:param> 
            </s:include>
        </s:div>
        <div class="row">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
                <div class="Mensajes" style="display: none;">
                    <s:if test="hasActionErrors()">
                        <div class="alert alert-danger" id="info" role="alert"><h4><s:actionerror/></h4></div>
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
                <s:form action="inv_InsArqueo" method="post" theme="simple">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>CREACIÓN DE ARQUEOS</h3>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Sede:</td>
                                <td> <s:select list="sedes"  id="arqueo.arque_sede" name="arqueo.arque_sede" required="true" headerKey="-1" headerValue="Seleccione una Sede.." cssClass="form-control" />
                                </td>
                            </tr>
                        <tr>
                            <td>Valor en la caja:</td>
                            <td><s:textfield name="arqueo.arque_valorc" id="arqueo.arque_valorc" cssClass="form-control" onkeypress="mascaraMonedaNew(this,expresionRegular)"/></td>
                        </tr>
                        <tr>
                            <td>Comentario:</td>
                            <td><s:textfield name="arqueo.arque_comen" id="arqueo.arque_comen" cssClass="form-control"/></td>
                        </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertarArqueo</s:param>
                                        <s:param name="title">Adicion Arqueo</s:param>
                                    </s:include>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
        </div>
    </body>
</html>
