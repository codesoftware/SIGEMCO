<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
    <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
    <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdReferencia.js"></script>
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
        <br/>
            <s:form name="inv_ConReferencia" action="inv_ConReferencia" theme="simple">
            <s:textfield name="accion" cssStyle="display:none" value="consultaGeneral"/>
            <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                <div class="row">
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                        <div class="alert alert-success text-center"  role="alert" ><h3>Consulta General de Referencias</h3></div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4 col-sm-4 col-xs-4">
                        Estado:<br>
                        <s:select  list="estadoMap"  name="referencia.refe_estado" required="true" headerKey="-1" headerValue="Estado" cssClass="form-control"/>

                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-4">
                        Camara (mpx):<br>
                        <s:select list="camara"  name="referencia.refe_came" required="true" headerKey="-1" headerValue="Seleccione mpx de la camara" cssClass="form-control"/>
                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-4">
                        Memoria (GB)<br>
                        <s:select list="memoria" name="referencia.refe_memori" required="true" headerKey="-1" headerValue="Seleccione Almacenamiento Interno" cssClass="form-control"/> 
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4 col-sm-4 col-xs-4">
                        Pantalla (pulgadas)<br>
                        <s:select list="pantalla" name="referencia.refe_pantalla" required="true" headerKey="-1" headerValue="Seleccione pulgadas de la pantalla" cssClass="form-control"/> 
                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-4">
                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                            <s:param name="function">consultarReferencias</s:param>
                            <s:param name="title">Busqueda de Referencias</s:param>
                        </s:include>
                    </div>
                </div>
            </div>                    
        </s:form>
    </div>
    <div class="col-md-2 col-xs-0 col-sm-0"></div>
</div>
<br/>
<div class="row">
    <div class="col-md-1 col-xs-0 col-sm-0"></div>
    <div class="col-md-10 col-xs-12 col-sm-12">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Descripci&oacute;n</th>
                    <th>Camara</th>
                    <th>Memoria</th>
                    <th>Pantalla</th>
                    <th>Estado</th>
                    <th>Accion</th>
                </tr> 
            </thead>
            <tbody>
                <%
                    int i = 0;
                %>
            <s:iterator value="resultReferencia">
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
                    <td><s:property value="refe_desc"/></td>
                <td><s:property value="refe_came"/></td>
                <td><s:property value="refe_memori"/></td>
                <td><s:property value="refe_pantalla"/></td>
                <td><s:property value="refe_estado"/></td>
                <td>
                <s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" >
                    <s:param name="function">actulizarEspecifico</s:param>
                    <s:param name="title">Actualizar Referencia  </s:param>
                    <s:param name="paramFunction">'<s:property value="refe_refe"/>'</s:param>
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
                <s:form action="inv_conUpdReferencia" id="inv_conUpdReferencia" cssStyle="display:none;"  theme="simple" >
    <s:textfield name="accion" id="accion" cssStyle="display:none" value="consultaUpd"/>
    <s:textfield name="referencia.refe_refe" id="refe_refe"/>            
</s:form>
</body>
</html>
