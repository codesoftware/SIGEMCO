<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/Fac_InsertClienFactura.js"></script>        
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
        <div class="row" id="buscaCliente">
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
                <s:form action="Fac_consCliente" name="Fac_consCliente" id="Fac_consCliente" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaCliente" />
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" ><h3>FACTURACIÓN</h3></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" ><h3>Registro de cliente para inicio de Facturación</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Cedula Cliente: </td>
                                <td><s:textfield cssClass="form-control" name="cliente.clien_cedula" required="true" id="cedulaCliente"/></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;" >
                                    <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                        <s:param name="function">inicio</s:param>
                                        <s:param name="title">Buscar Cliente</s:param>                                    
                                    </s:include>                                    
                                </td>
                            </tr>                    
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row" id="clienteNuevo" style="display: none;">
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
                <s:form action="Fac_nuevoCliente" name="Fac_nuevoCliente" id="Fac_nuevoCliente" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="insertarCliente" />
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" ><h3>NUEVO CLIENTE</h3></td>
                            </tr>                            
                        </thead>
                        <tbody>
                            <tr>
                                <td>Cedula:</td>
                                <td style="text-align: left">
                                    <b><s:text name="cliente.clien_cedula"/></b>
                                    <s:textfield name="cliente.clien_cedula" style="display:none"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Nombres:*</td>
                                <td><s:textfield  name="cliente.clien_nombres" cssClass="form-control" /></td>                                
                            </tr>
                            <tr>
                                <td>Apellidos*:</td>
                                <td><s:textfield name="cliente.clien_apellidos" cssClass="form-control" /></td>
                            </tr>
                            <tr>
                                <td>Correo:</td>
                                <td><s:textfield name="cliente.clien_correo" cssClass="form-control"/></td>
                            </tr>                            
                            <tr>
                                <td>Telefono:</td>
                                <td><s:textfield name="cliente.clien_telefono" cssClass="form-control" /></td>                                
                            </tr>                            
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/newClient.jsp" >
                                        <s:param name="function">insertar</s:param>
                                        <s:param name="title">Adicionar Cliente a la base de Datos</s:param>                                    
                                    </s:include>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">Recuerde que los campos obligatorios son (*)</td>
                            </tr>
                        </tfoot>
                    </table>                    
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>

        <br/>
        <br/>
        <div id="clienteNuevo" style="display:none">

        </div>
        <s:set name="cliente" value="%{existeCliente}" />
        <s:if test="%{#cliente.equalsIgnoreCase('N')}">            
            <script>
                $('#clienteNuevo').show('fold');
                $('#buscaCliente').hide('slow');
            </script>
        </s:if>
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
