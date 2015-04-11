<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/CONTABILIDAD/Con_MovContable.js"></script>



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
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
            <div class="col-md-8 col-xs-12 col-sm-12">
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
                <br/>
                <s:form name="inv_consMovContable" action="inv_consMovContable" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="consultaGeneral"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>Consulta General de Movimientos Contables</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Clase:<br>
                                <s:select list="clasePUC"  name="moviContable.clas_clas" id="listClase" required="true" headerKey="-1" headerValue="Seleccione una Clase.." cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Grupo:<br>
                                <s:select list="grupoPUC" name="moviContable.grup_grup" id="grup_grup" required="true" headerKey="-1" headerValue="Seleccione Grupo.." cssClass="form-control"/> 
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Cuenta:<br>
                                <s:select list="cuentaPUC" name="moviContable.cuen_cuen" id="cuen_cuen" required="true" headerKey="-1" headerValue="Seleccione cuenta.." cssClass="form-control"/> 
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                <br>
                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                    <s:param name="function">buscaGeneralMvCon</s:param>
                                    <s:param name="title">Busqueda de Movimientos de Inventario</s:param>
                                </s:include>
                            </div>
                        </div>

                    </div>                    
                </s:form>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <s:if test="%{resultMoviContable != null}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Descripci&oacute;n</th>
                                <th>Naturaleza</th>
                                <th>Usuario Implicado</th>
                                <th>Implica Facturaci&oacute;n</th>
                                <th>Implica Reverso Facturaci&oacute;n</th>
                                <th>Implica Inventario Inicial</th>
                                <th>Accion</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <%
                                int i = 0;
                            %>
                            <s:iterator value="resultMoviContable">
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
                                    <td><s:property value="mvco_id_llave"/></td>
                                    <td><s:property value="mvco_lladetalle"/></td>
                                    <td><s:property value="mvco_mvco"/></td>
                                    <td><s:property value="mvco_naturaleza"/></td>
                                    <td><s:property value="mvco_tercero"/></td>
                                    <td><s:property value="mvco_trans"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>                        
        </div>
    </body>
</html>
