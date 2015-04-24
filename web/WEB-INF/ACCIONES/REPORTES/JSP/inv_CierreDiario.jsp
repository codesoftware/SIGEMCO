<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/adm_cierres.js"></script>
        <style>
            .ocultar{display: none;}
        </style>
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
                <s:form name="inv_consCierre" action="inv_consCierre" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="cierreDiario"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>CIERRE DIARIO</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Sede:<br>
                                <s:select list="sedes"  id="sede" name="sede" required="true" headerKey="-1" headerValue="Seleccione una Sede.." cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Fecha:<br>
                                <div class="input-group date" >
                                    <s:textfield  cssClass="form-control" name="fecha" readonly="true"/>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">
                            <br>

                            <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp">
                                <s:param name="function">insertaCierre</s:param>
                                <s:param name="title">Consulta de cierre</s:param>
                            </s:include>
                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                <s:param name="function">consultaCierre</s:param>
                                <s:param name="title">Consulta de cierre</s:param>
                            </s:include>
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
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th class="ocultar">Clase</th>
                                <th class="ocultar">Grupo</th>
                                <th class="ocultar">Cuenta</th>
                                <th class="mostrar">Codigo SubCta.</th>
                                <th>Nombre SubCta.</th>
                                <th>Documento</th>
                                <th>Naturaleza</th>
                                <th>Fecha</th>
                                <th>Valor</th>
                            </tr> 
                        </thead>
                        <tbody>                            
                            <s:iterator value="resultMoviContable">                                    
                                <tr>                                    
                                    <td class="ocultar"><s:property value="clas_nombre"/></td>
                                    <td class="ocultar"><s:property value="grup_nombre"/></td>
                                    <td class="ocultar"><s:property value="cuen_nombre"/></td>
                                    <td>
                                        <a href="#" onclick="obtenerAsientocontable('<s:property value="mvco_trans"/>');
                                                $('#partidaDoble').modal('show');" >
                                        <s:property value="sbcu_codigo"/></td>
                                    </a>
                                    <td>
                                        <s:property value="sbcu_nombre"/>
                                    </td>
                                    <td>
                                        <s:if test="%{mvco_lladetalle == 'mvin'}">
                                            Compra/Adicion Prod.
                                        </s:if>
                                        <s:elseif test="%{mvco_lladetalle == 'fact'}">
                                            Facturacion
                                        </s:elseif>
                                        <s:elseif test="%{mvco_lladetalle == 'corin'}">
                                            Correccion Inventario
                                        </s:elseif>
                                        <%--<s:property value="mvco_lladetalle"/>--%>
                                    </td>
                                    <td>
                                        <s:if test="%{mvco_naturaleza == 'D'}">
                                            Debito
                                        </s:if>
                                        <s:else>
                                            Credito
                                        </s:else>
                                        <%--<s:property value="mvco_naturaleza"/>--%>
                                    </td>
                                    <td>
                                        <s:property value="mvco_fecha"/>
                                    </td>
                                    <td>
                                        $ <s:property value="mvco_valor"/>                                        
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>                        
        </div>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="partidaDoble">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">ASIENTO CONTABLE</h4>
                    </div>
                    <div class="modal-body">
                        <span id="tablaAsientocontable"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-success" data-dismiss="modal" id="parametrizarPrecio">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <s:if test="%{moviContable.clas_clas != null }">
            <script type="text/javascript">
                var clase = '<s:text name="moviContable.clas_clas"/>';
                if (clase != '-1') {
                    traeGrupoXClase(clase);
                }
                var grupo = '<s:text name="moviContable.grup_grup"/>';
                document.getElementById('grup_grup').value = grupo;
                if (grupo != '-1') {
                    traeCuentaXGrupo(grupo);
                }
                var cuenta = '<s:text name="moviContable.cuen_cuen"/>';
                document.getElementById('cuen_cuen').value = cuenta;
            </script>
        </s:if>
    </body>
</html>
