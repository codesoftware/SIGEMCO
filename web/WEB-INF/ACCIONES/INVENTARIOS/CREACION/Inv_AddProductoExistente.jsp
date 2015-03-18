<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>        
        <script type="text/javascript" src="<%=RutaSitio %>/JS/INVENTARIOS/Inv_AddProductoExistente.js" ></script>
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
        <s:if test="%{bandera.equalsIgnoreCase('S')}" >
        <div id="row">
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
                <s:form theme="simple" action="inv_consProdForAddEx" id="inv_consProdForAddEx" autocomplete="off">
                    <s:textfield name="accion" value="consultaForAddEx" cssStyle="display:none" />
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="alert alert-success text-center"  role="alert" ><h3>BUSQUEDA DEL PRODUCTO</h3></div>
                        Código:<br>
                        <s:textfield cssClass="form-control" name="producto.dska_codigo"  placeholder="Codigo del Producto" /><br>
                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                            <s:param name="function">buscarProductoIndividual</s:param>
                            <s:param name="title">Busqueda Individual por Codigo</s:param>
                        </s:include>
                    </div>
                    <s:textfield name="accion" value="consIndividual" cssStyle="display:none"/>
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        </s:if>
        <s:else>
            ya busco
        </s:else>
    </body>
</html>
