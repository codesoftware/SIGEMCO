<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <s:head/>
        <script>
            function insertar() {
                document.getElementById("adm_insertHabitacion").submit();
            }
            //---------------------------------------------------------------
            function cleanForm() {
                document.getElementById("habitacion_numHabi").value = "";
                document.getElementById("habitacion_numMaxPers").value = "";
                document.getElementById("habitacion_numMinPers").value = "";
                document.getElementById("habitacion_iva").value = "";
                document.getElementById("habitacion_bano").value = "-1";
                document.getElementById("habitacion_television").value = "-1";
                document.getElementById("habitacion_cable").value = "-1";
                document.getElementById("habitacion_numCamas").value = "";
                document.getElementById("habitacion_camaAux").value = "-1";
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
                <s:form theme="simple" action="adm_insertHabitacion" name="adm_insertHabitacion" id="adm_insertHabitacion">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" ><h3>INGRESAR HABITACIONES</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Numero Habitacion:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numHabi" title="Recuerda que el primer digito es piso en el cual se encuentra la habitación" />
                                </td>
                            </tr>
                            <tr>
                                <td>Num. Maximo de Personas:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="habitacion.numMaxPers" />
                                </td>
                            </tr>
                            <tr>
                                <td>Num. Minimo de Personas</td>
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
                                <td>Baño:</td>
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
                                <td>Cama Aúxiliar:</td>
                                <td>
                                    <s:select cssClass="form-control"  list="yesNo"  name="habitacion.camaAux" headerKey="-1" headerValue="Cama Auxiliar" />
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" >
                                        <s:param name="function">insertar</s:param>
                                        <s:param name="title">Insertar Habitación</s:param>                                    
                                    </s:include>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>            
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="mensajesOk" style="display: none;">
            <s:if test="hasActionMessages()">
                <s:actionmessage/>
                <script>
                    mostrarMsn();
                </script>
            </s:if>                
        </div>
    </body>
</html>