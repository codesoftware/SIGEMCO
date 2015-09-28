<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/CORE/AUTOCOMPLETAR/jquery-ui.min.js"></script>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Adm_InsertarProducto.js"></script>   
        <link rel="stylesheet" type="text/css" href="<%=RutaSitio%>/CSS/CORE/AUTOCOMPLETAR/jquery-ui.structure.min.css" />
        <link rel="stylesheet" type="text/css" href="<%=RutaSitio%>/CSS/CORE/AUTOCOMPLETAR/jquery-ui.theme.min.css" />
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
        <br>  
        <div class="row errorMarcas" style="display:none">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
                <div class="alert alert-danger" id="info" role="alert">
                    No existe ninguna Marca parametrizada las cuales son 
                    necesarias para el ingreso de productos, por favor parametrizar al menos una.
                </div>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <div class="row errorProveedores" style="display:none">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
                <div class="alert alert-danger" id="info" role="alert">
                    No existe ningun Proveedor parametrizado en el sistema por favor parametriza al menos uno para continuar el ingreso de productos
                </div>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <s:if test="proveedores == null">
            <script>
                $('.IngProducto').hide('slow');
                $('.errorProveedores').show('slow');
            </script>
        </s:if>
        <s:if test="%{referencias != null}">
            <s:form  action="inv_insertProducto" id="inv_insertProducto" theme="simple" method="post" autocomplete="off" >
                <div class="IngProducto">
                    <s:if test="%{marcas == null}">                        
                        entro aqui
                        <script>
                            $('.IngProducto').hide('slow');
                            $('.errorMarcas').show('slow');
                        </script>
                    </s:if>                    
                    <div class="row datosProd">
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
                            <table class="table table-bordered" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th colspan="2" style="text-align: center;" class="alert alert-info text-center">
                                <h3>INGRESO DE PRODUCTOS</h3>
                                </th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr style="display: none;">
                                        <td>Nombre: </td>
                                        <td><s:textfield name="producto.nombre" required="true" cssClass="form-control" id="producto_nombre"/></td>
                                    </tr>
                                    <tr style="display: none;">
                                        <td style="width: 40%;">Referencia</td>
                                        <td style="width: 60%;"><s:textfield label="Descripción Prod" name="producto.descripcion" required="true" cssClass="form-control" id="producto_descripcion"/></td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="modeloAsociado.descripcion"/></td>
                                        <td>
                                            <s:select cssClass="form-control" list="referencias" name="producto.referencia" id="referencia" headerKey="-1" headerValue="seleccione..." />
                                        </td>
                                    </tr>
                                    <%--<tr>
                                        <td>Codigo</td>
                                        <td><s:textfield name="producto.codigo" required="true" maxLength="9" cssClass="form-control" id="producto_codigo"/></td>
                                    </tr>--%>                                    
                                    <tr>
                                        <td style="width: 40%;">Marca:</td>
                                        <td style="width: 60%;">
                                            <s:if test="%{marcas != null}">           
                                            <s:select  list="marcas"  name="producto.marca" required="true" headerKey="-1" headerValue="Marca" cssClass="form-control" id="producto_marca" />
                                            </s:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Categoria:</td>
                                        <td><s:select  list="categorias"  name="producto.categoria" required="true" headerKey="-1" headerValue="Categoria" cssClass="form-control" id="producto_categoria" /></td>
                                    </tr>
                                    <tr>
                                        <td>Sede de Ingreso:</td>
                                        <td><s:select  list="sedes"  name="producto.sede" required="true" headerKey="-1" headerValue="Sede"  cssClass="form-control" id="sedes"/></td>
                                    </tr>
                                    <tr>
                                        <td>Proveedor:</td>
                                        <td><s:select  list="proveedores"  name="producto.proveedores" required="true" headerKey="-1" headerValue="Proveedor.."  cssClass="form-control" id="proveedores"/></td>
                                    </tr>
                                    <tr>
                                        <td>Cantidad:</td>
                                        <td><s:textfield name="producto.cantidad" requiered="true" cssClass="form-control" id="producto_cantidad" onkeypress="return validaNumeros(event)" /></td>
                                    </tr>
                                    <tr>
                                        <td>Costo:</td>
                                        <td><s:textfield name="producto.costo" requiered="true" title="Este es el costo unitario de cada producto que va ha ingresar" cssClass="form-control" id="producto_costo" onblur="cambioVlr(this.value)" onkeypress="return validaNumeros(event)" onkeyup="mascaraMoneda(this)"/></td>
                                    </tr>
                                    <tr>
                                        <td>Gravamen:</td>
                                        <td>
                                            <s:textfield name="producto.iva" required="true" cssStyle="max-lenght: 10;" cssClass="form-control" id="iva" value="S" readonly="true"/>
                                            <%--<s:select  list="yesNo"  name="producto.iva" required="true" headerKey="-1" headerValue="Cobro Iva" onchange="cambioIva(this.value)" cssClass="form-control" id="gravamen"/>--%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Porcentaje iva:</td>
                                        <td><s:textfield name="producto.porcIva" cssClass="form-control" readonly="true"/></td>
                                    </tr>
                                    <tr class="vencimiento" style="display: none">
                                        <td>Fecha de Vencimiento:</td>
                                        <td>
                                            <div class="input-group date">
                                                <s:textfield name="producto.fechaVencimiento" cssClass="form-control" cssStyle="" readonly="true"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>

                                        </td>
                                    </tr>
                                    <tr class="regUnico" style="display: none" >
                                        <td>Registro Unico</td>
                                        <td><s:textfield name="producto.registroUnico" requiered="true" title="Este es el costo unitario de cada producto que va ha ingresar" cssClass="form-control" id="producto_registroUnico" /></td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="2" class="text-right">
                                            <s:include value="/WEB-INF/TEMPLATE/botones/contabiliza.jsp" > 
                                                <s:param name="function">contabilizar</s:param>
                                                <s:param name="title">Adicionar un Producto al Inventario</s:param>
                                            </s:include>                                        
                                            <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div class="col-md-3 col-xs-0 col-sm-0"></div>
                    </div>
                    <div class="row contabilidad" style="display: none">                
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
                </div>            
            </s:form>             
            <div id="msnErrorParametrizacion" style="display: none; width: 100%">
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
                <div class="col-md-6 col-xs-12 col-sm-12">
                    <h4 class="alert alert-danger">
                        <span id="errorPar"></span>
                    </h4>
                </div>
                <div class="col-md-3 col-xs-0 col-sm-0"></div>            
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

            <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="mensajeProdSim">
                <div class="modal-dialog">                
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">PRODUCTOS SIMILARES</h4>
                        </div>
                        <div class="modal-body">
                            <span id="textoMsnSimilares"></span>
                        </div>
                        <div class="modal-footer">                        
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                CANCELAR
                            </button>
                            <button type="button" class="btn btn-success" data-dismiss="modal" id="continuaContabilizar">
                                CONTINUAR
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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
        </body>
        <script>
            var iva = '<s:text name="producto.porcIva" />';
            if (iva == 'producto.porcIva') {
                $('#errorPar').html('Por favor parametrizar el iva de los productos antes de ingresar el producto<br/> Que se encuentra en Administracion -> Empresa -> Parametros Generales ')
                $('.IngProducto').hide('slow');
                $('#msnErrorParametrizacion').show('slow');
            }
        </script>
        <s:set name="variable" value="mandaParamePrecio"/>
        <s:if test="%{#variable.equalsIgnoreCase('S')}">
            <s:form action="inv_BuscaProducto" id="inv_BuscaProducto" autocomplete="off" theme="simple">
                <s:textfield name="accion" cssStyle="display:none" value="buscarProducto" />
                <s:textfield name="producto.codigo" />
            </s:form>
            <script>
                obtenerAsientocontable('<s:text name="producto.idTranMvCo" />');
                $('#partidaDoble').modal('show');
            </script>
        </s:if>
        <s:if test="hasActionErrors()">
            <script>
                var costo = '<s:text name="producto.costo"/>';
                costo = mascaraMonedaConValor(costo.toString());
                $('#producto_costo').val(costo);
                cambioVlr(costo);
                mostrarMsn();
            </script>
        </s:if>
    </s:if>
    <s:else>
        <div class="col-md-3 col-xs-0 col-sm-0"></div>
        <div class="col-md-6 col-xs-12 col-sm-12">
            <div class="alert alert-danger" id="info" role="alert">
                No existe ninguna referencia parametrizada las cuales son 
                necesarias para el ingreso de productos, por favor parametrizar al menos una.
            </div>
        </div>
        <div class="col-md-3 col-xs-0 col-sm-0"></div>
    </s:else>
</html>
