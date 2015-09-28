<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/USUARIO/Adm_ConsUsuarios.js"></script>
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
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
            <div class="col-md-6 col-sm-12 col-xs-12 thumbnail">
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
                <s:form theme="simple" action="adm_consultaUsuario" id="adm_consultaUsuario">
                    <div class="row">
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            <div class="alert alert-success text-center"  role="alert" ><h3>CONSULA GENERAL DE USUARIOS</h3></div>
                        </div>                      
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">
                            <label for="estado">Estado Usuario:</label><br/>                       
                            <s:select cssClass="form-control" list="estadoUsuario"  name="estado" required="true" headerKey="-1" headerValue="Todos.."/>
                        </div>       
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">
                            <label for="perfiles">Perfil de Usuario:</label><br/>                         
                            <s:select cssClass="form-control"  list="perfiles"  name="perfil" required="true" headerKey="-1" headerValue="Todos.."/>
                        </div>
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">
                            <label for="perfiles">Sedes:</label><br/>
                            <s:select cssClass="form-control" list="sedes"  name="sedesFiltro" required="true" headerKey="-1" headerValue="Todos.."/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4 col-sm-4 col-xs-4"></div>
                        <div class="form-group col-md-4 col-sm-4 col-xs-4"></div>
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">   
                            <s:include value="/WEB-INF/TEMPLATE/botones/findUser.jsp" />                       
                            <s:include value="/WEB-INF/TEMPLATE/botones/report.jsp" />
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>       
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <s:if test="resultConsGeneral != null">
                    <table class="table table-hover" style="width: 100%">
                        <thead>
                            <tr>
                                <th>NOMBRE</th>
                                <th>IDENTIFICACIÓN</th>
                                <th>CORREO</th>
                                <th>USUARIO</th>
                                <th>PERFIL</th>
                                <th>SEDE</th>
                            </tr>                    
                        </thead>
                        <tbody>
                            <%
                                int i = 0;
                            %>
                            <s:iterator value="resultConsGeneral">
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
                                    <td><s:property value="nombre"/></td>
                                    <td><s:property value="cedula"/></td>
                                    <td><s:property value="correo"/></td>
                                    <td><s:property value="usuario"/></td>
                                    <td><s:property value="NomPerfil"/></td>
                                    <td><s:property value="sede"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>                
            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
        </div>
    </body>
</html>
