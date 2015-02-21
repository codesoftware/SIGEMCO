<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        </head>
        <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/NEWTEMPLATE/FrameTop.jsp" >
                <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/SEDE/Adm_AddSede.js"></script>
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

                <s:form action="adm_insertSede" theme="simple" id="adm_insertSede" method="post">
                    <s:textfield name="accion" cssStyle="display:none" value="insertar"/>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="4" class="alert alert-info text-center"><h3>CREACÓN DE SEDES</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Nombre:</td>
                                <td><s:textfield cssClass="form-control" name="sede.sede_nombre" /></td>
                            </tr>
                            <tr>
                                <td>Dirección:</td>
                                <td><s:textfield cssClass="form-control" name="sede.sede_direccion" /></td>
                            </tr>
                            <tr>
                                <td>Teléfono:</td>
                                <td><s:textfield cssClass="form-control" name="sede.sede_telefono" /></td>
                            </tr>
                        </tbody>
                        <tr>
                            <td colspan="4">
                                <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                    <s:param name="function">insertarSede</s:param>
                                    <s:param name="title">Adicion de una sede al Sistema</s:param>
                                </s:include>
                                <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
            </s:form>
        </div>
    </body>
</html>
