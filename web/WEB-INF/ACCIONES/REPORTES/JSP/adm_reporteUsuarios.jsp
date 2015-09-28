<%@page import="co.com.hotel.datos.session.Parametros"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<%    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String permisos = usuario.getPermisos();
    Parametros parametros = (Parametros) session.getAttribute("parametros");
%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/REPORTES/adm_reporteUsuarios.js"></script>
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
                <div id="tabs">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#tabs-1" data-toggle="tab">Busqueda</a></li>
                        <li><a href="#tabs-2" data-toggle="tab">Explicación</a></li>
                    </ul>
                </div>
                <s:form theme="simple" action="adm_usuariosRep" id="adm_usuariosRep" autocomplete="off">
                    <div id="myTabContent" class="tab-content">
                        <div id="tabs-1" class="tab-pane fade in active">
                            <div class="form-group col-md-12 col-xs-12 col-sm-12 thumbnail">
                                <div class="form-group col-md-12 col-xs-12 col-sm-12 alert-success text-center"><h3>Usuarios de la aplicacion</h3></div>
                                <div class="form-group col-md-6 col-xs-6 col-sm-6">
                                    Perfil de Usuario<br>
                                    <s:select cssClass="form-control" list="perfilesMap"  name="usuarioRep.idPerfil" id="usuarioRep_idPerfil" required="true" headerKey="-1" headerValue="Todos"/>
                                </div>
                                <div class="form-group col-md-6 col-xs-6 col-sm-6">
                                    Estado<br>
                                    <s:select cssClass="form-control" list="estadoMap"  name="usuarioRep.estado" id="usuarioRep_estado" headerKey="-1" headerValue="Todos" />
                                </div>
                                <div class="form-group col-md-12 col-xs-12 col-sm-12">
                                    <a class="btnElimina" style="float: right" onclick="generaReporte()">
                                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>&nbsp;Buscar</button>    
                                    </a>
                                </div>
                                </table>                        
                            </div>
                        </div>
                        <div id="tabs-2" class="tab-pane fade">
                            <div class="form-group col-md-12 col-xs-12 col-sm-12 thumbnail alert-info">
                                <p>
                                    Este reporte Muestra los usuarios que estan activos, no activos o
                                    todos los usuarios de la aplicacion
                                </p>
                                <p>
                                    En la pestaña <b>BUSQUEDA</b> podremos realizar los filtros para la generacion del reporte.
                                </p>
                                <p>
                                    Despues de esto En la pestaña <b>GENERAR</b> podremos generar el reporte en pdf o online
                                </p>
                            </div>
                        </div>
                    </div>                
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <script>
            $(function () {
                $('#myTab a:last').tab('show');
            })
        </script>
    </body>
</html>
