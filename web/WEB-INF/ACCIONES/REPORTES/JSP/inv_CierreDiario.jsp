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
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <div class="row">
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
            <div class="col-md-6 col-xs-12 col-sm-12">
                <s:form name="inv_consCierre" action="inv_consCierre" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="cierreDiario"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 thumbnail">
                        <div class="row">
                            <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                                <div class="alert alert-success text-center"  role="alert" ><h3>CIERRE DIARIO</h3></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-5 col-sm-5 col-xs-5">
                                Sede:<br>
                                <s:select list="sedes"  id="sede" name="sede" required="true" headerKey="-1" headerValue="Seleccione una Sede.." cssClass="form-control" />
                            </div>
                            <div class="form-group col-md-7 col-sm-7 col-xs-7">
                                Fecha:<br>
                                <div class="input-group date" >
                                    <s:textfield  cssClass="form-control" name="fecha" readonly="true"/>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-5 col-sm-5 col-xs-5"></div>
                            <div class="form-group col-md-3 col-sm-3 col-xs-3">
                                <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp">
                                    <s:param name="function">insertaCierre</s:param>
                                    <s:param name="title">Consulta de cierre</s:param>
                                </s:include>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                <a href="#" class="btn btn-primary" onclick="consultaCierre()" >  
                                    CONSULTAR                                    
                                </a>
                            </div>
                            <div class="form-group col-md-4 col-sm-4 col-xs-4">
                                <a href="#" class="btn btn-primary" onclick="consultaCierreDetalle()" >  
                                    CONSULTAR DETALLE                                    
                                </a>
                            </div>
                        </div>                        
                    </div>                    
                </s:form>
            </div>
            <div class="col-md-3 col-xs-0 col-sm-0"></div>
        </div>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="mensaje">
                <div class="modal-dialog">                
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">INFORMACION</h4>
                        </div>
                        <div class="modal-body">
                            <span id="textoMsn"></span>
                        </div>
                        <div class="modal-footer">                        
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                ACEPTAR
                            </button>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>

