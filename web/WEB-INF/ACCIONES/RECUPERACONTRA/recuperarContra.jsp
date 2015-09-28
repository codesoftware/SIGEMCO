<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/NEWTEMPLATE/cabecera.jsp"%>
    </head>
    <body>
        <br/>
        <br/>
        <s:form action="recupera" autocomplete="off" theme="simple">
            <div class="row " >
                <div class="col-sm-1 col-md-4"></div>
                <div class="col-sm-10 col-md-4 thumbnail">
                    <br/>
                    <div class="form-group col-sm-12 col-md-12">
                     <div class="alert alert-info text-center" role="alert"><h1>Recuperar tu cuenta</h1></div>
                    <p>Para poder ayudarte a recuperar tu contraseña por favor digita la direccion de correo 
                    que tienes registrada en tu cuenta.</p>
                    <s:textfield cssClass="form-control" name="correo" required="true" placeholder="example@correo.com" /><br>
                    </div>
                    <div class="form-group text-center col-sm-12 col-md-12">
                        <s:submit label="Enviar" cssClass="btn btn-primary" />
                        <s:a action="inicio">Ir al inicio</s:a>
                    </div>
                </div>
                <div class="col-sm-1 col-md-4"></div>

            </div>
        </s:form>
            
    </body>
</html>