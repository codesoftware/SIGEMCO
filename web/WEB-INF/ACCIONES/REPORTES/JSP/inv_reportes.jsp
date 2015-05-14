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
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Adm_Reportes.js"></script>
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
                <s:form name="inv_reporteGeneral" action="inv_reporteGeneral" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="reporteGeneral"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>REPORTES</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Tipo:<br>
                                <s:select list="reportes"  id="reportes" name="reportes" required="true" headerKey="-1" headerValue="Favor seleccione un reporte......" cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Fecha Inicial:<br>
                                <div class="input-group date" >
                                    <s:textfield  cssClass="form-control" name="fechaIni" readonly="true"/>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                Fecha Final:<br>
                                <div class="input-group date" >
                                    <s:textfield  cssClass="form-control" name="fechaFin" readonly="true"/>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-4 col-sm-4 col-xs-4">
                            <br>
                            <div class="form-group col-md-2 col-sm-2 col-xs-2">                                
                                <img src="<%=RutaSitio%>/IMAGENES/GIFS/xlsx.png" width="60px" id="imagenXls" onclick="ejecutaReporte()"/>
                            </div>
                        </div>

                    </div>                    
                </s:form>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <br/>
    </body>
</html>
