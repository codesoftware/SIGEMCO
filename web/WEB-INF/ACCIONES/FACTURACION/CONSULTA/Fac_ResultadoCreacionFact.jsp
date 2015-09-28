<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATE/cabecera.jsp"></s:include>
        <s:head/> 
        <script type="text/javascript" src="/PROYECTOHOTEL/JS/FACTURACION/Fac_ResultadoCreacionFact.js"></script>
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
    <center>
        <div id="contenido">
            <br/>
            <br/>
            <br/>
            <div class="mensajesOk" style="display: none;">
                <s:if test="hasActionMessages()">
                    <s:actionmessage/>
                    <script>
                        mostrarMsn();
                    </script>
                </s:if>                
            </div>
            <br/>
            <br/>
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
            <div id="factura">
                <div class="tableConsultaMultiFiltro" style="width: 75%">
                    <table>
                        <tr>
                            <td colspan="4" class="titulo" style="text-align: center;">Datos Generales de la Factura</td>
                        </tr>
                        <tr>
                            <td><b>Factura No.</b> <s:text name="factura.fact_fact" /></td>       
                            <td><b>Fecha:</b> <s:text name="factura.fact_fec_ini" /></td>       
                            <td><b>Estado:</b> <s:text name="factura.fact_estado" />  </td>
                            <td><b>Facturo:</b> <s:text name="factura.usuarioFacturo" /></td>
                        </tr>                    
                        <tr>
                            <td colspan="4" style="text-align: center;" class="titulo" >Datos del cliente</td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-weight: bold;">Nombre:</td>
                            <td ><s:text name="factura.cliente.nombres" /></td>
                            <td style="text-align: right;font-weight: bold;">Apellidos:</td>
                            <td><s:text name="factura.cliente.apellidos" /></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-weight: bold;">Cedula</td>
                            <td><s:text name="factura.cliente.cedula" /></td>
                            <td style="text-align: right;font-weight: bold;">Celular</td>
                            <td><s:text name="factura.cliente.cel" /></td>
                        </tr>
                    </table>
                </div>
                <br/>
                <br/>
                <div class="tableConsultaMultiFiltro" style="width: 90%">
                    <table>
                        <tr>
                            <td class="titulo" colspan="9" style="text-align: center">Detalle factura Servicios</td>
                        </tr>
                        <tr>
                            <td colspan="6" style="text-align: center; font-weight: bolder">Detalles</td>
                            <td colspan="3" style="text-align: center; font-weight: bolder">Valores</td>
                        </tr>
                        <tr>
                            <td>Id</td>
                            <td>Fecha</td>
                            <td>Habitación</td>
                            <td>Num. Días</td>
                            <td>Costo por noche</td>
                            <td>Reservación</td>
                            <td>Iva Habitación</td>
                            <td>Hospedaje Habitacion</td>
                            <td>Habitación Total</td>
                        </tr>
                        <%
                            int i = 0;
                        %>
                        <s:iterator value="factura.detalleServicio">
                            <%
                                i++;
                                if (i % 2 == 0) {
                            %>
                            <tr>
                                <%
                                } else {
                                %>
                            <tr style="background-color: #D6E3E2;">
                                <%
                                    }
                                %>                            
                                <td style="text-align: center;"><s:property value="dtsv_dtsv"/></td>
                                <td><s:property value="dtsv_fecha"/></td>
                                <td style="text-align: center;"><s:property value="numHabitacion"/></td>
                                <td style="text-align: center;"><s:property value="diasReserv"/></td>
                                <td><s:property value="precioHabitacion"/></td>
                                <td style="text-align: center;"><s:property value="dtsv_rvha"/></td>
                                <td><s:property value="dtsv_valor_iva"/></td>
                                <td><s:property value="dtsv_valor_sv"/></td>
                                <td><s:property value="dtsv_valor_venta"/></td>                                
                            </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="9">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td class="titulo" colspan="3" style="text-align: center;">Valores Servicios&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td style="text-align: right;" colspan="2"><b>Valor Total Iva Hospedaje:</b></td>
                            <td><s:text name="factura.valorIva" /></td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td style="text-align: right;" colspan="2"><b>Valor Total Hospedaje:</b></td>
                            <td><s:text name="factura.valorServicio" /></td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td style="text-align: right;background-color: #D6E3E2;" colspan="2"><b>Valor Total Hospedaje (Sevicio+Iva):</b></td>
                            <td style="background-color: #D6E3E2;"><s:text name="factura.valorTotal" /></td>
                        </tr>
                    </table>
                </div>
            </div>
            <br/>
            <br/>
            <s:if test="factura.fact_estado == 'Por Aprobación'">
                <form action="Fact_AprobarFactura" name="Fact_AprobarFactura" id="Fact_AprobarFactura">
                    <table>
                        <tr>
                            <td><a class="btnInserta" onclick="envioConfirmar()">Confirmar</a></td>
                            <td><a class="btnElimina">Rechazar</a></td>
                        </tr>
                        <tr  style="display: none;">
                            <td><s:textfield name="factura.fact_fact" cssStyle="display: none;"/></td>
                        </tr>
                    </table>                            
                </form>
            </s:if>

        </div>
    </center>
</body>
</html>
