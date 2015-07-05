<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <s:head/>
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
            <div class="col-md-4 col-sm-12 col-xs-12">
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
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-0 col-xs-0"></div>
            <div class="col-md-4 col-sm-12 col-xs-12">
                <div class="row thumbnail">
                    <div class="alert alert-success text-center"  role="alert" ><h3>ACTIVACION O INACTIVACION DE PRODUCTOS</h3></div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        Codigo Producto:<br>
                        <s:textfield cssClass="form-control" name="producto.codigo"/>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <a class="btn btn-primary">
                            Buscar
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-0 col-xs-0"></div>
        </div>
    </body>
</html>
