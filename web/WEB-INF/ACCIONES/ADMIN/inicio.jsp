<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include> 
            <style>
                .ocultas{
                    display: none;
                }
            </style>
        </head>
        <body>
        <s:div >
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
        <br/>
        <br/>

        <div class="row">
            <div class="col-md-2 col-sm-0 col-xs-0"></div>
            <div class="col-md-8 col-sm-12 col-xs-12" >
                <div class="alert alert-warning text-center"><h3 id="tituloAlerta"></h3></div>
                <table class="table table-bordered table-hover">
                    <thead id="cabeceraTabla">
                        
                    </thead>
                    <tbody id="alertas">

                    </tbody>
                </table>
            </div>
            <div class="col-md-2 col-sm-0 col-xs-0"></div>
        </div>
        <script type="text/javascript">
            <s:i18n name="co.com.sigemco.alfa.archivos.MessagesBundleProducto" >
                var columna = '<s:text name="texto1" />';         
            </s:i18n>
        </script>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/inicio.js"></script>
    </body>
</html>
