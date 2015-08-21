<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenRecetas.js"></script>
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
        <s:form theme="simple" action="Inv_ConsGenReceta" method="post">
            <s:textfield name="accion" value="consGeneral" cssStyle="display:none;"/>
            <div class="row">
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
                <div class="col-md-6 col-xs-12 col-sm-12">
                    <div class="panel">
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h3>CONSULTA GENERAL RECETAS O PLATOS</h3></div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="nombre">Nombre:</label>
                                    <s:textfield name="rece_nombre" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="descripcion">Descripcion:</label>
                                    <s:textfield name="rece_desc" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-12 ">
                                <div class="form-group">
                                    <label for="codigo">Codigo:</label>
                                    <s:textfield name="rece_codigo" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="col-md-12 col-xs-12 col-sm-12 text-right">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-xs-0 col-sm-0"></div>
            </div>
        </s:form>
        <s:if test="recetas != null">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <div class="row">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Estado</th>
                                    <th>Costo Receta</th>
                                    <th>Promedio</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="recetas">
                                    <tr>
                                        <td>
                                            <script>
                                                var permiso = '<s:text name="permisoAct"/>';
                                                if (permiso == 'S') {
                                                    document.write("<a href=\"#\" onclick=\"actualizar('<s:text name="rece_rece"/>')\"><s:text name="rece_codigo"/></a>");
                                                } else {
                                                    document.write('<s:text name="rece_codigo"/>');
                                                }
                                            </script>                                            
                                        </td>
                                        <td>
                                            <script>
                                                var permisoPrecio = '<s:text name="permisoPrecio"/>';
                                                var permisoAddProd = '<s:text name="permisoAddProd"/>';
                                                if (permisoPrecio == 'S' || permisoAddProd == 'S') {
                                                    document.write("<a href=\"#\" onclick=\"ejecutaAcciones('<s:text name="rece_rece"/>','<s:text name="rece_codigo"/>')\"><s:text name="rece_nombre"/></a>");
                                                } else {
                                                    document.write('<s:text name="rece_nombre"/>');
                                                }
                                            </script> 
                                        </td>                                        
                                        <td><s:text name="rece_desc"/></td>                                        
                                        <td><s:text name="rece_estado"/></td>                                        
                                        <td>
                                            <script>
                                                var valor = '<s:text name="rece_costo" />';
                                                document.write(mascaraMonedaConValor(valor));
                                            </script>
                                        </td>
                                        <td><s:text name="rece_promedio"/></td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>                        
                    </div>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
        </s:if>
        <s:form action="consActualizaReceta" theme="simple" method="post" cssStyle="display:none;" id="consultaActualizacionReceta">
            <s:textfield name="receta.rece_rece" id="rece_receActu" />
            <s:textfield name="accion" value="consultaActuliza" />
        </s:form>
        <s:form action="Inv_buscaRecetaXCod" theme="simple" method="post" cssStyle="display:none;" id="Inv_buscaRecetaXCod">
            <s:textfield name="receta.rece_codigo" cssClass="form-control" id="rece_codigoPrecio" />
        </s:form>
        <s:form action="Inv_buscaRecetaXCodAddProd" theme="simple" method="post" cssStyle="display:none;" id="Inv_buscaRecetaXCodAddProd">
            <s:textfield name="receta.rece_rece" cssClass="form-control" id="rece_receAddProd" />
        </s:form>
        <!-- Div utilizado para visualizar las acciones que tiene habilitadas el usuario -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="ejecutaAcciones">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Acciones</h4>
                    </div>
                    <div class="modal-body">
                        Cual de las siguientes acciones desea ejecutar al producto
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>
                        <s:if test="permisoPrecio.equalsIgnoreCase('S')">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnParaPrecio">
                                PARAMETRIZAR PRECIO
                            </button>
                        </s:if>
                        <s:if test="permisoAddProd.equalsIgnoreCase('S')">
                            <button type="button" class="btn btn-danger" data-dismiss="modal" id="btnAddProdReceta">
                                ADICION DE PRODUCTOS
                            </button>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
