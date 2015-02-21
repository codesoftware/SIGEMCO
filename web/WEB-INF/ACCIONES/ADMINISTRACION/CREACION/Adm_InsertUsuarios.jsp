<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/USUARIO/Adm_InsertUsuarios.js"></script>
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
                        <div class="alert alert-danger" role="alert" ><h4><s:actionerror /></h4></div>
                        <script>
                            mostrarMsn();
                        </script>
                    </s:if>
                </div>
                <div class="MensajesOk" style="display: none;">
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-success"  role="alert" ><h4><s:actionmessage/></h4></div>
                        <script>
                            mostrarMsnOk();
                        </script>
                    </s:if>
                </div>
                <br>
                <s:form action="ingUsuario" theme="simple" cssClass="formInsertUsuario" >
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" class="alert alert-info text-center"><h3>INGRESAR USUARIO</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td >Nombres: </td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.nombre" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Apellidos: </td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.apellido" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Cedula: </td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.cedula" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Correo:</td>
                                <td><s:textfield cssClass="form-control" name="usuaNuevo.correo" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Usuario:</td>
                                <td><s:textfield cssClass="form-control" name="aliasUsuarioNuevo" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Fecha de Nacimiento: </td>
                                <td style="width: 70%">
                                    <div class="input-group date" >
                                        <s:textfield name="usuaNuevo.fechaNacimiento" cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Perfil: </td>
                                <td><s:select cssClass="form-control" list="perfiles"  name="usuaNuevo.tipoUsuario" required="true" headerKey="-1" headerValue="Seleccione un Perfil.."/></td>
                            </tr>
                            <tr>
                                <td>Sede: </td>
                                <td><s:select cssClass="form-control" list="sedes"  name="usuaNuevo.sede" headerKey="-1" headerValue="Sede de ingreso" /></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/newUser.jsp"> 
                                    </s:include>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp">
                                    </s:include>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div> 
    </body>
</html>
