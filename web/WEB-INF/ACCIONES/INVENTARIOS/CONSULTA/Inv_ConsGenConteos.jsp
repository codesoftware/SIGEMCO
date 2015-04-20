<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_ConsGenConteos.js"></script>
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
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <s:form method="post" theme="simple" action="Inv_ConsGenConteos" id="Inv_ConsGenConteos">
                    <s:textfield name="accion" value="consGenCont" cssStyle="display:none;"/>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="alert alert-info text-center" colspan="2"><h4>Consulta de Conteos</h4></th>
                        </tr>
                        <tr>
                            <th style="width: 50%;">Sede</th>
                            <th style="width: 50%;">Fecha</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <s:select cssClass="form-control" list="sedes" id="sedes" name="conteo.copr_sede"  headerKey="-1" headerValue="Sede en el cual se realizara el conteo"/>
                                </td>
                                <td>
                                    <div class="input-group date" >
                                        <s:textfield name="conteo.copr_fecha" cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                        <s:param name="function">buscaGeneral</s:param>
                                        <s:param name="title">Buscar Conteos</s:param>
                                    </s:include>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>                
        </div>
        <div class="row">
            <div class="col-md-2 col-sm-0 col-xs-0"></div>
            <div class="col-md-8 col-sm-12 col-xs-12">
                <s:if test="%{listConteo != null}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Sede</th>
                                <th>Comentario</th>
                                <th>Fec. Creacion</th>
                                <th>Fec. Inicio</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="listConteo">
                                <tr>
                                    <td><s:property value="copr_copr" /></td>
                                    <td><s:property value="sede_nombre" /></td>
                                    <td><s:property value="copr_desc" /></td>
                                    <td><s:property value="copr_fecha" /></td>
                                    <td><s:property value="copr_fec_ini" /></td>
                                    <td>
                                        <s:if test="%{ copr_estado.equalsIgnoreCase('C') }">
                                            <a href="#" onclick="iniciarConteo('<s:property value="copr_copr" />')">Creado</a>
                                        </s:if>
                                        <s:elseif test="%{ copr_estadoequalsIgnoreCase('A') }">
                                            Abierto
                                        </s:elseif>
                                        <s:elseif test="%{ copr_estado.equalsIgnoreCase('X') }">
                                            Cerrado
                                        </s:elseif>
                                    </td>
                                </tr>                                
                            </s:iterator>
                        </tbody>
                    </table>                                        
                </s:if>                
            </div>
            <div class="col-md-2 col-sm-0 col-xs-0"></div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('.input-group.date').datepicker({
                    format: 'mm/dd/yyyy'
                });
            });
        </script>
        <s:form action="Inv_IniciaConteo" id="Inv_IniciaConteo" theme="simple" cssStyle="display:none;">
            <s:textfield name="accion" value="iniciaConteo" />
            <s:textfield name="conteo.copr_copr" id="copr_coprInicio" />
                                                
        </s:form>
    </body>
</html>
