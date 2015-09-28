<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Metodo Promedio Ponderado</title>
        <style>
            .titulo{
                font-size: 15px;
                font-weight: 700;
                text-align: center;
                background-color: rgba(255, 175, 20, 10);
            }
            .subTitulo{
                font-size: 14px;
                text-align: center;
                background-color: #fece2f;
                font-weight: 600;
            }
            table{
                border-collapse: collapse;
                border:1px solid #000000;
                width: 95%;
                margin-left: 2%;
            }
            table tr td{
                border-collapse: collapse;
                border:1px solid #000000;
            }
            .texto{
                font-size: 14px;
                font-weight: 600;
            }
            .lineaPar{
                background-color: #E0E0E0;
            }
        </style>
    </head>
    <body>
        <br/>
        <br/>
        <br/>
        <table>
            <tr>
                <td colspan="4" style="text-align: center;">REGISTRO DE CONTROL DE INVENTARIO</td>
            </tr>
            <tr>
                <td>Nombre del Contribuyente: </td>
                <td>Hotel Shaoloom GUADUAS</td>
                <td>PERIODO: </td>
                <td><s:text name="fechaIni"/> al <s:text name="fechaFin" /></td>
            </tr>
            <tr>
                <td>NIT:</td>
                <td>000-000-000</td>
                <td>NRC:</td>
                <td>888-777-777</td>
            </tr>
            <tr>
                <td>PRODUCTO:</td>
                <td><s:text name="producto.nombre" /></td>
                <td>CODIGO:</td>
                <td><s:text name="producto.codigo"/></td>
            </tr>
            <tr>
                <td>DESCRIPCION:</td>
                <td><s:text name="producto.descripcion"/></td>
                <td>REFERNCIA</td>
                <td><s:text name="producto.referencia"/></td>
            </tr>
        </table>
        <br/>
        <table>
            <tr class="titulo">
                <td colspan="4">Metodo: <b>Promedio Ponderado</b></td>
                <td colspan="3">ENTRADAS</td>
                <td colspan="3">SALIDAS</td>
                <td colspan="3">EXISTENCIAS</td>
            </tr>
            <tr class="subTitulo">
                <td >No.</td>
                <td>FECHA</td>
                <td>CONCEPTO</td>
                <td>SEDE</td>
                <td>CANT.</td>
                <td>COSTO UNIDAD</td>
                <td>COSTO TOTAL</td>
                <td>CANT.</td>
                <td>COSTO UNIDAD</td>
                <td>COSTO TOTAL</td>
                <td>CANT.</td>
                <td>COSTO UNIDAD</td>
                <td>COSTO TOTAL</td>                    
            </tr>
            <%
                int i = 0;
            %>
            <s:iterator value="detalleInv">
                <%
                    if (i % 2 == 0) {
                %>
                <tr class="texto lineaPar">
                    <%
                    } else {
                    %>
                <tr class="texto">
                    <%
                        }
                    i++;
                    %>
                    <td>&nbsp;<s:property value="consecutivo"/></td>
                    <td>&nbsp;<s:property value="fecha"/></td>
                    <td>&nbsp;<s:property value="mvInv"/></td>
                    <td>&nbsp;<s:property value="sede"/></td>
                    <td>&nbsp;<s:property value="cantEnt"/></td>
                    <td>&nbsp;<s:property value="costoUnidaddEnt"/></td>
                    <td>&nbsp;<s:property value="costoTotalEnt"/></td>                    
                    <td>&nbsp;<s:property value="cantSalidas"/></td>
                    <td>&nbsp;<s:property value="costoUnidadSal"/></td>
                    <td>&nbsp;<s:property value="costoTotalSal"/></td>
                    <td>&nbsp;<s:property value="cantTotal"/></td>
                    <td>&nbsp;<s:property value="costoUnidadTot"/></td>
                    <td>&nbsp;<s:property value="costoTotalTot"/></td>
                </tr>
            </s:iterator>
        </table>


    </body>
</html>
