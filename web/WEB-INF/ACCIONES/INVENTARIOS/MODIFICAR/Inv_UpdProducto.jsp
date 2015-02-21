<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <s:head/>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdProducto.js"></script>
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
                <br/>
                <s:form action="inv_UpdProducto" method="post" id="inv_UpdProducto" theme="simple"> 
                    <s:textfield name="producto.id" cssStyle="display:none;" cssClass="idProductoUpdate"/>
                    <s:textfield name="accion" cssStyle="display:none" value="updProductoIndv"/>
                    <s:textfield name="subAccion" cssStyle="display:none" value="" cssClass="subAccionForm" />
                    <table class="table table-bordered" >
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>MODIFICAR PRODUCTO</h3></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Nombres Producto:</td>
                                <td><s:textfield name="producto.nombre" required="true" cssClass="form-control" /></td>
                            </tr>
                            <tr>
                                <td>Descripción Prod:</td>
                                <td><s:textfield name="producto.descripcion" required="true" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Referencia:</td>
                                <td><s:textfield name="producto.referencia" required="true" maxLength="10" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Codigo:</td>
                                <td><s:textfield name="producto.codigo" required="true" maxLength="9" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Gravamen:</td>
                                <td><s:select list="yesNo"  name="producto.iva" required="true" headerKey="-1" headerValue="Cobro Iva" onchange="cambioIva(this.value)" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td>Porcentaje iva:</td>
                                <td><s:textfield name="producto.porcIva" required="true" cssStyle="max-lenght: 10;" cssClass="form-control" /></td>
                            </tr>
                            <tr>
                                <td>Marca:</td>
                                <td><s:textfield name="producto.marca" required="true" cssClass="form-control" /></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <span class="spanModificar" style="display: none">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp" > 
                                            <s:param name="function">actualizacionProducto</s:param>
                                            <s:param name="title">Modificación atributos del Producto</s:param>
                                        </s:include>
                                    </span>
                                    <span class="spanConsultar">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                            <s:param name="function">consultaProducto</s:param>
                                            <s:param name="title">Buscar Producto</s:param>
                                        </s:include>
                                    </span>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>            
        </div>
        <s:if test="%{bandera.equalsIgnoreCase('S')}">
            <script>
                manipulaBoton('Actualiza');
            </script>
        </s:if>
        <s:else>
            <script>
                manipulaBoton('Consulta');
            </script>

        </s:else>
    </body>
</html>
