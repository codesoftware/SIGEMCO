<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsProducto.js"></script>
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
                <s:form action="inv_consProdPorFiltrosGen" id="inv_consProdPorFiltrosGen" theme="simple">
                    <s:textfield name="accion" value="consultaGen" cssStyle="display:none" />
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>CONSULTA GENERAL DE PRODUCTOS</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Nombre Producto:<br>
                                <s:textfield name="producto.dska_nom_prod" placeholder="Filtro" cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Descripción Producto:<br>
                                <s:textfield name="producto.dska_desc" placeholder="Filtro" cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Referencia:<br>
                                <s:textfield name="producto.dska_refe" placeholder="Filtro" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Codigo:<br>
                                <s:textfield name="producto.dska_cod" placeholder="Filtro" cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Marca:<br>
                                <s:textfield name="producto.dska_marca" placeholder="Filtro" cssClass="form-control"/>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4 ">
                                <br>
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                    <s:param name="function">buscaGeneral</s:param>
                                    <s:param name="title">Busca Productos</s:param>
                                </s:include>
                            </div>
                        </div>                            
                    </div>                        
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <s:if test="%{listProductos != null}" >
                    <s:set var="permisoActualizar" value="perActualizar" />
                    <s:set var="permisoParPrecio" value="perParamPrecio" />
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Referencia</th>
                                <th>Codigo</th>
                                <th>Cant.<br/>Exis.</th>
                                <th>Marca</th>                                    
                            </tr>
                        </thead>
                        <tbody>
                            <%int i = 0;%>
                            <s:iterator value="listProductos">                            
                                <%if (i % 2 == 0) {%>
                                <tr class="active">
                                    <%} else {%>
                                <tr>
                                    <%}
                                        i++;%>
                                    <s:if test="%{#permisoActualizar.equalsIgnoreCase('S') || #permisoParPrecio.equalsIgnoreCase('S')}">
                                        <td>
                                            <a href="#" onclick="ejecutaAcciones('<s:text name="permisoActualizar" />', '<s:text name="permisoParPrecio" />', '<s:text name="dska_cod" />', '<s:text name="dska_dska" />')"><s:property value="dska_nom_prod"/></a>
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td><s:property value="dska_nom_prod"/></td>
                                    </s:else>
                                    <td><s:property value="dska_desc"/></td>
                                    <td><s:property value="dska_refe"/></td>
                                    <td><s:property value="dska_cod"/></td>
                                    <td><s:property value="cantExis"/></td>
                                    <td><s:property value="dska_marca"/></td>                                   
                                </tr>
                            </s:iterator>
                        <tbody>
                    </table>
                </s:if>

            </div>
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
        </div>        
        <s:form action="inv_UpdProducto" method="post" id="inv_UpdProducto"  theme="simple"> 
            <s:textfield name="producto.id" cssStyle="display:none;" id="idProductoUpdate"/>
            <s:textfield name="accion" cssStyle="display:none" value="updProductoIndv"/>
            <s:textfield name="subAccion" cssStyle="display:none" value="consulta" />        
        </s:form>

        <s:form theme="simple" action="inv_BuscaProducto" method="post"  id="inv_BuscaProducto" cssStyle="display:none;">
            <s:textfield name="accion" cssStyle="display:none" value="buscarProducto" />
            <s:textfield name="producto.codigo" id="codigoParametriza"/>
            <s:textfield name="producto.id" id="idParametriza"/>
        </s:form>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoAcciones">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Acci&oacute;n Producto</h4>
                    </div>
                    <div class="modal-body">
                        ¿Que accion desea Realizale al producto?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CERRAR
                        </button>
                        <button type="button" class="btn btn-primary" id="sticker">
                            STICKER
                        </button>
                        <button type="button" class="btn btn-primary" id="parametrizar">
                            PARAMETRIZAR PRECIO
                        </button>
                        <button type="button" class="btn btn-primary" id="actualizar">
                            ACTUALIZAR
                        </button>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>
