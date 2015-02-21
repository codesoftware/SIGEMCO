<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/NEWTEMPLATE/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/NEWTEMPLATE/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/ADMINISTRACION/PERMISOS/Adm_UpdPermisos.js"></script>
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
            <div class="col-md-4 col-sm-0 col-xs-0"></div>
            <div class="col-md-4 col-sm-12 col-xs-12 thumbnail">
                <div class="row">
                    <div class="form-group col-md-12 col-sm-12 col-xs-12 ">
                        <div class="alert alert-success text-center"  role="alert" ><h3>MODIFICACI&Oacute;N DE PERMISOS POR PERFIL</h3></div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6 col-sm-6 col-xs-6">
                        <label for="nombre">Nombre:</label><br>
                        <s:text name="objPerfil.nombre"/>
                    </div>
                    <div class="form-group col-md-6 col-sm-6 col-xs-6">
                        <label for="estado">Estado:</label><br>
                        <s:text name="objPerfil.estado" />
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-9 col-sm-9 col-xs-9">
                        <label for="estado">Descripcion:</label><br>
                        <s:text name="objPerfil.descripcion" />
                    </div>
                    <div class="form-group col-md-3 col-sm-3 col-xs-3">
                        <a class="btnReporte" ></a>
                        <button type="button" class="btn btn-primary" onclick="actualizar()">Actualizar</button>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-0 col-xs-0"></div>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
            <div class="col-md-6 col-sm-12 col-xs-12">                
                <s:form action="Adm_UpdPermPerf" id="Adm_UpdPermPerf" method="post" theme="simple">
                    <s:textfield name="objPerfil.permisos" cssStyle="display:none" cssClass="permisos"/>
                    <s:textfield name="objPerfil.id" cssStyle="display:none"/>
                    <s:textfield name="objPerfil.nombre" cssStyle="display:none"/>
                    <div class="row">
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Modulo de Administraci&oacute;n
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th><a id="admUsu">USUARIOS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoAdmUsu">
                                                    <tr>
                                                        <td style="width: 30%">Creación: </td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdUs1" id="AdUs1"></td>
                                                        <td style="width: 30%">Actualización: </td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdUs2" id="AdUs2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 30%">Modificación: </td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdUs3" id="AdUs3" ></td>
                                                        <td style="width: 30%">Cambio Clave:</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdUs4" id="AdUs4"></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 30%">Consultar </td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdUs5" id="AdUs5"></td>
                                                        <td style="width: 30%">&nbsp;&nbsp;&nbsp;</td>
                                                        <td style="width: 20%">&nbsp;&nbsp;&nbsp;</td>
                                                    </tr>
                                                </tbody>
                                            </table>                                        
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo" colspan="4"><a id="admEmp">PARAMETROS EMPRESARIALES</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoAdmEmp">
                                                    <tr>
                                                        <td style="width: 30%">Información Empresarial</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdEm1" id="AdEm1"></td>
                                                        <td style="width: 30%">Parametros Generales </td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdEm2" id="AdEm2"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="admPerf">PERFILES</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoAdmPerf">
                                                    <tr>
                                                        <td style="width: 30%">Insertar</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdPf1" id="AdPf1"></td>
                                                        <td style="width: 30%">Actualización</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdPf2" id="AdPf2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 30%">Consulta General</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdPf3" id="AdPf3"></td>
                                                        <td style="width: 30%">Eliminación</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdPf4" id="AdPf4"></td>
                                                    </tr>
                                                </tbody>
                                            </table>                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="admSedes">SEDES</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoAdmSedes">
                                                    <tr>
                                                        <td style="width: 30%">Insertar</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdSe1" id="AdSe1"></td>
                                                        <td style="width: 30%">Actualización</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdSe2" id="AdSe2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 30%">Consulta General</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdSe3" id="AdSe3"></td>
                                                        <td style="width: 30%">Eliminación</td>
                                                        <td style="width: 20%"><input type="checkbox" value="AdSe4" id="AdSe4"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>                                                
                            </div>
                        </div>
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Modulo de Inventarios
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="InvPr">PRODUCTOS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoInvPro">
                                                    <tr>
                                                        <td style="width: 30%">Consulta</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InPr1" id="InPr1"  /></td>
                                                        <td style="width: 30%">Adicionar Nuevos</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InPr2" id="InPr2"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Adicionar Existentes</td>
                                                        <td><input type="checkbox" value="InPr3" id="InPr3" /></td>
                                                        <td>Inactivar Productos</td>
                                                        <td><input type="checkbox" value="InPr4" id="InPr4"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Actulizacion de Productos</td>
                                                        <td><input type="checkbox" value="InPr5" id="InPr5" /></td>
                                                        <td>Parametrizaci&oacute;n de Precios</td>
                                                        <td><input type="checkbox" value="InPr6" id="InPr6" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Ingreso Celular</td>
                                                        <td><input type="checkbox" value="InPr7" id="InPr7" /></td>
                                                        <td>Consulta Equipos Celulares</td>
                                                        <td><input type="checkbox" value="InPr8" id="InPr8" /></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">

                                        </div>                                        
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="InServ">SERVICIOS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoInvServ">
                                                    <tr>
                                                        <td style="width: 30%">Consulta General</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InSr1" id="InSr1" /></td>
                                                        <td style="width: 30%">Adicionar Nuevos</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InSr2" id="InSr2"/></td>
                                                    </tr>
                                                    <tr>                                    
                                                        <td>Inactivar Servicios</td>
                                                        <td><input type="checkbox" value="InSr3" id="InSr3"/></td>
                                                        <td>Actualizacion de Servicios</td>
                                                        <td><input type="checkbox" value="InSr4" id="InSr4"/></td>
                                                    </tr>
                                                    <tr>                                    
                                                        <td>Parametrizacion de Precios</td>
                                                        <td><input type="checkbox" value="InSr5" id="InSr5"/></td>
                                                        <td>&nbsp;&nbsp;&nbsp;</td>
                                                        <td>&nbsp;&nbsp;&nbsp;</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo" colspan="4"><a id="InMvInv">MOVIMIENTOS DE INVENTARIO</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoInvMvInv">
                                                    <tr>                                    
                                                        <td style="width: 30%">Inserci&oacute;n</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InMi1" id="InMi1"/></td>
                                                        <td style="width: 30%">Modificacion</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InMi2" id="InMi2"/></td>
                                                    </tr>
                                                    <tr>                                    
                                                        <td>Consulta General</td>
                                                        <td><input type="checkbox" value="InMi3" id="InMi3"/></td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    </tr>
                                                </tbody>                                
                                            </table>  
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="InRefe">CATEGORIAS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoInRefe">
                                                    <tr>                                    
                                                        <td style="width: 30%">Inserci&oacute;n</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InCat1" id="InCat1"/></td>
                                                        <td style="width: 30%">Modificacion</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InCat2" id="InCat2"/></td>
                                                    </tr>
                                                    <tr>                                    
                                                        <td>Consulta General</td>
                                                        <td><input type="checkbox" value="InCat3" id="InCat3"/></td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    </tr>
                                                </tbody>                                
                                            </table>
                                        </div>
                                    </div>                                    
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="InRefe">REFERENCIAS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoInRefe">
                                                    <tr>                                    
                                                        <td style="width: 30%">Inserci&oacute;n</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InRef1" id="InRef1"/></td>
                                                        <td style="width: 30%">Modificacion</td>
                                                        <td style="width: 20%"><input type="checkbox" value="InRef2" id="InRef2"/></td>
                                                    </tr>
                                                    <tr>                                    
                                                        <td>Consulta General</td>
                                                        <td><input type="checkbox" value="InRef3" id="InRef3"/></td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    </tr>
                                                </tbody>                                
                                            </table>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Modulo de Facturaci&oacute;n
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="FacCrea" >CREACI&Oacute;N</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoFacCrea">
                                                    <tr>
                                                        <td style="width: 30%;">Facturación desde Cero</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="FcCr1" id="FcCr1" /></td>
                                                        <td style="width: 30%;">Adición Prod. Fac. Existente</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="FcCr2" id="FcCr2" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Adición Serv. Y Productos Fac. Existente</td>
                                                        <td><input type="checkbox" value="FcCr3" id="FcCr3"/></td>
                                                        <td>Cerrar Factura</td>
                                                        <td><input type="checkbox" value="FcCr4" id="FcCr4"/></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>                            
                            </div>
                        </div>
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Modulo de Contabilidad
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="RepInv">PUC</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoRepInv">
                                                    <tr>
                                                        <td style="width: 30%;">Consulta General</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="CoPu1" id="CoPu1"/></td>
                                                        <td style="width: 30%;">Creacion SubCuentas</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="CoPu2" id="CoPu2"/></td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Reportes
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="RepInv">INVENTARIO</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoRepInv">
                                                    <tr>
                                                        <td style="width: 30%;">Promedio Ponderado</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="RpIn1" id="RpIn1"/></td>
                                                        <td style="width: 30%;">&nbsp;</td>
                                                        <td style="width: 20%;">&nbsp;</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <table style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th class="subTitulo"><a id="RepUsua" >USUARIOS</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody style="display: none;" class="subPermisoRepUsua">
                                                    <tr>
                                                        <td style="width: 30%;">Usuarios aplicación</td>
                                                        <td style="width: 20%;"><input type="checkbox" value="RpUs1" id="RpUs1"/></td>
                                                        <td style="width: 30%;">&nbsp;</td>
                                                        <td style="width: 20%;">&nbsp;</td>
                                                    </tr>
                                                </tbody>
                                            </table>                                                                                        
                                        </div>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-0 col-sm-0 col-xs-0"></div>
                    </div>
                </s:form>                                                
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>            
        </div>
        <s:set name="variable" value="objPerfil.permisos" />
        <s:if test="%{#variable != null}" >
            <script>
                mostrarPermisos('<s:property value="variable"/>');
            </script>
        </s:if>  
    </body>
</html>
