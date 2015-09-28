<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio %>/JS/CONTABILIDAD/PUC/Con_ConsGenClase.js"></script>
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
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <td colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>CLASES</h3></td>
                        </tr>
                        <tr>
                            <td class="alert alert-info text-center" style="width: 30%">Codigo</td>
                            <td class="alert alert-info text-center" style="width: 70%">Nombre</td>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="clase">
                            <tr>
                                <td style="text-align: center;">
                                    <s:property value="clas_codigo" />

                                </td>
                                <td>
                                    <a href="#" onclick="buscaClase('<s:text name="clas_clas"/>')">
                                    <s:property value="clas_nombre" />
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>        
        </div>
        <s:form action="buscaGruposXClase" id="buscaGruposXClase" cssStyle="display:none" theme="simple">
            <s:textfield name="clase.clas_clas" id="buscaClas_clas" />
        </s:form>
    </body>
</html>
