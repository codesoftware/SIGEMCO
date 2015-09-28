

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/NEWTEMPLATE/cabecera.jsp"%>
    </head>
    <body>
        <s:form action="recupera" autocomplete="off" theme="simple">
            <div class="row " >
                <div class="col-sm-1 col-md-4"></div>
                <div class="col-sm-10 col-md-4 thumbnail">
                    <br/>
                    <div class="form-group col-sm-12 col-md-12">
                        <div class="alert alert-info text-center" role="alert"><h1>ERROR RECUPERANDO CONTRASEÑA</h1></div>
                        <p>ha ocurrido un error al recuperar la contraseña. Por favor confirme datos e intentelo de nuevo.</p>

                    </div>
                    <div class="form-group text-center col-sm-12 col-md-12">
                        <s:a action="inicio">Ir al inicio</s:a>
                        </div>
                    </div>
                    <div class="col-sm-1 col-md-4"></div>

                </div>
        </s:form>
        <h1></h1>

    </body>
</html>
