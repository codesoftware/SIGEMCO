<%-- 
    Document   : Inv_InsertProved
    Created on : 29/06/2015, 10:21:56 AM
    Author     : john
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript" src="JS/INVENTARIOS/Inv_UpdProved.js"></script>
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
                <s:form action="inv_InsProved" method="post" theme="simple">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>CREACIÓN DE PROVEEDORES</h3>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Nombre:</td>
                                <td><s:textfield name="proved.prov_nombre" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Nit:</td>
                                <td><s:textfield name="proved.prov_nit" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Razón Social:</td>
                                <td><s:textfield name="proved.prov_razon_social" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Representante:</td>
                                <td><s:textfield name="proved.prov_representante" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Teléfono fijo:</td>
                                <td><s:textfield name="proved.prov_telefono" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Dirección:</td>
                                <td><s:textfield name="proved.prov_direccion" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Teléfono Celular:</td>
                                <td><s:textfield name="proved.prov_celular" cssClass="form-control"/></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertarProveedor</s:param>
                                        <s:param name="title">Adicion Proveedor</s:param>
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
