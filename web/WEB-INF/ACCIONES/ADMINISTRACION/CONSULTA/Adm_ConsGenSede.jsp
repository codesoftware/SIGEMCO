<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/SEDE/Adm_ConsdSede.js"></script>
        <s:head/>
        <style>
            .linkPemiso{
                cursor: pointer;
                color: #8f0b0b;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <s:div cssClass="header" >
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
                <br/>
                <div class="MensajesOk" style="display: none;">
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                        <script>
                            mostrarMsnOk();
                        </script>
                    </s:if>
                </div>
                <br/>
                <s:form action="adm_consultaSede" id="formConsultaSede">
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="alert alert-success text-center"  role="alert" ><h3>CONSULA GENERAL DE  SEDES</h3></div>
                        Estado:<br>
                        <select class="form-control" name="sede.sede_estado">
                            <option value="-1" >Todas</option>
                            <option value="A">Activo</option>
                            <option value="I">Inactivo</option>
                        </select><br>
                        <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                            <s:param name="function">consultar</s:param>
                            <s:param name="title">Busca Sedes Por Estado </s:param>                                    
                        </s:include>
                    </div>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-0 col-xs-0"></div>
            <div class="col-md-10 col-sm-12 col-xs-12">
                <table class="table table-hover">
                    <tr>
                        <td>NOMBRE</td>
                        <td>DIRECCIÓN</td>
                        <td>TELÉFONO</td>
                        <td>FECHA CREACIÓN</td>
                        <td>ACCIÓN</td>
                    </tr>
                    <%
                        int i = 0;
                    %>
                    <s:iterator value="resultSede">
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
                            <td><a href="#" onclick="permisoSede('<s:text name="sede_sede"/>')" class="linkPemiso"><s:property value="sede_nombre"/></a></td>
                            <td><s:property value="sede_direccion"/></td>
                            <td><s:property value="sede_telefono"/></td>                        
                            <td><s:property value="sede_fecin"/> </td>                        
                            <td><s:include value="/WEB-INF/TEMPLATE/botones/update.jsp" > 
                                    <s:param name="function">actualizarSede</s:param>
                                    <s:param name="title">Modificar Datos de la sede</s:param>
                                    <s:param name="paramFunction">'<s:text name="sede_sede" />'</s:param>
                                    <s:param name="clase">imagenIcono</s:param>
                                </s:include>
                            </td>                          
                        </tr>
                    </s:iterator>                
                </table>
                <div class="col-md-1 col-sm-0 col-xs-0"></div>
            </div>
        </div>
        <s:form action="adm_conEspSede" theme="simple" id="adm_conEspSede" method="post">
            <s:textfield type="hidden" name="sede.sede_sede" id="linkSede" />
        </s:form>
    </body>
</html>
