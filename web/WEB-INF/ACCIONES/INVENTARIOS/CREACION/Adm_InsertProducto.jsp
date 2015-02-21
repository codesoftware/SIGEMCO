<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Adm_InsertarProducto.js"></script>   
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
        <s:form  action="inv_insertProducto" id="inv_insertProducto" theme="simple" method="post">
            <div class="row IngProducto">
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
                            <tr>
                                <td style="width: 40%">Nombre: </td>
                                <td><s:textfield name="producto.nombre" required="true" cssClass="form-control" id="producto_nombre"/></td>
                            </tr>
                            <tr>
                                <td>Descripci&oacute;n:</td>
                                <td><s:textfield label="Descripción Prod" name="producto.descripcion" required="true" cssClass="form-control" id="producto_descripcion"/></td>
                            </tr>
                            <tr>
                                <td>Referencia:</td>
                                <td><s:textfield name="producto.referencia" required="true" maxLength="10" cssClass="form-control" id="producto_referencia"/></td>
                            </tr>
                            <%--<tr>
                                <td>Codigo</td>
                                <td><s:textfield name="producto.codigo" required="true" maxLength="9" cssClass="form-control" id="producto_codigo"/></td>
                            </tr>--%>
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
                            <tr>
                                <td>Marca:</td>
                                <td><s:textfield name="producto.marca" required="true" cssClass="form-control" id="producto_marca"/></td>
                            </tr>
                            <tr>
                                <td>Cantidad:</td>
                                <td><s:textfield name="producto.cantidad" requiered="true" cssClass="form-control" id="producto_cantidad" /></td>
                            </tr>
                            <tr>
                                <td>Costo:</td>
                                <td><s:textfield name="producto.costo" requiered="true" title="Este es el costo unitario de cada producto que va ha ingresar" cssClass="form-control" id="producto_costo"/></td>
                            </tr>
                            <tr>
                                <td>Categoria:</td>
                                <td><s:select  list="categorias"  name="producto.categoria" required="true" headerKey="-1" headerValue="Categoria" cssClass="form-control" id="producto_categoria" /></td>
                            </tr>
                            <tr>
                                <td>Sede de Ingreso:</td>
                                <td><s:select  list="sedes"  name="producto.sede" required="true" headerKey="-1" headerValue="Sede"  cssClass="form-control" id="sedes"/></td>
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
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertar</s:param>
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
    </body>
    <script>
        var iva = '<s:text name="producto.porcIva" />';
        if(iva =='producto.porcIva'){
            $('#errorPar').html('Por favor parametrizar el iva de los productos antes de ingresar el producto<br/> Que se encuentra en Administracion -> Empresa -> Parametros Generales ')
            $('.IngProducto').hide('slow');
            $('#msnErrorParametrizacion').show('slow');
        }
    </script>
</html>
