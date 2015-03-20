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
                    <s:form theme="simple" action="inv_consProdForAddEx" id="inv_consProdForAddEx" autocomplete="off">
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
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <s:form theme="simple">
                    <table class="table table-bordered">
                        <thead>
                            <tr onclick="ocultaDatosProd()">
                                <th class="alert alert-success text-center" colspan="4"><h4>Producto</h4></th>
                        </tr>
                        </thead>
                        <tbody class="datosProd">
                            <tr>
                                <td>Codigo:</td>
                                <td><s:text name="producto.dska_cod" /></td>
                                <td>Nombre:</td>
                                <td><s:text name="producto.dska_nom_prod" /></td>
                            </tr>
                            <tr>
                                <td>Existencias:</td>
                                <td><s:text name="producto.cantExis" /></td>
                                <td>Promedio Pond:</td>
                                <td><s:text name="producto.promPonderado" /></td>
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
                                <td><s:textfield name="addicionProd.dska_cod" cssClass="form-control"/></td>
                                <td>Costo:</td>
                                <td><s:textfield name="addicionProd.dska_nom_prod" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Sede:</td>
                                <td>
                                    <s:select cssClass="form-control" list="sedes" id="sedes" name="addicionProd.sede"  headerKey="-1" headerValue="Sede de ingreso del producto" />
                                </td>
                                <td>Movimiento de Inventario:</td>
                                <td><s:text name="moviInventario.mvin_descr" />
                                    <s:textfield name="addicionProd.mvIn" id="mvin_mvin" cssStyle="display:none;"/>
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
                </s:form>

            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>            
        </s:else>
    </body>
</html>
