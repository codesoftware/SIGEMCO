<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/PERFIL/Adm_UpdPerfil.js"></script>
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
        <br>     
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
                <s:form action="adm_updatePerfil" name="adm_insertPerfil" id="adm_insertPerfil" method="post" autocomplete="off" theme="simple">
                    <s:textfield name="perfil.id" cssClass="perfil.id" cssStyle="display:none"/>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>ACTUALIZAR PERFIL</h3></th>
                        </tr>
                        </thead>                        
                        <tbody>
                            <tr>
                                <td>Nombre:</td>
                                <td><s:textfield cssClass="form-control" name="perfil.nombre" required="true" onkeyup="buscaUsuario(this.value)"/></td>
                            </tr>
                            <tr>
                                <td>Descripción:</td>
                                <td><s:textfield cssClass="form-control" name="perfil.descripcion" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Estado:</td>
                                <td>
                                    <select class="form-control" name="perfil.estado" id="perfil.estado" style="width: 100%">
                                        <option value="-1">...</option>
                                        <option value="A">Activo</option>
                                        <option value="I">Inactivo</option>                                    
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <div class="row">
                                        <div class="form-group col-md-4 col-sm-0 col-xs-0"></div>
                                        <div class="form-group col-md-4 col-sm-0 col-xs-0" ></div>
                                        <div class="form-group col-md-4 col-sm-12 col-xs-12">                      
                                            <div class="divBtnConsulta" style="float: right" >
                                                <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                                    <s:param name="function">consultarPerfil</s:param>
                                                    <s:param name="title">Buscar Perfil</s:param>
                                                </s:include>
                                            </div>
                                            <div class="divBtnActualizar" style="display:none;float: right">
                                                <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp"  >
                                                    <s:param name="function">actualizar</s:param>
                                                    <s:param name="title">Modificar Atributos del Perifl</s:param>
                                                </s:include>
                                            </div>
                                            <div style="float: right">
                                                <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                            </div>
                                        </div>                                        
                                    </div>

                                    <div class="divBtnConsulta">

                                    </div>

                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
        <s:set name="estado" value="%{valorSelect}" />
        <s:if test="%{#estado.equalsIgnoreCase('A')}">            
            <script>
                var estado = '<s:property value="#estado"/>';
                document.getElementById('perfil.estado').value = estado;
            </script>
        </s:if>
        <s:elseif test="%{#estado.equalsIgnoreCase('I')}">
            <script>
                var estado = '<s:property value="#estado"/>';
                document.getElementById('perfil.estado').value = estado;
            </script>            
        </s:elseif>
        <s:else>
            <script>
                document.getElementById('perfil.estado').value = '-1';
            </script>            
        </s:else>
    </body>
</html>
