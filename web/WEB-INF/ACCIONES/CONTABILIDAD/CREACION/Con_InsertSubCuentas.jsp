<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/CONTABILIDAD/PUC/Con_InsertSubCuentas.js"></script>
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
                <s:form theme="simple" action="Con_InsertSubCuentas" name="Con_InsertSubCuentas" id="Con_InsertSubCuentas">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center"><h3>INGRESAR SUBCUENTAS</h3></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Clase:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="clase.clas_codigo" readonly="true"/> 
                                    <s:textfield cssClass="form-control" name="clase.clas_nombre" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Grupo:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="grupo.grup_codigo" readonly="true"/> 
                                    <s:textfield cssClass="form-control" name="grupo.grup_nombre" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Cuenta:</td>
                                <td>
                                    <s:textfield cssClass="form-control" name="cuenta.cuen_codigo" readonly="true"/> 
                                    <s:textfield cssClass="form-control" name="cuenta.cuen_nombre" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Nombre:</td>
                                <td><s:textfield cssClass="form-control" name="subCuenta.sbcu_nombre" id="sbcu_nombre"/></td>
                            </tr>
                            <tr>
                                <td>Descripción:</td>
                                <td><s:textfield cssClass="form-control" name="subCuenta.sbcu_descripcion" id="sbcu_descripcion"/></td>
                            </tr>
                            <tr>
                                <td>Naturaleza:</td>
                                <td>
                                    <s:select list="naturaleza" cssClass="form-control" name="subCuenta.sbcu_naturaleza" id="sbcu_naturaleza" headerKey="-1" headerValue="NATURALEZA..."/>
                                </td>
                            </tr>
                            <tr>
                                <td>Codigo:</td>
                                <td><s:textfield cssClass="form-control" name="subCuenta.sbcu_codigo" id="sbcu_codigo"/></td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <s:include value="/WEB-INF/TEMPLATE/botones/add.jsp" > 
                                        <s:param name="function">insertarSubCuenta</s:param>
                                        <s:param name="title">Adicion de la SubCuenta al sistema</s:param>
                                    </s:include>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="datos" style="display: none;">
                        <s:textfield name="clase.clas_clas" id="clas_clas" />
                        <s:textfield name="grupo.grup_grup" id="grup_grup" />
                        <s:textfield name="clase.clas_descripcion" id="clas_descripcion" />
                        <s:textfield name="cuenta.cuen_cuen" id="cuen_cuen" />
                    </div>
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>
    </body>
</html>
