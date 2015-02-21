<%@page import="co.com.hotel.datos.session.Parametros"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<%    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String permisos = usuario.getPermisos();
    Parametros parametros = (Parametros) session.getAttribute("parametros");
%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/REPORTES/inv_reporteinventario.js"></script>
        <script>
            function inicializarFechas() {
                var fechaFin = '<%=parametros.getFechaManana()%>';
                var fechaIni = '<%=parametros.getFechaUnMesAtras()%>';
                document.getElementById('fechaIni').value = fechaIni;
                document.getElementById('fechaFin').value = fechaFin;
            }
        </script>
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
                <s:form theme="simple" action="inv_consProdPorFiltro" id="inv_consProdPorFiltro" autocomplete="off">                
                    <s:textfield name="accion" value="consultaProdXFiltroIndv" cssStyle="display:none"/>                
                    <s:textfield name="producto.id" cssStyle="display:none;"/>
                    <div id="tabs">
                        <ul class="nav nav-tabs" id="myTab">
                            <li ><a href="#tabs-1" data-toggle="tab">Busqueda</a></li>
                            <li><a href="#tabs-2" data-toggle="tab">Explicación</a></li>
                            <li><a href="#tabs-3" data-toggle="tab">Filtros</a></li>
                        </ul>
                    </div>
                    <div id="myTabContent" class="tab-content">
                        <div id="tabs-1" class="tab-pane fade in active ">
                            <div class="Mensajes" style="display: none;">
                                <s:if test="hasActionErrors()">
                                    <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
                                    <script>
                                        mostrarMsn();
                                    </script>
                                </s:if>
                            </div>
                            <div class="form-group col-md-12 col-xs-12 col-sm-12 thumbnail">
                                <div colspan="3" class="col-md-12 col-xs-12 col-sm-12 alert-success text-center"><h3>PROMEDIO PONDERADO</h3></div>
                                <div class="form-group col-md-4 col-xs-4 col-sm-4">
                                    <td>Código:</td>
                                    <td style="width: 90%"><s:textfield cssClass="form-control" name="producto.codigo"/></td>
                                </div>
                                <div class="form-group col-md-4 col-xs-4 col-sm-4">
                                    <td>Nombre:</td>
                                    <td><s:textfield cssClass="form-control" name="producto.nombre"/></td>
                                </div>
                                <div class="form-group col-md-4 col-xs-4 col-sm-4">
                                    <td>Referencias:</td>
                                    <td><s:textfield cssClass="form-control" name="producto.referencia" /></td>
                                </div>   
                                <div class="form-group col-md-12 col-xs-12 col-sm-12">
                                    <a onclick="buscaProducto()" style="cursor:pointer;float: right" >
                                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>&nbsp;Buscar</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div id="tabs-2" class="tab-pane fade">
                            <div class="form-group alert alert-info" role="alert">
                                <p>
                                    Este reporte nos sirve para ver las entradas y salidas detalladas de cada producto.                       
                                </p>
                                <p>
                                    En la pestaña <b>BUSQUEDA</b> podremos seleccionar el producto del cual queremos conocer su inventario.
                                </p>
                                <p>
                                    Despues de esto En la pestaña <b>FILTROS</b> encontraremos los datos detallados del producto,
                                    podemos filtrarlo en el rango de fechas que nosotros deseemos y exportar en pdf el inventario por promedio ponderado y                        
                                </p>
                            </div>
                        </div>
                        <div id="tabs-3" class="tab-pane fade">
                            <s:set name="variable" value="bandera" />
                            <s:if test="%{#variable.equalsIgnoreCase('S')}">                            
                                <div class="form-group alert alert-info col-md-12 col-xs-12 col-sm-12" role="alert">
                                    <p>
                                        Si realmente este el <b>PRODUCTO</b> que buscas genera el reporte 
                                    </p>
                                </div>  
                                <br/>
                                <table class="table table-bordered">
                                    <tr >
                                        <td colspan="2" class="alert-info text-center">Detalle Producto</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 50%;">
                                            <table>
                                                <tr>
                                                    <td style="width: 40%; text-align: right"><b>Codigo: </b></td>
                                                    <td><s:text name="producto.codigo"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="width: 50%;">
                                            <table>
                                                <tr>
                                                    <td style="width: 40%; text-align: right"><b>Referencia: </b></td>
                                                    <td><s:text name="producto.referencia"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td style="width: 40%;text-align: right"><b>Nombre: </b></td>
                                                    <td><s:text name="producto.nombre"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td style="width: 40%;text-align: right"><b>Descripción: </b></td>
                                                    <td><s:text name="producto.descripcion"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr style="height: 50px;">
                                        <td>
                                            <table>
                                                <tr>
                                                    <td style="width: 40%;text-align: right;"><b>Marca: </b></td>
                                                    <td><s:text name="producto.marca"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="text-align: center;">
                                            <table>
                                                <tr>
                                                    <td style="width: 40%;text-align: right;">
                                                        <b>Productos <br/>Disponibles: </b>
                                                    </td>
                                                    <td><s:text name="producto.cantidad"/></td>
                                                </tr>
                                            </table>
                                        </td>

                                    </tr>
                                </table>                                
                                <table class="table table-bordered">
                                    <tr>
                                        <td colspan="2" class="text-center alert-success">Detalle Producto</td>
                                    </tr>                                                    
                                    <tr>
                                        <td>Fecha Incial:</td>
                                        <td>
                                            <div class="input-group date" >
                                                <s:textfield name="fechaIni" id="fechaIni" cssClass="form-control" readonly="true"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Fecha Final:</td>
                                        <td>
                                            <div class="input-group date" >
                                                <s:textfield name="fechaFin" id="fechaFin" cssClass="form-control" readonly="true"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 35px;text-align: right;">
                                            <a class="btnElimina" style="font-weight: bold;color: white;" onclick="generaReporte('<s:text name="producto.id"/>')">
                                                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;Generar Reporte</button>
                                            </a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <script>
                                inicializarFechas();
                            </script>
                        </s:if>
                        <s:else>
                            <div class="form-group alert alert-info" role="alert">
                                <p>
                                    Primero debe Realizar la busqueda del producto en la pestaña <b>BUSQUEDA</b>
                                </p>
                            </div>
                        </s:else>
                    </div>
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <form action="rep_generarReporte" style="display: none;">
            <input type="text" name="PNOMBRE_REPORTE"/>
            <input type="text" name="parametros" />
            <input type="text" name="nombre_Jasper" value="inv_promedioPonderado.jasper"/>
            <input type="text" name="SALIDA" value="PDF"/>
        </form>
        <s:set name="variable" value="bandera" />
        <s:if test="%{#variable.equalsIgnoreCase('S')}">                            
            <script>
                $(function() {
                    $('#myTab a:last').tab('show');
                })
            </script>
        </s:if>
        <s:else>
            <script>
                $(function() {
                    $('#myTab a:first').tab('show');
                })
            </script>
        </s:else>
    </body>
</html>