<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reservaciones por producto</title>
        <s:include value="/WEB-INF/TEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript">
                $(function() {
                    $("#tabs").tabs();
                });
            </script>
            <style>
                .tablaEspecial{
                    box-shadow: 10px 10px 5px #888888;
                    border:1px solid #000000;
                    -moz-border-radius-bottomleft:12px;
                    -webkit-border-bottom-left-radius:12px;
                    border-bottom-left-radius:12px;

                    -moz-border-radius-bottomright: 12px;
                    -webkit-border-bottom-right-radius: 12px;
                    border-bottom-right-radius:12px;
                }
                .titulo{
                    font-style: italic;
                    text-align: right;
                    font-weight: bolder;                    
                }
                .tablaEspecial tr td{
                    border:1px solid #000000;
                    padding:5px;
                    border-width:0px 1px 1px 0px;
                }
                .tablaEspecial td:last-child {                

                    -moz-border-radius-bottomright: 12px;
                    -webkit-border-bottom-right-radius: 12px;
                    border-bottom-right-radius:12px;
                }
            </style>
        </head>
        <body>
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
    <center>
        <div id="tabs" style="width: 90%">
            <ul>
                <li><a href="#tabs-1">Infomacion General</a></li>
                <li><a href="#tabs-2">Reservaciones</a></li>
            </ul>
            <div id="tabs-1">
                <br/>
                <br/>
                <table class="tablaEspecial">
                    <tr>
                        <td colspan="4" class="titulo" style="text-align: center;background-color: #ffaa56">Descripción de la Habitación</td>
                    </tr>
                    <tr>
                        <td class="titulo">Numero de Habitación</td>
                        <td><s:text name="habitacion.numHabi"/></td>
                        <td class="titulo">Estado Actual</td>
                        <td><s:text name="habitacion.estado"/></td>
                    </tr>
                    <tr>
                        <td class="titulo">Numero de Camas</td>
                        <td><s:text name="habitacion.numCamas" /></td>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="titulo">Capacidad</td>
                        <td><s:text name="habitacion.numMaxPers" /> personas</td>
                        <td class="titulo">Num. Min. Personas</td>
                        <td><s:text name="habitacion.numMinPers" /> personas</td>
                    </tr>
                    <tr>
                        <td colspan="4" class="titulo" style="text-align: center;background-color: #ffaa56">Servicios Disponibles</td>
                    </tr>
                    <tr>
                        <td class="titulo">Baño</td>
                        <td><s:text name="habitacion.bano" /></td>
                        <td class="titulo">Televisión</td>
                        <td><s:text name="habitacion.television" /></td>
                    </tr>
                    <tr>
                        <td class="titulo">Cable</td>
                        <td><s:text name="habitacion.cable" /></td>
                        <td class="titulo">Cama Aux</td>
                        <td><s:text name="habitacion.camaAux" /></td>
                    </tr>
                </table> 
            </div>
            <div id="tabs-2">
                <br/>
                <b>Habitacion Numreo: <s:text name="habitacion.numHabi"/></b>
                <br/>
                <s:if test="reservaciones != null">
                    <br/>
                    <div class="tableConsulta" style="width:85%; "> 
                        <table>
                            <tr>
                                <td>Cliente</td>
                                <td>Fecha Inicial</td>
                                <td>Fecha Final</td>
                                <td>Estado</td>
                                <td>Numero de Días</td>
                            </tr>
                            <s:iterator value="reservaciones">
                                <tr>
                                    <td><s:property value="nomClien"/></td>
                                    <td><s:property value="fechaIni"/></td>
                                    <td><s:property value="fechaFin"/></td>
                                    <td><s:property value="estado"/></td>
                                    <td><s:property value="numDias"/></td>
                                </tr>                           
                            </s:iterator>
                        </table>
                    </div>
                </s:if>
                <s:else>
                    <br/>
                    No hay reservaciones futuras para esta habitación
                    <br/>
                </s:else>
            </div>
        </div>
    </center>
</body>
</html>
