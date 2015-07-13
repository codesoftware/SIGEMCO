<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
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
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12" style="border: 1px solid #D6E3E2;padding-left: 0px;padding-right: 0px;">
                <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h4>INFORMACION GENERAL DE LA RECETA</h4></div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <s:text name="receta.rece_nombre" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Descripcion:</label>
                        <s:text name="receta.rece_desc" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Codigo:</label>
                        <s:text name="receta.rece_codigo" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Estado:</label>
                        <s:text name="receta.rece_estado" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Fecha Creación:</label>
                        <s:text name="receta.rece_fec_ingreso" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Promedio:</label>
                        <s:text name="receta.rece_promedio" />
                    </div>
                </div>  
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Sede:</label>
                        <s:select cssClass="form-control" list="sedes" name="receta.rece_sede"  headerKey="-1" headerValue="Sede a darle precio" />
                    </div>
                </div> 
                <div class="col-md-4 col-xs-6 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Precio:</label>
                        <s:textfield name="receta.rece_precio" cssClass="form-control" />
                    </div>
                </div> 
                <div class="col-md-4 col-xs-12 col-sm-12 text-right">
                    <br/>
                    <button type="submit" class="btn btn-primary">Parametrizar</button>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>        
    </body>
</html>
