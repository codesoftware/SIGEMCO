<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript" src="/PROYECTOHOTEL/JS/FACTURACION/Fact_ModificacionFactura.js" ></script>
        <s:head/> 
    </head>
    <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/TEMPLATE/FrameTop.jsp" > 
                <s:param name="nombre"><s:text name="usuario.apellido"/> <s:text name="usuario.nombre"/></s:param>
                <s:param name="perfil"><s:text name="usuario.NomPerfil"/></s:param>
                <s:param name="ultimoIngreso"><s:text name="usuario.ultimoIngreso"/></s:param>
            </s:include>
        </s:div>
        <br/>
        <s:div cssClass="navigator">
            <s:include value="/WEB-INF/TEMPLATE/menuHorizontal.jsp">
                <s:param name="title"><s:property value="usuario.usuario" /></s:param>
            </s:include> 
        </s:div>
        <br/>
    <center>
        <div id="contenido">
            <div class="errorMensajes" style="display: none;">
                <s:if test="hasActionErrors()">
                    <div class="errors">
                        <s:actionerror/>                                               
                        <script>
                            mostrarMsnErr();
                        </script>
                    </div>
                </s:if>
            </div>


            <div class="mensajesOk" style="display: none;">
                <s:if test="hasActionMessages()">
                    <s:actionmessage/>
                    <script>
                        mostrarMsn();
                    </script>
                </s:if>                
            </div>
            <br/>
            <s:if test="%{estado.equalsIgnoreCase('inicial')}">
                <s:form theme="simple" action="Fact_buscaFactura" id="Fact_buscaFactura" autocomplete="off" >
                    <s:textfield name="accion" value="busquedaInical" cssStyle="display:none"/>
                    <div class="tableInsert">
                        <table>
                            <thead>
                                <tr>
                                    <td colspan="4">Filtros de Busqueda de Facturas</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Cedula del Cliente</td>
                                    <td><s:textfield name="factura.cliente.cedula" title="Digite el numero de cedula que registro la factura"/></td>
                                    <td>Numero de Factura</td>
                                    <td><s:textfield name="factura.fact_fact" title="Digite el numero de la factura que se desea modificar" /></td>
                                </tr>                                
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                            <s:param name="function">buscaGeneral</s:param>
                                            <s:param name="title">Busca Facturas</s:param>
                                        </s:include>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </s:form>
            </s:if>
            <s:else>
                <s:form theme="simple" id="Fact_BuscaFacEspecificaGestion" name="Fact_BuscaFacEspecificaGestion" action="Fact_BuscaFacEspecificaGestion">
                    <s:textfield name="factura.fact_fact" cssStyle="display:none " />
                    <s:textfield name="accion" value="busquedaEspecifica" cssStyle="display:none"/>
                    <div class="acordionFact" style="width: 95%;text-align: left;">
                        <s:iterator value="resultados" >                        
                            <h3>
                                <s:text name="cliente.apellidos" /> <s:text name="cliente.nombres" /> Factura No. <s:text name="fact_fact" />  
                                &nbsp;&nbsp;&nbsp;
                            </h3>
                            <div>
                                <center>
                                    <div class="tableInsert" style="width: 95%">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <td colspan="6">
                                                        Informaci&oacute;n de la factura
                                                    </td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <b>Fecha Creación:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="fact_fec_ini" />
                                                    </td>
                                                    <td>
                                                        <b>Usuario que Facturo:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="usuarioFacturo" />
                                                    </td>                                    
                                                    <td>
                                                        <b>Estado:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="fact_estado" />
                                                    </td> 
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <b>Valor Iva:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="valorIva" />
                                                    </td>
                                                    <td>
                                                        <b>Valor Servicio:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="valorServicio" />
                                                    </td>
                                                    <td>
                                                        <b>Valor Total:</b>
                                                    </td>
                                                    <td>
                                                        <s:text name="valorTotal" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6" style="text-align: righ;">
                                                        <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp"  >
                                                            <s:param name="function">actualizarFactura</s:param>
                                                            <s:param name="title">Modificacion de Factura</s:param>
                                                            <s:param name="parameters">'<s:text name="fact_fact" />'</s:param>
                                                        </s:include>                                                
                                                    </td>
                                                </tr>
                                            </tbody>                                        
                                        </table>
                                    </div>
                                </center>
                            </div>                        
                        </s:iterator>
                    </div>
                </s:form>
            </s:else>
        </div>
    </center>
</body>
</html>
