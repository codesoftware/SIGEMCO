<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript" src="<%=RutaSitio %>/JS/ADMINISTRACION/USUARIO/Adm_UpdUsuario.js"></script>
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
        <br/>
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
                <div class="MensajesOk" style="display: none;">
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                        <script>
                            mostrarMsnOk();
                        </script>
                    </s:if>
                </div>
                <s:form action="consDatosXUsua" id="consDatosXUsua" theme="simple" method="post">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>Modificacion de Usuario</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Nombres</td>
                                <td><s:textfield cssClass="form-control nombreUsuario" name="usuaNuevo.nombre" required="true" onkeyup="buscaUsuario(this.value)" /></td>
                            </tr>
                            <tr>
                                <td>Apellidos</td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.apellido" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Cedula</td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.cedula" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Correo</td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.correo" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Usuario</td>
                                <td><s:textfield cssClass="form-control" name="aliasUsuarioNuevo" required="true" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>Fecha de Nacimiento</td>
                                <td><s:textfield cssClass="form-control fechaNacimiento" name="usuaNuevo.fechaNacimiento" required="true" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>Perfil de Usuario</td>
                                <td><s:select cssClass="form-control" list="perfilesMap"  name="usuaNuevo.idPerfil" required="true" headerKey="-1" headerValue="Seleccione un Perfil.."/></td>
                            </tr>
                            <tr>
                                <td>Estado</td>
                                <td><s:select cssClass="form-control" list="estadoMap"  name="usuaNuevo.estado" headerKey="-1" headerValue="Estado Usuario" /></td>
                            </tr>
                            <tr>
                                <td>Sede</td>
                                <td><s:select cssClass="form-control" list="sedes"  name="usuaNuevo.sede" headerKey="-1" headerValue="Sede Usuario" /></td>
                            </tr>
                            <tr style="display: none" >
                                <td><s:textfield name="usuaNuevo.idTius" required="true" readonly="true" /></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="2">
                                    <s:set name="variable" value="modifica" />
                                    <s:if test="%{#variable.equalsIgnoreCase('S')}">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                        <s:include value="/WEB-INF/TEMPLATE/botones/editUser.jsp" />                                    
                                    </s:if>
                                    <s:elseif test="%{#variable.equalsIgnoreCase('N')}">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/findUser.jsp" />
                                        <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                    </s:elseif>
                                    &nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>                
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
    </body>
</html>
