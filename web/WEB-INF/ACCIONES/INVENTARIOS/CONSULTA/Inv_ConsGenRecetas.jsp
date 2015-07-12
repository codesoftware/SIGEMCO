<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenRecetas.js"></script>
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
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <s:form theme="simple" action="Inv_ConsGenReceta" method="post">
            <s:textfield name="accion" value="consGeneral" cssStyle="display:none;"/>
            <div class="row">
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
                <div class="col-md-6 col-xs-12 col-sm-12">
                    <div class="panel">
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h3>CONSULTA GENERAL RECETAS O PLATOS</h3></div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="nombre">Nombre:</label>
                                    <s:textfield name="rece_nombre" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="descripcion">Descripcion:</label>
                                    <s:textfield name="rece_desc" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="codigo">Codigo:</label>
                                    <s:textfield name="rece_codigo" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-12 col-xs-12 col-sm-12 text-right">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
            </div>
        </s:form>
        <s:if test="recetas != null">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <div class="row">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Estado</th>
                                    <th>Promedio</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="recetas">
                                    <tr>
                                        <td>
                                            <script>
                                                var permiso = '<s:text name="permisoAct"/>';
                                                if (permiso == 'S') {
                                                    document.write("<a href=\"#\" onclick=\"actualizar('<s:text name="rece_rece"/>')\"><s:text name="rece_codigo"/></a>");
                                                } else {
                                                    document.write('<s:text name="rece_codigo"/>');
                                                }
                                            </script>                                            
                                        </td>
                                        <td><s:text name="rece_nombre"/></td>                                        
                                        <td><s:text name="rece_desc"/></td>                                        
                                        <td><s:text name="rece_estado"/></td>                                        
                                        <td><s:text name="rece_promedio"/></td>                                        
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>                        
                    </div>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
        </s:if>
        <s:form action="consActualizaReceta" theme="simple" method="post" cssStyle="display:none;" id="consultaActualizacionReceta">
            <s:textfield name="receta.rece_rece" id="rece_receActu" />
            <s:textfield name="action" value="consultaActuliza" />
        </s:form>
    </body>
</html>
