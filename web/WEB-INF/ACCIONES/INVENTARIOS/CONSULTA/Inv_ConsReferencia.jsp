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
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
            <div class="col-md-8 col-xs-12 col-sm-12">
                <s:form name="inv_ConReferencia" action="inv_ConReferencia" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaGeneral"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>Consulta General de Referencias</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                                Estado:<br>
                                <s:select  list="estadoMap"  name="referencia.refe_estado" required="true" headerKey="-1" headerValue="Estado" cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                                <s:text name="modeloAsociado.caracteristica1"/><br>
                                <s:textfield name="referencia.refe_came" cssClass="form-control"/>                                
                            </div>
                            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                                <s:text name="modeloAsociado.caracteristica2"/><br>
                                <s:textfield name="referencia.refe_memori" cssClass="form-control"/>                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                                <s:text name="modeloAsociado.caracteristica3"/><br>
                                <s:textfield name="referencia.refe_pantalla" cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-6 col-xs-12">
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
        <s:if test="%{resultReferencia != null }">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Descripci&oacute;n</th>
                                <th><s:text name="modeloAsociado.caracteristica1"/></th>
                                <th><s:text name="modeloAsociado.caracteristica2"/></th>
                                <th><s:text name="modeloAsociado.caracteristica3"/></th>
                                <th>Estado</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <s:iterator value="resultReferencia">                                
                                <tr>
                                    <td>
                                        <a href="#" onclick="actulizarEspecifico('<s:property value="refe_refe"/>');">
                                            <s:property value="refe_nombre"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="#" onclick="actulizarEspecifico('<s:property value="refe_refe"/>');">
                                            <s:property value="refe_desc"/>
                                        </a>
                                    </td>
                                    <td><s:property value="refe_came"/></td>
                                    <td><s:property value="refe_memori"/></td>
                                    <td><s:property value="refe_pantalla"/></td>
                                    <td>
                                        <script>
                                    var estado = '<s:property value="refe_estado"/>';
                                    if (estado == 'A') {
                                        document.write('ACTIVO');
                                    } else {
                                        document.write('INACTIVO');
                                    }
                                        </script>                                        
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>                        
            </div>
        </s:if>
        <s:form action="inv_conUpdReferencia" id="inv_conUpdReferencia" cssStyle="display:none;"  theme="simple" >
            <s:textfield name="accion" id="accion" cssStyle="display:none" value="consultaUpd"/>
            <s:textfield name="referencia.refe_refe" id="refe_refe"/>            
        </s:form>
    </body>
</html>
