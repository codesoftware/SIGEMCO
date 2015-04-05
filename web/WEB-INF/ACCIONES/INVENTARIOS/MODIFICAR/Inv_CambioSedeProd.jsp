<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>        
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_CambioSedeProd.js"></script>
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
        <!-- Div para mensajes de error y mensajes -->
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
        <s:if test="producto == null" >
            <div class="row"> 
                <div class="col-md-3 col-sm-0 col-xs-0"></div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <s:form action="inv_ConsProdCambioSede" id="inv_ConsProdCambioSede" method="post" theme="simple">
                        <s:textfield name="accion" value="buscaProdCambioSede" cssStyle="display:none"/>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>CAMBIO DE SEDES</h3></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="text-center"><h4>PRODUCTO A CAMBIAR DE SEDE</h4></td>
                                </tr>
                                <tr>
                                    <td>Codigo del Producto</td>
                                    <td><s:textfield name="producto.dska_cod" id="codigoProd" cssClass="form-control" onkeyup="remplazaGuion(this.value,'codigoProd');validarEnter(event,'1')" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2" style="text-align: right;">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                            <s:param name="function">consultarProd</s:param>
                                            <s:param name="title">Buscar Producto</s:param>                                    
                                        </s:include>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                    </td>
                                </tr>
                            </tfoot>
                        </table> 
                    </s:form>                               
                </div>
                <div class="col-md-3 col-sm-0 col-xs-0"></div>
            </div>
        </s:if>
        <s:else>
            <div class="row">
                <div class="col-md-2 col-sm-0 col-xs-0"></div>
                <div class="col-md-8 col-sm-12 col-xs-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr onclick="ocultaDatosProd()">
                                <th class="alert alert-success text-center" colspan="6"><h4>Producto</h4></th>
                        </tr>
                        </thead>
                        <tbody class="datosProd">
                            <tr>
                                <td>Codigo:</td>
                                <td><s:text name="producto.dska_cod" /></td>
                                <td>Promedio Pond:</td>
                                <td><s:text name="producto.promPonderado" /></td>
                                <td>Existencias:</td>
                                <td><s:text name="producto.cantExis" /></td>
                            </tr>
                            <tr>
                                <td>Nombre:</td>
                                <td><s:text name="producto.dska_nom_prod" /></td>
                                <td>Marca:</td>
                                <td><s:text name="producto.dska_marca" /></td>
                                <td>Referencia:</td>
                                <td><s:text name="producto.referenciaNombre" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-2 col-sm-0 col-xs-0"></div>
            </div>
            <div class="row">
                <div class="col-md-1 col-sm-0 col-xs-0"></div>
                <div class="col-md-10 col-sm-12 col-xs-12">
                    <s:form theme="simple" method="post" id="inv_cambioSedeProd" action="inv_cambioSedeProd">
                        <s:textfield name="accion" value="cambiaSedeProd" cssStyle="display:none;"/>
                        <s:textfield name="producto.dska_dska" id="dska_dska" cssStyle="display:none;"/>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th colspan="4" class="alert alert-success text-center">Origen/Destino</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Sede Origen:</td>
                                    <td>Existencias: <label class="existenciaOrigen">0</label></td>
                                    <td>
                                        <s:select cssClass="form-control" list="sedes" id="sedeOrigen" name="sedeOrigen"  headerKey="-1" headerValue="Sede de ingreso de salida del producto" onchange="cambiaSede(this.value,1)"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Sede Destino:</td>
                                    <td>Existencias: <label class="existenciaDest">0</label></td>
                                    <td>
                                        <s:select cssClass="form-control" list="sedes" id="sedeDestino" name="sedeDestino"  headerKey="-1" headerValue="Sede de ingreso de destino del producto" onchange="cambiaSede(this.value,2)"/>
                                    </td>                                    
                                </tr>
                                <tr>
                                    <td>
                                        # de Prod. a Trasladar:
                                    </td>
                                    <td>
                                        <s:textfield name="producto.cantidad" cssClass="form-control" id="cantidadTraslado" onkeypress="return validaNumeros(event)" />
                                    </td>
                                    <td style="text-align: center;">
                                        <a onclick="trasladar()" title="Traslado de productos de una sede a otra">
                                            <button type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon glyphicon-resize-horizontal"></span>&nbsp;Trasladar
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </s:form>
                </div>
                <div class="col-md-1 col-sm-0 col-xs-0"></div>
            </div>
        </s:else>
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
