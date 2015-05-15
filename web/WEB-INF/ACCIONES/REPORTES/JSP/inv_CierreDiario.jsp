<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<%@ page language="java" import="java.sql.*"%> 
<%@ page import="java.util.*"%> 
<%@ page import="java.sql.DriverManager" %>
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
        <%
            String IP;
            String host;
            String req="";
            req=request.getRemoteAddr();
            IP = java.net.InetAddress.getLocalHost().getHostAddress();
            host = java.net.InetAddress.getLocalHost().getHostName();
            System.out.println("host ip"+IP+request.getRemoteHost());
            System.out.println("host "+host+request.getRemoteAddr());
            
        %>
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
                                <a onclick="consultaCierre()" >Consultar</a>
                            <%--<s:include value="/WEB-INF/TEMPLATE/botones/find.jsp">
                                <s:param name="function">consultaCierre</s:param>
                                <s:param name="title">Consulta de cierre</s:param>
                            </s:include>--%>
                        </div>

                    </div>                    
                </s:form>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <br/>        
    </body>
</html>
