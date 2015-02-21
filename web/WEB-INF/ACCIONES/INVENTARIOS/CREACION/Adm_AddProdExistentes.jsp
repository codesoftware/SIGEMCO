<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Adm_AddProdExistentes.js"></script>
        <style>
            .tooltip-inner {                
                width: 160px; 
            }
            .has-error{
                border-color: #a94442;
            }
        </style>
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
                <s:set name="variable" value="bandera" />
                <s:if test="%{#variable.equalsIgnoreCase('S')}">
                    <s:form theme="simple" action="inv_consPrIndAddExistente" id="inv_consPrIndAddExistente" autocomplete="off">
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                            <div class="alert alert-success text-center"  role="alert" ><h3>BUSQUEDA DEL PRODUCTO</h3></div>
                            Código:<br>
                            <s:textfield cssClass="form-control" name="producto.codigo"  placeholder="Codigo del Producto" /><br>
                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                <s:param name="function">buscarProductoIndividual</s:param>
                                <s:param name="title">Busqueda Individual por Codigo</s:param>
                            </s:include>
                        </div>
                        <s:textfield name="accion" value="consIndividual" cssStyle="display:none"/>
                    </s:form>
                </s:if>                
                <s:else>                    
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="alert alert-success text-center"  role="alert" ><h3>PRODUCTO EL CUAL SE ADICIONARA AL INVENTARIO</h3></div>
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            Código:
                            <s:textfield cssClass="form-control" name="producto.codigo" readonly="true"/>
                        </div>
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            Nombre:
                            <s:textfield cssClass="form-control"  name="producto.nombre" readonly="true" />
                        </div>
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            Descripción:
                            <s:textfield cssClass="form-control" name="producto.descripcion" readonly="true"/>
                        </div>
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            Marca:
                            <s:textfield cssClass="form-control" name="producto.marca" readonly="true" />
                        </div>
                        <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                            <div style="float: right">
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                    <s:param name="function">buscarProductoIndividual</s:param>
                                    <s:param name="title">Iniciar Busqueda de Nuevo</s:param>
                                    <s:param name="link">href="reenvioGeneral.action?accion=219"</s:param>                                            
                                </s:include>

                            </div>
                        </div>   
                    </div>
                    <s:form theme="simple" action="inv_addProdExstInv" id="inv_addProdExstInv" autocomplete="off">
                        <s:textfield name="producto.marca" cssStyle="display:none;"/>
                        <s:textfield name="producto.descripcion" cssStyle="display:none;"/>
                        <s:textfield name="producto.nombre" cssStyle="display:none;"/>
                        <s:textfield name="producto.codigo" cssStyle="display:none;" />
                        <s:textfield name="producto.id" cssStyle="display:none;" id="idProducto"/>
                        <s:textfield name="accion" value="addProductosExistents" cssStyle="display:none;"/>
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
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="alert alert-info text-center" colspan="4" >
                            <h3>INSERCIÓN DE PRODUCTOS AL INVENTARIO</h3>
                            </th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        No. Productos:
                                    </td>
                                    <td>
                                        <s:textfield cssClass="form-control" id="numProd" name="addicionProd.noProductos" onkeypress="return validaNumeros(event)"/>
                                    </td>                                
                                    <td>
                                        Costo:
                                    </td>
                                    <td>
                                        <s:textfield cssClass="form-control" id="costo" name="addicionProd.costo" data-toggle="tooltip" data-placement="right" title="Recuerda que el costo del Producto es lo que le pagaste al proveedor por todos los productos adquiridos no por cada unidad del producto" />
                                    </td>
                                </tr>
                                <tr id="promedio" style="display: none;">
                                    <td>
                                        Promedio Ponderado:
                                    </td>
                                    <td colspan="3">
                                        <div class="input-group" style="width: 100%">
                                            <span class="input-group-addon">$</span>
                                            <s:textfield cssClass="form-control" id="promPonderado" data-toggle="tooltip" data-placement="right" title="Este es el ultimo costo ponderado del producto" readonly="true" />
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Mov. de inventario:
                                    </td>
                                    <td>
                                        <s:select cssClass="form-control" id="movInv"   name="addicionProd.movInv" list="movInventarios" headerKey="-1" headerValue="Seleccione un Movimiento.." onchange="cambioMovimento(this.value)"/>
                                    </td>
                                    <td>
                                        Naturaleza del Mov.:
                                    </td>
                                    <td>
                                        <s:textfield name="addicionProd.natuMov" id="natuMov" cssClass="form-control" readonly="true" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Sede del producto:
                                    </td>
                                    <td colspan="3">
                                        <s:select cssClass="form-control" list="sedes" id="sedes" name="addicionProd.sede"  headerKey="-1" headerValue="Sede de ingreso del producto" />                                        
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td style="text-align: right; height: 35px;" colspan="4">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/insertMovimiento.jsp" > 
                                            <s:param name="function">ingresoProducto</s:param>
                                            <s:param name="title">Adicionar Movimiento</s:param>
                                        </s:include>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </s:form>
            </s:else>               
            <div class="mensajesOk" style="display: none;">
                <s:if test="hasActionMessages()">
                    <s:actionmessage/>
                    <script>
                        mostrarMsn();
                    </script>
                </s:if>                
            </div>                           
        </div> 
        <div class="col-md-3 col-xs-0 col-sm-0"></div>
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
