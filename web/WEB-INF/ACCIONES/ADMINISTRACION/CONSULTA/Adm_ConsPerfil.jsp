<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/PERFIL/Adm_ConsdPerfil.js"></script>
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
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="Mensajes" style="display: none;">
                    <s:if test="hasActionErrors()">
                        <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
                        <script>
                            mostrarMsn();
                        </script>
                    </s:if>
                </div>
                <s:form theme="simple" action="adm_consultaPerfil" id="formConsultaPerfil">
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="alert alert-success text-center"  role="alert" ><h3>CONSULA GENERAL DE PERFILES</h3></div>
                        Estado:<br>
                        <select class="form-control" name="perfil.estado">
                            <option value="-1" >Todos</option>
                            <option value="A">Activo</option>
                            <option value="I">Inactivo</option>
                        </select><br>
                        <!--<a href="#" class="btnConsulta" onclick="consultar()">CONSULTAR</a>&nbsp;&nbsp;&nbsp;-->
                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                            <s:param name="function">consultar</s:param>
                            <s:param name="title">Busca Perfiles Por Estado </s:param>                                    
                        </s:include>
                    </div>
                </s:form>
                <div class="MensajesOk" style="display: none;">
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                        <script>
                            mostrarMsnOk();
                        </script>
                    </s:if>
                </div>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <s:if test="%{resultPerfil != null }">
                    <table class="table">
                        <tr>
                            <td>NOMBRE</td>
                            <td>DESCRIPCION</td>
                            <td>ESTADO</td>
                            <td>IDENTIFICACION</td>
                        </tr>
                        <%
                            int i = 0;
                        %>
                        <s:iterator value="resultPerfil">
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
                                <td><a href="#" onclick="permisoPerfil('<s:text name="id"/>')" class="linkPemiso"><s:property value="nombre"/></a></td>
                                <td><s:property value="descripcion"/></td>
                                <td><s:property value="estado"/></td>                        
                                <td><s:property value="id"/> </td>                        
                            </tr>
                        </s:iterator>                
                    </table>
                </s:if>                
            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <form action="adm_updPermisos" id="adm_updPermisos" method="post">
                <input type="hidden" name="perfil" id="linkPerfil" value="">
            </form>
        </div>
    </body>
</html>
