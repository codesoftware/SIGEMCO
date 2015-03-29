<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>        
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_AddProductoExistente.js" ></script>
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
        <s:if test="%{bandera.equalsIgnoreCase('S')}" >
            <div id="row">
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
                    <s:form theme="simple" action="inv_consProdForAddEx" id="inv_consProdForAddEx" autocomplete="off" method="post">
                        <s:textfield name="accion" value="consultaForAddEx" cssStyle="display:none" />
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                            <div class="alert alert-success text-center"  role="alert" ><h3>BUSQUEDA DEL PRODUCTO</h3></div>
                            Código:<br>
                            <s:textfield cssClass="form-control" name="producto.dska_cod"  placeholder="Codigo del Producto" /><br>
                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                <s:param name="function">buscarProductoIndividual</s:param>
                                <s:param name="title">Busqueda Individual por Codigo</s:param>
                            </s:include>
                        </div>
                    </s:form>
                </div>
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
            </div>
        </s:if>
        <s:else>
            <s:form theme="simple" id="inv_ejecutaAddProdEx" action="inv_ejecutaAddProdEx" method="post" autocomplete="off" >
                <s:textfield name="producto.dska_dska" cssStyle="display:none;" />
                <s:textfield name="accion" cssStyle="display:none;" value="addProdInventario"/>
                <div class="row datosProdDiv">
                    <div class="col-md-1 col-xs-0 col-sm-0"></div>
                    <div class="col-md-10 col-xs-12 col-sm-12">

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
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="alert alert-success text-center" colspan="4"><h4>Datos de la Compra</h4></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Num Productos:</td>
                                    <td><s:textfield name="addicionProd.noProductos" cssClass="form-control" id="numProductos" onkeypress="return validaNumeros(event)" onkeyup="mascaraMoneda(this)"/></td>
                                    <td>Costo:</td>
                                    <td><s:textfield name="addicionProd.costo" cssClass="form-control"  id="costo" onkeypress="return validaNumeros(event)" onkeyup="mascaraMoneda(this)" onchange="cambioVlr(this.value)"/></td>
                                </tr>
                                <tr>
                                    <td>Sede:</td>
                                    <td>
                                        <s:select cssClass="form-control" list="sedes" id="sedes" name="addicionProd.sede"  headerKey="-1" headerValue="Sede de ingreso del producto"/>
                                    </td>
                                    <td>Movimiento de Inventario:</td>
                                    <td><s:text name="moviInventario.mvin_descr" />
                                        <s:textfield name="addicionProd.movInv" id="mvin_mvin" cssStyle="display:none;"/>
                                        <script>
                                            var mvin_mvin = '<s:text name="moviInventario.mvin_mvin" />';
                                            document.getElementById('mvin_mvin').value = mvin_mvin;
                                        </script>
                                    </td>
                            <tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4" class="text-right">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/contabiliza.jsp" > 
                                            <s:param name="function">contabilizar</s:param>
                                            <s:param name="title">Adicionar un Producto al Inventario</s:param>
                                        </s:include>                                        
                                        <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                    </td>
                                </tr>
                            </tfoot>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-1 col-xs-0 col-sm-0"></div>
                </div>
                <div class="row datosContabiliza" style="display: none;">
                    <div class="col-md-2 col-xs-0 col-sm-0"></div>
                    <div class="col-md-8 col-xs-12 col-sm-12">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="alert alert-info text-center" colspan="4">Factura de Compra</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Valor Producto</td>
                                    <td>$<span id="vlrProd"></span></td>
                                    <td>Valor Iva:</td>
                                    <td>$<span id="vlrIva"></span><input type="hidden" id="vlrIvaText" value="0"/></td>
                                </tr>
                                <tr>
                                    <td style="width: 15%">Valor Total:</td>
                                    <td style="width: 35%">$ <span id="vlrTotal">0</span><input type="hidden" id="vlrTotalText" /></td>
                                    <td style="width: 15%">Suma SubCuentas:</td>
                                    <td style="width: 35%">$ <span id="vlrSumCuentas">0</span><input type="hidden" id="vlrSumCuentasText" value="0"/></td>
                                </tr>
                                <tr>
                                    <td>SubCuenta</td>
                                    <td>
                                        <input type="text" class="form-control" id="codigo_subcuenta" onkeyup="validarEnter(event, '1')" />
                                    </td>
                                    <td>
                                        Valor:
                                    </td>
                                    <td>
                                        <div class="input-group">
                                            <span class="input-group-addon">$</span>
                                            <input type="text" id="valorSubCuenta" class="form-control" onkeypress="return validaNumeros(event)" onkeyup="mascaraMoneda(this)" />
                                        </div>
                                    </td>
                                </tr>    
                                <tr id="tableFacturaCompra">

                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4" style="text-align: right;">
                                        <a onclick="datosProducto()" title="Ver Todos los datos del producto">
                                            <button type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Producto
                                            </button>
                                        </a>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                            <s:param name="function">agrearCuenta</s:param>
                                            <s:param name="title">Registro de Contabilidad de la Factura de Compra</s:param>
                                        </s:include>                            
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="alert alert-info text-center" colspan="5">CUENTAS AGREGADAS</th>
                                </tr>
                                <tr class="alert alert-success">
                                    <th>COD SUBCUENTA</th>
                                    <th>DESCRIPCION </th>
                                    <th>NATURALEZA</th>
                                    <th>VALOR</th>
                                    <th>ELIMINA</th>
                                </tr>
                            </thead> 
                            <tbody id="subcuentasAdicionadas">

                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5" style="text-align: right;">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                            <s:param name="function">insertar</s:param>
                                            <s:param name="title">Insertar el producto al inventario</s:param>
                                        </s:include>    
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="col-md-2 col-xs-0 col-sm-0"></div>
                </div>
            </s:form>
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
        <!-- Modal necesario para visualizar el asiento contable-->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="partidaDoble">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">ASIENTO CONTABLE</h4>
                    </div>
                    <div class="modal-body">
                        <span id="tablaAsientocontable"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-success" data-dismiss="modal" id="parametrizarPrecio">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <s:if test="%{idTrans == null}" >
        </s:if>
        <s:else>
            <script>
                obtenerAsientocontable('<s:text name="producto.transMvcon" />');
                $('#partidaDoble').modal('show');
            </script>
        </s:else>
    </body>
</html>
