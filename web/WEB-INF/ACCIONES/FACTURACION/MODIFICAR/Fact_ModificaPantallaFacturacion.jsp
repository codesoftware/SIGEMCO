<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/Fact_ModificaPantallaFacturacion.js"></script>
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
        <s:form method="post" action="Inv_adicionaPantallaPrinc" id="Inv_adicionaPantallaPrinc" theme="simple">
            <s:textfield name="accion" value="addPantallaPrincipal" cssStyle="display:none"/>
            <s:textfield name="tipoProducto" id="tipoProducto" cssStyle="display:none"/>
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12" style="border: 1px solid #D6E3E2;padding-left: 0px;padding-right: 0px;">
                    <div class="panel">
                        <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h3>PRODUCTOS Y RECETAS QUE DESEA VER EN LA PANTALLA PRINCIPAL DE FACTURACION</h3></div>
                        <div class="col-md-5 col-xs-12 col-sm-12 ">
                            <div class="form-group">
                                <label for="codigo">Codigo:</label>
                                <s:textfield name="receta.rece_codigo" cssClass="form-control" id="codigo" onkeyup="validarEnter(event,'1');"/>
                            </div>                                
                        </div>
                        <div class="col-md-5 col-xs-12 col-sm-12 ">
                            <div class="form-group">
                                <label for="posicion">Posicion:</label>
                                <s:textfield name="posicion" cssClass="form-control" onkeypress="soloNumeros();" id="posicion"/>
                            </div>                                
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-12 ">
                            <br/>
                            <a class="btn btn-primary adcionaLista">Adicionar</a>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12" id="consultas">

                        </div>
                    </div>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
        </s:form>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <table class="table table-bordered table-responsive">
                    <thead class="alert alert-info">
                        <tr>
                            <td colspan="4" class="text-center">PRODUCTOS</td>
                        </tr>
                        <tr>
                            <th style="width: 10%">Codigo</th>
                            <th style="width: 60%">Nombre</th>
                            <th style="width: 25%">Posición</th>
                            <th style="width: 5%">Accion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="productosPantalla">
                            <tr>
                                <td><s:property value="ppfa_codigo"/></td>
                                <td><s:property value="ppfa_nombre"/></td>
                                <td><s:property value="ppfa_posicion"/></td>
                                <td>
                                    <button type="button" class="btn btn-danger" onclick="eliminarFila(this)" data-ppfa="<s:property value="ppfa_ppfa"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </td>
                            </tr>
                        </s:iterator>                
                    </tbody>
                </table>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <table class="table table-bordered table-responsive">
                    <thead class="alert alert-info">
                        <tr>
                            <td colspan="4" class="text-center">RECETAS</td>
                        </tr>
                        <tr>
                            <th style="width: 10%">Codigo</th>
                            <th style="width: 60%">Nombre</th>
                            <th style="width: 25%">Posición</th>
                            <th style="width: 5%">Accion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="recetasPantalla">
                            <tr>
                                <td><s:property value="ppfa_codigo"/></td>
                                <td><s:property value="ppfa_nombre"/></td>
                                <td><s:property value="ppfa_posicion"/></td>
                                <td>
                                    <button type="button" class="btn btn-danger" onclick="eliminarFila(this)" data-ppfa="<s:property value="ppfa_ppfa"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </td>
                            </tr>
                        </s:iterator>                
                    </tbody>
                </table>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
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
