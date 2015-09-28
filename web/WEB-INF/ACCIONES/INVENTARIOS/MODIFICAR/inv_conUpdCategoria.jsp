<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/INVENTARIOS/Inv_UpdCategoria.js"></script>

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
            <div class="col-md-4 col-xs-0 col-sm-0"></div>
            <div class="col-md-4 col-xs-12 col-sm-12">
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
                <s:form action="inv_UpdCategoria" method="post" id="inv_UpdCategoria" theme="simple"> 
                    <s:textfield name="categoria.cate_cate" cssStyle="display:none;" cssClass="idCategoriaUpdate"/>
                    <s:textfield name="accion" cssStyle="display:none" value="updCategoriaIndv"/>
                    <s:textfield name="subAccion" cssStyle="display:none" value="" cssClass="subAccionForm" />
                    <table style="width: 100%" class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2" style="text-align: center;" class="alert alert-info text-center"><h3>MODIFICAR CATEGORIA</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><h4>Descripción:</h4></td>
                                <td><s:textfield name="categoria.cate_desc" required="true" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td><h4>Fecha vencimiento:</h4></td>
                                <td><s:textfield  name="categoria.cate_feven" required="true" maxLength="10" cssClass="form-control" /></td>
                            </tr>

                            <tr>
                                <td><h4>Estado:</h4></td>
                                <td><s:select  list="estadoMap"  name="categoria.cate_estado" required="true" headerKey="-1" headerValue="Estado" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td><h4>registro Unico:</h4></td>
                                <td><s:select  list="runico"  name="categoria.cate_runic" required="true" headerKey="-1" headerValue="Registo unico" cssClass="form-control"/></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr style="text-align: right">
                                <td colspan="2">
                                    <span class="spanModificar">
                                        <s:include value="/WEB-INF/TEMPLATE/botones/modify.jsp" > 
                                            <s:param name="function">actualizacionCategoria</s:param>
                                            <s:param name="title">Modificación atributos de la Categoria</s:param>

                                        </s:include>
                                    </span>
                                    <s:include value="/WEB-INF/TEMPLATE/botones/clean.jsp" />                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </s:form>
            </div>

            <div class="col-md-4 col-xs-0 col-sm-0"></div>
        </div>
    </body>
</html>
