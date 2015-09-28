<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_AdicionaProductosReceta.js"></script>
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
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row" >
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12" style="border: 1px solid #D6E3E2;padding-left: 0px;padding-right: 0px;">
                <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h4>INFORMACION GENERAL DE LA RECETA</h4></div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <s:text name="receta.rece_nombre" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Descripcion:</label>
                        <s:text name="receta.rece_desc" />
                        <s:textfield name="receta.rece_rece" id="idRecetaPrinc" theme="simple" cssStyle="display:none;"/>
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Codigo:</label>
                        <s:text name="receta.rece_codigo" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Estado:</label>
                        <s:text name="receta.rece_estado" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="descripcion">Fecha Creación:</label>
                        <s:text name="receta.rece_fec_ingreso" />
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="promedio">Promedio:</label>
                        <span id="costoRecetaLabel"><s:text name="receta.rece_promedio" /></span>
                    </div>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 ">
                    <div class="form-group">
                        <label for="costoReceta" >Costo aproximado Manualmente:</label>
                        &nbsp;&nbsp; $&nbsp;<s:text name="receta.rece_costo" />
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12" style="border: 1px solid #D6E3E2;padding-left: 0px;padding-right: 0px;">
                <div class="col-md-12 col-xs-12 col-sm-12 alert alert-success text-center"><h4>CONSULTAR PRODUCTO</h4></div>
                <div class="col-md-6 col-xs-12 col-sm-6 ">
                    <div class="form-group">
                        <label for="nombre">CODIGO PRODUCTO:</label>
                        <s:textfield name="producto.dska_cod" id="codigoProducto" cssClass="form-control" onkeyup="validarEnter(event,'1');" autocomplete="off" theme="simple" />
                    </div>
                </div>
                <div class="col-md-3 col-xs-6 col-sm-6 text-center">
                    <br/>
                    <a class="btn btn-primary" id="consultaProducto">CONSULTAR</a>
                </div>
                <div class="col-md-3 col-xs-5 col-sm-6 text-center">
                    <br/>
                    <a class="btn btn-danger" id="adicionaProdReceta">ADICIONAR</a>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12" id="productoConsulta">
                </div>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <table class="table table-responsive table-bordered">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Promedio</th>
                            <th>Cantidad</th>
                            <th>Acción</th>
                        </tr>
                    </thead> 
                    <tbody id="filasProdAdicionados">                        
                        <s:iterator value="productosReceta">
                            <tr class="filaAdicionada">
                                <td>
                                    <s:property value="dska_cod"/>
                                </td>
                                <td>
                                    <s:property value="dska_nombre" />
                                </td>
                                <td>
                                    <s:property value="repr_promedio"/>
                                </td>
                                <td>
                                    <s:property value="repr_cantidad"/>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger" data-dska="<s:property value="repr_dska"/>" onclick="eliminarFila(this)">
                                        <span class="glyphicon glyphicon-remove"></span> 
                                    </button>
                                </td>                            
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>  
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
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
    </body>
</html>
