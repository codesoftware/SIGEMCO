<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenServ.js"></script>
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
        <div calss="row">
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
                <s:form theme="simple" action="inv_consGenServ" id="inv_consGenServ" method="post">
                    <div class="row thumbnail">
                        <div class="alert alert-success text-center"  role="alert" ><h3>CONSULTA GENERAL DE SERVICIOS</h3></div>
                        <div class="form-group col-md-4">
                            Baño:<br>
                            <s:select cssClass="form-control" list="yesNo" headerKey="-1" name="habitacion.bano" headerValue="TODOS" />
                        </div>
                        <div class="form-group col-md-4">
                            Cable:<br>
                            <s:select  cssClass="form-control" list="yesNo"  name="habitacion.cable" headerKey="-1" cssStyle="width:70%;" headerValue="TODOS" />
                        </div>
                        <div class="form-group col-md-4">
                            Televisión:<br>
                            <s:select cssClass="form-control" list="yesNo"  name="habitacion.television"  cssStyle="width:70%;" headerKey="-1" headerValue="TODOS" />
                        </div>
                        <div class="form-group col-md-4">
                            Cama Auxiliar:<br>
                            <s:select cssClass="form-control"   list="yesNo"  name="habitacion.camaAux" cssStyle="width:70%;" headerKey="-1" headerValue="TODOS" />
                        </div>
                        <div class="form-group col-md-4">
                            Numero de Camas:<br>
                            <s:textfield cssClass="form-control" name="habitacion.numCamas"/>
                        </div>
                        <div class="form-group col-md-4" >
                            <br>
                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" > 
                                <s:param name="function">consultar</s:param>
                                <s:param name="title">Busqueda de Servicios por filtros</s:param>
                            </s:include>
                        </div>

                    </div>
                </s:form>  
            </div>
        </div>
        <s:if test="%{rtaHabitacion != null }" >
            <s:set var="variable" value="bandera"/>
            <div class="row"> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <table class="table">
                        <tr>
                            <td>Num. Habitación</td>
                            <td>Num. Max. Pers</td>
                            <td>Num. Min. Pers</td>
                            <td>Num Camas</td>
                            <td>Cama Auxiliar</td>
                            <td>% Iva</td>
                            <s:if test="%{#variable.equalsIgnoreCase('S')}">
                                <td>Acción</td>
                            </s:if>
                        </tr>
                        <%
                            int i = 0;
                        %>
                        <s:iterator value="rtaHabitacion">
                            <%
                                if (i % 2 == 0) {
                            %>
                            <tr class="active">
                                <%
                                } else {
                                %>
                            <tr>
                                <%
                                    }
                                    i++;
                                %>
                                <td><s:property value="numHabi"/></td>
                                <td><s:property value="numMaxPers"/></td>
                                <td><s:property value="numMinPers"/></td>
                                <td><s:property value="numCamas"/></td>
                                <td><s:property value="camaAux"/></td>
                                <td><s:property value="iva"/></td>
                                <s:if test="%{#variable.equalsIgnoreCase('S')}">
                                    <td style="height: 25px;">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" >
                                            <s:param name="function">actulizarEspecifico</s:param>
                                            <s:param name="title">Actualizar Habitación Numero: <s:property value="numHabi"/>: </s:param>
                                            <s:param name="paramFunction">'<s:property value="numHabi"/>'</s:param>
                                            <s:param name="clase">imagenIconoPeq</s:param>
                                        </s:include>
                                    </td>
                                </s:if>                                    
                            </tr>
                        </s:iterator>
                    </table>
                </s:if>                        
            </div>
        </div>
        <s:form id="adm_updServicio" action="adm_updServicio" theme="simple" cssStyle="display:none;">
            <s:textfield name="accion" value="updServicio" cssStyle="display:none;"/>
            <s:textfield name="subAccion" id="subAccion" cssStyle="display:none;" value="consulta" />
            <s:textfield name="habitacion.numHabi" id="numHabiPorUpd" />
        </s:form>
    </body>
</html>
