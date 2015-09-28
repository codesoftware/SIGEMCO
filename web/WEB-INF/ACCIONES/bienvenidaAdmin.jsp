<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        </head>
        <body>
            <br/>
            <br/>
        <s:set name="update" value="usuario.cambioContra"/>
        <s:if test="%{#update=='SI'}">
            <s:form action="cambioContra" autocomplete="off" theme="simple">
                <div class="row">
                    <div class="col-sm-1 col-md-4"></div>
                    <div class="col-sm-10 col-md-4 thumbnail">
                        <div class="form-group col-sm-12 col-md-12">
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
                            <div class="alert alert-info text-center" role="alert"><h1>CAMBIO DE CLAVE INICIAL</h1></div>
                            <p>Por razones de Seguridad Se solicita cambiar su clave ya que la 
                                clave con la que ingreso fue generada por el sistema.</p>
                            <div class="form-group">
                                <label for="contra">Nueva Contraseña</label>
                                <s:password name="contra"  required ="true" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="contra">Confirmar Contraseña</label>
                                <s:password name="contra2" required = "true" cssClass="form-control"/>
                            </div>
                            <div class="form-group text-center ">
                                <s:submit label="Ingresar"  cssClass="btn btn-primary"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-1 col-md-4"></div>
                </div>
            </s:form>
        </s:if>
        <s:else>
            <script>
                location.href = "<%=RutaSitio%>/inicioAdmin.action";
            </script>
        </s:else>
        <script type="text/javascript">
            $(function() {
                $('.btn-primary').val('Enviar');
            });
        </script>
    </body>
</html>
