<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <s:head/>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdServicio.js"></script>
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
                <s:form theme="simple" action="adm_updServicio" name="adm_updServicio" id="adm_updServicio">
                    <s:textfield name="accion" value="updServicio" cssStyle="display:none;"/>
                    <s:textfield name="subAccion" id="subAccion" cssStyle="display:none;" value="consulta" />
                    <s:textfield name="habitacion.idHabitacion" cssStyle="display:none"/>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center"><h3>ACTUALIZAR HABITACION</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Numero Habitación:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numHabi" id="numHabi" title="Recuerda que el primer digito es piso en el cual se encuentra la habitación" />
                                </td>
                            </tr>
                            <tr>
                                <td>Num. Maximo de Personas:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numMaxPers" />
                                </td>
                            </tr>
                            <tr>
                                <td>Num. Minimo de Personas:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numMinPers" />
                                </td>
                            </tr>
                            <tr>
                                <td>Iva:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.iva" />
                                </td>
                            </tr>
                            <tr>
                                <td>Baño</td>
                                <td>
                                    <s:select cssClass="form-control" list="yesNo"  name="habitacion.bano" headerKey="-1" headerValue="Servicio Baño" />
                                </td>
                            </tr>
                            <tr>
                                <td>Televisión:</td>
                                <td>
                                    <s:select cssClass="form-control" list="yesNo"  name="habitacion.television" headerKey="-1" headerValue="Servicio de Televisión" />
                                </td>
                            </tr>
                            <tr>
                                <td>Cable:</td>
                                <td>
                                    <s:select cssClass="form-control" list="yesNo"  name="habitacion.cable" headerKey="-1" headerValue="Servicio de Cable" />
                                </td>
                            </tr>
                            <tr>
                                <td>Numero de Camas:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numCamas" />
                                </td>
                            </tr>
                            <tr>
                                <td>Cama Auxiliar:</td>
                                <td>
                                    <s:select cssClass="form-control"  list="yesNo"  name="habitacion.camaAux" headerKey="-1" headerValue="Cama Auxiliar" />
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <span class="spanUpdate">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp" >
                                            <s:param name="function">actualizaServicio</s:param>
                                            <s:param name="title">Actualizar Habitacion</s:param>                                                                        
                                        </s:include>
                                    </span>
                                    <span class="spanConsulta">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                            <s:param name="function">consultaServicio</s:param>
                                            <s:param name="title">Buscar Habiacion</s:param>                                    
                                        </s:include>
                                    </span>                                   
                                </td>
                            </tr>
                        </tfoot>
                    </table>            
                </s:form>
            </div>
        </div>
        <div class="MensajesOk" style="display: none;">
            <s:if test="hasActionMessages()">
                <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                <script>
                    mostrarMsnOk();
                </script>
            </s:if>
        </div>
        <s:set var="bandera" value="bandera"/>
        <s:if test="%{bandera.equalsIgnoreCase('S')}">
            <script>
                manipulaBoton('Actualiza');
            </script>
        </s:if>
        <s:else>
            <script>
                manipulaBoton('Consulta');
            </script>
        </s:else>
    </body>
</html>