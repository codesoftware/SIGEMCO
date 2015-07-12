<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript">
            $(function(){
               $('#insertarBtn').click(function(){
                   document.forms[0].submit();
               }); 
            });
        </script>
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
        <div class="row">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
            <s:form theme="simple" method="post" action="Inv_InsertReceta">
                <s:textfield name="accion" value="insertReceta" cssStyle="display:none" />
                <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td class="alert alert-info text-center" colspan="2"><h3>Adición de Recetas o Platos</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    Nombre:
                                </td>
                                <td>
                                    <s:textfield name="receta.rece_nombre" cssClass="form-control"/>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    Descripción:
                                </td>
                                <td>
                                    <s:textfield name="receta.rece_desc" cssClass="form-control"/>
                                </td>                            
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <a href="#" class="btn btn-default" id="insertarBtn">
                                        Insertar
                                    </a>
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
