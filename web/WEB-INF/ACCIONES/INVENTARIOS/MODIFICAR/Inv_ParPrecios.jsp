<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ParPrecio.js"></script>
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
                <s:if test="habitacion == null" >
                    <s:form theme="simple" action="inv_ConsultaServ" id="inv_ConsultaServ" autocomplete="off">
                        <div class="row thumbnail">
                            <div class="alert alert-success text-center"><h3>PARAMETRIZACIÓN DE PRECIOS</h3></div>
                            <p>Habitación a darle el precio<p>
                            <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                Numero de habitacion:
                                <s:textfield cssClass="form-control" name="habitacion.numHabi" />
                            </div>
                            <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                    <s:param name="function">consultar</s:param>
                                    <s:param name="title">Buscar Habiacion</s:param>                                    
                                </s:include>
                                <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                            </div>
                        </div>  
                    </s:form>
                </s:if>
                <s:else>
                    <s:form theme="simple" action="inv_ParamPrecioSv" id="inv_ParamPrecioSv" autocomplete="off">
                        <s:textfield name="habitacion.idHabitacion" cssStyle="display:none" />
                        <s:textfield name="habitacion.numHabi" cssStyle="display:none" />
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td colspan="2" class="alert alert-info text-center"><h3>PARAMETRIZACIÓN DE PRECIOS<h3></td>
                                </tr>
                                <tr>
                                    <td colspan="2">Habitación a darle el precio</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" style="text-align: center"><b>Datos Basicos Habitación</b></td>
                                </tr>
                                <tr>
                                    <td>
                                        Num Habitacion: <s:text name="habitacion.numHabi" />
                                    </td>
                                    <td>
                                        Id Habitación: <s:text name="habitacion.idHabitacion" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><s:textfield cssClass="form-control" name="habitacion.precio" label="Precio Bruto"/></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                            <s:param name="title">Buscar Otra Habitación</s:param>
                                            <s:param name="link">href="reenvioGeneral.action?accion=231"</s:param>
                                        </s:include>
                                        <s:include value="/WEB-INF/TEMPLATE/botones/parametrizar.jsp" >
                                            <s:param name="title">Parametrizar precio de la habitación</s:param>
                                            <s:param name="function">insertarParametro</s:param>                                                
                                        </s:include>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </s:form>
                <s:if test="hisPrecio != null">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td colspan="3" class="alert-success text-center">Historial de Precios</td>
                                </tr>
                                <tr>
                                    <td>Fecha</td>
                                    <td>Precio</td>
                                    <td>Estado</td>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="hisPrecio">
                                    <tr>
                                        <td><s:property value="prha_fecha"/></td>
                                        <td><s:property value="prha_precio"/></td>
                                        <td><s:property value="prha_estado"/></td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                </s:if>
                <s:else>
                    <b>ESTA HABITACION NO TIENE HISTORIAL DE PRECIOS</b>
                </s:else>
            </s:else>
            </div>
        </div>
    </body>
</html>
