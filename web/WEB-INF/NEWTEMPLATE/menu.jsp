<%@page import="co.com.hotel.datos.session.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String permisos = usuario.getPermisos();
%>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#" onclick="irInicio()">SIGEMCO</a>
    </div>
    <div class="navbar-collapse collapse">

        <!-- Left nav -->
        <ul class="nav navbar-nav">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;Administración</a>
                <ul class="dropdown-menu">
                    <%if (permisos.indexOf(".AdUs1.") >= 0 || permisos.indexOf(".AdUs2.") >= 0 || permisos.indexOf(".AdUs3.") >= 0 || permisos.indexOf(".AdUs4.") >= 0 || permisos.indexOf(".AdUs5.") >= 0) {%>
                    <li><a href="#">Usuarios</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".AdUs1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=111">Insertar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdUs5.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=110">Consultar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdUs3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=112">Modificar</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".AdPf1.") >= 0 || permisos.indexOf(".AdPf2.") >= 0 || permisos.indexOf(".AdPf3.") >= 0 || permisos.indexOf(".AdUs4.") >= 0) {%>
                    <li><a href="#">Perfiles</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".AdPf1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=121">Insertar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdPf2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=122">Modificar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdPf3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=124">Consulta General</a></li>
                                <%}
                                    if (permisos.indexOf(".AdPf4.") >= 0) {%>
                            <li style="-webkit-border-radius: 0 0 10px 10px;border-radius: 0 0 10px 10px;"><a>Eliminar</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".AdSe1.") >= 0 || permisos.indexOf(".AdSe2.") >= 0 || permisos.indexOf(".AdSe3.") >= 0 || permisos.indexOf(".AdSe4.") >= 0) {%>
                    <li><a href="#">Sedes</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".AdSe1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=141">Insertar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdSe2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=142">Modificar</a></li>
                                <%}
                                    if (permisos.indexOf(".AdSe3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=144">Consulta General</a></li>
                                <%}
                                    if (permisos.indexOf(".AdSe4.") >= 0) {%>
                            <li style="-webkit-border-radius: 0 0 10px 10px;border-radius: 0 0 10px 10px;"><a>Eliminar</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".AdEm1.") >= 0 || permisos.indexOf(".AdEm2.") >= 0) {%>
                    <li><a href="#">Empresa</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".AdEm1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=131">Información</a></li>
                                <%}
                                    if (permisos.indexOf(".AdEm2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=132">Parametros Generales</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}%>
                </ul>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;Inventarios</a>
                <ul class="dropdown-menu">
                    <%if (permisos.indexOf(".InPr1.") >= 0 || permisos.indexOf(".InPr2.") >= 0 || permisos.indexOf(".InPr3.") >= 0 || permisos.indexOf(".InPr4.") >= 0 || permisos.indexOf(".InPr5.") >= 0) {%>
                    <li><a href="#">Productos</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InPr1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=214">Consultar Productos</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr6.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=216">Parametrizacion Precios</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=211">Adicionar Prod. Nuevo</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=219">Adicionar Prod. Existente</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr4.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=215">Inactivar Prod. Existente</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr5.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=212">Actualizar Producto</a></li>
                                <%}%>
                        </ul>    
                    </li>
                    <%}
                        if (permisos.indexOf(".InSr1.") >= 0 || permisos.indexOf(".InSr2.") >= 0 || permisos.indexOf(".InSr3.") >= 0) {%>
                    <li><a href="#">Servicio</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InSr1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=224" >Consulta Servicios</a></li>
                                <%}
                                    if (permisos.indexOf(".InSr2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=221">Adicionar Serv. Nuevo</a></li>
                                <%}
                                    if (permisos.indexOf(".InSr3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=225">Inactivar Servicios</a></li>
                                <%}
                                    if (permisos.indexOf(".InSr5.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=231">Parametrizaci&oacute;n de Precios</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".InMi1.") >= 0 || permisos.indexOf(".InMi2.") >= 0 || permisos.indexOf(".InMi3.") >= 0) {%>
                    <li><a href="#">Mov. Inventario</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InMi1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=241">Adicionar Mov Inventario</a></li>
                                <%}
                                    if (permisos.indexOf(".InMi2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=242">Actualizar Mov Inventario </a></li>
                                <%}
                                    if (permisos.indexOf(".InMi3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=244">Consultar Movimientos </a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".InRef1.") >= 0 || permisos.indexOf(".InRef2.") >= 0 || permisos.indexOf(".InRef3.") >= 0) {%>
                    <li><a href="#">Referencias Celular</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InRef1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=261">Adicionar Referencia</a></li>
                                <%}
                                    if (permisos.indexOf(".InRef2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=262">Actualizar Referencia </a></li>
                                <%}
                                    if (permisos.indexOf(".InRef3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=264">Consultar Referencia</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".InCat1.") >= 0 || permisos.indexOf(".InCat2.") >= 0 || permisos.indexOf(".InCat3.") >= 0) {%>
                    <li><a href="#">Categorias</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InCat1.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=271">Adicionar Referencia</a></li>
                                <%}
                                    if (permisos.indexOf(".InCat2.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=272">Actualizar Referencia </a></li>
                                <%}
                                    if (permisos.indexOf(".InCat3.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=274">Consultar Referencia</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}
                        if (permisos.indexOf(".InPr7.") >= 0 || permisos.indexOf(".InPr8.") >= 0) {%>
                    <li><a href="#">Celulares</a>
                        <ul class="dropdown-menu">
                            <%if (permisos.indexOf(".InPr7.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=251">Ingreso</a></li>
                                <%}
                                    if (permisos.indexOf(".InPr8.") >= 0) {%>
                            <li><a href="reenvioGeneral.action?accion=254">Consultas</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}%>
                </ul>
            </li>
            <% if (permisos.indexOf(".FcCr1.") >= 0 || permisos.indexOf(".FcCr3.") >= 0) { %>
            <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Facturación</a>
                <ul class="dropdown-menu">
                    <%if (permisos.indexOf(".FcCr1.") >= 0) {%>
                    <li><a href="reenvioGeneral.action?accion=311">Crear Factura Inicial</a></li>
                        <%}
                            if (permisos.indexOf(".FcCr3.") >= 0) {%>
                    <li><a href="reenvioGeneral.action?accion=312">Adición Serv. Y Productos Fac. Existente</a></li>
                        <%}%>
                    <li><a href="#">Cerrar Factura</a></li>
                </ul>
            </li>            
            <%}
                if (permisos.indexOf(".RpIn1.") >= 0 || permisos.indexOf(".RpUs1.") > 0) {%>
            <li><a href="#"><span class="glyphicon glyphicon-floppy-save"></span>&nbsp;Reportes</a>
                <ul class="dropdown-menu">
                    <% if (permisos.indexOf(".RpUs1.") > 0) {%>
                    <li><a href="#">Usuarios</a>
                        <ul class="dropdown-menu">
                            <li><a href="reenvioGeneral.action?accion=431">Activos</a></li>
                            <li><a href="reenvioGeneral.action?accion=431">Facturación de Usuarios</a></li>
                        </ul>
                    </li>
                    <%}%>
                    <% if (permisos.indexOf(".RpIn1.") > 0) {%>
                    <li><a href="#">Inventario</a>
                        <% if (permisos.indexOf(".RpIn1.") > 0) { %>
                        <ul class="dropdown-menu">
                            <li><a href="reenvioGeneral.action?accion=411">Promedio Ponderado</a></li>
                        </ul>
                        <%}%>
                    </li>
                    <%}%>
                </ul>
            </li>
            <%}%>
            <%if (permisos.indexOf(".CoPu1.") >= 0) {%>
            <li><a href="#"><span class="glyphicon glyphicon-book"></span>&nbsp;Contabilidad</a>
                <ul class="dropdown-menu">
                    <li><a href="#">PUC</a>
                        <ul class="dropdown-menu">
                            <% if (permisos.indexOf(".CoPu1.") > 0) {%>
                            <li><a href="reenvioGeneral.action?accion=514">Consulta General</a>                                
                            </li>
                            <%}%>                            
                        </ul>
                    </li>
                </ul>
            </li>
            <%}%>
        </ul>

        <!-- Right nav -->
        <ul class="nav navbar-nav navbar-right">
            <li><a href="logout.action" aria-label="Left Align">
                    <span class="glyphicon glyphicon-off" ></span>&nbsp;Salir
                </a>             
            </li>
        </ul>
    </div><!--/.nav-collapse -->
    <script>
        function irInicio() {
            location.href = RutaSitio + '/inicioAdmin.action';
        }
    </script>
</div>