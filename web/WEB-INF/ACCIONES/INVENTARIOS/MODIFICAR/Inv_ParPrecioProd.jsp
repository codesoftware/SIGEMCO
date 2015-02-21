<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ParPrecioProd.js"></script>
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
            <div class="col-md-6 col-xs-0 col-sm-0">
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
                <br/>
                <s:if test="producto == null" >
                    <s:form action="inv_BuscaProducto" id="inv_BuscaProducto" autocomplete="off" theme="simple">
                        <s:textfield name="accion" cssStyle="display:none" value="buscarProducto" />
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>PARAMETRIZACIÓN DE PRECIOS</h3></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="text-center"><h4>PRODUCTO A DARLE EL PRECIO</h4></td>
                                </tr>
                                <tr>
                                    <td>Codigo del Producto</td>
                                    <td><s:textfield name="producto.codigo" id="codigoProd" cssClass="form-control" onkeyup="remplazaGuion(this.value,'codigoProd');validar(event)" /></td>
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
                </s:if>
                <s:else>
                    <div class="col-md-0 col-xs-0 col-sm-0"></div>
                    <div class="col-md-12 col-xs-12 col-sm-12">
                        <s:form action="inv_ParamPrecioPr" id="inv_ParamPrecioPr" autocomplete="off" theme="simple">
                            <s:textfield name="producto.id" cssStyle="display:none" id="dska_dska"/>
                            <s:textfield name="producto.codigo" cssStyle="display:none" />
                            <s:textfield name="accion" value="parametrizarPr" cssStyle="display:none" />
                            <s:textfield name="producto.nombre" cssStyle="display:none" />
                            <s:textfield name="producto.descripcion" cssStyle="display:none" />
                            <s:textfield name="producto.cantidad" cssStyle="display:none" />
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="4" style="text-align: center;" class="alert alert-info text-center"><h3>PARAMETRIZACIÓN DE PRECIOS</h3></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="4" style="text-align: center"><b>Datos Basicos del Producto</b></td>
                                    </tr>
                                    <tr>
                                        <td>Nombre:</td>
                                        <td colspan="2">
                                            <s:text name="producto.nombre" />
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 25%">
                                            Codigo del producto:
                                        </td>
                                        <td style="width: 25%">
                                            <s:text name="producto.codigo" />
                                        </td>
                                        <td style="width: 25%">
                                            Id Producto:
                                        </td>
                                        <td style="width: 25%">
                                            <s:text name="producto.id" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Descripci&oacute;n del producto: 
                                        </td>
                                        <td>
                                            <s:text name="producto.descripcion" />
                                        </td>
                                        <td>
                                            Cantidades Existentes: 
                                        </td>
                                        <td>
                                            <s:text name="producto.cantidad" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Sedes:
                                        </td>
                                        <td colspan="3">
                                            <s:select cssClass="form-control" list="sedes" name="producto.sede"  headerKey="-1" headerValue="Sede a darle precio" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Precio Bruto</td>
                                        <td colspan="3">
                                            <div class="input-group">
                                                <span class="input-group-addon">$</span>
                                                <s:textfield cssClass="form-control" name="producto.precio" title="Precio que deseas vender el producto sin impuestos" data-toggle="tooltip" data-placement="left" onkeyup="mascaraMoneda(this)" onkeypress="return validaNumeros(event)" id="futuroPrecio"/>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="4" style="text-align: right;">
                                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                                <s:param name="title">Buscar Otro Producto</s:param>
                                                <s:param name="link">href="reenvioGeneral.action?accion=216"</s:param>
                                            </s:include>
                                            <s:include value="/WEB-INF/TEMPLATE/botones/parametrizar.jsp" >
                                                <s:param name="title">Parametrizar precio del Producto</s:param>
                                                <s:param name="function">insertarParametro</s:param>                                                
                                            </s:include>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </s:form>
                        <s:if test="%{listaPrecioSede!=null}" >
                            <ul class="nav nav-tabs" id="myTab">
                                <%int i = 1;%>
                                <s:iterator value="listaPrecioSede">
                                    <li>
                                        <a href="#tabs-<%=i%>" data-toggle="tab" style="text-transform: uppercase;"><s:property value="sede_nombre" /></a>
                                    </li>
                                    <%i++;%>
                                </s:iterator>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <%int j = 1;%>
                                <s:iterator value="listaPrecioSede">
                                    <div id="tabs-<%=j%>" class="tab-pane fade">
                                        <br/>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th colspan="3" style="text-align: center;" class="alert alert-info text-center"><h3>HISTORIAL DE PRECIOS</h3></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <s:if test="%{precios!=null}" >
                                                    <tr>
                                                        <td><h4 style="font-weight: bold">Fecha</h4></td>
                                                        <td><h4 style="font-weight: bold">Precio</h4></td>
                                                        <td><h4 style="font-weight: bold">Estado</h4></td>
                                                    </tr>
                                                    <s:iterator value="precios">
                                                        <tr>
                                                            <td><s:property value="prpr_fecha"/></td>
                                                            <td><s:property value="prpr_precio"/></td>
                                                            <td><s:property value="prpr_estado"/></td>
                                                        </tr>                                                        
                                                    </s:iterator>                                                    
                                                </s:if>
                                                <s:else>
                                                    <tr>
                                                        <td>NO EXISTEN PRECIOS PARAMETRIZADOS PARA ESTA SEDE</td>
                                                    </tr>
                                                </s:else>
                                            </tbody>
                                        </table>
                                    </div>
                                    <%j++;%>
                                </s:iterator>
                            </div>
                        </s:if>
                    </div>
                    <div class="col-md-0 col-xs-0 col-sm-0"></div>
                </s:else>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <br/>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="confirmacion">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Confirmaci&oacute;n Parametrizaci&oacute;m Precio</h4>
                    </div>
                    <div class="modal-body">
                        El valor que desea Parametrizarle al producto es inferior al Costo ponderado del mismo
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>
                        <button type="button" class="btn btn-primary" id="continuar">
                            CONTINUAR                            
                        </button>                        
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(function() {
                $('#myTab a:first').tab('show');
            });
        </script>
        <s:if test="%{producto.precio != null }"> 
            <script>
                var precio = '<s:text name="producto.precio" />'
                precio = mascaraMonedaConValor(precio);
                document.getElementById('futuroPrecio').value = precio;
            </script>
        </s:if>
    </body>
</html>
