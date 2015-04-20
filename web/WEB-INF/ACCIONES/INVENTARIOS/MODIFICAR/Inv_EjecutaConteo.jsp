<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_EjecutaConteo.js"></script>
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
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th colspan="4" class="alert alert-success text-center">Datos Conteo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Identificador:</td>
                            <td><s:text name="conteo.copr_copr" /></td>
                            <td>Sede Implicada:</td>
                            <td><s:text name="conteo.sede_nombre" /></td>
                        </tr>
                        <tr>
                            <td>Descripcion:</td>
                            <td><s:text name="conteo.copr_desc" /></td>
                            <td>Fecha Inicial:</td>
                            <td><s:text name="conteo.copr_fecha" /></td>
                        </tr>                        
                    </tbody>                    
                </table>
            </div>
            <div class="col-md-10 col-sm-0 col-xs-0"></div>
        </div>
        <div id="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-0 col-xs-0">
                <s:form theme="simple">
                    <s:textfield name="conteo.copr_copr" id="copr_coprId"/>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td class="alert alert-info text-center" colspan="4">Ejecucion conteo</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Codigo Producto:</td>
                                <td><s:textfield name="dska_codigo" id="codigo" cssClass="form-control" onkeyup="validarEnter(event,'1')"/></td>
                                <td>Cantidad:</td>
                                <td><s:textfield name="dska_cantidad" cssClass="form-control" id="cantidad" /></td>
                            </tr>
                            <tr>
                                <td colspan="3"></td>
                                <td class="text-right">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">agregaProductoConteo</s:param>
                                        <s:param name="title">Registro de Productos a Inventario</s:param>
                                    </s:include> 
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </s:form>
            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>                            
            <div class="col-md-10 col-sm-12 col-xs-12">
                <table class="table table-bordered">

                </table>
            </div>                            
            <div class="col-md-1 col-sm-0 col-xs-0"></div>                            
        </div>
        <!-- Div utilizado para visualizar los mensajes -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="mensaje">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">INFORMACION</h4>
                    </div>
                    <div class="modal-body">
                        <span id="textoMsn"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
