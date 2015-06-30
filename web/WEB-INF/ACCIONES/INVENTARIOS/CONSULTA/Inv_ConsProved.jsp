<%-- 
    Document   : Inv_ConsProved
    Created on : 29/06/2015, 12:40:44 PM
    Author     : john
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdProved.js"></script>
        <s:head/>
        <style>
            .linkPemiso{
                cursor: pointer;
                color: #8f0b0b;
                text-decoration: none;
            }
        </style>
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
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <div class="row">
            <div class="col-md-4 col-xs-0 col-sm-0"></div>
            <div class="col-md-4 col-xs-12 col-sm-12">
                <br/>
                <s:form name="inv_ConProved" action="inv_ConProved" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaGeneral"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>Consulta General de Proveedores</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-8 col-sm-8 col-xs-8">
                                Estado:<br>
                                <s:select  list="estadoMap"  name="proved.prov_estado" required="true" headerKey="-1" headerValue="Estado" cssClass="form-control"/>

                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                <br/>
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                    <s:param name="function">consultarProveedores</s:param>
                                    <s:param name="title">Busqueda de Proveedores</s:param>
                                </s:include>
                            </div>
                        </div>
                    </div>                    
                </s:form>
            </div>
            <div class="col-md-4 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <s:if test="resultProved != null">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Nit</th>
                                <th>Razon Social</th>
                                <th>Estado</th>
                                <th>Acción</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <%
                                int i = 0;
                            %>
                            <s:iterator value="resultProved">
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
                                    <td><s:property value="prov_nombre"/></td>
                                    <td><s:property value="prov_nit"/></td>
                                    <td><s:property value="prov_razon_social"/></td>
                                    <td><s:property value="prov_estado"/></td>
                                    <td>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" >
                                            <s:param name="function">actualizarEspecifico</s:param>
                                            <s:param name="title">Actualizar Proveedor </s:param>
                                            <s:param name="paramFunction">'<s:property value="prov_prov"/>'</s:param>
                                            <s:param name="clase">imagenIconoPeq</s:param>
                                        </s:include>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>                        
            </div>

        </s:if>  
        <s:form action="inv_conUpdProved" id="inv_conUpdProved" cssStyle="display:none;"  theme="simple" >
            <s:textfield name="accion" id="accion" cssStyle="display:none" value="consultaUpd"/>
            <s:textfield name="proved.prov_prov" id="prov_prov"/>            
        </s:form>
    </body>
</html>
