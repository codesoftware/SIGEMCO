<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript">
                function cleanForm() {
                    document.getElementsByName("perfil.nombre")[0].value = '';
                    document.getElementsByName("perfil.descripcion")[0].value = '';
                    document.getElementsByName("perfil.estado")[0].value = '-1';
                    document.getElementsByName("inserto")[0].value = 'N';
                    $('.btnInserta').show('slow');
                    $('#perfil_nombre').removeAttr('readonly');
                    $('#perfil_descripcion').removeAttr('readonly');
                    $('.MensajesOk').hide("slow");
                    $('.Mensajes').hide("slow");
                }

                function insertar() {
                    var formulario = document.getElementById("adm_insertPerfil");
                    formulario.submit();
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
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
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
                <br>
                <s:form action="adm_insertPerfil" theme="simple" name="adm_insertPerfil" id="adm_insertPerfil">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" class="alert alert-info text-center"><h3>INGRESAR PERFIL</h3></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Nombres:</td>
                                <td><s:textfield cssClass="form-control"  name="perfil.nombre" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Descripción: </td>
                                <td><s:textfield cssClass="form-control"  name="perfil.descripcion" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Estado:</td>
                                <td><select Class="form-control" name="perfil.estado" id="perfil.estado" style="width: 100%">
                                        <option value="-1">...</option>
                                        <option value="A">Activo</option>
                                        <option value="I">Inactivo</option>                                    
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" >
                                        <s:param name="function">insertar</s:param>
                                        <s:param name="title">Insertar Perfil</s:param>
                                    </s:include>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>
            <div style="display: none;">
                <s:textfield name="inserto" />
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
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
        <s:set name="inserto" value="%{inserto}" />
        <s:if test="%{#inserto.equalsIgnoreCase('S')}">            
            <script>
                $('#perfil_nombre').attr('readonly', 'readonly');
                $('#perfil_descripcion').attr('readonly', 'readonly');
                $('.btnInserta').css('display', 'none');
            </script>
        </s:if>
    </body>
</html>